package id314920505_id316013804;

public class InvalidInputException extends Exception{
	private String msg;
	
	public InvalidInputException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
