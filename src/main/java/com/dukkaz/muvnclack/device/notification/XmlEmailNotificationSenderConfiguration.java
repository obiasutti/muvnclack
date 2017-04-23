package com.dukkaz.muvnclack.device.notification;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

import org.simplejavamail.mailer.config.TransportStrategy;

@XmlAccessorType(XmlAccessType.FIELD)
public class XmlEmailNotificationSenderConfiguration implements EmailNotificationSenderConfiguration {

	private String name;

	private String emailAddress;

	private String smtpHost;

	private String smtpPort;

	private String smtpUsername;

	private String smtpPassword;

	private String senderName = "MuvNClack";

	private String senderEmailAddress = "otavioalemanha@gmail.com";

	private String dateFormat = "EEEE, d MMMM yyyy, HH:mm zzzz";

	private String zoneId = "America/Sao_Paulo";

	private String transportStrategy = TransportStrategy.SMTP_SSL.toString();

	@Override
	public String getDateFormat() {
		return this.dateFormat;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	@Override
	public String getName() {
		return this.name;
	}

	public String getSenderEmailAddress() {
		return senderEmailAddress;
	}

	@Override
	public String getSenderName() {
		return this.senderName;
	}

	public String getSmtpHost() {
		return smtpHost;
	}

	public String getSmtpPassword() {
		return smtpPassword;
	}

	public String getSmtpPort() {
		return smtpPort;
	}

	public String getSmtpUsername() {
		return smtpUsername;
	}

	@Override
	public String getZoneId() {
		return this.zoneId;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSenderEmailAddress(String senderEmailAddress) {
		this.senderEmailAddress = senderEmailAddress;
	}

	public void setSenderName(String senderName) {
		this.senderName = senderName;
	}

	public void setSmtpHost(String smtpHost) {
		this.smtpHost = smtpHost;
	}

	public void setSmtpPassword(String smtpPassword) {
		this.smtpPassword = smtpPassword;
	}

	public void setSmtpPort(String smtpPort) {
		this.smtpPort = smtpPort;
	}

	public void setSmtpUsername(String smtpUsername) {
		this.smtpUsername = smtpUsername;
	}

	public void setZoneId(String zoneId) {
		this.zoneId = zoneId;
	}

	public String getTransportStrategy() {
		return transportStrategy;
	}

	public void setTransportStrategy(String transportStrategy) {
		this.transportStrategy = transportStrategy;
	}

}