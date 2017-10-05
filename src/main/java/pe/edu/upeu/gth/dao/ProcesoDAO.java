package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class ProcesoDAO {

	JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public ProcesoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Proceso() {
        sql = "select * from rhtv_proceso where es_proceso='1'";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Pro_Paso_Id(String id_req,
            String id_pro, String id_dir, String id_dep,
            String id_area, String id_ti_planilla) {
        sql = "select  *  from rhvd_requerimiento_proceso "
                + "where ES_REQ_PROCESO is not null ";
        if (id_req != null) {
            sql += (!"".equals(id_req)) ? " and  ID_REQUERIMIENTO = '" + id_req + "'" : "";
        }
        if (id_pro != null) {
            sql += (!"".equals(id_pro)) ? " and  ID_PROCESO = '" + id_pro + "'" : "";
        }
        if (id_dir != null) {
            sql += (!"".equals(id_dir)) ? " and  ID_DIRECCION = '" + id_dir + "'" : "";
        }
        if (id_dep != null) {
            sql += (!"".equals(id_dep)) ? " and  ID_DEPARTAMENTO = '" + id_dep + "'" : "";
        }
        if (id_area != null) {
            sql += (!"".equals(id_area)) ? " and  ID_AREA = '" + id_area + "'" : "";
        }
        if (id_ti_planilla != null) {
            sql += (!"".equals(id_ti_planilla)) ? " and  ID_TIPO_PLANILLA = '" + id_ti_planilla + "'" : "";
        }
        return jt.queryForList(sql);
    }

    public void statupdate(String id, String es) {
        sql = "CALL RHSP_UPDATE_ES_PROCESO(?,?)";
        jt.update(sql, id, es);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_AllProceso() {
        sql = "select * from rhtv_proceso";
        return jt.queryForList(sql);
    }

    public void editprocess(String id, String nom, String desc) {
        sql = "CALL RHSP_UPDATE_PROCESO(?,?,?)";
        jt.update(sql, id, nom, desc);
    }

    public void create(String nom, String desc) {
        sql = "CALL RHSP_INSERT_PROCESO(?,?)";
        jt.update(sql, nom, desc);
    }
	
}
