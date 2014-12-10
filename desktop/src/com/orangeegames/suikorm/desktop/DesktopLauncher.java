package com.orangeegames.suikorm.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.orangeegames.suikorm.SuikodenRM;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "SuikoTest";
		config.width = 1280;
		config.height = 720;
		new LwjglApplication(new SuikodenRM(), config);
	}
}
