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

import org.mybatis.guice.transactional.Transactional;

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
		}catch(PersistenceException ex){
			throw new ExcepcionServiciosAlquiler("Error al consultar el valor de la multa del item"+itemId,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override
	public Cliente consultarCliente(long docu) throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarCliente((int)docu);
		}catch(PersistenceException ex){
			throw new ExcepcionServiciosAlquiler("Error al consultar el cliente "+docu,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
		
	}

	@Override 
	public List<ItemRentado> consultarItemsCliente(long idcliente) throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarItemsCliente((int)idcliente);
		}catch(PersistenceException ex){
			throw new ExcepcionServiciosAlquiler("Error al consultar los items del cliente "+idcliente,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}		
	}

	@Override 
	public List<Cliente> consultarClientes() throws ExcepcionServiciosAlquiler { 
		try {
			return clienteDAO.consultarClientes();
		}catch(PersistenceException ex){
			throw new ExcepcionServiciosAlquiler("Error al consultar los clientes",ex);
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
			throw new ExcepcionServiciosAlquiler("Error al consultar la multa del item  rentado"+iditem,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}		 
	}

	@Override 
	public TipoItem consultarTipoItem(int id) throws ExcepcionServiciosAlquiler { 
		try { 
			return tipoItemDAO.getTipoItem(id); 
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar el tipo item"+id,ex);
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

	@Transactional
	@Override 
	public void registrarAlquilerCliente(Date date, long docu, Item item, int numdias) throws ExcepcionServiciosAlquiler{ 
		try { 
			clienteDAO.agregarItemRentadoACliente((int)docu,item.getId(), date,Date.valueOf(date.toLocalDate().plusDays(numdias)) );
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al registrar el alquiler del item"+item.getId(),ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Transactional
	@Override 
	public void registrarCliente(Cliente c) throws ExcepcionServiciosAlquiler { 
		try { 
			clienteDAO.agregarCliente(c);
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al registrar el cliente"+c,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Override 
	public long consultarCostoAlquiler(int iditem, int numdias) throws ExcepcionServiciosAlquiler { 
		try { 
			return consultarItem(iditem).getTarifaxDia()*numdias;
		} catch (ExcepcionServiciosAlquiler ex) { 
			throw new ExcepcionServiciosAlquiler("Error al consultar el costo del alquiler del item"+iditem,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}

	@Transactional
	@Override 
	public void actualizarTarifaItem(int id, long tarifa) throws ExcepcionServiciosAlquiler { 
		try { 
			itemDAO.actualizarTarifaItem(id, tarifa);
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al actualizar la tarifa del item"+id,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}
	
	@Transactional
	@Override 
	public void registrarItem(Item i) throws ExcepcionServiciosAlquiler {
		try { 
			itemDAO.save(i);
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al registrar el item"+i,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		} 
	}
	
	@Transactional
	@Override 
	public void vetarCliente(long docu, boolean estado) throws ExcepcionServiciosAlquiler {
		try { 
			clienteDAO.vetarCliente((int)docu, estado);
		} catch (PersistenceException ex) { 
			throw new ExcepcionServiciosAlquiler("Error al vetar el cliente"+docu,ex);
		}catch(Exception e) {
			throw new UnsupportedOperationException("Not supported yet.");
		}
	}	

}
