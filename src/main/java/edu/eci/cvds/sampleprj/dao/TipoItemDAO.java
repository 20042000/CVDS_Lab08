package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.exception.PersistenceException;

public interface TipoItemDAO {
	
	public void save(TipoItem tipo) throws PersistenceException; 
	public TipoItem load(int id) throws PersistenceException;
}
