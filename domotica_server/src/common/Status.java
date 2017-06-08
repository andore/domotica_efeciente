package common;

public enum Status 
{
	OFF(0), ON(1) , AUTO(2);	
	public int s;
	Status(int s)
	{
		this.s = s;
	}
	
	public static int OFF_ = 0;
	public static int ON_ = 1;
	public static int AUTO_ = 2;
	
	public static Status getStatus(int s)
	{
		switch (s) {
		case 0:
			return Status.OFF;
		case 1:
			return Status.ON;
		case 2:
			return Status.AUTO;
		default:
			return null;	
		}
	}
}
