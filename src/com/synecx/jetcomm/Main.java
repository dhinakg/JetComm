package com.synecx.jetcomm;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.google.gson.Gson;

public class Main {
	private static final File IMAGE = new File("/home/synecx/Documents/Robotics/JetComm/test_images/test_1.png");

	public static void main(String args[]) throws IOException {
		Gson gson = new Gson();

		BufferedImage bufferedImage = ImageIO.read(IMAGE);
		Convert convert = new Convert(bufferedImage);

		Server server = new Server(7777);
		server.startServer(gson.toJson(convert.convertToRGBA()));
	}
}