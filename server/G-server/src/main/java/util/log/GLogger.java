package util.log;

import java.io.BufferedWriter;
import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import util.constants.Modules;

/**
 * Logging class to separate modules into specific log files
 * - NETWORK:  			logs/network_yyyy_mm_dd_nnn.log
 * - DISPATCH: 			logs/dispatch_yyyy_mm_dd_nnn.log
 * - DB: 				logs/db_yyyy_mm_dd_nnn.log
 * - UPDATE: 			logs/update_yyyy_mm_dd_nnn.log
 * - AUTHENTICATION: 	logs/authentication_yyyy_mm_dd_nnn.log
 * - STARTER: 			logs/starter_yyyy_mm_dd_nnn.log
 * - GAME: 				logs/game_yyyy_mm_dd_nnn.log
 * 
 * Severity is defined by numbers:
 *  - 1:	INFO
 *  - 2:	WARNING
 *  - 3:	ERROR
 *  - 4: 	DEBUG
 */
public final class GLogger {
	
	
	static boolean consoleOut = false;
	static GLogFiles logFiles;
	BufferedWriter writer;
	
	public GLogger(boolean consoleOut) {
		this.consoleOut = consoleOut;
		logFiles = new GLogFiles();
	}
	
	/**
	 * Writes INFO messages to the log file of the specified module
	 * @param module The name of the module
	 * @param message The message to be displayed
	 */
	public static void info(int module, String message) {
		String timeStamp = new SimpleDateFormat("yyyy-MMdd-HH mm:ss").format(Calendar.getInstance().getTime());
		String fullMessage =  timeStamp + " " + "[INFO]" + ": " + message;
		logFiles.writelog(module, fullMessage);
		
		writeToConsole(module, message);
	}
	
	/**
	 * Writes WARNING messages to the log file of the specified module
	 * @param module The name of the module
	 * @param message The message to be displayed
	 */
	public static void warn(int module, String message) {
		String timeStamp = new SimpleDateFormat("yyyy-MMdd-HH mm:ss").format(Calendar.getInstance().getTime());
		String fullMessage =  timeStamp + " " + "[WARN]" + ": " + message;
		logFiles.writelog(module, fullMessage);
		
		writeToConsole(module, message);
	}
	
	/**
	 * Writes ERROR messages to the log file of the specified module
	 * @param module The name of the module
	 * @param message The message to be displayed
	 */
	public static void error(int module, String message) {
		String timeStamp = new SimpleDateFormat("yyyy-MMdd-HH mm:ss").format(Calendar.getInstance().getTime());
		String fullMessage =  timeStamp + " " + "[ERROR]" + ": " + message;
		logFiles.writelog(module, fullMessage);
		
		writeToConsole(module, message);

	}
	
	
	/**
	 * Writes DEBUG messages to the log file of the specified module
	 * @param module The name of the module
	 * @param message The message to be displayed
	 */
	public static void debug(int module, String message) {
		String timeStamp = new SimpleDateFormat("yyyy-MMdd-HH mm:ss").format(Calendar.getInstance().getTime());
		String fullMessage =  timeStamp + " " + "[DEBUG]" + ": " + message;
		logFiles.writelog(module, fullMessage);
		
		writeToConsole(module, message);
	}
	
	private static void writeToConsole(int module, String message) {
		if(!consoleOut)
			return;
		
		switch(module) {
			case Modules.STARTER: System.out.println("STARTER - " + message);
				break;
			case Modules.DB: System.out.println("DB - " + message);
				break;
				case Modules.NETWORK: System.out.println("NETWORK - " + message);
			break;
				case Modules.DISPATCHER: System.out.println("DISPATCHER - " + message);
			break;
				case Modules.GAME: System.out.println("GAME" + message);
			break;
				case Modules.UPDATE: System.out.println("UPDATE - " + message);
			break;
				case Modules.AUTHENTICATION: System.out.println("AUTHENTICATION - " + message);
			break;
		
		}
		
	}
}
