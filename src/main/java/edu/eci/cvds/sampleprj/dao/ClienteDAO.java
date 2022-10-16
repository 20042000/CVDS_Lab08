package edu.eci.cvds.sampleprj.dao;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;

public interface ClienteDAO {
	
	public Cliente consultarCliente(int id) throws PersistenceException;
	
	public void agregarItemRentadoACliente(int documento, int idit, Date fechainicio,Date fechafin) throws PersistenceException;

	public List<Cliente> consultarClientes() throws PersistenceException;

}
