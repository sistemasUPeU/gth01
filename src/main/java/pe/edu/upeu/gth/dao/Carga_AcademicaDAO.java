/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import oracle.jdbc.OracleConnection;
import org.json.JSONArray;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import pe.edu.upeu.gth.util.DateFormat;
import pe.edu.upeu.gth.util.WebServiceClient;

/**
 *
 * @author Cesar
 */
public class Carga_AcademicaDAO {
    //MODIFICAR TODAS LAS CONSULTAS A LOS OBJETOS DAO, YA NO SE USARÁN MODELOS

    private JdbcTemplate jt;
    private String sql = "";

    public Carga_AcademicaDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public String DNI_ID_TRABAJADOR(String dni) {
        sql = "select id_trabajador  from rhtm_trabajador  where  trim(NU_DOC)=?";
        return jt.queryForObject(sql, String.class, dni.trim());
    }

    public void INSERT_CARGA_ACADEMICA(String ID_CARGA_ACADEMICA, String ES_CARGA_ACADEMICA, String CAMPUS, String ES_TIPO_DOC, String NU_DOC, String AP_PATERNO, String AP_MATERNO, String NO_TRABAJADOR, String NO_FACULTAD, String NO_EAP, String DE_CARGA, String NO_CURSO, String NU_GRUPO, String DE_HORARIO, int CA_HLAB, String DE_CONDICION, String DE_TIPO_CURSO, String ES_PROCESADO, String FE_CREACION) {
        jt.update("CALL RHSP_INSERT_CARGA_ACADEMICA( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? )", null, null, CAMPUS, ES_TIPO_DOC, NU_DOC, AP_PATERNO, AP_MATERNO, NO_TRABAJADOR, NO_FACULTAD, NO_EAP, DE_CARGA, NO_CURSO, NU_GRUPO, DE_HORARIO, CA_HLAB, DE_CONDICION, DE_TIPO_CURSO, ES_PROCESADO, null);
    }

    /* REVISAR USO PARA PODER MODIFICAR
    public String insertDGP(DGP d) {
        
    }
     */
    public String INSERT_PROCESO_CARGA_ACADEMICA(String ID_PROCESO_CARGA_AC, String ES_PROCESO_CARGA_AC, String CA_TIPO_HORA_PAGO, double CA_TOTAL_HL, String FE_DESDE, String FE_HASTA, String ES_PROCESADO, String US_CREACION, String FE_CREACION, String US_MODIF, String FE_MODIF, String IP_USUARIO, String NO_USUARIO, String ID_DGP) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_PROCESO_CARGA_AC");
        Map<String, Object> inParamMap = new HashMap<String, Object>();
        try {
            inParamMap.put("ID_PROCESO_CARGA_AC_SP", null);
            inParamMap.put("ES_PROCESO_CARGA_AC_SP", null);
            inParamMap.put("CA_TIPO_HORA_PAGO_SP", CA_TIPO_HORA_PAGO);
            inParamMap.put("CA_TOTAL_HL_SP", CA_TOTAL_HL);
            inParamMap.put("FE_DESDE_SP", DateFormat.toFormat1(FE_DESDE));
            inParamMap.put("FE_HASTA_SP", DateFormat.toFormat1(FE_HASTA));
            inParamMap.put("ES_PROCESADO_SP", ES_PROCESADO);
            inParamMap.put("US_CREACION_SP", US_CREACION);
            inParamMap.put("FE_CREACION_SP", null);
            inParamMap.put("US_MODIF_SP", US_MODIF);
            inParamMap.put("FE_MODIF_SP", FE_MODIF);
            inParamMap.put("IP_USUARIO_SP", IP_USUARIO);
            inParamMap.put("NO_USUARIO_SP", NO_USUARIO);
            inParamMap.put("ID_DGP_SP", ID_DGP);
        } catch (ParseException ex) {
            Logger.getLogger(Carga_AcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }

    /*REVISAR USO (USO DE MODELOS (DTO Objects) NO UTILIZADO)*/
    public List<Map<String, Object>> ListCarAca() {
        sql = "SELECT * FROM RHVD_CARGA_ACADEMICA where es_procesado is null or es_procesado ='0'";
        return jt.queryForList(sql);
    }

    public String INSERT_DETALLE_CARGA_ACADEMICA(String ID_DETALLE_CARGA_ACADEMICA, String ID_PROCESO_CARGA_AC, String ID_CARGA_ACADEMICA, String ES_DETALLE_CARGA) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_DETALLE_CARGA_ACA");
        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("ID_DETALLE_CARGA_ACADEMICA_SP", null);
        inParamMap.put("ID_PROCESO_CARGA_AC_SP", ID_PROCESO_CARGA_AC);
        inParamMap.put("ID_CARGA_ACADEMICA_SP", ID_CARGA_ACADEMICA);
        inParamMap.put("ES_DETALLE_CARGA_SP", null);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }

    public String INSERT_CARGA_ACADEMICA(String ID_CARGA_ACADEMICA, String ES_CARGA_ACADEMICA, String CAMPUS, String ES_TIPO_DOC, String NU_DOC, String AP_PATERNO, String AP_MATERNO, String NO_TRABAJADOR, String NO_FACULTAD, String NO_EAP, String DE_CARGA, String NO_CURSO, String NU_GRUPO, String DE_HORARIO, double CA_HLAB, String DE_CONDICION, String DE_TIPO_CURSO, String ES_PROCESADO, String FE_CREACION, String ID_PROCESO_CARGA_AC) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_CARGA_ACADEMICA");
        Map<String, Object> inParamMap = new HashMap<>();
        inParamMap.put("ID_CARGA_ACADEMICA_SP", null);
        inParamMap.put("ES_CARGA_ACADEMICA_SP", null);
        inParamMap.put("CAMPUS_SP", CAMPUS);
        inParamMap.put("ES_TIPO_DOC_SP", ES_TIPO_DOC);
        inParamMap.put("NU_DOC_SP", NU_DOC);
        inParamMap.put("AP_PATERNO_SP", AP_PATERNO);
        inParamMap.put("AP_MATERNO_SP", AP_MATERNO);
        inParamMap.put("NO_TRABAJADOR_SP", NO_TRABAJADOR);
        inParamMap.put("NO_FACULTAD_SP", NO_FACULTAD);
        inParamMap.put("NO_EAP_SP", NO_EAP);
        inParamMap.put("DE_CARGA_SP", DE_CARGA);
        inParamMap.put("NO_CURSO_SP", NO_CURSO);
        inParamMap.put("NU_GRUPO_SP", NU_GRUPO);
        inParamMap.put("DE_HORARIO_SP", DE_HORARIO);
        inParamMap.put("CA_HLAB_SP", CA_HLAB);
        inParamMap.put("DE_CONDICION_SP", DE_CONDICION);
        inParamMap.put("DE_TIPO_CURSO_SP", DE_TIPO_CURSO);
        inParamMap.put("ES_PROCESADO_SP", ES_PROCESADO);
        inParamMap.put("FE_CREACION_SP", FE_CREACION);
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("ID_TABLE");
    }

    public String INSERT_PAGO_DOCENTE(String ID_PAGO_DOCENTE, String NU_CUOTA, double CA_CUOTA, String FE_PAGO, String ES_PAGO_DOCENTE, String ID_PROCESO_CARGA_AC, String FE_CREACION, String US_MODIF, String FE_MODIF, String IP_USUARIO, String NO_USUARIO) {

        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("RHSP_INSERT_PAGO_DOCENTE");
        Map<String, Object> inParamMap = new HashMap<>();
        try {
            inParamMap.put("ID_PAGO_DOCENTE_SP", null);
            inParamMap.put("NU_CUOTA_SP", NU_CUOTA);
            inParamMap.put("CA_CUOTA_SP", CA_CUOTA);
            inParamMap.put("FE_PAGO_SP", DateFormat.toFormat1(FE_PAGO));
            inParamMap.put("ES_PAGO_DOCENTE_SP", ES_PAGO_DOCENTE);
            inParamMap.put("ID_PROCESO_CARGA_AC_SP", ID_PROCESO_CARGA_AC);
            inParamMap.put("FE_CREACION_SP", null);
            inParamMap.put("US_MODIF_SP", US_MODIF);
            inParamMap.put("FE_MODIF_SP", FE_MODIF);
            inParamMap.put("IP_USUARIO_SP", IP_USUARIO);
            inParamMap.put("NO_USUARIO_SP", NO_USUARIO);
        } catch (ParseException ex) {
            Logger.getLogger(Carga_AcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String)simpleJdbcCallResult.get("ID_TABLE");
    }

    public List<Map<String, Object>> Lista_detalle_academico(String idtr, String facultad, String eap, String ciclo, String dni) {
        sql = "SELECT * FROM RHVD_DETALLE_CARGA_ACADEMICA WHERE campus is not null";
        sql += (idtr.equals("")) ? "" : " and TRIM(ID_TRABAJADOR)= '" + idtr + "' ";
        sql += (facultad.trim().equals("")) ? "" : " and TRIM(NO_FACULTAD)='" + facultad.trim() + "' ";
        sql += (eap.equals("")) ? "" : "  AND TRIM(NO_EAP)='" + eap.trim() + "' ";
        sql += (ciclo.trim().equals("")) ? "" : "  AND TRIM(DE_CARGA)='" + ciclo.trim() + "' ";
        sql += (dni.trim().equals("")) ? "" : "  AND TRIM(NU_DOC)='" + dni.trim() + "' ";
        return jt.queryForList(sql);
    }

    public void PROCESAR_CARGA_ACADEMICA(String id_proceso, String iddgp) {
        jt.update("CALL PROCESAR_CARGA_ACADEMICA( ?, ? )", id_proceso, iddgp.trim());
    }

    public List<Map<String, Object>> Cuotas_Pago_Docente(String fe_desde, String fe_hasta, double pago_semanal) {
        sql = "select html  from table(rhfu_cuotas_docente(to_date(?,'dd/mm/yyyy'),to_date(?,'dd/mm/yyyy'),?))";
        return jt.queryForList(sql, fe_desde, fe_hasta, pago_semanal);
    }

    public List<Map<String, Object>> List_Carga_Academica_WS(String semestre) {
        sql = "SELECT cat.NU_DOC, cat.AP_PATERNO , cat.AP_MATERNO , cat.NO_TRABAJADOR, cat.NO_FACULTAD,cat.DE_CARGA "
                + "FROM RHTM_CARGA_ACADEMICA_TEMP cat "
                + "WHERE cat.DE_CARGA = ?"
                + "GROUP BY cat.NU_DOC, cat.AP_PATERNO , cat.AP_MATERNO , cat.NO_TRABAJADOR, cat.NO_FACULTAD,cat.DE_CARGA "
                + "ORDER BY cat.NU_DOC, cat.AP_PATERNO , cat.AP_MATERNO , cat.NO_TRABAJADOR, cat.NO_FACULTAD,cat.DE_CARGA ASC";
        return jt.queryForList(sql, semestre);
    }

    public Map<String, Object> getProcesoCargaAcademciaById(String id) {
        sql = "select ID_PROCESO_CARGA_AC,ES_PROCESO_CARGA_AC,ID_TIPO_HORA_PAGO,CA_TOTAL_HL,to_char(FE_DESDE,'dd/mm/yyyy') as fe_desde ,to_char(FE_HASTA,'dd/mm/yyyy') as fe_hasta,ES_PROCESADO,ID_DGP from RHTM_PROCESO_CARGA_ACADEMICA where ID_PROCESO_CARGA_AC=?";
        return jt.queryForMap(sql, id);
    }

    public String syncupCargaAcademica(String semestre, String methodProperties[]) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jt).withProcedureName("rhsp_ws_carga_academica");
        Map<String, Object> inParamMap = new HashMap<>();
        JSONArray arr = null;
        String response = null;
        try {
            arr = WebServiceClient.getData(semestre, methodProperties);
            int length = arr.length();
            String[] campus = new String[length];
            String[] tipo_doc = new String[length];
            String[] nu_doc = new String[length];
            String[] app = new String[length];
            String[] apm = new String[length];
            String[] nombre = new String[length];
            String[] facu = new String[length];
            String[] eap = new String[length];
            String[] de_carga = new String[length];
            String[] curso = new String[length];
            String[] grupo = new String[length];
            String[] horario = new String[length];
            double[] hb_lab = new double[length];
            String[] hb_de_condicion = new String[length];
            String[] hb_ti_curso = new String[length];
            System.out.println("registros aceptados:" + length);
            for (int i = 0; i < arr.length(); i++) {
                hb_ti_curso[i] = (arr.getJSONObject(i).getJSONObject("tipocurso").has("content")) ? String.valueOf(arr.getJSONObject(i).getJSONObject("tipocurso").get("content")) : "";
                horario[i] = (arr.getJSONObject(i).getJSONObject("horario").has("content")) ? String.valueOf(arr.getJSONObject(i).getJSONObject("horario").get("content")) : "";
                campus[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("campus").get("content"));
                grupo[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("grupo").get("content"));
                nu_doc[i] = (arr.getJSONObject(i).getJSONObject("numerodocumento").has("content")) ? String.valueOf(arr.getJSONObject(i).getJSONObject("numerodocumento").get("content")) : "";
                nombre[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("nombre").get("content"));
                //System.out.println(nombre[i]);
                hb_de_condicion[i] = (arr.getJSONObject(i).getJSONObject("condicion").has("content")) ? String.valueOf(arr.getJSONObject(i).getJSONObject("condicion").get("content")) : "";
                de_carga[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("carga").get("content"));
                curso[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("nombrecurso").get("content"));
                app[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("apepat").get("content"));
                apm[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("apemat").get("content"));
                eap[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("eap").get("content"));
                hb_lab[i] = (arr.getJSONObject(i).getJSONObject("hlab").has("content")) ? Double.parseDouble(String.valueOf(arr.getJSONObject(i).getJSONObject("hlab").get("content"))) : 0.0;
                tipo_doc[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("tipodocumento").get("content"));
                facu[i] = String.valueOf(arr.getJSONObject(i).getJSONObject("facultad").get("content"));
                //  eapId=...
            }
            Array array_to_pass1 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_CAMPUS", campus);
            Array array_to_pass2 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_ES_TIPO_DOC", tipo_doc);
            Array array_to_pass3 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NU_DOC", nu_doc);
            Array array_to_pass4 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_AP_PATERNO", app);
            Array array_to_pass5 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_AP_MATERNO", apm);
            Array array_to_pass6 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NO_TRABAJADOR", nombre);
            Array array_to_pass7 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NO_FACULTAD", facu);
            Array array_to_pass8 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NO_EAP", eap);
            Array array_to_pass9 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_DE_CARGA", de_carga);
            Array array_to_pass10 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NO_CURSO", curso);
            Array array_to_pass11 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_NU_GRUPO", grupo);
            Array array_to_pass12 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_DE_HORARIO", horario);
            Array array_to_pass13 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_CA_HLAB", hb_lab);
            Array array_to_pass14 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_DE_CONDICION", hb_de_condicion);
            Array array_to_pass15 = ((OracleConnection) jt.getDataSource().getConnection()).createOracleArray("ARR_WS_DE_TIPO_CURSO", hb_ti_curso);
            
            inParamMap.put("ARR_CAMPUS", array_to_pass1);
            inParamMap.put("ARR_ES_TIPO_DOC", array_to_pass2);
            inParamMap.put("ARR_NU_DOC", array_to_pass3);
            inParamMap.put("ARR_AP_PATERNO", array_to_pass4);
            inParamMap.put("ARR_AP_MATERNO", array_to_pass5);
            inParamMap.put("ARR_NO_TRABAJADOR", array_to_pass6);
            inParamMap.put("ARR_NO_FACULTAD", array_to_pass7);
            inParamMap.put("ARR_NO_EAP", array_to_pass8);
            inParamMap.put("ARR_DE_CARGA", array_to_pass9);
            inParamMap.put("ARR_NO_CURSO", array_to_pass10);
            inParamMap.put("ARR_NU_GRUPO", array_to_pass11);
            inParamMap.put("ARR_DE_HORARIO", array_to_pass12);
            inParamMap.put("ARR_CA_HLAB", array_to_pass13);
            inParamMap.put("ARR_DE_CONDICION", array_to_pass14);
            inParamMap.put("ARR_DE_TIPO_CURSO", array_to_pass15);
            
        } catch (Exception ex) {
            Logger.getLogger(Carga_AcademicaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        SqlParameterSource in = new MapSqlParameterSource(inParamMap);
        Map<String, Object> simpleJdbcCallResult = simpleJdbcCall.execute(in);
        return (String) simpleJdbcCallResult.get("logRegister");
    }
}
