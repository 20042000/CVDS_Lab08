package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO {
	
	public void save(ItemRentado id) throws PersistenceException; 
	public ItemRentado load(int id) throws PersistenceException;

}
