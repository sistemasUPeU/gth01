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
import pe.edu.upeu.gth.properties.UserMachineProperties;
import pe.edu.upeu.gth.util.DateFormat;

/**
 *
 * @author Cesar
 */
public class Datos_Hijo_TrabajadorDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Datos_Hijo_TrabajadorDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void INSERT_DATOS_HIJO_TRABAJADOR(String ID_DATOS_HIJOS_TRABAJADOR,
            String ID_TRABAJADOR, String AP_PATERNO, String AP_MATERNO,
            String NO_HIJO_TRABAJADOR, String FE_NACIMIENTO,
            String ES_SEXO, String ES_TIPO_DOC, String NU_DOC,
            String ES_PRESENTA_DOCUMENTO, String ES_INSCRIPCION_VIG_ESSALUD,
            String ES_ESTUDIO_NIV_SUPERIOR, String US_CREACION,
            String FE_CREACION, String US_MODIF, String FE_MODIF,
            String IP_USUARIO, String ES_DATOS_HIJO_TRABAJADOR) {
        try {
            jt.update("CALL RHSP_INSERT_DA_HI_TRA( ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", null, ID_TRABAJADOR,
                    AP_PATERNO, AP_MATERNO, NO_HIJO_TRABAJADOR,
                    DateFormat.toFormat1(FE_NACIMIENTO), ES_SEXO,
                    ES_TIPO_DOC.trim(), NU_DOC, ES_PRESENTA_DOCUMENTO,
                    ES_INSCRIPCION_VIG_ESSALUD, ES_ESTUDIO_NIV_SUPERIOR,
                    US_CREACION, null, null, null, UserMachineProperties.getAll(),
                    "1");
        } catch (ParseException ex) {
            Logger.getLogger(Datos_Hijo_TrabajadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> LISTA_HIJOS(String id) {
        sql = "select ID_DATOS_HIJOS_TRABAJADOR, ID_TRABAJADOR,"
                + "AP_PATERNO,AP_MATERNO,NO_HIJO_TRABAJADOR,"
                + "TO_CHAR(FE_NACIMIENTO,'yyyy-mm-dd') AS FE_NACIMIENTO, "
                + "ES_SEXO, ES_TIPO_DOC, NU_DOC, ES_PRESENTA_DOCUMENTO, "
                + "ES_INSCRIPCION_VIG_ESSALUD, ES_ESTUDIO_NIV_SUPERIOR, "
                + "US_CREACION,FE_CREACION,US_MODIF,FE_MODIF,IP_USUARIO,"
                + "ES_DATOS_HIJO_TRABAJADOR from "
                + "RHTD_DATOS_HIJO_TRABAJADOR where ID_TRABAJADOR=?";
        return jt.queryForList(sql, id);
    }

    public int ASIGNACION_F(String idtr) {
        sql = "select count(*) from RHTD_DATOS_HIJO_TRABAJADOR where "
                + "to_number((sysdate - FE_NACIMIENTO)/365 ) <18 and "
                + "ID_TRABAJADOR=?";
        return jt.queryForObject(sql, Integer.class, idtr.trim());
    }

    public void MOD_HIJOS_TRAB(String ID_DATOS_HIJOS_TRABAJADOR,
            String AP_PATERNO, String AP_MATERNO,
            String NO_HIJO_TRABAJADOR, String FE_NACIMIENTO,
            String ES_SEXO, String ES_TIPO_DOC, String NU_DOC,
            String ES_INSCRIPCION_VIG_ESSALUD,
            String ES_ESTUDIO_NIV_SUPERIOR, String id_usuario) {
        try {
            jt.update("CALL RHSP_MOD_HIJOS( ?, ?, ?, ?, ?, ?, ?, ?, ?, "
                    + "?, ?, ?)", ID_DATOS_HIJOS_TRABAJADOR, AP_PATERNO,
                    AP_MATERNO, NO_HIJO_TRABAJADOR,
                    DateFormat.toFormat1(FE_NACIMIENTO), ES_SEXO, ES_TIPO_DOC,
                    NU_DOC, ES_INSCRIPCION_VIG_ESSALUD, ES_ESTUDIO_NIV_SUPERIOR,
                    id_usuario, UserMachineProperties.getAll());
        } catch (ParseException ex) {
            Logger.getLogger(Datos_Hijo_TrabajadorDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void ELIMINAR_HIJO(String id_hijo, String id_id_trabajador) {
        sql = "UPDATE RHTD_DATOS_HIJO_TRABAJADOR SET "
                + "ES_DATOS_HIJO_TRABAJADOR = '0' WHERE "
                + "ID_TRABAJADOR = ? and ID_DATOS_HIJOS_TRABAJADOR = ?";
        jt.update(sql, id_id_trabajador, id_hijo);
    }

    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String, Object>> LISTA_HIJO(String idHijo, String idtr) {
        sql = "SELECT ID_DATOS_HIJOS_TRABAJADOR,ID_TRABAJADOR,"
                + "AP_PATERNO,AP_MATERNO,NO_HIJO_TRABAJADOR,"
                + "to_char(FE_NACIMIENTO,'yyyy-mm-dd')fe_nacimiento, "
                + "ES_SEXO,ES_TIPO_DOC,NU_DOC, ES_PRESENTA_DOCUMENTO, "
                + "ES_INSCRIPCION_VIG_ESSALUD, ES_ESTUDIO_NIV_SUPERIOR, "
                + "US_CREACION, FE_CREACION, US_MODIF, FE_MODIF, "
                + "IP_USUARIO, ES_DATOS_HIJO_TRABAJADOR from "
                + "RHTD_DATOS_HIJO_TRABAJADOR where "
                + "ID_TRABAJADOR=? and ID_DATOS_HIJOS_TRABAJADOR =?";
        return jt.queryForList(sql, idtr.trim(), idHijo.trim());
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_hijo_filtro(String desde,
            String hasta, String edad, String nom, String pat,
            String mat, String dn, String gen) {
        sql = "SELECT * FROM RHVD_FILTRO_EDAD ";
        sql += (!desde.equals("") & !(hasta.equals(""))) ? "where EDAD BETWEEN '" + desde.trim() + "' and '" + hasta.trim() + "'" : "";
        sql += (!edad.equals("")) ? "where EDAD='" + edad.trim() + "'" : "";
        sql += (!nom.equals("")) ? "where UPPER(NO_HIJO_TRABAJADOR)='" + nom.trim().toUpperCase() + "'" : "";
        sql += (!pat.equals("")) ? "where UPPER(AP_PATERNO)='" + pat.trim().toUpperCase() + "'" : "";
        sql += (!mat.equals("")) ? "where UPPER(AP_MATERNO)='" + mat.trim().toUpperCase() + "'" : "";
        sql += (!dn.equals("")) ? "where DNI='" + dn.trim() + "'" : "";
        sql += (!gen.equals("")) ? "where genero='" + gen.trim() + "'" : "";
        return jt.queryForList(sql);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Listar_Cumpleaños(String mes,
            String dia, String aps, String dep, String are,
            String sec, String pue, String fec, String edad,
            String ape, String mat, String nom, String tip, String num) {
        sql = "SELECT * FROM RHVD_FILTRO_CUMPL_TRAB ";
        sql += (!aps.equals("")) ? "Where UPPER(CO_APS)='" + aps.trim().toUpperCase() + "'" : "";
        sql += (!dep.equals("")) ? "Where UPPER(DEPARTAMENTO)='" + dep.trim().toUpperCase() + "'" : "";
        sql += (!are.equals("")) ? "Where UPPER(AREA)='" + are.trim().toUpperCase() + "'" : "";
        sql += (!sec.equals("")) ? "Where UPPER(SECCION)='" + sec.trim().toUpperCase() + "'" : "";
        sql += (!pue.equals("")) ? "Where UPPER(PUESTO)='" + pue.trim().toUpperCase() + "'" : "";
        //sql += (!fec.equals("")) ? "Where FECHA_NAC='" + fec.trim() + "'" : "";            
        sql += (!edad.equals("")) ? "Where EDAD='" + edad.trim() + "'" : "";
        sql += (!ape.equals("")) ? "Where UPPER(AP_PATERNO)='" + ape.trim().toUpperCase() + "'" : "";
        sql += (!mat.equals("")) ? "Where UPPER(AP_MATERNO)='" + mat.trim().toUpperCase() + "'" : "";
        sql += (!nom.equals("")) ? "Where UPPER(NO_TRABAJADOR)='" + nom.trim().toUpperCase() + "'" : "";
        sql += (!tip.equals("")) ? "Where UPPER(TIPO)='" + tip.trim().toUpperCase() + "'" : "";
        sql += (!num.equals("")) ? "Where NU_DOC='" + num.trim() + "'" : "";
        //buscar por rango de mes de cumpleaÃ±os*/

        sql += (!mes.equals("") & !mes.equals("13")) ? "where mes='" + mes.trim() + "' " : "";
        sql += (!mes.equals("") & mes.equals("13")) ? "" : "";
        sql += (!dia.equals("")) ? "and dia='" + dia.trim() + "'" : "";
        return jt.queryForList(sql);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> Lis_Hijos_id_tr(String idtr) {
        sql = "select ID_DATOS_HIJOS_TRABAJADOR, ID_TRABAJADOR, "
                + "AP_PATERNO, AP_MATERNO, NO_HIJO_TRABAJADOR,"
                + "TO_CHAR( TO_DATE(FE_NACIMIENTO,'YYYY/MM/DD'),"
                + "'DD/MM/YYYY') AS FE_NACIMIENTO , SEXO, NU_DOC, "
                + "ES_PRESENTA_DOCUMENTO, ESSALUD, SUPERIOR, "
                + "US_CREACION, FE_CREACION, US_MODIF, FE_MODIF, "
                + "IP_USUARIO, ES_DATOS_HIJO_TRABAJADOR, "
                + "DE_TIP_DOC from rhvd_detalle_hijo where "
                + "ID_TRABAJADOR=? and ES_DATOS_HIJO_TRABAJADOR = '1' ";
        return jt.queryForList(sql,idtr);
    }
}
