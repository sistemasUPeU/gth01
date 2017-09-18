/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.HashMap;
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
public class Detalle_Centro_Costo_DAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public Detalle_Centro_Costo_DAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    
    public String INSERT_DETALLE_CENTRO_COSTO(String ID_DETALLE_CENTRO_COSTO,
          //  String ID_CENTRO_COSTO, 
            String ID_DGP, double CA_PORCENTAJE, String ES_DETALLE_CC, 
            String US_CREACION, String FE_CREACION, String US_MODIF, 
            String FE_MODIF, String IP_USUARIO, String ID_CONTRATO, 
            String ID_DEPART_CENTRO_COSTO) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_DETALLE_CC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_DETALLE_CENTRO_COSTO_SP", null);
        //inParamMap.put("ID_CENTRO_COSTO_SP", ID_CENTRO_COSTO);
        inParamMap.put("ID_DGP_SP", ID_DGP);
        inParamMap.put("CA_PORCENTAJE_SP", CA_PORCENTAJE);
        inParamMap.put("ES_DETALLE_CC_SP", ES_DETALLE_CC);
        inParamMap.put("US_CREACION_SP", US_CREACION);
        inParamMap.put("FE_CREACION_SP", FE_CREACION);
        inParamMap.put("US_MODIF_SP", US_MODIF);
        inParamMap.put("FE_MODIF_SP", FE_MODIF);
        inParamMap.put("IP_USUARIO_SP", IP_USUARIO);
        inParamMap.put("ID_CONTRATO_SP", ID_CONTRATO);
        inParamMap.put("ID_DEPART_CENTRO_COSTO_SP", ID_DEPART_CENTRO_COSTO);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }
    
    public void Modificar_Centro_Costo_porc(String id_dcc, Double porc, String id_user) {
        jt.update("CALL RHSP_MOD_POR_D_CEN_C( ?, ?, ?)", id_dcc.trim(), porc, id_user.trim());
    }
}
