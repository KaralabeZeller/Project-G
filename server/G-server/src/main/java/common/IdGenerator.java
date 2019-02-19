package common;

public final class IdGenerator {
	
	private static long idCounter = 0;
	private static int idIntCounter = 0;

	public static synchronized String createID()
	{
	    return String.valueOf(idCounter++);
	}    
	
	public static synchronized int createIntID()
	{
	    return idIntCounter++;
	}    
	
}
