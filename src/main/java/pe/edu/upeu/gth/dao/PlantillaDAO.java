/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Cesar
 */
public class PlantillaDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public PlantillaDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Planilla(String id_direc, String id_depart,
            String id_seccion, String id_puesto, String id_area) {
        sql = "select RHFU_BUSCAR_PLANTILLA(?, "
                + "?, ?, ?,'" + id_direc + "') from dual";
        String id=jt.queryForObject(sql,String.class,id_puesto,id_seccion,
                id_area,id_depart,id_direc);
        sql = "select * from RHTC_PLANTILLA_PUESTO pp, "
                    + "RHTC_PLANTILLA_CONTRACTUAL pc where "
                    + "pp.ID_PLANTILLA_CONTRACTUAL = pc.ID_PLANTILLA_CONTRACTUAL and "
                    + "pp.es_plantilla_puesto = 1 and "
                    + "pc.ID_PLANTILLA_CONTRACTUAL=?";
        return jt.queryForList(sql,id);
    }

}
