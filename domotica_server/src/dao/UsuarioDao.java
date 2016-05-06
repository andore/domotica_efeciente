package dao;

import org.hibernate.Session;

public class UsuarioDao extends AbstractDao {

	public UsuarioDao() {
		super();
	}
	
	public void insere(Usuario usuario) throws DbException
	{
		if(usuario!=null)
		{
			super.insert(usuario);
		}
	}

}
