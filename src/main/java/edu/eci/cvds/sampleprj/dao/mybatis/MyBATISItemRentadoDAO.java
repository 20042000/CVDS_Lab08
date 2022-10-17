package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemRentadoMapper;
import edu.eci.cvds.samples.entities.ItemRentado;

public class MyBATISItemRentadoDAO {
	
	@Inject
	private ItemRentadoMapper itemRentadoMapper;
	
	public ItemRentado load(int id) throws PersistenceException{
		try{ 
			return itemRentadoMapper.consultarItemRentado(id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar el item rentado "+id,e);
		}
	}

}
