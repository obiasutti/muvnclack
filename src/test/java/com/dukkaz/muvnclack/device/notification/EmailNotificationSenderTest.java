package com.dukkaz.muvnclack.device.notification;

import java.io.File;

import org.junit.Before;
import org.junit.Test;

public class EmailNotificationSenderTest {

	private EmailNotificationSender notificationSender;

	@Before
	public void setUp() throws Exception {
		notificationSender = new EmailNotificationSender();
		XmlEmailNotificationSenderConfiguration configuration = new XmlEmailNotificationSenderConfiguration();
		configuration.setEmailAddress("otavio@biasutti.com.br");
		configuration.setName("Otavio Biasutti");
		configuration.setSmtpHost("smtp.strato.de");
		configuration.setSmtpPort("465");
		configuration.setSmtpUsername("webmaster@dukkaz.com");
		configuration.setSmtpPassword("deutsch15@#");
		notificationSender.init(configuration);
	}

	@Test
	public void testPerform() throws Exception {
		notificationSender
				.setPictureFile(new File(EmailNotificationSenderTest.class.getResource("/test.jpg").toURI()).getAbsolutePath());
		notificationSender.perform();
	}

}
