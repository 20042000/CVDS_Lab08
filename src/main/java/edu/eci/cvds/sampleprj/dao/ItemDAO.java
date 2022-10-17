package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Item;

public interface ItemDAO {
	
	//insertarItem
	public void save(Item it) throws PersistenceException;
	
	//consultarItem
	public Item load(int id) throws PersistenceException;

	//consultarItems
	public List<Item> consultarItems() throws PersistenceException;
	
	//consultar Items disponibles
	public List<Item> consultarItemsDisponibles () throws PersistenceException;
	
	//consultar la multa del alquiler
	public long consultarMultaAlquiler(int itemId, Date finRenta) throws PersistenceException;
	
	//actualizar la tarifa del item
	public void actualizarTarifaItem(int id, long tarifa) throws PersistenceException;
}

