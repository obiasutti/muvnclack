package com.dukkaz.muvnclack.device;

/**
 * A device having a single purpose to perform an action.
 * 
 * @author Otavio Biasutti
 *
 * @param <T>
 *            the configuration object type
 */
public interface SingleActionDevice<T extends DeviceConfiguration> {

	/**
	 * Initializes the device with the given configuration.
	 */
	void init(T configuration);

	/**
	 * If the device is ready to perform.
	 * 
	 * @return <code>true</code> if the action can be performed. Otherwise
	 *         <code>false</code> if initialization did not yet take place or
	 *         failed.
	 */
	boolean isReady();

	/**
	 * Executes the device's action.
	 */
	void perform();

}
