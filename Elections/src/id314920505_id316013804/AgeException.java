package id314920505_id316013804;

public class AgeException extends Exception {
	private String msg;
	
	public AgeException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
