package com.dukkaz.muvnclack;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.Paths;

import javax.xml.bind.JAXB;

import com.dukkaz.muvnclack.device.camera.Camera;
import com.dukkaz.muvnclack.device.motionsensor.MotionSensor;
import com.dukkaz.muvnclack.device.notification.EmailNotificationSender;

public class MuvNClack {

	public static void main(String[] args) throws IOException {

		XmlConfiguration configuration;

		if (args == null || args.length != 1) {
			try (InputStream openStream = MuvNClack.class.getResource("/configuration.xml").openStream()) {
				configuration = JAXB.unmarshal(openStream, XmlConfiguration.class);

			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		} else {
			configuration = JAXB.unmarshal(Paths.get(args[0]).toFile(), XmlConfiguration.class);
		}

		MotionSensor motionSensor = new MotionSensor();
		motionSensor.init(configuration.getMotionSensor());

		Camera camera = new Camera();
		camera.init(configuration.getCamera());

		EmailNotificationSender emailNotificationSender = new EmailNotificationSender();
		emailNotificationSender.init(configuration.getNotification());

		while (true) {

			motionSensor.perform();
			camera.perform();
			String pictureFile = camera.getFile();
			emailNotificationSender.setPictureFile(pictureFile);
			emailNotificationSender.perform();
		}

	}

}
