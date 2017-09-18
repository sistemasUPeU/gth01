/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.sql.Types;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.properties.UserMachineProperties;
import pe.edu.upeu.gth.util.DateFormat;

/**
 *
 * @author Cesar
 */
public class DgpDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public DgpDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void INSERT_DGP(String ID_DGP, String FE_DESDE,
            String FE_HASTA, double CA_SUELDO, String DE_DIAS_TRABAJO,
            String ID_PUESTO, String ID_REQUERIMIENTO,
            String ID_TRABAJADOR, String CO_RUC,
            String DE_LUGAR_SERVICIO, String DE_SERVICIO,
            String DE_PERIODO_PAGO, String DE_DOMICILIO_FISCAL,
            String DE_SUBVENCION, String DE_HORARIO_CAPACITACION,
            String DE_HORARIO_REFRIGERIO, String DE_DIAS_CAPACITACION,
            String ES_DGP, String US_CREACION, String FE_CREACION,
            String US_MODIF, String FE_MODIF, String IP_USUARIO,
            double CA_BONO_ALIMENTARIO, double DE_BEV,
            String DE_ANTECEDENTES_POLICIALES,
            String ES_CERTIFICADO_SALUD, String DE_MONTO_HONORARIO,
            String LI_MOTIVO, String ES_MFL, double BONO_PUESTO,
            double ASIGNACION_FAMILIAR, String ES_PRESUPUESTADO) {
        try {
            jt.update("CALL RHSP_INSERT_DGP( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?,?,?,?,?)", null,
                    DateFormat.toFormat1(FE_DESDE), DateFormat.toFormat1(FE_HASTA),
                    CA_SUELDO, DE_DIAS_TRABAJO, ID_PUESTO, ID_REQUERIMIENTO,
                    ID_TRABAJADOR, CO_RUC, DE_LUGAR_SERVICIO, DE_SERVICIO,
                    DE_PERIODO_PAGO, DE_DOMICILIO_FISCAL, DE_SUBVENCION,
                    DE_HORARIO_CAPACITACION, DE_HORARIO_REFRIGERIO, DE_DIAS_CAPACITACION,
                    ES_DGP, US_CREACION, null, US_MODIF, null, UserMachineProperties.getAll(),
                    CA_BONO_ALIMENTARIO, DE_BEV, DE_ANTECEDENTES_POLICIALES,
                    ES_CERTIFICADO_SALUD, DE_MONTO_HONORARIO, LI_MOTIVO,
                    ES_MFL, BONO_PUESTO, ASIGNACION_FAMILIAR, ES_PRESUPUESTADO);
        } catch (ParseException ex) {
            Logger.getLogger(DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> USER_DGP(String id_dgp) {
        sql = " select u.*,du.NO_TRABAJADOR ,du.AP_PATERNO,du.AP_MATERNO, "
                + "p.DE_PASOS as paso from RHVD_USER_AUT u ,RHTC_PASOS p,"
                + "RHVD_USUARIO du  where u.ID_EMPLEADO=du.ID_EMPLEADO and "
                + "u.ID_PASOS= p.ID_PASOS  and u.ID_DGP=? "
                + "AND TRIM(u.ID_PUESTO)<>'0'";
        return jt.queryForList(sql, id_dgp);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_ID_TRAB_DGP(String id) {
        sql = "select TO_CHAR(dgp.FE_DESDE,'dd-mm-yyyy') AS "
                + "FE_DESDE,TO_CHAR(dgp.FE_HASTA,'dd-mm-yyyy') AS "
                + "FE_HASTA ,dgp.*,r.*,pd.* from RHTM_DGP dgp, "
                + "RHTR_REQUERIMIENTO r ,RHVD_PUESTO_DIRECCION pd where "
                + "pd.ID_PUESTO=dgp.ID_PUESTO  and "
                + "r.ID_REQUERIMIENTO= dgp.ID_REQUERIMIENTO and "
                + "dgp.ID_TRABAJADOR=?";
        return jt.queryForList(sql, id);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_DET_DGP(String id_dep) {
        sql = "select  rhfu_get_cd_aps_trabajador(tr.id_trabajador) as "
                + "cod_aps,tr.NU_DOC, trim(rhfu_anno_procesamiento_dgp"
                + "(dgp.ID_DGP)) AS anno_procesamiento, "
                + "trim(rhfu_mes_procesamiento_dgp(dgp.ID_DGP)) AS "
                + "mes_procesamiento, dgp.id_dgp , dgp.id_trabajador,"
                + "tr.no_trabajador,tr.ap_paterno,tr.ap_materno, "
                + "dgp.id_puesto,to_char(dgp.fe_desde,'dd/mm/yyyy') as "
                + "fe_desde,to_char(dgp.fe_hasta,'dd/mm/yyyy') as "
                + "fe_hasta,dgp.ca_sueldo, pd.no_puesto, pd.no_area, "
                + "r.no_req,dgp.ES_DGP ,dgp.LI_MOTIVO,"
                + "dgp.CA_BONO_ALIMENTARIO,dgp.CA_BONIFICACION_P,"
                + "dgp.CA_ASIG_FAMILIAR from RHTR_REQUERIMIENTO r,"
                + "RHTM_DGP dgp , RHTM_TRABAJADOR tr,"
                + "RHVD_PUESTO_DIRECCION  pd where "
                + "r.ID_REQUERIMIENTO = dgp.ID_REQUERIMIENTO and "
                + "dgp.ES_DGP is not null and "
                + "dgp.ID_PUESTO=pd.ID_PUESTO and "
                + "tr.ID_TRABAJADOR = dgp.ID_TRABAJADOR";
        sql += (!id_dep.equals("")) ? " and pd.ID_DEPARTAMENTO='" + id_dep.trim() + "'" : "";
        sql += " ORDER BY TO_NUMBER(SUBSTR(dgp.ID_DGP,5,LENGTH(dgp.ID_DGP))) DESC";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_DGP_BY(String id_user) {
        sql = "SELECT a.ID_AUTORIZACION,dgp.ID_TRABAJADOR,"
                + "a.ES_AUTORIZACION, tr.AP_PATERNO,tr.AP_MATERNO,"
                + "tr.NO_TRABAJADOR,dd.NO_PUESTO,dp.DE_PASOS,"
                + "dp.NO_PROCESO,"
                + "(to_char(a.FE_CREACION,'dd/mm/yy hh:mm:ss')) AS "
                + "FECHA_CREACION FROM RHTV_AUTORIZACION a, "
                + "RHTM_DGP dgp, RHVD_REQ_PASO_PU dp, "
                + "RHVD_PUESTO_DIRECCION dd, RHTM_TRABAJADOR tr "
                + "WHERE dgp.ID_DGP= a.ID_DGP "
                + "AND a.ID_DETALLE_REQ_PROCESO=dp.ID_DETALLE_REQ_PROCESO "
                + "AND dp.ID_PUESTO = a.ID_PUESTO\n"
                + "AND dgp.ID_PUESTO = dd.ID_PUESTO "
                + "AND tr.ID_TRABAJADOR = dgp.ID_TRABAJADOR "
                + "AND dp.ID_PASOS = a.ID_PASOS "
                + "AND trim(a.US_CREACION) =?";
        return jt.queryForList(sql, id_user);
    }

    public void VAL_DGP_PASOS() {
        jt.update("CALL RHSP_VAL_DGP");
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_DGP_PROCESO(String id_dep, String id_dir, String idPuesto, Boolean procAcad) {
        sql = "select * from RHVD_ES_REQUERIMIENTO where "
                + "ES_PORCENT IS NOT NULL ";
        sql += (id_dep.trim().equals("")) ? "" : " and ID_DEPARTAMENTO='" + id_dep.trim() + "' ";
        sql += (id_dir.trim().equals("")) ? "" : " and ID_DIRECCION='" + id_dir.trim() + "' ";
        sql += (idPuesto.trim().equals("")) ? "" : " and ID_DETALLE_REQ_PROCESO in (select ID_DETALLE_REQ_PROCESO from "
                + "RHVD_REQ_PASO_PU where id_puesto='" + idPuesto.trim() + "') ";
        if (id_dep.equals("DPT-0019")) {
            sql = "select * from RHVD_ES_REQUERIMIENTO where ES_PORCENT IS NOT NULL  ";
        }
        sql += (procAcad) ? " and es_proc_acad>0" : " and es_proc_acad=0 ";
        sql += " ORDER BY TO_NUMBER(SUBSTR(ID_DGP,5,LENGTH(ID_DGP))) DESC";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> VAL_TRA_DGP(String id_tr) {
        sql = "select count(*) as TOTAL,ID_DGP from RHTM_DGP where "
                + "ID_TRABAJADOR=? and ES_DGP='0' "
                + "group by ID_DGP";
        return jt.queryForList(sql, id_tr.trim());
    }

    public int VAL_OPC_DGP(String idtr) {
        sql = "select count(ID_DGP) from RHTM_DGP where ES_DGP='0' and "
                + "ID_TRABAJADOR=?";
        return jt.queryForObject(sql, Integer.class, idtr.trim());
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_ID_DGP(String id) {
        sql = "SELECT * FROM RHVD_DET_DGP WHERE ID_DGP=?";
        return jt.queryForList(sql, id);
    }

    public String MAX_ID_DGP() {
        sql = "SELECT 'DGP-' ||MAX (SUBSTR(ID_DGP,5,8)) FROM RHTM_DGP";
        return jt.queryForObject(sql, String.class);
    }

    public int VALIDAR_DGP_CONTR(String id_dgp, String id_tr) {
        sql = "select count(*) from rhtm_contrato where "
                + "id_dgp ='" + id_dgp + "' and "
                + "ES_FIRMO_CONTRATO is not null and "
                + "id_trabajador='" + id_tr + "'";
        return jt.queryForObject(sql, Integer.class);
    }

    public void REG_DGP_FINAL(String IDDGP) {
        sql = "UPDATE RHTM_DGP SET ES_DGP='0' WHERE ID_DGP=?";
        jt.update(sql, IDDGP.trim());
    }

    public void MOD_REQUE(String ID_DGP, String FE_DESDE,
            String FE_HASTA, double CA_SUELDO, String ID_PUESTO,
            String ID_REQUERIMIENTO, double CA_BONO_ALIMENTARIO,
            double DE_BEV, double CA_CENTRO_COSTOS,
            String DE_ANTECEDENTES_POLICIALES,
            String DE_CERTIFICADO_SALUD) {
        try {
            jt.update("CALL RHSP_MOD_REQUERIMIENTO(  ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?)", DateFormat.toFormat1(FE_DESDE),
                    DateFormat.toFormat1(FE_HASTA), CA_SUELDO,
                    ID_PUESTO, ID_REQUERIMIENTO, CA_BONO_ALIMENTARIO,
                    DE_BEV, CA_CENTRO_COSTOS, DE_ANTECEDENTES_POLICIALES,
                    DE_CERTIFICADO_SALUD, ID_DGP);
        } catch (ParseException ex) {
            Logger.getLogger(DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Incomplet(String iddep, boolean permisoAdmin) {
        sql = "SELECT * FROM RHVD_ES_REQ_INCOMPLETO ";
        if (!permisoAdmin) {
            sql += (!"".equals(iddep)) ? " where ID_DEPARTAMENTO='" + iddep + "'" : "";
        }
        sql += "order by ID_DGP";
        return jt.queryForList(sql);
    }

    public void RECHAZAR_DGP(String IDDGP) {
        jt.update("CALL RHSP_RECHAZAR_DGP(?)", IDDGP);
    }

    public void HABILITAR_DGP(String IDDGP) {
        jt.update("CALL RHSP_HABILITAR_DGP(?)", IDDGP);
    }

    public void eliminarDGP(String IDDGP) {
        jt.update("CALL RHSP_ELIMINAR_DGP(?)", IDDGP);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> list_Req(String id) {
        sql = "select id_contrato,id_dgp,fe_desde ||' - ' || "
                + "fe_hasta ||' | '|| NO_PUESTO as descripcion from "
                + "RHVD_LIST_CONTRATO where id_trabajador=?";
        return jt.queryForList(sql, id);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Cargar_Datos_Dgp(String id) {
        sql = " select c.id_contrato,c.id_dgp,c.CA_SUELDO,"
                + "c.CA_BONO_ALIMENTO,c.CA_BEV,c.CA_SUELDO_TOTAL,"
                + "d.DE_ANTECEDENTES_POLICIALES,"
                + "d.ES_CERTIFICADO_SALUD ,d.NO_BANCO,"
                + "d.NO_BANCO_OTROS,d.ES_GEN_NU_CUENTA,d.NU_CUENTA,"
                + "d.NU_CUENTA_BANC from RHVD_LIST_CONTRATO c "
                + "left outer join RHVD_DET_DGP d on "
                + "(d.ID_DGP = c.ID_DGP) where "
                + "c.id_contrato=?";
        return jt.queryForList(sql, id);
    }

    public String Imprimir_det_proceso(String iddgp, String idrp, String iddep) {
        sql = "SELECT f.de_pasos, f.nu_pasos,s.es_autorizacion, "
                + "rhfu_count_aut_dgp (?) as count_aut,"
                + "rhfu_detalle_puesto_aut (?,f.id_pasos,?) "
                + "as puesto_aut FROM (SELECT p.id_pasos, "
                + " p.id_proceso, rp.id_detalle_req_proceso, p.de_pasos, "
                + "p.nu_pasos, p.co_pasos, pro.no_proceso, rp.id_direccion, "
                + "rp.id_departamento, rp.id_requerimiento "
                + "FROM rhtc_pasos p, rhtv_proceso pro, "
                + "rhtr_detalle_req_proceso rp "
                + "WHERE pro.id_proceso = p.id_proceso "
                + "AND rp.id_proceso = pro.id_proceso "
                + "AND rp.ES_REQ_PROCESO='1' AND p.ES_PASOS ='1')f "
                + "LEFT OUTER JOIN (SELECT pu.NO_PUESTO, "
                + "du.NO_USUARIO, a.co_pasos, a.DE_PASOS, "
                + "a.es_autorizacion, a.fe_creacion, "
                + "a.id_autorizacion, a.id_departamento, "
                + "a.id_detalle_pasos, a.id_detalle_req_proceso, "
                + "a.id_dgp, a.id_direccion, a.id_pasos, "
                + "a.id_proceso, a.id_puesto, a.id_requerimiento, "
                + "a.no_proceso, a.nu_pasos, a.us_creacion , "
                + "dt.AP_PATERNO, dt.AP_MATERNO, dt.NO_TRABAJADOR, "
                + "dgp.CA_SUELDO, du.AP_PATERNO    AS us_ap_p, "
                + "du.AP_MATERNO AS us_ap_mat , du.NO_TRABAJADOR AS us_no_tr, "
                + "du.NO_PUESTO AS us_no_puesto, du.NO_AREA AS us_no_area, "
                + "du.NO_DEP AS us_no_dep FROM (SELECT a.id_detalle_req_proceso, "
                + "a.id_dgp, a.id_pasos, d.id_proceso, d.id_detalle_pasos, "
                + "d.DE_PASOS, d.NU_PASOS, d.CO_PASOS , d.no_proceso, "
                + "d.id_puesto, d.id_direccion, d.id_departamento, "
                + "d.id_requerimiento, a.id_autorizacion, "
                + "a.fe_creacion, a.es_autorizacion, a.us_creacion "
                + "FROM (SELECT * FROM rhvd_req_paso_pu) d "
                + "LEFT OUTER JOIN rhtv_autorizacion a "
                + "ON (a.id_pasos=d.id_pasos AND d.id_pasos=a.id_pasos "
                + "AND d.id_puesto=a.id_puesto AND "
                + "d.id_detalle_req_proceso=a.id_detalle_req_proceso)) a, "
                + "rhtm_dgp dgp, rhtm_trabajador dt, rhvd_usuario du, "
                + "rhvd_puesto_direccion pu WHERE dgp.id_dgp=a.id_dgp "
                + "AND dt.id_trabajador=dgp.id_trabajador "
                + "AND du.id_usuario=a.us_creacion "
                + "AND dgp.id_puesto=pu.id_puesto "
                + "AND dgp.id_dgp=?) s ON "
                + "(s.ID_DETALLE_REQ_PROCESO=f.ID_DETALLE_REQ_PROCESO "
                + "AND f.id_pasos=s.id_pasos) WHERE f.ID_DETALLE_REQ_PROCESO=? "
                + "ORDER BY to_number(SUBSTR(f.nu_pasos,2,LENGTH(f.nu_pasos))) ASC";
        List<Map<String, Object>> list = jt.queryForList(sql, iddgp, idrp, iddep.trim(), iddgp, idrp);
        String cadena = "";
        for (int i = 0; i < list.size(); i++) {
            Map<String, Object> o = list.get(i);
            if ((String) o.get("es_autorizacion") != null) {
                if (((String) o.get("es_autorizacion")).equals("1")) {
                    if (i == 1) {
                        cadena = cadena
                                + " <div class=\"new-circle done\" rel=\"popover-hover\" data-placement=\"top\" data-original-title=\"" + (String) o.get("de_pasos") + "\" data-content=\""
                                + (String) o.get("puesto_aut") + "\" data-html=\"true\">"
                                + "<span class=\"new-label\">&#10004;</span>"
                                + "<span class=\"new-title\">" + (String) o.get("nu_pasos") + "</span> "
                                + "</div>";
                    } else {
                        cadena = cadena
                                + " <span class=\"new-bar done\"></span> "
                                + "<div class=\"new-circle done\" rel=\"popover-hover\" data-placement=\"top\" data-original-title=\"" + (String) o.get("de_pasos") + "\" data-content=\"" + (String) o.get("puesto_aut") + " \" data-html=\"true\">"
                                + "<span class=\"new-label\">&#10004;</span>"
                                + "<span class=\"new-title\">" + (String) o.get("nu_pasos") + "</span>"
                                + " </div>";
                    }
                }
                if (((String) o.get("es_autorizacion")).equals("2")) {
                    cadena = cadena
                            + " <span class=\"new-bar done\"></span> "
                            + "<div  class=\"new-circle rechazo\" rel=\"popover-hover\" data-placement=\"top\" data-original-title=\"" + (String) o.get("de_pasos") + "\" data-content=\" " + (String) o.get("puesto_aut") + " \" data-html=\"true\">"
                            + "<span style='color:white; font:bold;' class=\"new-label fa fa-warning\"></span>"
                            + "<span class=\"new-title\">" + (String) o.get("nu_pasos") + "</span>"
                            + " </div>";
                }
            } else if ((Integer) o.get("count_aut") + 1 == i) {
                //if (false) {
                cadena = cadena
                        + " <span class=\"new-bar active\"></span> "
                        + "<div class=\"new-circle active\" rel=\"popover-hover\" data-placement=\"top\" data-original-title=\"" + (String) o.get("de_pasos") + "\" data-content=\"" + (String) o.get("puesto_aut") + " \" data-html=\"true\">"
                        + "<span class=\"new-label fa fa-inbox\"></span>"
                        + "<span class=\"new-title\">" + (String) o.get("nu_pasos") + "</span>"
                        + " </div>";

            } else {
                cadena = cadena
                        + " <span class=\"new-bar \"></span> "
                        + "<div class=\"new-circle\" rel=\"popover-hover\" data-placement=\"top\" data-original-title=\"" + (String) o.get("de_pasos") + "\" data-content=\"" + (String) o.get("puesto_aut") + " \" data-html=\"true\">"
                        + "<span class=\"new-label fa fa-lock\"></span>"
                        + "<span class=\"new-title\">" + (String) o.get("nu_pasos") + "</span>"
                        + " </div>";
            }
        }
        return cadena;
    }
    
    public int VALIDAR_DGP_CONTRATO(String id) {
        sql = "select count (*) from rhtm_contrato where id_dgp=?";
        return jt.queryForObject(sql, Integer.class,id);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPLACED, 
    WE USE MAP OBJECTS INSTEAD). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> LIST_CUEN_SUEL(String id_trabajador) {
        sql = "SELECT * FROM RHTD_CUENTA_SUELDO WHERE ID_TRABAJADOR = ? ";
        return jt.queryForList(sql,id_trabajador);
    }
    
    public String obt_idtr_x_dgp(String id_dgp) {
        sql = "SELECT ID_TRABAJADOR FROM  RHTM_DGP WHERE ID_DGP=?";
        return jt.queryForObject(sql, String.class,id_dgp.trim());
    }
    
    public int Can_cc_iddgp(String id_dgp) {
        sql = "SELECT COUNT(*) FROM RHTD_DETALLE_CENTRO_COSTO WHERE ID_DGP=?";
        return jt.queryForObject(sql, Integer.class,id_dgp.trim());
    }
    
    public void MODIFICAR_DGP(String ID_DGP, String FE_DESDE, 
            String FE_HASTA, double CA_SUELDO, String DE_DIAS_TRABAJO, 
            String ID_PUESTO, String ID_REQUERIMIENTO, 
            String ID_TRABAJADOR, String CO_RUC, 
            String DE_LUGAR_SERVICIO, String DE_SERVICIO, 
            String DE_PERIODO_PAGO, String DE_DOMICILIO_FISCAL, 
            String DE_SUBVENCION, String DE_HORARIO_CAPACITACION, 
            String DE_HORARIO_REFRIGERIO, String DE_DIAS_CAPACITACION, 
            String ES_DGP, String US_CREACION, String FE_CREACION, 
            String US_MODIF, String FE_MODIF, String IP_USUARIO, 
            double CA_BONO_ALIMENTARIO, double DE_BEV, 
            String DE_ANTECEDENTES_POLICIALES, 
            String ES_CERTIFICADO_SALUD, String DE_MONTO_HONORARIO, 
            String LI_MOTIVO, String ES_MFL, double BONO_PUESTO, 
            String ES_PRESUPUESTADO) {
        try {
            jt.update("CALL RHSP_MODIFICAR_DGP( ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?,?,?)", ID_DGP,DateFormat.toFormat1(FE_DESDE),
                    DateFormat.toFormat1(FE_HASTA),CA_SUELDO,
                    DE_DIAS_TRABAJO,ID_PUESTO,ID_REQUERIMIENTO,
                    ID_TRABAJADOR,CO_RUC,DE_LUGAR_SERVICIO,
                    DE_SERVICIO,DE_PERIODO_PAGO,DE_DOMICILIO_FISCAL,
                    DE_SUBVENCION,DE_HORARIO_CAPACITACION,DE_HORARIO_REFRIGERIO,
                    DE_DIAS_CAPACITACION,ES_DGP,null,null,US_MODIF,
                    null,IP_USUARIO,CA_BONO_ALIMENTARIO,DE_BEV,
                    DE_ANTECEDENTES_POLICIALES,ES_CERTIFICADO_SALUD,
                    DE_MONTO_HONORARIO,LI_MOTIVO,ES_MFL,BONO_PUESTO,
                    ES_PRESUPUESTADO);
        } catch (ParseException ex) {
            Logger.getLogger(DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean val_fe_inicio_dgp(String fecha) {
        int i=0;
        try {
            i=jt.queryForObject("begin   ? :=rhfu_val_fe_desde_dgp(?);end;",
                    Integer.class,Types.INTEGER,DateFormat.toFormat1(fecha));
        } catch (ParseException ex) {
            Logger.getLogger(DgpDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return (i!=0);
    }
}
