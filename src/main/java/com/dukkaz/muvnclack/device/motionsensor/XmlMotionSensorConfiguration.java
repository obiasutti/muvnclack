package com.dukkaz.muvnclack.device.motionsensor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlMotionSensorConfiguration implements MotionSensorConfiguration {

	public long interval = 500;

	public boolean off = false;

	public XmlMotionSensorConfiguration() {
	}

	public long getInterval() {
		return interval;
	}

	public void setInterval(long interval) {
		this.interval = interval;
	}

	public boolean isOff() {
		return off;
	}

	public void setOff(boolean off) {
		this.off = off;
	}

}