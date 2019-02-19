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

/**
 * 
 * @author KaralabeZeller
 * 
 * <p>Creates a list of files and file handlers to be able to sort the incoming log messages
 * Logs are sorted acct. the Modules, which references the Modules class 
 * 
 * If a new component is added to the server, this class must be modified, to be able to log it
 * </p>
 */
public class GLogFiles {

	List<FileWriter> files;
	List<PrintWriter> writers;
	String date;
	
	/**
	 * Initializes  the file and file handler lists
	 * Queries the current date, which will be added to the filename
	 */
	public GLogFiles () {
		files = new LinkedList<FileWriter>();
		writers = new LinkedList<PrintWriter>();
		
		DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd");
		Date currDate = new Date();
		date = dateFormat.format(currDate);
		
		
		
		initFiles();
	}

	/**
	 * Helper function to fill the lists
	 */
	private void initFiles() {
		for(int module = 0; module < Modules.NUM_OF_MODULES; module++) {
			addFile(module);
		}
			
	}
	
	/**
	 * Creates new file and file handler, which is then added to the list
	 * @param module The number of the module to create the filename
	 */
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
				case Modules.UM:			 	files.add(new FileWriter(getUniqueFilename("um")));
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

    /**
     * Creates a unique filename pased on the prefix parameter, the current date and a unique autoincrement sequence
     * mask: <prefix>_yyyy_mm_dd_seq(3).log
     * 
     * @param prefix String representation of the module, which is the prefix of the log file
     * @return the unique filename
     */
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
	
	/**
	 * Writes the message from the parameter to the log file designated by the moduel
	 * 
	 * @param module The number of the module, which file will be updated
	 * @param message The message to be written into the logfile
	 */
	public void writelog(int module, String message) {
		PrintWriter pw = writers.get(module);
		pw.println(message);
	}
	


}
