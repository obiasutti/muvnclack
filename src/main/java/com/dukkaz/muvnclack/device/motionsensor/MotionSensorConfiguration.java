package com.dukkaz.muvnclack.device.motionsensor;

import com.dukkaz.muvnclack.device.DeviceConfiguration;

public interface MotionSensorConfiguration extends DeviceConfiguration {

	long getInterval();

	boolean isOff();

	void setInterval(long interval);

	void setOff(boolean off);

}