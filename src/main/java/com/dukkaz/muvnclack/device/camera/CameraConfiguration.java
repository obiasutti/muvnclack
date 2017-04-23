package com.dukkaz.muvnclack.device.camera;

import com.dukkaz.muvnclack.device.DeviceConfiguration;
import com.hopding.jrpicam.enums.Exposure;

public interface CameraConfiguration extends DeviceConfiguration {

	String getPhotoFolder();

	void setPhotoFolder(String photoFolder);

	int getWidth();

	int getHeight();

	int getBrightness();

	Exposure getExposure();

	int getTimeout();

	int getMaxPhotos();

}
