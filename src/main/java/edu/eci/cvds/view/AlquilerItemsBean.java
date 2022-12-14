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



@SuppressWarnings("serial")
@ManagedBean(name="AlquilerItemsBean")
@ApplicationScoped
public class AlquilerItemsBean extends BasePageBean {

    @Inject
    private ServiciosAlquiler serviciosAlquiler;
    private Cliente selectedCliente;
	private long costo;
    public List<Cliente> consultarClientes(){
        List<Cliente> clientes = null;
        try{
            clientes=serviciosAlquiler.consultarClientes();
        } catch (ExcepcionServiciosAlquiler ex) {
            setErrorMessage(ex);
        }
        return clientes;
    }
    public Cliente consultarCliente(long documento){
        Cliente cliente=null;
        try {
            cliente=serviciosAlquiler.consultarCliente(documento);
        } catch (Exception ex) {
            setErrorMessage(ex);
        }
        return cliente;
    }
    public void registrarCliente(long doc,String nombre,String telefono, String direccion,String mail){
        try{
            serviciosAlquiler.registrarCliente(new Cliente(nombre,doc,telefono,direccion,mail));
        } catch (Exception ex) {
            setErrorMessage(ex);
        }
    }

    public void setSelectedCliente(Cliente cliente){
    	this.selectedCliente = cliente;
    	}

    public Cliente getSelectedCliente(){
        return selectedCliente;
    }
	
	
	public long consultarMulta(Item it) {
		long multa = 0;
		try{
			multa= serviciosAlquiler.consultarMultaAlquiler(it.getId(),new Date(System.currentTimeMillis()));
		}
		catch(ExcepcionServiciosAlquiler ex){
            setErrorMessage(ex);
        }
		return multa;
    }
	
	
	public void registrarAlquilerCliente(int id,int numDiasAlquilar){
        try{
            Item item = serviciosAlquiler.consultarItem(id);
            serviciosAlquiler.registrarAlquilerCliente(new Date(System.currentTimeMillis()),selectedCliente.getDocumento(),item,numDiasAlquilar);
        }catch(ExcepcionServiciosAlquiler ex){
            setErrorMessage(ex);
        }
    }
	
	public void consultarCosto(int id, int numDiasAlquilar){
        try {
            costo = serviciosAlquiler.consultarCostoAlquiler(id, numDiasAlquilar);
        } catch (ExcepcionServiciosAlquiler ex){
            setErrorMessage(ex);
        }
    }
	
	public long getCosto(){
        return costo;
    }

    private void setErrorMessage(Exception ex){
        String message = ex.getMessage();
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
    }

}
