package id3;

import java.util.ArrayList;
import java.util.List;

public class RepositorioArvores {

	private static List<No> arvores = new ArrayList<No>();
	private static RepositorioArvores r;
	
	public RepositorioArvores(){}
	
	public static void adiciona(No n)
	{
		arvores.add(n);
	}
	
	public static void remove(No n)
	{
		arvores.remove(n);
	}
	
	public static List<No> getArvores() {
		return arvores;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public static RepositorioArvores getInstance()
	{
		if(r==null)
		{
			r = new RepositorioArvores();
		}
		return r;
	}
}
