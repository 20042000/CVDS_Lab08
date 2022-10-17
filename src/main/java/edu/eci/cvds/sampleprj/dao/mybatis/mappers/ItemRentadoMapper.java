package edu.eci.cvds.sampleprj.dao.mybatis.mappers;

import org.apache.ibatis.annotations.Param;
import edu.eci.cvds.samples.entities.ItemRentado;

/**
 * 
 * @author Bermudez - Ladino
 *
 */
public interface ItemRentadoMapper {
	
	public  ItemRentado consultarItemRentado(@Param("iditr")int id);

}
