package edu.eci.cvds.sampleprj.dao;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.ItemRentado;

public interface ItemRentadoDAO { 
	
	//consultar item rentado
	public ItemRentado load(int id) throws PersistenceException;

}
