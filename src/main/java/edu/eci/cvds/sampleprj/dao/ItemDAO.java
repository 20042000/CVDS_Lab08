package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {
	
	//insertarItem
	public void save(Item it) throws PersistenceException;
	
	//consultarItem
	public Item load(int id) throws PersistenceException;

}
