package common;

public final class IdGenerator {
	
	private static long idCounter = 0;

	public static synchronized String createID()
	{
	    return String.valueOf(idCounter++);
	}    
	
}
