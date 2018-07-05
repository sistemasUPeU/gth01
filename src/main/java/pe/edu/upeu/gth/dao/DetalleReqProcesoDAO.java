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
public class DetalleReqProcesoDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public DetalleReqProcesoDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public String insertDetalleReqProceso(Map<String, Object> drp) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_DETALLE_REQ_PROC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_PROCESO_SP", drp.get("IdProceso"));
        inParamMap.put("ES_REQ_PROCESO_SP", drp.get("EsReqProceso"));
        inParamMap.put("ID_DIRECCION_SP", drp.get("IdDireccion"));
        inParamMap.put("ID_DEPARTAMENTO_SP", drp.get("IdDepartamento"));
        inParamMap.put("ID_AREA_SP", drp.get("IdArea"));
        inParamMap.put("ID_REQUERIMIENTO_SP", drp.get("IdRequerimiento"));
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }
    
    public Boolean deleteDetalleReqProceso(String id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    /*STORED PROCEDURE USED IN THIS METHOD DOES NOT EXISTS IN DATABASE.
    public Boolean updateDetalleReqProceso(String IdDetalleReqProceso,String IdProceso,String EsReqProceso,String IdDireccion,String IdDepartamento,String IdArea,String IdRequerimiento) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_UPDATE_DETALLE_REQ_PROC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_DETALLE_PROCESO_SP", IdDetalleReqProceso);
        inParamMap.put("ID_PROCESO_SP", IdProceso);
        inParamMap.put("ES_REQ_PROCESO_SP", EsReqProceso);
        inParamMap.put("ID_DIRECCION_SP", IdDireccion);
        inParamMap.put("ID_DEPARTAMENTO_SP", IdDepartamento);
        inParamMap.put("ID_AREA_SP", IdArea);
        inParamMap.put("ID_REQUERIMIENTO_SP", IdRequerimiento);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (Boolean) simpleJdbcCallResult.get("ID_TABLE");
    }*/
    
    public List<Map<String,Object>> listDetalleReqProceso() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
