/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

/**
 *
 * @author Cesar
 */
public class HorarioDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public HorarioDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public String Max_id_Detalle_Horario() {
        sql = "SELECT 'DHO-'||MAX (SUBSTR(ID_DETALLE_HORARIO,5,10)) FROM RHTD_DETALLE_HORARIO";
        return jt.queryForObject(sql, String.class);
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_V_Horario(String iddgp) {
        sql = "select * from rhvd_horario where id_dgp=? order by id_horario asc";
        return jt.queryForList(sql, iddgp);
    }

    public void Insert_Horario(String ID_HOR, String HO_DESDE, String HO_HASTA, String DIA_HOR, String ES_HOR, String ID_DET_HOR) {
        jt.update("CALL RHSP_INSERT_HORARIO( ?, ?, ?, ?, ?, ?)", null, HO_DESDE,
                HO_HASTA, DIA_HOR, ES_HOR, ID_DET_HOR.trim());
    }

    public String Insert_Detalle_Horario(String ID_DGP,
            String ES_DE_HOR, String US_CRE, String FE_CRE, String US_MODIF,
            String FE_MODIF, String ID_TIPO_HORARIO, String ES_MOD_FORMATO,
            Double ca_h_total) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_PROCESO_CARGA_AC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_PROCESO_CARGA_AC_SP", null);
        inParamMap.put("ID_DGP_SP", ID_DGP);
        inParamMap.put("ES_DETALLE_HORARIO_SP", ES_DE_HOR);
        inParamMap.put("US_CREACION_SP", US_CRE);
        inParamMap.put("FE_CREACION_SP", FE_CRE);
        inParamMap.put("US_MODIFICACION_SP", US_MODIF);
        inParamMap.put("FE_MODIFICACION_SP", FE_MODIF);
        inParamMap.put("ID_TIPO_HORARIO_SP", ID_TIPO_HORARIO);
        inParamMap.put("ES_MOD_FORMATO_SP", ES_MOD_FORMATO);
        inParamMap.put("CA_HORAS_TOTAL_SP", ca_h_total);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }
    
    public String id_det_horario(String id_dgp) {
        sql = "SELECT ID_TIPO_HORARIO FROM RHTD_DETALLE_HORARIO WHERE ID_DGP=?";
        return jt.queryForObject(sql, String.class,id_dgp.trim());
    }
    
    public String id_detalle_horario(String id_dgp) {
        sql = "SELECT ID_DETALLE_HORARIO FROM RHTD_DETALLE_HORARIO WHERE ID_DGP=?";
        return jt.queryForObject(sql, String.class,id_dgp.trim());
    }
    
    public void ELIMINAR_HORARIO(String ID_DET_HORARIO) {
        jt.update("CALL RHSP_ELIMINAR_DET_HORARIO(?)",ID_DET_HORARIO.trim());
    }
    
    public void modificar_horario(String ho_desde, String ho_hasta, String id_horario) {
        jt.update("CALL RHSP_MODIFICAR_HORARIO(?,?,?)",id_horario,ho_desde,ho_hasta);
    }
    
    public String Insert_Det_Hor_Casos_Esp(String ID_DET_HOR, String ID_DGP, 
            String ES_DE_HOR, String US_CRE, String FE_CRE, String US_MODIF, 
            String FE_MODIF, String ID_TIPO_HORARIO, String ES_MOD_FORMATO, 
            Double ca_h_total) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_PROCESO_CARGA_AC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_PROCESO_CARGA_AC_SP", null);
        inParamMap.put("ID_DGP_SP", ID_DGP);
        inParamMap.put("ES_DETALLE_HORARIO_SP", ES_DE_HOR);
        inParamMap.put("US_CREACION_SP", US_CRE);
        inParamMap.put("FE_CREACION_SP", FE_CRE);
        inParamMap.put("US_MODIFICACION_SP", US_MODIF);
        inParamMap.put("FE_MODIFICACION_SP", FE_MODIF);
        inParamMap.put("ID_TIPO_HORARIO_SP", ID_TIPO_HORARIO);
        inParamMap.put("ES_MOD_FORMATO_SP", ES_MOD_FORMATO);
        inParamMap.put("CA_HORAS_TOTAL_SP", ca_h_total);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }

}
