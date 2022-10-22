
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
				Cliente cliente = serviciosAlquiler.consultarCliente(documento);
			} catch(ExcepcionServiciosAlquiler e) { 
				r = true;
			} catch(IndexOutOfBoundsException e) { 
				r = true;
			}
			// Validate no Client was found;
			Assert.assertTrue(r);
		};
	}*/

	@Test
	public void noDeberiaConsultarElvalorMultaSiItemNoExiste() {
		try {
			Assert.assertNull(serviciosAlquiler.valorMultaRetrasoxDia(0));
		}catch(ExcepcionServiciosAlquiler e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void deberiaConsultarValorMultaRetrasoxDia() {
		try {
			Assert.assertEquals(0,serviciosAlquiler.valorMultaRetrasoxDia(1));
		}catch(ExcepcionServiciosAlquiler e) {
			fail(e.getMessage());
		}	
	} 
}
