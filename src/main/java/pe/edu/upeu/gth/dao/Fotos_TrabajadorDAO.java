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
public class Fotos_TrabajadorDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Fotos_TrabajadorDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public String List_id_Fotos_Trabajador(String idtr) {
        sql = "select id_foto from rhtr_fotos_trabajador where id_trabajador=?";
        return jt.queryForObject(sql, String.class, idtr);
    }

    public void INSERT_FOTOS_TRABAJADOR(String ID_FOTO, String DE_FOTO, String AR_FOTO, String NO_AR_FOTO, String TA_AR_FOTO, String TI_AR_FOTO, String ID_TRABAJADOR) {
        jt.update("CALL RHSP_INSERT_FOTOS_TRABAJADOR( ?, ?, ?, ?, ?, ?, ?)", null,
                DE_FOTO, AR_FOTO, NO_AR_FOTO, TA_AR_FOTO, TI_AR_FOTO, ID_TRABAJADOR);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Fotos_usuario(String idtr, String tipo) {
        String tabla = "";
        if (tipo.equals("todo")) {
            tabla = "rhth_fotos_trabajador";
        } else {
            tabla = "rhtr_fotos_trabajador";
        }
        sql = "select * from " + tabla + " where id_trabajador= ?";
        return jt.queryForList(sql, idtr);
    }

}
