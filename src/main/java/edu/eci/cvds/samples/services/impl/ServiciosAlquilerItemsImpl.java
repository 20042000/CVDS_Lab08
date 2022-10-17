package edu.eci.cvds.samples.services.impl;

import com.google.inject.Inject; 
import com.google.inject.Singleton;
import edu.eci.cvds.exception.ExcepcionServiciosAlquiler;
import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.sampleprj.dao.ClienteDAO; 
import edu.eci.cvds.sampleprj.dao.ItemDAO;
import edu.eci.cvds.sampleprj.dao.TipoItemDAO;
import edu.eci.cvds.samples.entities.Cliente; 
import edu.eci.cvds.samples.entities.Item; 
import edu.eci.cvds.samples.entities.ItemRentado; 
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ServiciosAlquiler; 
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Singleton
public class ServiciosAlquilerItemsImpl implements ServiciosAlquiler{
	@Inject
	private ItemDAO itemDAO;
	
	@Inject
	private ClienteDAO clienteDAO;
	
	@Inject
	private TipoItemDAO tipoItemDAO;

	@Override 
	public int valorMultaRetrasoxDia(int itemId) throws ExcepcionServiciosAlquiler { 
		try {
			return (int)itemDAO.load(itemId).getTarifaxDia();
		}catch(PersistenceException pe){
			throw new ExcepcionServiciosAlquiler(pe.getMessage());
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override
	public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarCliente((int)docu);
		}catch(PersistenceException pe){
			throw new ExcepcionServiciosAlquiler(pe.getMessage());
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override 
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarItemsCliente((int)idcliente);
		}catch(PersistenceException pe){
			throw new ExcepcionServiciosAlquiler(pe.getMessage());
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}		
	}

	@Override 
	public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarClientes();
		}catch(PersistenceException pe){
			throw new ExcepcionServiciosAlquiler(pe.getMessage());
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Override 
	public Item consultarItem(int id) throws ExcepcionServiciosAlquiler { 
		try { 
			return itemDAO.load(id); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar el item "+id,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Override 
	public List<Item> consultarItemsDisponibles() throws ExcepcionServiciosAlquiler { 
		try { 
			return itemDAO.consultarItemsDisponibles(); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar el item  disponibles",ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override 
	public long consultarMultaAlquiler(int iditem, Date fechaDevolucion) throws ExcepcionServiciosAlquiler { 
		try { 
			return itemDAO.consultarMultaAlquiler(iditem,fechaDevolucion); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar la multa del item  rentado",ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}		 
	}

	@Override 
	public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler { 
		try { 
			return tipoItemDAO.getTipoItem(id); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item",ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override 
	public List<TipoItem> consultarTiposItem() throws ExcepcionServiciosAlquiler { 
		try { 
			return tipoItemDAO.getTiposItems(); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar los tipos items",ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override 
	public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler{ 
		try { 
			
			clienteDAO.agregarItemRentadoACliente((int)docu,item.getId(), date,Date.valueOf(date.toLocalDate().plusDays(numdias)) );
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al registrar el alquiler",ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Override 
	public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler { 
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override 
	public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler { 
		throw new UnsupportedOperationException("Not supported yet.");
	}

	@Override 
	public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler { 
		throw new UnsupportedOperationException("Not supported yet.");
	}
	@Override 
	public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet."); 
		//To change body of generated methods, choose Tools | Templates.
	}
	@Override 
	public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
		throw new UnsupportedOperationException("Not supported yet."); 
		//To change body of generated methods, choose Tools | Templates.
	}	

}
