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
public class PasoDAO {
    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public PasoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
    
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Paso_Habilitado(String Proceso) {
        sql = "select * from rhtc_pasos where  es_pasos='1'";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Paso_x_Puesto(String id) {
        sql = "select pd.* ,p.ID_PASOS,p.ID_DETALLE_PASOS,"
                    + "p.ES_DETALLE_PASOS from RHTD_DETALLE_PASOS p, "
                    + "RHVD_PUESTO_DIRECCION pd where "
                    + "pd.ID_PUESTO = p.ID_PUESTO  and p.ID_PASOS=?";
        return jt.queryForList(sql,id);
    }
    
    public void INSERT_PASOS(String ID_PASOS, String ID_PROCESO, String DE_PASOS, 
            String NU_PASOS, String CO_PASOS) {
        jt.update("CALL RHSP_INSERT_PASOS(?, ?, ?, ?, ? )",null,ID_PROCESO,DE_PASOS,
                NU_PASOS,CO_PASOS);
    }
    
    public void UPDATE_PASOS(String ID_PASOS, String ID_PROCESO, String DE_PASOS, 
            String NU_PASOS, String CO_PASOS, String ES_PASO) {
        jt.update("CALL RHSP_UPDATE_PASOS(?, ?, ?, ?, ? ,?)",ID_PASOS,ID_PROCESO,
                DE_PASOS,NU_PASOS,CO_PASOS, ES_PASO);
    }
    
    public void DELETE_PASOS(String ID_PASOS) {
        sql = "DELETE FROM RHTC_PASOS WHERE ID_PASOS=?";
        jt.update(sql,ID_PASOS.trim());
    }
    
    public void DELETE_PUESTO_PASO(String IDDP) {
        jt.update("CALL DELETE_PUESTO_PASO(?)",IDDP);
    }
    
    public void UPDATE_NU_PASO(String ID_PASO, String NU_PASO) {
        jt.update("CALL UPDATE_PASOS_PROCESO(?, ?)",NU_PASO,ID_PASO);
    }
    
    public void ESTADO_DETALLE_PUESTO(String ID, String ESTADO) {
        jt.update("CALL RHSP_ESTADO_DETALLE_PUESTO(?, ?)",ID,Integer.parseInt(ESTADO));
    }
    
}
