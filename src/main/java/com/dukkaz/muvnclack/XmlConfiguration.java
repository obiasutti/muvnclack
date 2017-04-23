package com.dukkaz.muvnclack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.dukkaz.muvnclack.device.camera.XmlCameraConfiguration;
import com.dukkaz.muvnclack.device.motionsensor.XmlMotionSensorConfiguration;
import com.dukkaz.muvnclack.device.notification.XmlEmailNotificationSenderConfiguration;

@XmlRootElement(name = "configuration")
@XmlAccessorType(XmlAccessType.FIELD)
public class XmlConfiguration {

	@XmlElement
	private XmlMotionSensorConfiguration motionSensor = new XmlMotionSensorConfiguration();

	@XmlElement
	private XmlCameraConfiguration camera = new XmlCameraConfiguration();

	@XmlElement
	private XmlEmailNotificationSenderConfiguration notification = new XmlEmailNotificationSenderConfiguration();

	public XmlEmailNotificationSenderConfiguration getNotification() {
		return notification;
	}

	public void setNotification(XmlEmailNotificationSenderConfiguration notification) {
		this.notification = notification;
	}

	public XmlMotionSensorConfiguration getMotionSensor() {
		return motionSensor;
	}

	public void setMotionSensor(XmlMotionSensorConfiguration motionSensor) {
		this.motionSensor = motionSensor;
	}

	public XmlCameraConfiguration getCamera() {
		return camera;
	}

	public void setCamera(XmlCameraConfiguration camera) {
		this.camera = camera;
	}

}
