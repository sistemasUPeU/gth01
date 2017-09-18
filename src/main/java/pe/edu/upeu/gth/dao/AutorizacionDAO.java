/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import pe.edu.upeu.gth.properties.UserMachineProperties;

/**
 *
 * @author Cesar
 */
public class AutorizacionDAO {
    //MODIFICAR TODAS LAS CONSULTAS A LOS OBJETOS DAO, YA NO SE USARÁN MODELOS

    private JdbcTemplate jt;
    private String sql = "";

    public AutorizacionDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void Insert_Autorizacion(String ID_AUTORIZACION, String ID_DGP, String ES_AUTORIZACION, String NU_PASOS, String IP_USUARIO, String US_CREACION, String US_MODIF, String FE_MODIF, String CO_PUESTO, String ID_PUESTO, String ID_DETALLE_REQ_PROCESO, String ID_PASOS) {
        sql = "CALL RHSP_INSERT_AUTORIZACION( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jt.update(sql, null, ID_DGP, ES_AUTORIZACION, NU_PASOS, UserMachineProperties.getAll(), US_CREACION, US_MODIF, FE_MODIF, CO_PUESTO, ID_PUESTO, ID_DETALLE_REQ_PROCESO, ID_PASOS);
    }

    public List<Map<String, Object>> List_Detalle_Autorizacion(String iddgp, String idrp) {
        sql = "SELECT rec.co_pasos,rec.de_pasos, rec.es_autorizacion, "
                + "rec.fe_creacion, rec.id_autorizacion,rec.id_departamento,"
                + "rec.id_detalle_pasos,rec.id_detalle_req_proceso,rec.id_dgp,"
                + "rec.id_direccion,rec.id_pasos, rec.id_proceso, rec.id_puesto, "
                + "rec.id_requerimiento, rec.no_proceso, rec.nu_pasos, "
                + "rec.us_creacion, rec.ap_paterno, rec.ap_materno, "
                + "rec.no_trabajador, rec.ca_sueldo, rec.us_ap_p, rec.us_ap_mat, "
                + "rec.us_no_tr, rec.us_no_area, rec.us_no_puesto, "
                + "rec.us_no_dep, rec.no_puesto,rec.no_usuario ,"
                + "com.CM_COMENTARIO,rhfu_aut_complete_time(rec.id_dgp, rec.id_autorizacion) as taskNum "
                + "FROM(SELECT f.co_pasos,  f.de_pasos, s.es_autorizacion, "
                + " s.fe_creacion, s.id_autorizacion, s.id_departamento, "
                + "  s.id_detalle_pasos, s.id_detalle_req_proceso, s.id_dgp, "
                + " s.id_direccion, s.id_pasos, s.id_proceso, s.id_puesto, "
                + " s.id_requerimiento, s.no_proceso, f.nu_pasos, "
                + "s.us_creacion, s.ap_paterno, s.ap_materno, "
                + "s.no_trabajador, s.ca_sueldo, s.us_ap_p, s.us_ap_mat, "
                + " s.us_no_tr, s.us_no_area, s.us_no_puesto, s.us_no_dep, "
                + " s.no_puesto, s.no_usuario "
                + "FROM (SELECT p.id_pasos, p.id_proceso, rp.id_detalle_req_proceso, "
                + " p.de_pasos, p.nu_pasos, p.co_pasos, pro.no_proceso, "
                + " rp.id_direccion, rp.id_departamento,  rp.id_requerimiento "
                + " FROM rhtc_pasos p , rhtv_proceso pro, rhtr_detalle_req_proceso rp "
                + " WHERE pro.id_proceso = p.id_proceso AND rp.id_proceso    = pro.id_proceso "
                + " AND rp.ES_REQ_PROCESO='1' AND p.ES_PASOS ='1') f "
                + "LEFT OUTER JOIN (SELECT pu.NO_PUESTO, du.NO_USUARIO, "
                + " a.co_pasos, a.DE_PASOS,  a.es_autorizacion, a.fe_creacion, "
                + " a.id_autorizacion,  a.id_departamento, a.id_detalle_pasos, "
                + " a.id_detalle_req_proceso, a.id_dgp, a.id_direccion, "
                + " a.id_pasos, a.id_proceso, a.id_puesto, a.id_requerimiento, "
                + " a.no_proceso, a.nu_pasos, a.us_creacion , dt.AP_PATERNO, "
                + " dt.AP_MATERNO, dt.NO_TRABAJADOR, dgp.CA_SUELDO, "
                + " du.AP_PATERNO AS us_ap_p, du.AP_MATERNO AS us_ap_mat , "
                + " du.NO_TRABAJADOR AS us_no_tr, du.NO_PUESTO AS us_no_puesto, "
                + " du.NO_AREA AS us_no_area, du.NO_DEP AS us_no_dep "
                + " FROM (SELECT a.id_detalle_req_proceso, a.id_dgp, "
                + " a.id_pasos, d.id_proceso, d.id_detalle_pasos , "
                + " d.DE_PASOS, d.NU_PASOS, d.CO_PASOS , d.no_proceso , "
                + " d.id_puesto, d.id_direccion, d.id_departamento , "
                + " d.id_requerimiento , a.id_autorizacion, a.fe_creacion, "
                + " a.es_autorizacion, a.us_creacion FROM "
                + " (SELECT * FROM rhvd_req_paso_pu ) d "
                + " LEFT OUTER JOIN rhtv_autorizacion a ON ( a.id_pasos =d.id_pasos "
                + " AND d.id_pasos =a.id_pasos AND d.id_puesto =a.id_puesto "
                + " AND d.id_detalle_req_proceso=a.id_detalle_req_proceso) "
                + " ) a , rhtm_dgp dgp , rhtm_trabajador dt , rhvd_usuario du , "
                + " rhvd_puesto_direccion pu WHERE dgp.id_dgp =a.id_dgp "
                + " AND dt.id_trabajador = dgp.id_trabajador AND du.id_usuario =a.us_creacion "
                + " AND dgp.id_puesto =pu.id_puesto "
                + " AND dgp.id_dgp =? "
                + " ) s ON ( s.ID_DETALLE_REQ_PROCESO=f.ID_DETALLE_REQ_PROCESO "
                + "AND f.id_pasos =s.id_pasos ) "
                + "WHERE f.ID_DETALLE_REQ_PROCESO =? "
                + "ORDER BY to_number(substr(f.nu_pasos,2,length(f.nu_pasos))) ASC) "
                + "rec LEFT OUTER join RHTR_COMENTARIO_DGP com ON (rec.ID_DGP=com.ID_DGP "
                + "and rec.ID_AUTORIZACION=com.ID_AUTORIZACION)";
        return jt.queryForList(sql, iddgp.trim(), idrp.trim());
    }

    public List<Map<String, Object>> List_id_Autorizacion(String id_autorizacion, String id_user, String iddgp) {
        sql = "select ID_TRABAJADOR, NO_TRABAJADOR, AP_PATERNO, AP_MATERNO, NO_PUESTO, NU_PASOS, ID_DGP, CO_PASOS, ID_DETALLE_REQ_PROCESO, DE_PASOS, ID_DEPARTAMENTO, "
                + "ID_PUESTO, ID_REQUERIMIENTO, ID_TIPO_PLANILLA, NO_REQ, ID_PASOS, NO_USUARIO, ID_USUARIO, NO_SECCION,"
                + " NO_AREA, FE_CREACION, VAL_PLAZO, AR_FOTO, DE_FOTO, ID_FOTO, NO_AR_FOTO, TA_AR_FOTO, TI_AR_FOTO, VER_LIST_PLAZO, "
                + "ELAB_CONTRATO, VAL_FIRM_CONTRATO, NO_DEP, MES_CREACION, VAL_COD_APS_EMPLEADO, VAL_COD_HUELLA_EMP, CO_APS, CO_HUELLA_DIGITAL, LI_MOTIVO,"
                + " ES_MFL, DI_CORREO_PERSONAL, DI_CORREO_INST, VAL_CONTRATO_ADJUNTO ,val_dgp_contrato,mes_plazo  from rhvd_autorizar_dgp where id_puesto=?";
        sql += (!"".equals(id_user)) ? " and id_usuario='" + id_user + "'" : "";
        sql += (!"".equals(iddgp)) ? " and id_dgp='" + iddgp + "'" : "";
        sql += (true) ? " order by fe_creacion " : "";
        return jt.queryForList(sql, id_autorizacion);
    }

    public List<Map<String, Object>> List_Autorizacion_Academico(String id, String id_user, String iddgp) {
        sql = "select *  from RHVD_AUTORIZAR_CARGA_ACADEMICA where id_puesto=?";
        sql += (!"".equals(id_user)) ? " and id_usuario='" + id_user + "'" : "";
        sql += (!"".equals(iddgp)) ? " and id_dgp='" + iddgp + "'" : "";
        return jt.queryForList(sql, id);
    }

    public void Elim_Aut(String id_Autorizacion) {
        sql = "DELETE FROM RHTV_AUTORIZACION WHERE ID_AUTORIZACION='" + id_Autorizacion.trim() + "'";
        jt.update(sql);
    }

    public List<Map<String, Object>> Det_Autorizacion(String id_rpp) { //VERIFICAR EL USO DE ESTA FUNCION
        sql = "select * from rhvd_req_paso_pu where id_detalle_req_proceso =? and trim(nu_pasos)='P1'";
        return jt.queryForList(sql, id_rpp.trim());
    }

    public List<Map<String, Object>> List_Autorizados(String id_puesto) {
        sql = "SELECT * FROM RHVD_DGP_AUTORIZADOS where ID_PUESTO = ?";
        return jt.queryForList(sql, id_puesto);
    }

    /*REVISAR Y MODIFICAR EL USO DE SER NECESARIO*/
    public Map<String, Object> Insert_Autorizacion_dev(String ID_AUTORIZACION, String ID_DGP, String ES_AUTORIZACION, String NU_PASOS, String IP_USUARIO, String US_CREACION, String US_MODIF, String FE_MODIF, String CO_PUESTO, String ID_PUESTO, String ID_DETALLE_REQ_PROCESO, String ID_PASOS) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_AUTORIZACION_DEV");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        inParamMap.put("ID_AUTORIZACION_SP", null);
        inParamMap.put("ID_DGP_SP", ID_DGP);
        inParamMap.put("ES_AUTORIZACION_SP", ES_AUTORIZACION);
        inParamMap.put("NU_PASOS_SP", NU_PASOS);
        inParamMap.put("IP_USUARIO_SP", IP_USUARIO);
        inParamMap.put("US_CREACION_SP", US_CREACION);
        inParamMap.put("US_MODIF_SP", US_MODIF);
        inParamMap.put("FE_MODIF_SP", FE_MODIF);
        inParamMap.put("CO_PUESTO_SP", CO_PUESTO);
        inParamMap.put("ID_PUESTO_SP", ID_PUESTO);
        inParamMap.put("ID_DETALLE_REQ_PROCESO_SP", ID_DETALLE_REQ_PROCESO);
        inParamMap.put("ID_PASOS_SP", ID_PASOS);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return simpleJdbcCallResult;
    }

    public void Insert_comentario_Aut(String ID_COMENTARIO_DGP_SP, String id_autorizacion, String id_dgp, String us_creacion, String es_comentario, String fe_creacion, String comentario) {
        sql = "CALL RHSP_INSERT_COMENTARIO_AUTOR( ?, ?, ?, ?, ?, ?,?)";
        jt.update(sql, null, id_autorizacion.trim(), id_dgp.trim(), us_creacion.trim(), es_comentario.trim(), fe_creacion, comentario);
    }

    /*REVISAR Y MODIFICAR EL USO DE SER NECESARIO*/
    public Map<String, Object> Mes_plazo(String id_dgp) {
        sql = "SELECT TO_CHAR(pl.FE_HASTA,'MONTH',"
                + "'nls_date_language=spanish') as mes_hasta "
                + "FROM RHTR_CUMPLIMIENTO_PLAZO cp,RHTR_PLAZO pl "
                + "WHERE  pl.ID_PLAZO = cp.ID_PLAZO and "
                + "pl.TI_PLAZO='2'  and cp.ID_DGP=?";
        return jt.queryForMap(sql, id_dgp.trim());
    }

    public List<Map<String, Object>> List_Dgp_Autorizados(String id_usuario, int pageNumber, int pageSize, int mes, String año) {
        sql = "SELECT "
                + "        dgp.id_dgp,\n"
                + "        trim(rhfu_mes_procesamiento_dgp(dgp.id_dgp) )\n"
                + "         || ' - '\n"
                + "         || trim(rhfu_anno_procesamiento_dgp(dgp.id_dgp) ) AS mes_anno_aut,\n"
                + "        trim(rhfu_anno_procesamiento_dgp(dgp.id_dgp) ) AS anno_procesamiento,\n"
                + "        trim(rhfu_mes_procesamiento_dgp(dgp.id_dgp) ) AS mes_procesamiento,\n"
                + "        TO_CHAR(\n"
                + "            dgp.fe_creacion,\n"
                + "            'MONTH',\n"
                + "            'nls_date_language=spanish'\n"
                + "        ) AS mes_creacion,\n"
                + "        trb.no_trabajador,\n"
                + "        trb.ap_paterno,\n"
                + "        trb.ap_materno,\n"
                + "        pu.no_puesto,\n"
                + "        au.id_puesto,\n"
                + "        pu.no_area,\n"
                + "        pu.no_dep,\n"
                + "        req.no_req,\n"
                + "        p.de_pasos,\n"
                + "        TO_CHAR(\n"
                + "            dgp.fe_creacion,\n"
                + "            'dd/mm/yy HH:MI:SS'\n"
                + "        ) AS fe_creacion,\n"
                + "        TO_CHAR(\n"
                + "            au.fe_creacion,\n"
                + "            'dd/mm/yy HH:MI:SS'\n"
                + "        ) AS fe_autorizacion,\n"
                + "        dgp.li_motivo,\n"
                + "        dgp.es_mfl,\n"
                + "        au.us_creacion,\n"
                + "        dgp.es_activ_sis_estado,\n"
                + "        dgp.es_proc_asignacion_f,\n"
                + "        dgp.id_trabajador, %s  \n"
                + "    FROM\n"
                + "        (select * from rhtv_autorizacion order by fe_creacion desc )  au, "
                + "        rhtm_dgp dgp, "
                + "        rhtm_trabajador trb, "
                + "        rhvd_puesto_direccion pu, "
                + "        rhtr_requerimiento req, "
                + "        rhtc_pasos p "
                + "    WHERE "
                + "            au.id_dgp = dgp.id_dgp\n"
                + "        AND\n"
                + "            dgp.id_trabajador = trb.id_trabajador\n"
                + "        AND\n"
                + "            dgp.id_puesto = pu.id_puesto\n"
                + "        AND\n"
                + "            dgp.id_requerimiento = req.id_requerimiento\n"
                + "        AND\n"
                + "            p.id_pasos = au.id_pasos\n"
                + "        AND\n"
                + "            dgp.es_dgp IS NOT NULL and au.US_CREACION=?"
                + "    %s " //  + " ORDER BY au.fe_creacion DESC";
                //  String sql = "SELECT * FROM RHVD_DGP_AUTORIZADOS WHERE US_CREACION='" + id_usuario + "'  and mes_procesamiento is not null";
                /* sql += (!año.equals("")) ? " AND to_number(TRIM(to_char(to_date(mes_procesamiento,'MONTH','nls_date_language=spanish'),'mm')))='"
             + (mes + 1) + "' AND TRIM(año_procesamiento)='" + año
             + "' " : "  AND to_number(TRIM(to_char(to_date(mes_procesamiento,'MONTH','nls_date_language=spanish'),'mm')))=to_number(to_char(sysdate,'mm')) AND TRIM(año_procesamiento)=to_char(sysdate,'YYYY') "*/;
        return jt.queryForList(sql, id_usuario);
    }

    /*REVISAR*/
    public Integer getListAuthorizeRequirementsSize(String id_usuario, int mes, String año) {
        sql = "SELECT count(1)"
                + "    FROM\n"
                + "        rhtv_autorizacion au,\n"
                + "        rhtm_dgp dgp,\n"
                + "        rhtm_trabajador trb,\n"
                + "        rhvd_puesto_direccion pu,\n"
                + "        rhtr_requerimiento req,\n"
                + "        rhtc_pasos p\n"
                + "    WHERE\n"
                + "            au.id_dgp = dgp.id_dgp\n"
                + "        AND\n"
                + "            dgp.id_trabajador = trb.id_trabajador\n"
                + "        AND\n"
                + "            dgp.id_puesto = pu.id_puesto\n"
                + "        AND\n"
                + "            dgp.id_requerimiento = req.id_requerimiento\n"
                + "        AND\n"
                + "            p.id_pasos = au.id_pasos\n"
                + "        AND\n"
                + "            dgp.es_dgp IS NOT NULL and au.US_CREACION=?"
                + "    ORDER BY au.fe_creacion DESC";
        return jt.queryForObject(sql, Integer.class, id_usuario);
    }

    public List<Map<String, Object>> List_procesar_req(boolean tipo_list, boolean permisoAsigFam, boolean permisoEsSistema) {
        sql = "select ID_DGP, NO_TRABAJADOR, AP_PATERNO, AP_MATERNO, NO_PUESTO, NO_SECCION, NO_AREA, NO_DEP, "
                + "NO_REQ, ES_ACTIV_SIS_ESTADO, ES_PROC_ASIGNACION_F, "
                + "ID_TRABAJADOR ,MES_PROCESAMIENTO_DGP,ANNO_PROCESAMIENTO_DGP from rhvd_req_proc_area_rem  ";
        if (!tipo_list) {
            if (permisoAsigFam & permisoEsSistema) {
                sql += " where ES_ACTIV_SIS_ESTADO =1 and ES_PROC_ASIGNACION_F=1";
            } else if (permisoAsigFam) {
                sql += " where ES_PROC_ASIGNACION_F=1";
            } else if (permisoEsSistema) {
                sql += " where ES_ACTIV_SIS_ESTADO =1";
            }
        } else if (tipo_list) {
            if (permisoAsigFam & permisoEsSistema) {
                sql += " where ES_ACTIV_SIS_ESTADO =0 or ES_PROC_ASIGNACION_F=0";
            } else if (permisoAsigFam) {
                sql += " where ES_PROC_ASIGNACION_F=0";
            } else if (permisoEsSistema) {
                sql += " where ES_ACTIV_SIS_ESTADO =0";
            }
        }
        return jt.queryForList(sql);
    }

    public List<Map<String, Object>> ShowCkbEstado_procesarIndiviual(String iddgp) {
        sql = "select ID_DGP,ES_ACTIV_SIS_ESTADO, ES_PROC_ASIGNACION_F  from rhvd_req_proc_area_rem where id_dgp=? ";
        return jt.queryForList(sql, iddgp);
    }

    public boolean UpdateDgp_EstadoProcesar(String[] iddgp, int tipo, boolean estado) {
        boolean x = false;
        try {
            int i = 0;
            int e = (estado) ? 1 : 0;
            Array array_to_pass = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARRAY_ID_DGP", iddgp);
            i = jt.update("call actualizar_dgps(?,?,?)", array_to_pass, tipo, e);
            if (1 == 1) {
                x = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(AutorizacionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return x;
    }
}
