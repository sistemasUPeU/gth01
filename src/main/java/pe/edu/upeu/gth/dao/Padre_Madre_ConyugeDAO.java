/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.net.InetAddress;
import java.net.UnknownHostException;
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
public class Padre_Madre_ConyugeDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public Padre_Madre_ConyugeDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void INSERT_CONYUGE(String ES_TRABAJA_UPEU_CONYUGUE,
            String AP_NOMBRES_CONYUGUE, String FE_NAC_CONYUGUE, String TI_DOC_ID,
            String NU_DOC, String LI_INSCRIPCION_VIG_ESSALUD, String US_MODIF,
            String IP_USUARIO, String ID_TRABAJADOR, String ID_CONYUGUE) {
        try {
            jt.update("CALL RHSP_INSERT_CONYUGUE(  ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    ES_TRABAJA_UPEU_CONYUGUE, AP_NOMBRES_CONYUGUE,
                    DateFormat.toFormat1(FE_NAC_CONYUGUE), TI_DOC_ID, NU_DOC,
                    LI_INSCRIPCION_VIG_ESSALUD, US_MODIF,
                    InetAddress.getLocalHost().getHostAddress(), ID_TRABAJADOR,
                    ID_CONYUGUE);
        } catch (ParseException ex) {
            Logger.getLogger(Padre_Madre_ConyugeDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnknownHostException ex) {
            Logger.getLogger(Padre_Madre_ConyugeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void MOD_PADRE_MADRE_CONYUGUE(String AP_NOMBRES_PADRE,
            String AP_NOMBRES_MADRE, String ES_TRABAJA_UPEU_CONYUGUE,
            String AP_NOMBRES_C, String FE_NAC_C, String ID_TIPO_DOC_C,
            String NU_DOC_C, String LI_INSCRIPCION_VIG_ESSALUD_C,
            String ID_TRABAJADOR, String US_MODIF, String IP_USUARIO) {
        jt.update("CALL RHSP_MOD_PADRE_MAD_CON( ?, ?, ?, ?, ?, ?, ?, ? ,?, ?, ?)",
                AP_NOMBRES_PADRE, AP_NOMBRES_MADRE, ES_TRABAJA_UPEU_CONYUGUE,
                AP_NOMBRES_C, FE_NAC_C, ID_TIPO_DOC_C, NU_DOC_C,
                LI_INSCRIPCION_VIG_ESSALUD_C, ID_TRABAJADOR, US_MODIF, IP_USUARIO);
    }

    public boolean MOD_PADRES(String AP_NOMBRES_PADRE, String AP_NOMBRES_MADRE,
            String ID_TRABAJADOR, String USER) {
        return (jt.update("CALL RHSP_MOD_PADRES( ?, ?, ?, ?, ?)",
                AP_NOMBRES_PADRE, AP_NOMBRES_MADRE, ID_TRABAJADOR,
                USER, UserMachineProperties.getAll()) != 0);
    }

    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> Listar_Padres(String id) {
        sql = "select  id_trabajador,AP_NOMBRES_PADRE,AP_NOMBRES_MADRE from "
                + "RHTM_TRABAJADOR where id_trabajador=?";
        return jt.queryForList(sql, id);
    }

    public void INSERT_PADRES(String AP_NOMBRES_MADRE, String AP_NOMBRES_PADRE,
            String ID_TRABAJDOR, String US_MODIF, String IP_USUARIO) {
        jt.update("CALL RHSP_INSERT_PADRES(  ?, ?, ?, ?, ?)", AP_NOMBRES_PADRE,
                AP_NOMBRES_MADRE, ID_TRABAJDOR, US_MODIF, IP_USUARIO);
    }

}
