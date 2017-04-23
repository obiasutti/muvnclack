package com.dukkaz.muvnclack.device.camera;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dukkaz.muvnclack.device.BaseDevice;
import com.hopding.jrpicam.RPiCamera;
import com.hopding.jrpicam.exceptions.FailedToRunRaspistillException;

public class Camera extends BaseDevice<CameraConfiguration> {

	private static final Logger LOG = LoggerFactory.getLogger(Camera.class);

	private RPiCamera camera;

	private CameraConfiguration configuration;

	private String file;

	private void cleanup() throws IOException {

		this.setFile(null);

		String photoFolder = configuration.getPhotoFolder();

		Path path = Paths.get(photoFolder);

		Files.createDirectories(path);

		int count = 0;

		for (Path file : Files.walk(path, 1).collect(Collectors.toList())) {

			if (++count > configuration.getMaxPhotos()) {
				Files.deleteIfExists(file);
				LOG.info("Deleted file [{}]", file.toAbsolutePath());
			}
		}

	}

	@Override
	protected void configure(CameraConfiguration configuration) {

		try {

			this.configuration = configuration;

			camera = new RPiCamera(configuration.getPhotoFolder());

			camera.setWidth(configuration.getWidth());
			camera.setHeight(configuration.getHeight());
			camera.setBrightness(configuration.getBrightness());
			camera.setExposure(configuration.getExposure());
			camera.setTimeout(configuration.getTimeout());
			camera.setDateTimeOn();

		} catch (FailedToRunRaspistillException e) {
			throw new IllegalStateException("The camera could not be initialized.", e);
		}

	}

	@Override
	protected void doPerform() {

		try {

			cleanup();

			this.setFile(camera
					.takeStill(MessageFormat.format("pic-{0}.jpg",
							DateTimeFormatter.ofPattern("yyyyMMddHHmmss").format(ZonedDateTime.now())))
					.getAbsolutePath());

			LOG.info("New picture taken [{}]", this.getFile());

		} catch (IOException | InterruptedException e) {
			LOG.error("Failed to take a picture.", e);
		}

	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
