package com.dukkaz.muvnclack.device.camera;

import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CameraTest {

	private Camera camera;

	@Before
	public void setUp() throws Exception {
		camera = new Camera();
		XmlCameraConfiguration configuration = new XmlCameraConfiguration();
		camera.init(configuration);
	}

	@Test
	public void testPerform() throws Exception {
		camera.perform();
		Assert.assertNotNull(camera.getFile());
		Assert.assertTrue(Files.exists(Paths.get(camera.getFile())));
	}

}
