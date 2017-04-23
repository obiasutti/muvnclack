package com.dukkaz.muvnclack.device.motionsensor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dukkaz.muvnclack.device.BaseDevice;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalInput;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.RaspiPin;
import com.pi4j.io.gpio.event.GpioPinDigitalStateChangeEvent;
import com.pi4j.io.gpio.event.GpioPinListenerDigital;

public class MotionSensor extends BaseDevice<MotionSensorConfiguration> implements GpioPinListenerDigital {

	private static final Logger LOG = LoggerFactory.getLogger(MotionSensor.class);

	private MotionSensorConfiguration configuration;

	private GpioPinDigitalInput motionSensorPin;

	private boolean motionDetected;

	@Override
	protected void configure(MotionSensorConfiguration configuration) {
		this.configuration = configuration;

		motionSensorPin = GpioFactory.getInstance().provisionDigitalInputPin(RaspiPin.GPIO_04,
				PinPullResistance.PULL_DOWN);

	}

	/**
	 * Blocking call that waits until the sensor detects any motion based on the
	 * current sensitivity.
	 */
	protected void doPerform() {

		if (this.motionSensorPin == null) {
			throw new IllegalStateException("MotionSensor not initalized.");
		}

		motionSensorPin.addListener(this);

		try {
			
			Thread.sleep(5000);
			
			LOG.info("Motion sensor ready and waiting...");

			while (!this.configuration.isOff() && !this.isMotionDetected()) {
				Thread.sleep(this.configuration.getInterval());
			}

			this.setMotionDetected(false);

		} catch (final Exception e) {
			LOG.error("Motion sensor failed.", e);
		}

		motionSensorPin.removeAllListeners();
	}

	@Override
	public void handleGpioPinDigitalStateChangeEvent(GpioPinDigitalStateChangeEvent event) {

		if (event.getState().isHigh()) {

			this.setMotionDetected(true);
			LOG.info("Motion detected!");
		}
		
		LOG.info("Event [{}]", event.getEventType());
	}

	private boolean isMotionDetected() {
		return this.motionDetected;
	}

	private synchronized void setMotionDetected(boolean motionDetected) {
		this.motionDetected = motionDetected;

	}

}
