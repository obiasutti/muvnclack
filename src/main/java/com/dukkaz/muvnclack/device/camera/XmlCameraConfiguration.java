package com.dukkaz.muvnclack.device.camera;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import com.hopding.jrpicam.enums.Exposure;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlCameraConfiguration implements CameraConfiguration {

	private String photoFolder = "/home/muvnclack/photos";

	private int width = 500;

	private int height = 500;

	private int brightness = 50;

	private Exposure exposure = Exposure.AUTO;

	private int timeout = 2;

	private int maxPhotos = 1000;

	public XmlCameraConfiguration() {
	}

	@Override
	public String getPhotoFolder() {
		return this.photoFolder;
	}

	public void setPhotoFolder(String photoFolder) {
		this.photoFolder = photoFolder;
	}

	@Override
	public int getWidth() {
		return width;
	}

	@Override
	public int getHeight() {
		return height;
	}

	@Override
	public int getBrightness() {
		return this.brightness;
	}

	@Override
	public Exposure getExposure() {
		return exposure;
	}

	@Override
	public int getTimeout() {
		return timeout;
	}

	@Override
	public int getMaxPhotos() {
		return maxPhotos;
	}

}
