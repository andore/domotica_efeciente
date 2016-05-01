package dao;

public class DbException extends Exception
{
	private String msg;
	private String sqlState;
	
	public DbException(String msg, String cause) {
        super(msg, new Exception(cause));
        this.msg = msg;
    }
	
	public DbException(String msg, String cause, String sqlState) {
        super(msg, new Exception(cause));
        this.msg = msg;
        this.sqlState = sqlState;
    }
 
    public String getMsg() {
        return msg;
    }

	public String getSqlState() {
		return sqlState;
	}
}
