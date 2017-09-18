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
public class FuncionDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public FuncionDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_funciones() {
        sql = "SELECT f.ID_FUNCION,f.DE_FUNCION,f.ES_FUNCION,f.US_CREACION,"
                + "f.FE_CREACION,f.US_MODIF,f.FE_MODIF,f.ID_PUESTO, f.TI_FUNCION,"
                + "p.NO_PUESTO FROM RHTD_FUNCION f LEFT OUTER JOIN RHTR_PUESTO p ON("
                + "p.ID_PUESTO = f.ID_PUESTO)";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_fun_x_pu(String id_pu) {
        sql = "SELECT f.ID_FUNCION,f.DE_FUNCION,f.ES_FUNCION, f.TI_FUNCION,"
                + "p.ID_PUESTO,p.NO_PUESTO FROM RHTD_FUNCION f,RHTR_PUESTO p where "
                + "p.ID_PUESTO = f.ID_PUESTO and f.ID_PUESTO=?";
        return jt.queryForList(sql, id_pu.trim());
    }

    public void Insertar_funcion(String id_pu, String de_fu, String user_crea,
            String tipo_funcion) {
        jt.update("CALL RHSP_INSERT_FUNCION( ?, ?, ?, ?)", id_pu.trim(), 
                de_fu.trim(), user_crea.trim(), tipo_funcion.trim());
    }

    public boolean Modificar_funcion(String id_fun, String es_fun, String de_fun,
            String id_pu, String us_mod, String tipo_funcion) {
        return (jt.update("CALL RHSP_MOD_FUNCION( ?, ?, ?, ?, ?, ?)", id_fun.trim(),
                de_fun.trim(), es_fun.trim(), us_mod.trim(), id_pu.trim(),
                tipo_funcion.trim()) != 0);
    }
    
    public void Eliminar_funcion(String id_fun) {
        jt.update("CALL RHSP_ELIMINAR_FUNCION(?)", id_fun);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Funciones() {
        sql = "SELECT f.ID_FUNCION,f.DE_FUNCION,f.ES_FUNCION,f.US_CREACION,"
                + "f.FE_CREACION,f.US_MODIF,f.FE_MODIF,f.ID_PUESTO,p.NO_PUESTO, "
                + "f.TI_FUNCION FROM RHTD_FUNCION f LEFT OUTER JOIN RHTR_PUESTO p ON("
                + "p.ID_PUESTO = f.ID_PUESTO) ORDER BY f.DE_FUNCION ASC";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_dep_x_dir(String id_de) {
        sql = "select d.ID_DEPARTAMENTO, d.NO_DEP from rhtx_departamento d, "
                + "rhtx_direccion r where d.ID_DIRECCION=r.ID_DIRECCION and "
                + "d.ID_DIRECCION=?";
        return jt.queryForList(sql,id_de.trim());
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_ar_x_dep(String id_ar) {
        sql ="SELECT A.ID_AREA, A.NO_AREA FROM RHTD_AREA A, "
                + "RHTX_DEPARTAMENTO D WHERE "
                + "A.ID_DEPARTAMENTO= D.ID_DEPARTAMENTO AND "
                + "D.ID_DEPARTAMENTO=?";
        return jt.queryForList(sql,id_ar);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_sec_x_area(String id_se) {
        sql ="SELECT S.ID_SECCION, S.NO_SECCION FROM RHTD_AREA A, "
                + "RHTR_SECCION S WHERE S.ID_AREA= A.ID_AREA AND A.ID_AREA=?";
        return jt.queryForList(sql,id_se);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_pu_x_sec(String id_pu) {
        sql ="SELECT P.ID_PUESTO, P.NO_PUESTO FROM RHTR_PUESTO P, "
                + "RHTR_SECCION S WHERE S.ID_SECCION=P.ID_SECCION AND "
                + "P.ID_SECCION=?";
        return jt.queryForList(sql,id_pu);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_x_fun_x_idpu(String id_pu) {
        sql = "SELECT * FROM RHTD_FUNCION where ID_PUESTO=? AND ES_FUNCION='1'";
        return jt.queryForList(sql,id_pu.trim());
    }

}
