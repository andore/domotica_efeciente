package common;

public class EstruturaException extends Exception
{
	private static final long serialVersionUID = 1L;
	private String msg;
	public EstruturaException(String msg, String cause) {
        super(msg, new Exception(cause));
        this.msg = msg;
    }
 
    public String getMsg() {
        return msg;
    }
}
