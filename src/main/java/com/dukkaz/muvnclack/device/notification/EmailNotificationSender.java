package com.dukkaz.muvnclack.device.notification;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;

import javax.mail.Message;

import org.simplejavamail.email.Email;
import org.simplejavamail.mailer.Mailer;
import org.simplejavamail.util.ConfigLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dukkaz.muvnclack.device.BaseDevice;

public class EmailNotificationSender extends BaseDevice<EmailNotificationSenderConfiguration> {

	private static final Logger LOG = LoggerFactory.getLogger(EmailNotificationSender.class);

	private EmailNotificationSenderConfiguration configuration;

	protected void doPerform() {

		if (this.getPictureFile() != null) {

			mailer.sendMail(createEmail(configuration));

			LOG.info("Picture [{}] sent to [{}].", this.getPictureFile(), this.configuration.getEmailAddress());

			this.setPictureFile(null);
			
		} else {
			
			LOG.warn("EmailNotificationSender was called but on picture was sent.");
		}

	}

	private String pictureFile;

	private Mailer mailer;

	@Override
	protected void configure(EmailNotificationSenderConfiguration configuration) {
		this.configuration = configuration;

		Properties properties = new Properties();

		properties.setProperty(ConfigLoader.Property.SMTP_HOST.key(), configuration.getSmtpHost());
		properties.setProperty(ConfigLoader.Property.SMTP_PORT.key(), configuration.getSmtpPort());
		properties.setProperty(ConfigLoader.Property.SMTP_USERNAME.key(), configuration.getSmtpUsername());
		properties.setProperty(ConfigLoader.Property.SMTP_PASSWORD.key(), configuration.getSmtpPassword());
		properties.setProperty(ConfigLoader.Property.TRANSPORT_STRATEGY.key(), configuration.getTransportStrategy());

		ConfigLoader.loadProperties(properties, false);

		mailer = new Mailer();

	}

	private Email createEmail(EmailNotificationSenderConfiguration configuration) {

		Email email = new Email();

		email.setFromAddress(configuration.getSenderName(), configuration.getSenderEmailAddress());
		email.setReplyToAddress(configuration.getSenderName(), configuration.getSenderEmailAddress());
		email.addRecipient(configuration.getName(), configuration.getEmailAddress(), Message.RecipientType.TO);
		email.setSubject("New picture taken by " + this.configuration.getSenderName());
		email.setText("Picture taken on " + DateTimeFormatter.ofPattern(configuration.getDateFormat())
				.format(ZonedDateTime.now(ZoneId.of(configuration.getZoneId()))) + Character.LINE_SEPARATOR);

		try {
			email.addEmbeddedImage(this.getPictureFile(), Files.readAllBytes(Paths.get(this.getPictureFile())),
					"image/jpg");
		} catch (IOException e) {
			LOG.error("Error embedding the image [" + this.getPictureFile() + "]", e);
		}

		return email;
	}
	
	public String getPictureFile() {
		return pictureFile;
	}

	public void setPictureFile(String pictureFile) {
		this.pictureFile = pictureFile;
	}

}
