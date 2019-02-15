package util;

public class GException extends Exception {
	
	private final String code;
	
	public GException(String code) {
		super();
		this.code = code;
	}

	public GException(String message, Throwable cause, String code) {
		super(message, cause);
		this.code = code;
	}

	public GException(String message, String code) {
		super(message);
		this.code = code;
	}

	public GException(Throwable cause, String code) {
		super(cause);
		this.code = code;
	}
	
	public String getCode() {
		return this.code;
	}
}
