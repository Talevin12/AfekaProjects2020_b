package id314920505_id316013804;

public class InvalidInputException extends Exception{

	private static final long serialVersionUID = 1L;
	private String msg;
	
	public InvalidInputException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
