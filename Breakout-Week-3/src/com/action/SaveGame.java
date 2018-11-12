package com.action;

import java.io.FileOutputStream;

import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.commands.MacroCommand;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class SaveGame {

	public SaveGame() {}
	public static Logger logger = LogManager.getLogger(SaveGame.class); 
	
	public void save(ArrayList<MacroCommand> list) throws Exception {
		Date date = new Date() ;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") ;
		String filename = "Save_" + dateFormat.format(date) + ".ser";

		FileOutputStream fileStream = new FileOutputStream(filename);
		ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
		
		objectStream.writeObject(list);
		logger.debug(" Object written to file in save game");
		fileStream.close();
		objectStream.close();
	}
	
}