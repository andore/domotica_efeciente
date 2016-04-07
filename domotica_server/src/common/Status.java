package common;

public enum Status 
{
	ON(1), OFF(0), AUTO(2);	
	public int s;
	Status(int s)
	{
		this.s = s;
	}
}
