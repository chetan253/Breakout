package com.action;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import com.commands.MacroCommand;
import com.controller.GameController;

public class LoadGame {
	private GameController controller;
	private Breakout game;

	public LoadGame() {}

	public ArrayList<MacroCommand> load(String path) throws Exception {
		
		FileInputStream fileStream = new FileInputStream(path);
		ObjectInputStream objectStream = new ObjectInputStream(fileStream);

		ArrayList<MacroCommand> list = (ArrayList<MacroCommand>) objectStream.readObject();
		
		fileStream.close();
		objectStream.close();
		System.out.println("Game load");
		return list;
	}
}
