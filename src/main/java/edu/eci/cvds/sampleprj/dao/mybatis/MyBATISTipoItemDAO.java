package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.List;

import com.google.inject.Inject;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.TipoItemMapper;
import edu.eci.cvds.samples.entities.TipoItem;

public class MyBATISTipoItemDAO implements TipoItemDAO {
	@Inject
	private TipoItemMapper tipoItemMapper;
	
	@Override
	public void save(TipoItem tipo) throws PersistenceException{
		try{ 
			tipoItemMapper.addTipoItem(tipo.getDescripcion());
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al registrar el tipo item "+tipo.getDescripcion(),e);
		}
	}
	
	@Override
    public List<TipoItem> getTiposItems() throws PersistenceException{
		try{ 
			return tipoItemMapper.getTiposItems();
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar los tipos items ",e);
		}	
	}
    
	@Override
    public TipoItem getTipoItem(int id) throws PersistenceException{
		try{ 
			return tipoItemMapper.getTipoItem(id);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar tipo item "+ id,e);
		}
	}
    
}
