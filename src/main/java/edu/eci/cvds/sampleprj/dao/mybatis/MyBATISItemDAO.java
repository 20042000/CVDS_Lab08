package edu.eci.cvds.sampleprj.dao.mybatis;

import com.google.inject.Inject;
import com.google.inject.Singleton; 
import edu.eci.cvds.sampleprj.dao.ItemDAO; 
import edu.eci.cvds.exception.PersistenceException; 
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper; 
import edu.eci.cvds.samples.entities.Item; 
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper; 
import edu.eci.cvds.samples.entities.TipoItem; 
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MyBATISItemDAO implements ItemDAO {
	
	@Inject
	private ItemMapper itemMapper;
	
	//insertarItem
	@Override 
	public void save(Item it) throws PersistenceException{ 
		try{ itemMapper.insertarItem(it);
		} catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al registrar el item "+it.toString(),e);
		}
	}
	
	//consultarItem
	@Override 
	public Item load(int id) throws PersistenceException { 
		try{ 
			return itemMapper.consultarItem(id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar el item "+id,e);
		}
	}
	
	@Override 
	public List<Item> consultarItems() throws PersistenceException{
		try{ 
			return itemMapper.consultarItems();
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar los items ",e);
		}
	}
	
	@Override 
	public List<Item> consultarItemsDisponibles () throws PersistenceException{
		try{ 
			return itemMapper.consultarItemsDisponibles();
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar los items disponibles ",e);
		}	
	}
	
	@Override
	public long consultarMultaAlquiler(int itemId, Date finRenta) throws PersistenceException{
		try{ 
			return itemMapper.consultarMultaAlquiler(itemId,finRenta);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar la multa del item rentado ",e);
		}
	}
}


