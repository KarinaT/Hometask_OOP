package src.com.epam.mentoring.util;

public class MoneyException extends Exception{

	private static final long serialVersionUID = 1L;
	private String message;
	
	public MoneyException(String message) {
		this.message = message;
	}
}
