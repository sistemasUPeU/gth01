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
public class DireccionDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public DireccionDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Direccion() {
        sql = "select * from RHTX_DIRECCION where id_filial = '1' order by NO_DIRECCION";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_zona() {
        sql = " select * from RHTX_ZONA";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Direccion() {
        sql = "select * from rhtx_direccion order by NO_DIRECCION";
        return jt.queryForList(sql);
    }

    public boolean Editar_Direccion(String id, String nombre, String ncorto, String estado, String filial) {
        return (jt.update("CALL RHSP_MOD_DIRECCION( ?, ?, ?, ?, ?)",
                id, nombre, ncorto, estado, filial) != 0);
    }

    public boolean Crear_Direccion(String nombre, String ncorto, String estado, String filial) {
        return (jt.update("CALL RHSP_INSERT_DIRECCION( ?, ?, ?, ?)",
                nombre, ncorto, estado, filial) != 0);
    }

    public boolean Eliminar_Direccion(String id) {
        return (jt.update("CALL RHSP_ELIMINAR_DIRECCION(?)", id) != 0);
    }
    
    public boolean Activar_Direccion(String id) {
        return (jt.update("CALL RHSP_ACTIVAR_DIRECCION(?)", id) != 0);
    }
    
    public boolean Desactivar_Direccion(String id) {
        return (jt.update("CALL RHSP_DESACTIVAR_DIRECCION(?)", id) != 0);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Direccion_estado() {
        sql = "select * from rhtx_direccion where ES_DIRECCION='1' order by NO_DIRECCION";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Direccion_filial(String fil) {
        sql = "select * from rhtx_direccion where id_filial=? order by NO_DIRECCION";
        return jt.queryForList(sql,fil);
    }
    
    
}
