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
public class ModuloDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public ModuloDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Modulo() {
        sql = "SELECT * FROM RHTV_MODULO ORDER BY NO_MODULO";
        return jt.queryForList(sql);
    }

}
