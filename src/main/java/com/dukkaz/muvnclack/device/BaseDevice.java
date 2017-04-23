package com.dukkaz.muvnclack.device;

public abstract class BaseDevice<T extends DeviceConfiguration> implements SingleActionDevice<T> {

	private boolean ready;

	@Override
	public void init(T configuration) {

		if (this.isReady()) {
			throw new IllegalStateException("This device has been already initialized.");
		}

		this.configure(configuration);

		this.ready = true;
	}

	protected abstract void configure(T configuration);

	@Override
	public boolean isReady() {

		return ready;
	}

	@Override
	public void perform() {

		if (!this.isReady()) {
			throw new IllegalStateException("This device needs to be initialized.");
		}

		this.doPerform();
	}

	protected abstract void doPerform();

}
