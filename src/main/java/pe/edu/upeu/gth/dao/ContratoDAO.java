/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.util.DateFormat;

/**
 *
 * @author Cesar
 */
public class ContratoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public ContratoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void INSERT_CONTRATO(String ID_CONTRATO, String ID_DGP, String FE_DESDE, String FE_HASTA, String FE_CESE,
            String ID_FUNC, String LI_CONDICION, double CA_SUELDO, double CA_REINTEGRO, double CA_ASIG_FAMILIAR,
            double HO_SEMANA, double NU_HORAS_LAB, double DIA_CONTRATO, String TI_TRABAJADOR,
            String LI_REGIMEN_LABORAL, String ES_DISCAPACIDAD, String TI_CONTRATO, String LI_REGIMEN_PENSIONARIO,
            String ES_CONTRATO_TRABAJADOR, String US_CREACION, String FE_CREACION, String US_MODIF, String FE_MODIF,
            String US_IP, String FE_VACACIO_INI, String FE_VACACIO_FIN, String ES_CONTRATO, String ID_FILIAL,
            String ID_PUESTO, double CA_BONO_ALIMENTO, String LI_TIPO_CONVENIO, String ES_FIRMO_CONTRATO,
            double NU_CONTRATO, String DE_OBSERVACION, String ES_APOYO, String TI_HORA_PAGO, String NU_DOCUMENTO,
            String ES_ENTREGAR_DOC_REGLAMENTOS, String ES_REGISTRO_HUELLA, String DE_REGISTRO_SISTEM_REMU,
            String ID_TRABAJADOR, double CA_SUELDO_TOTAL, String ID_REGIMEN_LABORAL, String ID_MODALIDAD,
            String ID_SUB_MODALIDAD, String CO_GR_OCUPACION, String FE_SUSCRIPCION, String CO_TI_MONEDA,
            String CO_TI_REM_VARIAB, String DE_REMU_ESPECIE, String DE_RUC_EMP_TRAB, String CO_SUCURSAL,
            String DE_MYPE, String ES_TI_CONTRATACION, double CA_BEV, String ID_TIPO_PLANILLA,
            String ES_REMUNERACION_PROCESADO, String ID_HORARIO, String ID_PLANTILLA_CONTRACTUAL,
            double ca_bonificacion_p, String ES_MFL, String PRACTICANTE, String situacionEspecial) {
        try {
            jt.update("CALL RHSP_INSERT_CONTRATO(?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,"
                    + " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?,?,"
                    + "?,?)", null, ID_DGP, DateFormat.toFormat1(FE_DESDE),
                    (FE_HASTA == null) ? "" : DateFormat.toFormat1(FE_HASTA),
                    FE_CESE, ID_FUNC, LI_CONDICION, CA_SUELDO, CA_REINTEGRO,
                    CA_ASIG_FAMILIAR, HO_SEMANA, NU_HORAS_LAB, DIA_CONTRATO,
                    TI_TRABAJADOR, LI_REGIMEN_LABORAL, ES_DISCAPACIDAD,
                    TI_CONTRATO, LI_REGIMEN_PENSIONARIO, "1", US_CREACION,
                    FE_CREACION, US_MODIF, FE_MODIF, US_IP, FE_VACACIO_INI,
                    FE_VACACIO_FIN, "1", ID_FILIAL, ID_PUESTO, CA_BONO_ALIMENTO,
                    LI_TIPO_CONVENIO, ES_FIRMO_CONTRATO, NU_CONTRATO, DE_OBSERVACION,
                    ES_APOYO, TI_HORA_PAGO, NU_DOCUMENTO, ES_ENTREGAR_DOC_REGLAMENTOS,
                    ES_REGISTRO_HUELLA, DE_REGISTRO_SISTEM_REMU, ID_TRABAJADOR,
                    CA_SUELDO_TOTAL, ID_REGIMEN_LABORAL, ID_MODALIDAD,
                    ID_SUB_MODALIDAD, CO_GR_OCUPACION, DateFormat.toFormat1(FE_SUSCRIPCION),
                    CO_TI_MONEDA, CO_TI_REM_VARIAB, DE_REMU_ESPECIE, DE_RUC_EMP_TRAB,
                    CO_SUCURSAL, DE_MYPE, ES_TI_CONTRATACION, CA_BEV, ID_TIPO_PLANILLA,
                    "0", ID_HORARIO.trim(), ID_PLANTILLA_CONTRACTUAL,
                    ca_bonificacion_p, ES_MFL, PRACTICANTE, situacionEspecial);
        } catch (ParseException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<String> List_Rh_Contrato_Idtr() {
        sql = "select id_trabajador from rhtm_contrato where "
                + "id_trabajador not in (select id_trabajador from "
                + "rhtd_empleado where es_empleado = 1 and "
                + "id_trabajador is not null) and es_contrato=1";
        return jt.queryForList(sql, String.class);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_Rh_Contrato_Fec(String id_departamento, String fecha_creacion) {
        sql = "select c.id_contrato, c.id_trabajador,dt.ap_paterno,"
                + "dt.ap_materno,dt.no_trabajador, c.fe_desde,"
                + "c.fe_hasta,c.ca_sueldo ,dp.no_area,dp.no_seccion,"
                + "dp.no_puesto, to_char(c.fe_creacion,'dd/mm/yy') from "
                + "RHTM_CONTRATO c, RHTM_TRABAJADOR dt, "
                + "RHVD_PUESTO_DIRECCION dp where dt.id_trabajador = c.id_trabajador and "
                + "dp.id_puesto = c.id_puesto AND TO_CHAR(c.fe_creacion,'dd/mm/yy') = "
                + "TO_CHAR(to_date(?),'dd/mm/yy') "
                + "and dp.id_departamento=?";
        return jt.queryForList(sql, fecha_creacion, id_departamento);
    }

    public void Venc_Cont() {
        jt.update("begin venc_contrato; end;");
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_modalidad() {
        sql = " select * from  RHTX_MODALIDAD order by DE_MODALIDAD ";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> list_reg_labo() {
        sql = "select * from  RHTX_REGIMEN_LABORAL order by DE_REGIMEN_L";
        return jt.queryForList(sql);
    }

    public List<Map<String, Object>> List_submodalidad() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String Buscar_id_tr(String id_dgp) {
        sql = "SELECT ID_CONTRATO FROM RHTM_CONTRATO WHERE ID_DGP=?";
        return jt.queryForObject(sql, String.class, id_dgp);
    }

    public String Buscar_id_contrato_x_idtr(String idtr) {
        sql = "SELECT ID_CONTRATO FROM RHTM_CONTRATO where "
                + "ID_TRABAJADOR=? and ES_CONTRATO='1'";
        return jt.queryForObject(sql, String.class, idtr.trim());
    }

    public String Contrato_max(String id_tr) {
        sql = "SELECT ID_CONTRATO FROM RHTM_CONTRATO WHERE "
                + "ID_CONTRATO= (SELECT 'CTO-'||lpad(TO_CHAR"
                + "(MAX(TO_NUMBER(SUBSTR(ID_CONTRATO,5,8)))),6,'0') "
                + "FROM RHTM_CONTRATO  WHERE  ID_TRABAJADOR=?)";
        return jt.queryForObject(sql, String.class, id_tr);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_contra_x_idcto(String id_cto) {
        sql = "SELECT * FROM RHVD_CONTRATOS_HISTORIAL WHERE "
                + "ID_CONTRATO=?";
        return jt.queryForList(sql, id_cto);
    }

    public String MAX_ID_CONTRATO() {
        sql = "SELECt 'CTO-'||MAX(SUBSTR(ID_CONTRATO,5,8)) FROM RHTM_CONTRATO";
        return jt.queryForObject(sql, String.class);
    }

    public void INSERT_CONTRATO_ADJUNTO(String ID_CONTRATO_ADJUNTO,
            String ID_CONTRATO, String NO_ARCHIVO, String NO_ARCHIVO_ORIGINAL,
            String ES_CONTRATO_ADJUNTO, String IP_USUARIO, String US_CREACION,
            String FE_CREACION, String US_MODIF, String FE_MODIF) {
        jt.update("CALL RHSP_INSERT_CONTRATO_ADJUNTO ( ?, ?, ?, ?, ?, "
                + "?, ?, ?, ?, ? )", null, ID_CONTRATO, NO_ARCHIVO,
                NO_ARCHIVO_ORIGINAL, "1", IP_USUARIO, US_CREACION,
                FE_CREACION, US_MODIF, FE_MODIF);
    }

    public void UPDATE_FIRMA(String ID_TR, String IDDGP) {
        jt.update("CALL RHSP_UPDATE_ES_FIRMA( ?,?)", IDDGP, ID_TR.trim());
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> LIST_CASOS_ESPECIALES() {
        sql = "select * from RHVD_LIST_EMPLEADO where "
                + "ID_TRABAJADOR IN (select DISTINCT ID_TRABAJADOR "
                + "from RHTM_CONTRATO where ID_DGP is null ) and  "
                + "to_char(FE_CREACION_CONTRATO,'yy')  = to_char"
                + "(sysdate,'yy')";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_Contratos(String de, String al,
            String direccion, String dep, String area, String sec,
            String puesto, String sueldo_total, String nombre,
            String fe_i, String fe_fin, String fe_sus,
            String id_dep_ses) {
        sql = "select  *  from RHVD_FILTRO_CONTRATO_TERMINADO WHERE "
                + "FE_CREACION IS NOT NULL ";
        try {
            if (!de.equals("") || !al.equals("") || !fe_i.equals("") || !fe_fin.equals("") || !fe_sus.equals("")) {//si busca por fecha
                if (!de.equals("") && !al.equals("")) {
                    sql += "AND FE_CREACION BETWEEN '" + DateFormat.toFormat1(de) + "' AND '" + DateFormat.toFormat1(al) + "' ";
                }
                if (!fe_i.equals("") && !fe_fin.equals("")) {
                    sql += "AND FE_DESDE >= '" + DateFormat.toFormat1(fe_i) + "' AND FE_HASTA <='" + DateFormat.toFormat1(fe_fin) + "' ";
                } else {
                    if (!fe_i.equals("")) {
                        sql += "AND FE_DESDE BETWEEN '" + DateFormat.toFormat1(fe_i) + "' AND (select SYSDATE from dual) ";
                    }
                    if (!fe_fin.equals("")) {
                        sql += "AND FE_HASTA BETWEEN (SELECT MIN(FE_DESDE) AND '" + DateFormat.toFormat1(fe_fin) + "' ";
                    }
                }
                if (!fe_sus.equals("")) {
                    sql += "AND FE_SUSCRIPCION = '" + DateFormat.toFormat1(fe_sus) + "' ";
                }

            }
            if (direccion != null) {
                if (!id_dep_ses.equals("")) {
                    if (!id_dep_ses.trim().equals("DPT-0019")) {
                        sql += (!id_dep_ses.equals("")) ? " and ID_DEPARTAMENTO= '" + id_dep_ses.trim() + "'" : "";
                    } else {
                        sql += (!direccion.equals("")) ? " AND ID_DIRECCION = '" + direccion.trim() + "'" : "";
                        sql += (!dep.equals("")) ? " and ID_DEPARTAMENTO= '" + dep.trim() + "'" : "";
                    }
                }
                sql += (!area.equals("")) ? " and ID_AREA= '" + area.trim() + "'" : "";
                sql += (!sec.equals("")) ? " and ID_SECCION= '" + sec.trim() + "'" : "";
                sql += (!sueldo_total.equals("")) ? " and CA_SUELDO_TOTAL= '" + sueldo_total.trim() + "'" : "";
                sql += (!puesto.equals("")) ? " and ID_PUESTO= '" + puesto.trim() + "'" : "";

            }
            if (!nombre.equals("") || !sueldo_total.equals("")) {//si busca por datos del trabajador
                if (!sueldo_total.equals("")) {
                    sql += " and CA_SUELDO_TOTAL=" + sueldo_total;
                }
                sql += (!nombre.equals("")) ? " AND  UPPER(NO_AP)  like '%" + nombre.toUpperCase() + "%'" : "";
            }
        } catch (ParseException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_contra_x_idcto_json(String id_ctos) {
        sql = "SELECT rhfu_print_func_pri(h.ID_PUESTO) as "
                + "func1,rhfu_print_func_sec(h.ID_PUESTO)as "
                + "func2,h.* FROM RHVD_CONTRATOS_HISTORIAL h WHERE "
                + "h.ID_CONTRATO=?";
        return jt.queryForList(sql, id_ctos.trim());
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_doc_Contrato(String id_ctro) {
        sql = "SELECT * FROM RHTV_CONTRATO_ADJUNTO where "
                + "ID_CONTRATO=?";
        return jt.queryForList(sql, id_ctro.trim());
    }

    public int Count_doc_con(String id_ctro) {
        sql = "SELECT COUNT(*) FROM RHTV_CONTRATO_ADJUNTO where "
                + "ID_CONTRATO=? and ES_CONTRATO_ADJUNTO='1'";
        return jt.queryForObject(sql, Integer.class, id_ctro.trim());
    }

    public void Eliminar_Contratos_firmados(String id_contrato) {
        jt.update("CALL RHSP_DELETE_CONTRATOS_SUBIDOS(?)", id_contrato.trim());
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> List_contrato(String id_contrato) {
        sql = "SELECT * FROM RHVD_CONTRATO_DGP where ID_CONTRATO=?";
        return jt.queryForList(sql, id_contrato.trim());
    }

    public void MODIFICAR_CONTRATO(String ID_CONTRATO, String ID_DGP,
            String FE_DESDE, String FE_HASTA, String FE_CESE,
            String ID_FUNC, String LI_CONDICION, Double CA_SUELDO,
            Double CA_REINTEGRO, Double CA_ASIG_FAMILIAR,
            Double HO_SEMANA, Double NU_HORAS_LAB, Double DIA_CONTRATO,
            String TI_TRABAJADOR, String LI_REGIMEN_LABORAL,
            String ES_DISCAPACIDAD, String TI_CONTRATO,
            String LI_REGIMEN_PENSIONARIO, String ES_CONTRATO_TRABAJADOR,
            String US_CREACION, String FE_CREACION, String US_MODIF,
            String FE_MODIF, String US_IP, String FE_VACACIO_INI,
            String FE_VACACIO_FIN, String ES_CONTRATO,
            String ID_FILIAL, String ID_PUESTO, Double CA_BONO_ALIMENTO,
            String LI_TIPO_CONVENIO, String ES_FIRMO_CONTRATO,
            Double NU_CONTRATO, String DE_OBSERVACION, String ES_APOYO,
            String TI_HORA_PAGO, String NU_DOCUMENTO,
            String ES_ENTREGAR_DOC_REGLAMENTOS, String ES_REGISTRO_HUELLA,
            String DE_REGISTRO_SISTEM_REMU, String ID_TRABAJADOR,
            Double CA_SUELDO_TOTAL, String ID_REGIMEN_LABORAL,
            String ID_MODALIDAD, String ID_SUB_MODALIDAD,
            String CO_GR_OCUPACION, String FE_SUSCRIPCION,
            String CO_TI_MONEDA, String CO_TI_REM_VARIAB,
            String DE_REMU_ESPECIE, String DE_RUC_EMP_TRAB,
            String CO_SUCURSAL, String DE_MYPE, String ES_TI_CONTRATACION,
            Double CA_BEV, String ID_TIPO_PLANILLA,
            String ES_REMUNERACION_PROCESADO, String ID_HORARIO,
            String ID_PLANTILLA_CONTRACTUAL, Double ca_bonificacion_p,
            String PRACTICANTE, String idSituacionEspecial) {
        try {
            jt.update("CALL RHSP_MODIF_CONTRATO( ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", ID_CONTRATO,
                    ID_DGP, DateFormat.toFormat1(FE_DESDE),
                    (FE_HASTA == null) ? "" : DateFormat.toFormat1(FE_HASTA),
                    FE_CESE, ID_FUNC, LI_CONDICION, CA_SUELDO, CA_REINTEGRO,
                    CA_ASIG_FAMILIAR, HO_SEMANA, NU_HORAS_LAB, DIA_CONTRATO,
                    TI_TRABAJADOR, LI_REGIMEN_LABORAL, ES_DISCAPACIDAD, TI_CONTRATO,
                    LI_REGIMEN_PENSIONARIO, "1", US_CREACION, FE_CREACION,
                    US_MODIF, FE_MODIF, US_IP, FE_VACACIO_INI, FE_VACACIO_FIN,
                    "1", ID_FILIAL, ID_PUESTO, CA_BONO_ALIMENTO, LI_TIPO_CONVENIO,
                    ES_FIRMO_CONTRATO, NU_CONTRATO, DE_OBSERVACION, ES_APOYO,
                    TI_HORA_PAGO, NU_DOCUMENTO, ES_ENTREGAR_DOC_REGLAMENTOS,
                    ES_REGISTRO_HUELLA, DE_REGISTRO_SISTEM_REMU, ID_TRABAJADOR,
                    CA_SUELDO_TOTAL, ID_REGIMEN_LABORAL, ID_MODALIDAD,
                    ID_SUB_MODALIDAD, CO_GR_OCUPACION, DateFormat.toFormat1(FE_SUSCRIPCION),
                    CO_TI_MONEDA, CO_TI_REM_VARIAB, DE_REMU_ESPECIE, DE_RUC_EMP_TRAB,
                    CO_SUCURSAL, DE_MYPE, ES_TI_CONTRATACION, CA_BEV, ID_TIPO_PLANILLA,
                    "0", ID_HORARIO, ID_PLANTILLA_CONTRACTUAL, ca_bonificacion_p,
                    PRACTICANTE, idSituacionEspecial);
        } catch (ParseException ex) {
            Logger.getLogger(ContratoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void VALIDAR_FE_CESE_CON() {
        jt.update("UPDATE RHTM_CONTRATO SET ES_CONTRATO = '0' WHERE FE_CESE <= SYSDATE");
    }

    public void HABILITAR_SI(String idc, String estado) {
        jt.update("CALL RHSP_HABILITAR_SI(?,?)", idc, estado);
    }

    public void VALIDAR_FE_HASTA_CON() {
        jt.update("CALL RHSP_VAL_ESTADO_CONTRATO");
    }

    public void validar_contrato(String id_cto) {
        jt.update("UPDATE RHTM_CONTRATO SET ES_CONTRATO = '0' WHERE "
                + "ID_CONTRATO = ?", id_cto);
    }

    public boolean validar_editar_contrato(String iduser, String idcontrato) {
        boolean x = false;
        x = (jt.queryForObject("select count(*) from RHTV_AUTORIZACION where "
                + "US_CREACION =? and ID_DGP in (select ID_DGP from "
                + "rhtm_contrato where ID_CONTRATO="
                + "?)", Integer.class, iduser, idcontrato) == 0);
        return x;

    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> ListaSelectorContrato(String id_trabajador) {
        sql = "SELECT ID_CONTRATO, TO_CHAR(FE_DESDE,'dd/mm/yyyy') AS "
                + "FE_DESDE,  TO_CHAR(FE_HASTA,'dd/mm/yyyy') AS "
                + "FE_HASTA FROM RHTM_CONTRATO  where "
                + "ID_TRABAJADOR=? order by "
                + "TO_NUMBER(SUBSTR(ID_CONTRATO,5,8)) desc";
        return jt.queryForList(sql,id_trabajador);
    }
}
