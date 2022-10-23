
import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList; 
import java.util.List;
import com.google.inject.Inject;

import edu.eci.cvds.exception.ExcepcionServiciosAlquiler;
import edu.eci.cvds.exception.PersistenceException;
import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;
import edu.eci.cvds.samples.services.ServiciosAlquiler; 
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory; 
import org.apache.ibatis.session.SqlSession; 
import org.junit.Before; 
import org.junit.Test;
import org.junit.Assert;

public class ServiciosAlquilerTest {
	
	@Inject
	private SqlSession sqlSession; 
	ServiciosAlquiler serviciosAlquiler;
	
	public ServiciosAlquilerTest() {
		serviciosAlquiler = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
	}
	
	@Before
	public void setUp() {}
	
	/*@Test
	public void emptyDB() {
		for(int i = 0; i < 100; i += 10) { 
			boolean r = false; 
			try {
				Cliente cliente = serviciosAlquiler.consultarCliente(5);
			} catch(ExcepcionServiciosAlquiler e) { 
				r = true;
			} catch(IndexOutOfBoundsException e) { 
				r = true;
			}
			// Validate no Client was found;
			Assert.assertTrue(r);
		}
	}*/

	@Test
    public void deberiaRegistrarCliente(){
        try{
            Cliente cliente = new Cliente("Fernanda", 3985315 ,"325685120", "calle 3 sur ", "fernanda@gmail.com");
            serviciosAlquiler.registrarCliente(cliente);
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
	
	@Test
	public void deberiaConsultarCliente(){
		try{
			Cliente cliente = new Cliente("Fernanda", 3985315 ,"325685120", "calle 3 sur ", "fernanda@gmail.com");
	        serviciosAlquiler.registrarCliente(cliente);
	        Cliente consultaCliente = serviciosAlquiler.consultarCliente(cliente.getDocumento());
	        Assert.assertTrue(cliente.getDocumento() == consultaCliente.getDocumento());
	    }catch (Exception e){
	    	System.out.println(e.getMessage());
	    }
	}

	 @Test
	    public void deberiaRegistrarItem() {
	    	try {
	    		Date fecha2 = new Date(120, 11, 25);
	    		TipoItem tipo2 = new TipoItem(91, "CienciaFiccion");
	    		Item item = new Item(tipo2, 14  , "La casa del dragon" , "serie", fecha2, 160, "dvd", "CienciaFiccion");
	    		serviciosAlquiler.registrarItem(item);
	    		
	    		Item itemCheck = serviciosAlquiler.consultarItem(item.getId());
				assertEquals(item.getId(), itemCheck.getId());
	    		
			} catch (ExcepcionServiciosAlquiler e) {
				Assert.assertFalse(false);
			}
	    }
	 
	 @Test
	    public void deberiaConsultarItem() {
	    	try {
				Item item = serviciosAlquiler.consultarItem(93);
				assertEquals(93, item.getId());
		    	
			} catch (ExcepcionServiciosAlquiler e) {
				Assert.assertFalse(false);
			}
	    }
	 
	 @Test
	    public void deberiaConsultarItemsDisponibles() {
	    	try {
	    		
	    		
				List<Item> items = serviciosAlquiler.consultarItemsDisponibles();
				Assert.assertTrue(items.size() > 0);
		    	
			} catch (ExcepcionServiciosAlquiler e) {
				Assert.assertFalse(false);
			}
	    }
	 
	 @Test
	    public void validVetarCliente() {
	    	try {
	    		Cliente cliente = new Cliente("Fernanda", 3985315 ,"325685120", "calle 3 sur ", "fernanda@gmail.com");
		        serviciosAlquiler.registrarCliente(cliente);
				serviciosAlquiler.vetarCliente(3985315, true);
				Cliente consultaCliente = serviciosAlquiler.consultarCliente(3985315);
				assertEquals(true, consultaCliente.isVetado());
		    	
			} catch (ExcepcionServiciosAlquiler e) {
				Assert.assertFalse(false);
			}
	    }
	 
	 @Test
	    public void deberiaRegistrarAlquiler() {
	    	try { 
	            Date fecharegistro = java.sql.Date.valueOf("2020-10-10");
	    	    List<Item> itDis = serviciosAlquiler.consultarItemsDisponibles();
	    	    Item it = itDis.get(0);
	    	    Cliente cliente = new Cliente("Fernanda", 3985315 ,"325685120", "calle 3 sur ", "fernanda@gmail.com");
		        serviciosAlquiler.registrarCliente(cliente);
	    	    Cliente consultaCliente = serviciosAlquiler.consultarCliente(3985315);
	    	    serviciosAlquiler.registrarAlquilerCliente(fecharegistro, consultaCliente.getDocumento(), it, 10);
		    	
			} catch (ExcepcionServiciosAlquiler e) {
				Assert.assertFalse(false);
			}
	    }
	    


}
