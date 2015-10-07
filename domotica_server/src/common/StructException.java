package common;

public class StructException extends Exception
{
	private String msg;
	public StructException(String msg, String cause) {
        super(msg, new Exception(cause));
        this.msg = msg;
    }
 
    public String getMsg() {
        return msg;
    }
}
