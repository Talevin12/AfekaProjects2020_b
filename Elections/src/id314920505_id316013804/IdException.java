package id314920505_id316013804;

public class IdException extends Exception{
	private String msg;
	
	public IdException(String msg) {
		this.msg = msg;
	}
	
	public String getMsg() {
		return this.msg;
	}
}
