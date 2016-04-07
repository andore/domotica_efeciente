package dao;

import org.hibernate.Session;

public class UsuarioDao extends AbstractDao {

	public UsuarioDao(Session sessao) {
		super(sessao);
	}
	
	public void insere(Usuario usuario) throws DbException
	{
		if(usuario!=null)
		{
			super.insert(usuario);
		}
	}

}
