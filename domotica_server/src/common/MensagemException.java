package common;

public class MensagemException extends Exception
{
	private String msg;
	public MensagemException(String msg, String cause) {
        super(msg, new Exception(cause));
        this.msg = msg;
    }
 
    public String getMsg() {
        return msg;
    }
}
