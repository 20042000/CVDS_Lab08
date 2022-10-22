package edu.eci.cvds.samples.services.client;

import java.sql.SQLException;
import java.util.ArrayList;

import edu.eci.cvds.samples.entities.Cliente;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

import edu.eci.cvds.exception.ExcepcionServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquiler;
import edu.eci.cvds.samples.services.ServiciosAlquilerFactory;

public class MainExample {
	
	 public static void main(String args[]) throws SQLException, ExcepcionServiciosAlquiler{
		 ServiciosAlquiler servicio = ServiciosAlquilerFactory.getInstance().getServiciosAlquilerTesting();
		 servicio.registrarCliente(new Cliente("Luisa", 10234512, "3128548965", "Calle 138 A sur", "luisa@gmail.com", false, null));
		 System.out.println(servicio.consultarCliente(10234512));
		 System.exit(0);
	 }
	
}
