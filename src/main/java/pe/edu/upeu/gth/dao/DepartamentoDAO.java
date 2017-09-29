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
public class DepartamentoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public DepartamentoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_Departamento() {
        sql = "select * from rhtx_departamento ORDER BY NO_DEP";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> dep_id(String id_pu) {
        sql = "SELECT ID_DEPARTAMENTO FROM RHVD_PUESTO_DIRECCION where "
                + "ID_PUESTO=?";
        return jt.queryForList(sql, id_pu.trim());
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_dep_id(String id) {
        sql = "SELECT ID_DEPARTAMENTO, NO_DEP FROM "
                + "RHVD_PUESTO_DIRECCION WHERE "
                + "ID_DIRECCION=? GROUP BY "
                + "ID_DEPARTAMENTO, NO_DEP ORDER BY NO_DEP";
        return jt.queryForList(sql, id);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_Departamento_Lima() {
        sql = "SELECT NO_DEP FROM RHVD_PUESTO_DIRECCION where "
                + "ID_DIRECCION='DIR-0002' OR "
                + "ID_DIRECCION='DIR-0001' OR "
                + "ID_DIRECCION='DIR-0003' OR "
                + "ID_DIRECCION='DIR-0004' GROUP BY "
                + "NO_DEP ORDER by NO_DEP";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_dep_x_dir(String id_de) {
        sql = "select  d.NO_DEP, d.NO_CORTO_DEP, d.ES_DEPARTAMENTO, d.ID_DEPARTAMENTO\n"
                + "from rhtx_departamento d, rhtx_direccion r\n"
                + "where d.ID_DIRECCION=r.ID_DIRECCION and "
                + "d.ID_DIRECCION=? and r.ES_DIRECCION='1'";
        return jt.queryForList(sql, id_de.trim());
    }
    
    public boolean Editar_Dep(String id, String nombre, String ncorto, String estado, String idDir) {
        return (jt.update("CALL RHSP_MOD_DEPARTAMENTO( ?, ?, ?, ?,?)",id,
                nombre,ncorto,estado,idDir)==1);
    }
    
    public boolean Crear_Dep(String nombre, String ncorto, String estado, String idDir) {
        return (jt.update("CALL RHSP_INSERT_DEPARTAMENTO( ?, ?, ?, ?)",nombre,
                ncorto,estado,idDir)==1);
    }
    
    public boolean Eliminar_Dep(String id) {
        return (jt.update("CALL RHSP_ELIMINAR_DEPARTAMENTO(?)",id)==1);
    }
    
    public boolean Activar_Dep(String id) {
        return (jt.update("CALL RHSP_ACTIVAR_DEPARTAMENTO(?)",id)==1);
    }
    
    public boolean Desactivar_Dep(String id) {
        return (jt.update("CALL RHSP_DESACTIVAR_DEPARTAMENTO(?)",id)==1);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_dep_x_es(String idDir) {
        sql = "select  d.NO_DEP, d.NO_CORTO_DEP, d.ES_DEPARTAMENTO, "
                + "d.ID_DEPARTAMENTO from rhtx_departamento d, "
                + "rhtx_direccion where "
                + "d.ID_DIRECCION=r.ID_DIRECCION and "
                + "d.ID_DIRECCION=? and "
                + "r.ES_DIRECCION='1' and d.ES_DEPARTAMENTO='1'";
        return jt.queryForList(sql,idDir);
    }
}
