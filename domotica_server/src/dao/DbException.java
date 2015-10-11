package dao;

public class DbException extends Exception
{
	private String msg;
	public DbException(String msg, String cause) {
        super(msg, new Exception(cause));
        this.msg = msg;
    }
 
    public String getMsg() {
        return msg;
    }
}
