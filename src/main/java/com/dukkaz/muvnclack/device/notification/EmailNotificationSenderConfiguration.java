package com.dukkaz.muvnclack.device.notification;

import com.dukkaz.muvnclack.device.DeviceConfiguration;

public interface EmailNotificationSenderConfiguration extends DeviceConfiguration {

	String getName();
	
	String getEmailAddress();

	String getSmtpHost();

	String getSmtpPort();

	String getSmtpUsername();

	String getSmtpPassword();

	String getSenderName();

	String getDateFormat();

	String getSenderEmailAddress();

	String getZoneId();

	String getTransportStrategy();

}