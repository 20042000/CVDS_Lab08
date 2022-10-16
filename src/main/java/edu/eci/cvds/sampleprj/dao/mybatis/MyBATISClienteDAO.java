package edu.eci.cvds.sampleprj.dao.mybatis;

import java.util.Date;
import java.util.List;

import com.google.inject.Inject; 
import edu.eci.cvds.sampleprj.dao.ClienteDAO; 
import edu.eci.cvds.exception.PersistenceException; 
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado; 

public class MyBATISClienteDAO implements ClienteDAO{
	
	@Inject
	private ClienteMapper clienteMapper;
	
	@Override 
	public Cliente consultarCliente(int documento) throws PersistenceException { 
		try{ 
			return clienteMapper.consultarCliente(documento);
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar el cliente "+documento,e);
		}
	}
	
	@Override 
	public void agregarItemRentadoACliente(int documento, int idit, Date fechainicio,Date fechafin) throws PersistenceException{ 
		try{ clienteMapper.agregarItemRentadoACliente(documento, idit, fechainicio,fechafin);
		} catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al registrar el item rentado "+documento,e);
		}
	}
	
	@Override 
	public List<Cliente> consultarClientes() throws PersistenceException { 
		try{ 
			return clienteMapper.consultarClientes();
		}catch(org.apache.ibatis.exceptions.PersistenceException e){ 
			throw new PersistenceException("Error al consultar los clientes ",e);
		}
	}
	

}
