package edu.eci.cvds.view;

import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.exception.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import java.util.List;

import java.sql.Date;

@ManagedBean(name="AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {
	@Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente cliente;
	private long precio;
	
    public List<Cliente> consultarClientes(){
        List<Cliente> clientes = null;
        try{
            clientes = serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler e) {
            setErrorMessage(e);
        }
        return clientes;
    }
    public Cliente consultarCliente(int id){
        Cliente cliente = null;
        try {
            cliente = serviciosAlquiler.consultarCliente(id);
        } catch (Exception ex) {
            setErrorMessage(ex);
        }
        return cliente;
    }
    public void registrarCliente(int documento, String nombre, String telefono, String direccion, String mail){
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre, documento, telefono, direccion, mail));
        } catch (Exception ex) {
            setErrorMessage(ex);
        }
    }

    public void setCliente(Cliente cliente){
		this.cliente = cliente;
	}

    public Cliente getCliente(){
        return cliente;
    }
	
	
	public long consultarMulta(Item itemId) {
		long valorMulta = 0;
		try{
			valorMulta = serviciosAlquiler.consultarMultaAlquiler(itemId.getId(),new Date(System.currentTimeMillis()));
		}
		catch(ExcepcionServiciosAlquiler ex){
            setErrorMessage(ex);
        }
		return valorMulta;
    }
	
	
	public void registrarAlquilerCliente(int id,int numDiasAlquilar){
        try{
            Item item = serviciosAlquiler.consultarItem(id);
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),cliente.getDocumento(),item,numDiasAlquilar);
        }catch(ExcepcionServiciosAlquiler ex){
            setErrorMessage(ex);
        }
    }
	
	public void consultarPrecio(int id, int numDiasAlquilar){
        try {
            precio = serviciosAlquiler.consultarCostoAlquiler(id, numDiasAlquilar);
        } catch (ExcepcionServiciosAlquiler e){
            setErrorMessage(e);
        }
    }
	
	public long getPrecio(){
        return precio;
    }

    private void setErrorMessage(Exception ex){
        String message = ex.getMessage();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

}
