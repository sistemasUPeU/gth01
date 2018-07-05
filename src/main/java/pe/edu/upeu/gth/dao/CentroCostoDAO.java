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
public class CentroCostoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public CentroCostoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> listarCcosto() {
        sql = "SELECT * FROM RHVD_CENTRO_COSTO ";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> listarCcosto(String idCCosto) {
        sql = "SELECT * FROM RHVD_CENTRO_COSTO "
                + "where id_centro_costo=?";
        return jt.queryForList(sql, idCCosto);
    }

    public boolean crearCCosto(String CO_CENTRO_COSTO, String DE_CENTRO_COSTO, String ID_DEPARTAMENTO, String ID_AREA, String ID_SECCION) {
        boolean x = false;
        int i = jt.update("CALL RHSP_INSERT_CENTRO_COSTO( ?, ?, ?, ?, ?)", CO_CENTRO_COSTO, DE_CENTRO_COSTO, ID_DEPARTAMENTO, ID_AREA, ID_SECCION);
        if (i == 1) {
            x = true;
        }
        return x;
    }

    public boolean editarCCosto(String ID_CENTRO_COSTO, String CO_CENTRO_COSTO, String DE_CENTRO_COSTO, String ID_DEPARTAMENTO, String ID_AREA, String ID_SECCION, String id_det_cc) {
        boolean x = false;
        int i = jt.update("CALL RHSP_MOD_CENTRO_COSTO( ?, ?, ?, ?, ?, ?,?)", ID_CENTRO_COSTO, CO_CENTRO_COSTO, DE_CENTRO_COSTO, ID_DEPARTAMENTO, ID_AREA, ID_SECCION, id_det_cc);
        if (i == 1) {
            x = true;
        }
        return x;
    }

    public boolean eliminarCCosto(String ID_CENTRO_COSTO) {
        boolean x = false;
        int i = jt.update("CALL RHSP_ELIMINAR_CENTRO_COSTO(?)", ID_CENTRO_COSTO);
        if (i == 1) {
            x = true;
        }
        return x;
    }

    public boolean AsignarCentroCosto(String ID_CENTRO_COSTO, String id_departamento, String id_area, String id_seccion) {
        boolean x = true;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_DEPART_CENTRO_C");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_DEPART_CENTRO_COSTO_SP", null);
        inParamMap.put("ID_DEPARTAMENTO_SP", id_departamento);
        inParamMap.put("ID_AREA_SP", id_area);
        inParamMap.put("ID_SECCION_SP", id_seccion);
        inParamMap.put("ES_DEPART_CENTRO_COSTO_SP", null);
        inParamMap.put("ID_CENTRO_COSTO_SP", ID_CENTRO_COSTO);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        String id=(String) simpleJdbcCallResult.get("ID_TABLE");
        return x;
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_Direccion() {
        sql = "select id_direccion,no_direccion from rhtx_direccion where ES_DIRECCION='1' order by NO_DIRECCION";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_Depxdir(String iddep) {
        sql = "SELECT E.ID_DEPARTAMENTO, E.NO_DEP FROM RHTX_DEPARTAMENTO E, RHTX_DIRECCION I\n"
                    + "WHERE E.ID_DIRECCION=I.ID_DIRECCION AND E.ID_DIRECCION=?";
        return jt.queryForList(sql,iddep);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_Arxdep(String iddep) {
        sql = "SELECT A.ID_AREA,A.NO_AREA\n"
                    + "FROM RHTD_AREA A, RHTX_DEPARTAMENTO D\n"
                    + "WHERE A.ID_DEPARTAMENTO=D.ID_DEPARTAMENTO\n"
                    + "AND D.ID_DEPARTAMENTO=?";
        return jt.queryForList(sql,iddep);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_SecxArea(String idArea) {
        sql = "SELECT S.ID_SECCION, S.NO_SECCION\n"
                    + "FROM RHTR_SECCION S,RHTD_AREA R\n"
                    + "WHERE S.ID_AREA= R.ID_AREA\n"
                    + "AND R.ID_AREA=?";
        return jt.queryForList(sql,idArea);
    }
    
    
    
    
    /*Centro_CostoDAO WAS MERGED WITH THIS CLASS, ALL Centro_CostoDAO METHODS ARE BELOW*/
    
    
    
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_centro_costo(String iddep) {
        sql = "select ID_DEPART_CENTRO_COSTO,id_centro_costo, "
                + "CO_CENTRO_COSTO ||' -  '||DE_CENTRO_COSTO "
                + "as DE_CENTRO_COSTO from RHVD_CENTRO_COSTO where  "
                + "id_departamento=? ";
        return jt.queryForList(sql,iddep.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_centro_costo() {
        sql = "select ID_CENTRO_COSTO,CO_CENTRO_COSTO,ID_DEPARTAMENTO,CO_CENTRO_COSTO ||' -  '||DE_CENTRO_COSTO as DE_CENTRO_COSTO   from RHVD_CENTRO_COSTO order by DE_CENTRO_COSTO";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Direccion_CC() {
        sql = "select distinct(d.id_direccion) as id_direccion, "
                + "d.no_direccion from RHVD_CENTRO_COSTO cc, "
                + "rhtx_departamento dp , rhtx_direccion d "
                + "where dp.id_departamento = cc.id_departamento and "
                + "dp.id_direccion = d.id_direccion "
                + "ORDER BY d.NO_DIRECCION";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Departamento_CC(String iddir) {
        sql = "select  distinct (cc.id_departamento) as id_departamento,"
                + "dp.no_dep   from RHVD_CENTRO_COSTO cc, "
                + "rhtx_departamento dp , rhtx_direccion d "
                + "where dp.id_departamento = cc.id_departamento and "
                + "dp.id_direccion = d.id_direccion and "
                + "d.id_direccion=? ORDER BY dp.NO_DEP";
        return jt.queryForList(sql,iddir);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Centro_Costo_Dep(String iddep) {
        sql = "select  cc.ID_DEPART_CENTRO_COSTO,cc.id_centro_costo,"
                + "cc.CO_CENTRO_COSTO ||' -  '||cc.DE_CENTRO_COSTO as "
                + "DE_CENTRO_COSTO  from RHVD_CENTRO_COSTO cc, "
                + "rhtx_departamento dp , rhtx_direccion d "
                + "where dp.id_departamento = cc.id_departamento and "
                + "dp.id_direccion = d.id_direccion and "
                + "cc.id_departamento=? "
                + "ORDER BY cc.DE_CENTRO_COSTO";
        return jt.queryForList(sql,iddep);
    }
    
    public List<Map<String, Object>> List_centr_id(String id_dgp) {
        sql = "SELECT distinct(C.ID_CENTRO_COSTO) as id_centro ,"
                + "c.CO_CENTRO_COSTO ||' -  '||c.DE_CENTRO_COSTO as "
                + "DE_CENTRO_COSTO  ,(d.ID_DETALLE_CENTRO_COSTO)as "
                + "id_det_cen FROM RHVD_CENTRO_COSTO C, "
                + "RHTD_DETALLE_CENTRO_COSTO d where d."
                + "ID_DGP=? and "
                + "C.ID_CENTRO_COSTO=d.ID_CENTRO_COSTO";
        return jt.queryForList(sql,id_dgp.trim());
    }
    public void Mod_det_centro(String id_cent_cos, String id_contrato) {
        jt.update("CALL RHSP_MOD_DET_CEN_C_IDT(?, ? )",id_cent_cos,id_contrato);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> Lis_c_c_id_contr(String id_contrato) {
        sql = "SELECT c.ID_CENTRO_COSTO ,c.DE_CENTRO_COSTO ,"
                + "c.CO_CENTRO_COSTO, c.ID_DEPARTAMENTO, "
                + " d.CA_PORCENTAJE FROM RHVD_CENTRO_COSTO c,"
                + "RHTD_DETALLE_CENTRO_COSTO d "
                + " where d.ID_DEPART_CENTRO_COSTO=c.ID_DEPART_CENTRO_COSTO "
                + "and  d.ID_CONTRATO=?";
        return jt.queryForList(sql,id_contrato.trim());
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Cargar_cc_dgp(String id) {
        sql = "select  CC.ID_DGP , cc.ID_CENTRO_COSTO,d.ID_DIRECCION,"
                + "c.ID_DEPARTAMENTO,c.ID_AREA,cc.CA_PORCENTAJE,"
                + "c.DE_CENTRO_COSTO from RHTD_DETALLE_CENTRO_COSTO cc, "
                + "RHVD_CENTRO_COSTO c , RHTX_DIRECCION d , "
                + "RHTX_DEPARTAMENTO dp where "
                + "cc.ID_CENTRO_COSTO = c.ID_CENTRO_COSTO and "
                + "dp.ID_DEPARTAMENTO = c.ID_DEPARTAMENTO and "
                + "cc.ES_DETALLE_CC='1'  and "
                + "dp.ID_DIRECCION = d.ID_DIRECCION and "
                + "id_contrato=?";
        return jt.queryForList(sql,id);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> listar_cc_area(String id) {
        sql = " select ID_DEPART_CENTRO_COSTO,id_centro_costo,"
                + "CO_CENTRO_COSTO,DE_CENTRO_COSTO  from "
                + "RHVD_CENTRO_COSTO where ID_AREA=?";
                return jt.queryForList(sql,id);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> listar_cc_seccion(String id) {
        sql = "select ID_DEPART_CENTRO_COSTO, ID_CENTRO_COSTO,"
                + "CO_CENTRO_COSTO,DE_CENTRO_COSTO  from "
                + "RHVD_CENTRO_COSTO where ID_SECCION=?";
        return jt.queryForList(sql,id);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> Lis_c_c_id_dgp(String id_dgp) {
        sql = "SELECT c.ID_CENTRO_COSTO ,c.DE_CENTRO_COSTO ,"
                + "c.CO_CENTRO_COSTO, c.ID_DEPARTAMENTO, "
                + "d.CA_PORCENTAJE FROM RHVD_CENTRO_COSTO c,"
                + "RHTD_DETALLE_CENTRO_COSTO d where "
                + "d.ID_CENTRO_COSTO=c.ID_CENTRO_COSTO and "
                + "d.ID_DGP =?";
        return jt.queryForList(sql,id_dgp.trim());
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> Cargar_dcc_dgp(String id) {
        sql = "select  distinct(c.CO_CENTRO_COSTO) ,  CC.ID_DGP, "
                + "cc.ID_CENTRO_COSTO,d.ID_DIRECCION,c.ID_DEPARTAMENTO,"
                + "c.ID_AREA,cc.CA_PORCENTAJE,c.DE_CENTRO_COSTO from "
                + "RHTD_DETALLE_CENTRO_COSTO cc , RHVD_CENTRO_COSTO c ,"
                + " RHTX_DIRECCION d , RHTX_DEPARTAMENTO dp where "
                + "cc.ID_DEPART_CENTRO_COSTO = c.ID_DEPART_CENTRO_COSTO  "
                + "and dp.ID_DEPARTAMENTO = c.ID_DEPARTAMENTO and "
                + "cc.ES_DETALLE_CC='1'  and dp.ID_DIRECCION = d.ID_DIRECCION "
                + "and cc.id_dgp=?";
        return jt.queryForList(sql,id);
    }
    
    public List<String> list_cc_x_con(String id_con) {
        sql = "SELECT (DC.ID_DETALLE_CENTRO_COSTO||'/'||CC.ID_DEPARTAMENTO||'/'||pu.ID_DIRECCION)as "
                + "centro_costos FROM RHVD_CENTRO_COSTO CC,"
                + "RHTD_DETALLE_CENTRO_COSTO DC,"
                + "RHVD_PUESTO_DIRECCION pu WHERE "
                + "DC.ID_CENTRO_COSTO = CC.ID_CENTRO_COSTO and "
                + "pu.ID_DEPARTAMENTO = CC.ID_DEPARTAMENTO AND "
                + "DC.ID_CONTRATO=? GROUP by "
                + "(DC.ID_DETALLE_CENTRO_COSTO||'/'||CC.ID_DEPARTAMENTO||'/'||"
                + "pu.ID_DIRECCION)";
        return jt.queryForList(sql,String.class,id_con.trim());
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> listCentroCostoByIdContrato(String id_con) {
        /*sql = "SELECT (c.ID_DEPART_CENTRO_COSTO)as id_centro ,(c.CO_CENTRO_COSTO||' - '||c.DE_CENTRO_COSTO)as DE_CENTRO_COSTO,c.CO_CENTRO_COSTO,c.id_area,c.id_seccion, c.ID_DEPARTAMENTO, d.CA_PORCENTAJE,pd.ID_DIRECCION,"
                    + "(d.ID_DETALLE_CENTRO_COSTO)as id_det_cen FROM RHVD_CENTRO_COSTO c,RHTD_DETALLE_CENTRO_COSTO d,RHVD_PUESTO_DIRECCION pd where pd.ID_DEPARTAMENTO=c.ID_DEPARTAMENTO"
                    + " and d.ID_CENTRO_COSTO=c.ID_CENTRO_COSTO and  d.ID_CONTRATO='" + id_con.trim() + "' ";*/
        sql = "SELECT (c.ID_DEPART_CENTRO_COSTO)AS id_centro , "
                + "c.id_area,c.id_seccion,c.ID_DIRECCION,"
                + "(c.CO_CENTRO_COSTO||' - ' ||c.DE_CENTRO_COSTO)AS "
                + "DE_CENTRO_COSTO, c.CO_CENTRO_COSTO, "
                + "c.ID_DEPARTAMENTO,d.CA_PORCENTAJE, "
                + "(d.ID_DETALLE_CENTRO_COSTO)AS id_det_cen FROM "
                + "RHVD_CENTRO_COSTO c, RHTD_DETALLE_CENTRO_COSTO d "
                + "WHERE d.ID_DEPART_CENTRO_COSTO   =c.ID_DEPART_CENTRO_COSTO "
                + "AND d.ID_CONTRATO=?";
        return jt.queryForList(sql,id_con);
    }
    public int count_cc_x_id_cont(String id_con) {
        sql = "SELECT COUNT(*) FROM RHTD_DETALLE_CENTRO_COSTO "
                + "WHERE ID_CONTRATO=?";
        return jt.queryForObject(sql, Integer.class,id_con.trim());
    }
    public void Eliminar_dcc(String id_dcc) {
        jt.update("CALL RHSP_ELIMINAR_DET_CC(?)",id_dcc.trim());
    }
    
    public List<Map<String, Object>> List_centr_iddgp(String id_dgp) {
        sql = "SELECT (c.ID_DEPART_CENTRO_COSTO)AS id_centro, "
                + "c.id_area,c.id_seccion,c.ID_DIRECCION,"
                + "(c.CO_CENTRO_COSTO||' - ' ||c.DE_CENTRO_COSTO)AS "
                + "DE_CENTRO_COSTO, c.CO_CENTRO_COSTO, "
                + "c.ID_DEPARTAMENTO,d.CA_PORCENTAJE, "
                + "(d.ID_DETALLE_CENTRO_COSTO)AS id_det_cen FROM "
                + "RHVD_CENTRO_COSTO c, RHTD_DETALLE_CENTRO_COSTO d "
                + "WHERE d.ID_DEPART_CENTRO_COSTO=c.ID_DEPART_CENTRO_COSTO AND "
                + "d.ID_DGP=?";
        return jt.queryForList(sql,id_dgp);
    }
}
