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
public class NacionalidadDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public NacionalidadDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
    
    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Nacionalidad() {
        sql = "SELECT ID_NACIONALIDAD,(trim(NO_NACIONALIDAD))as NO_NACIONALIDAD,"
                + "CO_NACIONALIDAD FROM RHTX_NACIONALIDAD ORDER BY NO_NACIONALIDAD";
        return jt.queryForList(sql);
    }

}
