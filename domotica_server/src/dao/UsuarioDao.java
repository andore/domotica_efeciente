package dao;

public class UsuarioDao extends AbstractDao {

	public UsuarioDao() throws DbException {
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
