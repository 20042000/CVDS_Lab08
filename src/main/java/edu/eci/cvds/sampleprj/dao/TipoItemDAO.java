package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;

import java.util.List;

import edu.eci.cvds.exception.PersistenceException;

public interface TipoItemDAO {
	
	public void save(TipoItem tipo) throws PersistenceException; 
		
    public List<TipoItem> getTiposItems() throws PersistenceException;
    
    public TipoItem getTipoItem(int id) throws PersistenceException;
    
   
}

