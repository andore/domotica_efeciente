package dao;

import java.util.List;

public class SensorDao extends AbstractDao {

	public SensorDao() throws DbException {
		super();
	}
	
	public void insere(Sensor sensor) throws DbException
	{
		super.insert(sensor);	
	}
	
	public void update(Sensor sensor) throws DbException
	{
		super.update(sensor);
	}
	
	@SuppressWarnings("rawtypes")
	public Sensor serachById(int id)
	{
		String sql = "from " + Sensor.class.getSimpleName() + " C WHERE C.id_sensor = :id_sensor";
		List sensores = sessao.createQuery(sql).setParameter("id_sensor", id).list();
		if( sensores!=null && sensores.size() > 0)
		{
			if(sensores.size() == 1)
			{
				return (Sensor) sensores.get(0);
			}
			else
			{
				logger.warn("Exeiste mais de um Sensor com ID:" + id);
				return null;
			}
		}
		else
		{
			logger.warn("Nenhum Sensor encontrado com ID:" + id);
			return null;
		}
	}

}
