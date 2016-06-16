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
	
}
