/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Cesar
 */
public class SeccionDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public SeccionDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    
    public Map<String,Object> getSecctionById(String id) {
        sql = "select * from RHTR_SECCION where id_seccion='" + id + "'";
        return jt.queryForMap(sql, id);
    }
}
