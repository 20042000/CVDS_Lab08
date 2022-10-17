/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.samples.services.client;



import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.Date;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.ItemRentado;
import edu.eci.cvds.samples.entities.TipoItem;

/**
 *
 * @author hcadavid
 */
public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     * @param args
     * @throws SQLException 
     */
    public static void main(String args[]) throws SQLException{
        SqlSessionFactory sessionfact = getSqlSessionFactory();

        SqlSession sqlss = sessionfact.openSession();

        ClienteMapper cm=sqlss.getMapper(ClienteMapper.class);
        System.out.println("--------------------------------");
        System.out.println("-------------Clientes-----------");
        System.out.println("--------------------------------");
        System.out.println(cm.consultarClientes());
        
        /*System.out.println("-------------------------------------------");
        System.out.println("-------------Informacion Cliente-----------");
        System.out.println("-------------------------------------------");
        cm.agregarItemRentadoACliente(2154421, 93,new Date(), new Date());
        System.out.println(cm.consultarCliente(2154421));*/
        
        ItemMapper im=sqlss.getMapper(ItemMapper.class);
        System.out.println("--------------------------------");
        System.out.println("-------------Items--------------");
        System.out.println("--------------------------------");
        System.out.println(im.consultarItems());
        
        /*Item nuevoItem  = new Item(new TipoItem (2, "ficcion"), 57, "star wars", "pelicula", new Date(), 3000, "dvd", "ficcion");
        im.insertarItem(nuevoItem);
        System.out.println("--------------------------------");
        System.out.println("---------Item agregado----------");
        System.out.println("--------------------------------");
        System.out.println(im.consultarItem(57));*/
        
        
        sqlss.commit();
        
        sqlss.close();
        System.exit(0);
      
    }


}
