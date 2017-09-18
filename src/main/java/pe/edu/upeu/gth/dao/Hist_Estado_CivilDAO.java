/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Cesar
 */
public class Hist_Estado_CivilDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public Hist_Estado_CivilDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    
    public void INSERT_HIST_ESTADO_CIVIL(String ID_ESTADO_CIVIL, 
            String LI_ESTADO_CIVIL, String FE_MODIFICACION, String US_MODIFICACION, 
            String ID_TRABAJADOR , String ES_REGISTRO) {
        jt.update("CALL RHSP_INSERT_ESTADO_CIVIL( ?, ?, ?, ?, ?, ?)",null,
                LI_ESTADO_CIVIL,FE_MODIFICACION,US_MODIFICACION,ID_TRABAJADOR,ES_REGISTRO);
    }
}
