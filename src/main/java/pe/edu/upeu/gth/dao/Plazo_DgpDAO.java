/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import pe.edu.upeu.gth.util.DateFormat;

/**
 *
 * @author Cesar
 */
public class Plazo_DgpDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Plazo_DgpDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Plazo(String tipo) {
        sql = "select id_plazo,no_plazo,det_alerta ,to_char(fe_desde,'dd-mm-yyyy')  as fe_desde ,"
                + " to_char(fe_hasta,'dd-mm-yyyy')  as fe_hasta,TO_CHAR(fe_hasta,'MONTH','nls_date_language=spanish')  as mes,"
                + " sysdate+CA_DIAS_TOLERANCIA as fe_tol, CA_DIAS_TOLERANCIA   from rhtr_plazo where es_plazo ='1' "
                + " and id_requerimiento='0' and SYSDATE BETWEEN FE_DESDE AND FE_HASTA+1 ";
        switch (tipo) {
            case "1":
                sql += " and ti_plazo='1'";
                break;
            case "2":
                sql += " and ti_plazo='2'";
                break;
        }
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Plazo(String tipo, String req, int dias,
            String dep, String id_dep, String id_area) {
        sql = "select *  from rhvd_plazo where no_plazo is not null";
        sql += (!tipo.equals("")) ? " AND  ti_plazo  = '" + tipo.trim() + "'" : "";
        sql += (!tipo.equals("")) ? " AND  id_requerimiento  = '" + req.trim() + "'" : "";
        sql += (!dep.equals("")) ? " AND  ID_DEPARTAMENTO_TOLERANCIA  = '" + dep.trim() + "'" : "";
        sql += (!id_dep.equals("")) ? " AND  ID_DEPARTAMENTO = '" + id_dep.trim() + "'" : "";
        sql += (!id_area.equals("")) ? " AND  ID_AREA= '" + id_area.trim() + "'" : "";
        return jt.queryForList(sql);
    }

    public String INSERT_PLAZO(String ID_PLAZO, String NO_PLAZO, String DET_ALERTA,
            String FE_DESDE, String FE_HASTA, String ES_PLAZO,
            String ID_REQUERIMIENTO, String TI_PLAZO, int CA_DIAS_TOLERANCIA,
            String ID_DEPARTAMENTO_TOLERANCIA, String ID_DEPARTAMENTO,
            String ID_AREA) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt)
                .withProcedureName("CALL RHSP_INSERT_PLAZO( ?, ?, ?, ?, ?, ?, ?, ?, "
                        + "?, ?,? ,?,?)");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        try {
            inParamMap.put("ID_PLAZO_SP", null);
            inParamMap.put("NO_PLAZO_SP", NO_PLAZO);
            inParamMap.put("DET_ALERTA_SP", DET_ALERTA);
            inParamMap.put("FE_DESDE_SP", DateFormat.toFormat1(FE_DESDE));
            inParamMap.put("FE_HASTA_SP", DateFormat.toFormat1(FE_HASTA));
            inParamMap.put("ES_PLAZO_SP", ES_PLAZO);
            inParamMap.put("ID_REQUERIMIENTO_SP", ID_REQUERIMIENTO);
            inParamMap.put("TI_PLAZO_SP", TI_PLAZO);
            inParamMap.put("CA_DIAS_TOLERANCIA_SP", CA_DIAS_TOLERANCIA);
            inParamMap.put("ID_DEPARTAMENTO_TOLERANCIA_SP", ID_DEPARTAMENTO_TOLERANCIA);
            inParamMap.put("ID_DEPARTAMENTO_SP", ID_DEPARTAMENTO);
            inParamMap.put("ID_AREA_SP", ID_AREA);
        } catch (ParseException ex) {
            Logger.getLogger(Plazo_DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }

    public void UPDATE_PLAZO(String ID_PLAZO, String NO_PLAZO, String DET_ALERTA,
            String FE_DESDE, String FE_HASTA, String ES_PLAZO,
            String ID_REQUERIMIENTO, String TI_PLAZO, int CA_DIAS_TOLERANCIA,
            String ID_DEPARTAMENTO_TOLERANCIA, String ID_DEPARTAMENTO,
            String ID_AREA) {
        try {
            jt.update("CALL RHSP_UPDATE_PLAZO( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    ID_PLAZO, NO_PLAZO, DET_ALERTA, DateFormat.toFormat1(FE_DESDE),
                    DateFormat.toFormat1(FE_HASTA), ES_PLAZO, ID_DEPARTAMENTO,
                    TI_PLAZO, CA_DIAS_TOLERANCIA, ID_DEPARTAMENTO_TOLERANCIA,
                    ID_DEPARTAMENTO, ID_AREA);
        } catch (ParseException ex) {
            Logger.getLogger(Plazo_DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void REMOVE_PLAZO(String ID_PLAZO) {
        sql = "DELETE FROM RHTR_PLAZO WHERE ID_PLAZO=?";
        jt.update(sql, ID_PLAZO.trim());
    }

    /*CHECK AND REPLACE USE OF THIS METHOD (DTO OBJECTS WERE REMOVED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Lista_detalle_plazo(String id) {
        sql = "select * from rhvd_dgp_plazo_1 where id_dgp=?";
        return jt.queryForList(sql, id.trim());
    }

    public String fecha_maxima_plazo() {
        sql = "select TO_CHAR(sysdate+CA_DIAS_TOLERANCIA ,'yyyy-mm-dd',"
                + "'nls_date_language=spanish') from RHTR_PLAZO where "
                + "TI_PLAZO='2' and ES_PLAZO ='1'";
        return jt.queryForObject(sql, String.class);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Plazo_id(String id_tipo, String iddgp) {
        sql = "select ID_PLAZO, no_plazo || ' ('|| to_char(to_date("
                + "fe_desde,'yyyy-mm-dd'),'dd/mm/yyyy') || ' - '|| "
                + "to_char(to_date(fe_hasta,'yyyy-mm-dd'),'dd/mm/yyyy')||') ' "
                + "as nombre  from RHVD_PLAZO where "
                + "ti_plazo=? and id_plazo not in "
                + "(select  id_plazo  from RHTD_SOLICITUD_DGP where "
                + "ID_DGP=?) order by to_number(substr("
                + "id_plazo,5,length(id_plazo))) desc";
        return jt.queryForList(sql, id_tipo.trim(), iddgp);
    }

    public void validar_Vig_plazos() {
        String id = "";
        Double dia = 0.0;
        Double mes = 0.0;
        Double anno = 0.0;
        sql = "SELECT id_plazo,(extract (month from FE_HASTA) - "
                + "extract(month from sysdate))as meses_con,(extract (day "
                + "from FE_HASTA) - extract(day from sysdate))as dia_con,("
                + "extract (year from FE_HASTA) - extract(year from sysdate))"
                + "as anno_con FROM RHTR_PLAZO";
        Map<String, Object> mp = jt.queryForMap(sql);
        id = (String) mp.get("id_plazo");
        dia = (Double) mp.get("dia_con");
        mes = (Double) mp.get("meses_con");
        anno = (Double) mp.get("anno_con");
        if (anno < 0) {
            jt.update("CALL RHSP_DESHABI_PLAZO(?)", id);
        } else if (anno == 0) {
            if (mes < 0) {
                jt.update("CALL RHSP_DESHABI_PLAZO(?)", id);
            } else if (mes == 0) {
                if (dia < 0) {
                    jt.update("CALL RHSP_DESHABI_PLAZO(?)", id);
                } else if (dia == 0 || dia > 0) {

                }
            } else if (mes > 0) {

            }
        } else if (anno > 0) {

        }
    }

    public void Val_Es_Plazo() {
        jt.update("CALL RHSP_VAL_ESTADO_PLAZO()");
    }

    public String HABILITAR_FECHA(String tipo, String req, String dia, String dep) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("rhfu_fecha_habilitada(?,?,?,?)");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("tipo", tipo);
        inParamMap.put("req", req);
        inParamMap.put("dias", dia);
        inParamMap.put("dep", dep);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("fecha");
    }

    public void Validar_Cumplimiento_plazo_t2() {
        //Comentarios extraídos de la versión original del sistema//
        String id_plazo = "";
        String id_cum_plazo = "";
        String id_dgp = "";
        String estado = "";
        //Busco los CUMPLIMIENTOS DE DGP DE PLAZO  TIPO 2
        sql = "SELECT pl.ID_PLAZO,cp.ID_CUMPLIMIENTO_PLAZO ,cp.ID_DGP,"
                + "pl.FE_DESDE,pl.FE_HASTA,cp.ES_CUMPLE_PLAZO FROM "
                + "RHTR_CUMPLIMIENTO_PLAZO cp, RHTR_PLAZO pl WHERE "
                + "pl.ID_PLAZO = cp.ID_PLAZO and pl.TI_PLAZO='2' AND "
                + "pl.ES_PLAZO='1'";
        List<Map<String, Object>> list = jt.queryForList(sql);
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> mp = list.get(i);
            id_plazo = (String) mp.get("ID_PLAZO");
            id_cum_plazo = (String) mp.get("ID_CUMPLIMIENTO_PLAZO");
            id_dgp = (String) mp.get("ID_DGP");
            estado = (String) mp.get("ES_CUMPLE_PLAZO");
            if (estado.trim().equals("0")) {

            } // ELEGIR Los DGP cuyo cumplimiento esta ok
            else if (estado.trim().equals("1")) {
                String dep = "";
                int cont = 0;
                int cont2 = 0;
                //ELEGIR LOS DGP EN PROCESO
                sql = "SELECT COUNT(*) FROM RHVD_USER_AUT u, "
                        + "RHTC_PASOS p, RHVD_USUARIO du WHERE "
                        + "u.ID_EMPLEADO=du.ID_EMPLEADO AND "
                        + "u.ID_PASOS=p.ID_PASOS AND "
                        + "u.ID_DGP =? AND TRIM(u.ID_PUESTO)<>'0'";
                cont = jt.queryForObject(sql, Integer.class, id_dgp.trim());
                //SI ESTA EN PROCESO
                if (cont > 0) {
                    sql = "SELECT u.ID_TRABAJADOR, u.ID_ROL, "
                            + "u.ID_DEPARTAMENTO, du.NO_TRABAJADOR,"
                            + "du.AP_PATERNO,du.AP_MATERNO, p.DE_PASOS AS paso "
                            + "FROM RHVD_USER_AUT u, RHTC_PASOS p, "
                            + "RHVD_USUARIO du WHERE u.ID_EMPLEADO="
                            + "du.ID_EMPLEADO AND u.ID_PASOS = p.ID_PASOS AND "
                            + "u.ID_DGP =? AND TRIM(u.ID_PUESTO)<>'0'";
                    List<Map<String, Object>> list2 = jt.queryForList(sql, id_dgp.trim());
                    for (int j = 0; j < list2.size(); j++) {
                        dep = (String) list2.get(j).get("ID_DEPARTAMENTO");
                        if (dep.trim().equals("DPT-0019")) {
                            //valida la llegada a rrhh
                            cont2++;
                        }
                    }// EXISTEN USUARIOS PROXIMOS A RRHH
                    if (cont == cont2) {
                        jt.update("CALL VAL_CUMPLE_PLAZO( ?, ?, ?)", 1, id_plazo, id_cum_plazo);
                    } //NO EXISTE NINGUN USUARIO PROXIMO A RRHH
                    else if (cont2 == 0) {
                        System.out.print("OK" + cont2);
                        //jt.update("CALL VAL_CUMPLE_PLAZO( ?, ?, ?)",0,id_plazo,id_cum_plazo);
                    }
                }//caso contrario no existe ningun usuario proximo a autorizar (no esta en proceso)
                else if (cont == 0) {
                    jt.update("CALL VAL_CUMPLE_PLAZO( ?, ?, ?)", 0, id_plazo, id_cum_plazo);
                }
            } else if (estado.trim().equals("2")) {

            }
        }
    }

    public void Validar_Cumplimiento_plazo_t1() {
        String id = "";
        String id_cumplimiento_plazo = "";
        String es_cumple_plazo = "";
        Double dia = 0.0;
        Double mes = 0.0;
        Double ano = 0.0;
        sql = "SELECT pl.ID_PLAZO,cp.ID_CUMPLIMIENTO_PLAZO,cp.ID_DGP,"
                + "pl.FE_DESDE,(extract (month from pl.FE_HASTA) - "
                + "extract(month from sysdate))as meses_con,(extract (day "
                + "from pl.FE_HASTA) - extract(day from sysdate))as dia_con,("
                + "extract (year from pl.FE_HASTA) - extract(year from sysdate))"
                + "as anno_con,cp.ES_CUMPLE_PLAZO FROM RHTR_CUMPLIMIENTO_PLAZO "
                + "cp, RHTR_PLAZO pl WHERE pl.ID_PLAZO = cp.ID_PLAZO and "
                + "pl.TI_PLAZO='1' AND pl.ES_PLAZO='1'";
        List<Map<String, Object>> list = jt.queryForList(sql);
        for (int i = 0; i < list.size(); i++) {
            id = (String) list.get(i).get("ID_PLAZO");
            id_cumplimiento_plazo = (String) list.get(i).get("ID_CUMPLIMIENTO_PLAZO");
            mes = (Double) list.get(i).get("MESES_CON");
            dia = (Double) list.get(i).get("DIA_CON");
            ano = (Double) list.get(i).get("ANNO_CON");
            es_cumple_plazo = (String) list.get(i).get("ES_CUMPLE_PLAZO");
            if (es_cumple_plazo.equals("1")) {
                if (ano < 0) {
                    jt.update("CALL RHSP_DESHABI_CUMPL_PLAZO(?)", id_cumplimiento_plazo);
                } else if (ano == 0) {
                    if (mes < 0) {
                        jt.update("CALL RHSP_DESHABI_CUMPL_PLAZO(?)", id_cumplimiento_plazo);
                    } else if (mes == 0) {
                        if (dia < 0) {
                            jt.update("CALL RHSP_DESHABI_CUMPL_PLAZO(?)", id_cumplimiento_plazo);
                        } else if (dia == 0 || dia > 0) {

                        }
                    } else if (mes > 0) {

                    }
                } else if (ano > 0) {

                }
            } else if (es_cumple_plazo.equals("0")) {

            }
        }
    }
    
    public int Validar_cumple_dias_pt2(String dgp) {
        int n = 0;
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withFunctionName("rhfu_habilitar_terminar_plazo");
        SqlParameterSource in = new MapSqlParameterSource().addValue("dgp", dgp);
        n = simpleJdbcCall.executeFunction(Integer.class, in);
        return n;
    }

}
