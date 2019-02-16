package common.log;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import common.constants.*;


public class GLogFiles {

	List<FileWriter> files;
	List<PrintWriter> writers;
	String date;
	

	public GLogFiles () {
		files = new LinkedList<FileWriter>();
		writers = new LinkedList<PrintWriter>();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		Date currDate = new Date();
		date = dateFormat.format(currDate);
		
		
		
		initFiles();
	}

	private void initFiles() {
		for(int module = 0; module < Modules.NUM_OF_MODULES; module++) {
			addFile(module);
		}
			
	}

	private void addFile(int module) {
		try {
			switch(module) {
				case Modules.STARTER: 			files.add(new FileWriter(getUniqueFilename("starter")));
												writers.add(new PrintWriter(files.get(module), true));	
												break;
				case Modules.NETWORK: 			files.add(new FileWriter(getUniqueFilename("network")));
												writers.add(new PrintWriter(files.get(module), true));
												break;
				case Modules.DB:				files.add(new FileWriter(getUniqueFilename("db")));
												writers.add(new PrintWriter(files.get(module), true));
												break;	
				case Modules.AUTHENTICATION: 	files.add(new FileWriter(getUniqueFilename("auth")));
												writers.add(new PrintWriter(files.get(module), true));
												break;
				case Modules.DISPATCHER: 		files.add(new FileWriter(getUniqueFilename("dispatch")));
												writers.add(new PrintWriter(files.get(module), true));
												break;
				case Modules.UPDATE: 			files.add(new FileWriter(getUniqueFilename("update")));
												writers.add(new PrintWriter(files.get(module), true));
												break;
				case Modules.GAME: 				files.add(new FileWriter(getUniqueFilename("game")));
												writers.add(new PrintWriter(files.get(module), true));
												break;
					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


	private String getUniqueFilename(String prefix) {
		int seq = 1;
		String seq_string = String.format("%03d", seq);
		
		String filename = "./logs/" + prefix + "_" + date + "_" + seq_string +".log";
		File uniqueFile = new File( filename);
		while(uniqueFile.exists()) {
			seq++;
			seq_string = String.format("%03d", seq);
			
			filename = "./logs/" + prefix + "_" + date + "_" + seq_string +".log";
			uniqueFile = new File(filename);
		}
		
		return filename;
		
	}
	
	
	public void writelog(int module, String message) {
		PrintWriter pw = writers.get(module);
		pw.println(message);
	}
	


}
