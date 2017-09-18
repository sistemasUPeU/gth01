/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author Cesar
 */
public class NotificationDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public NotificationDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public void Registrar(String Id_rol, String Es_visualizado, String Es_leido,
            String De_notification, String Di_notification, String Titulo,
            String Tipo_notification, String Id_usuario, Boolean Enable_send_email) {
        jt.update("CALL RHSP_INSERT_NOTIFICATION( ?, ?, ?, ?, ?, ?, ?, ?,?,?,?)",
                Id_rol, Es_visualizado, Es_leido, De_notification, Di_notification,
                Titulo, Tipo_notification, Id_usuario, Enable_send_email, Id_usuario,
                Id_usuario);
    }

    public int CountUnreadAuthorized(String id) {
        sql = "select count(*) as nuevas from RHTV_NOTIFICATION where "
                + "ES_VISUALIZADO=0 and TIPO_NOTIFICATION=1 and "
                + "ID_USUARIO=? order by FECHA_REG desc";
        return jt.queryForObject(sql, Integer.class, id);
    }

    public int CountUnreadUnAuthorized(String id) {
        sql = "select count(*) as nuevas from RHTV_NOTIFICATION where "
                + "ES_VISUALIZADO=0 and TIPO_NOTIFICATION=0 and "
                + "ID_USUARIO=? order by FECHA_REG desc";
        return jt.queryForObject(sql, Integer.class, id);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> List_Notifications_json(String id) {
        sql = "select * from rhtv_notification where ID_USUARIO=? "
                + "order by FECHA_REG desc";
        return jt.queryForList(sql,id);
    }
    
    public void visualizado(String id) {
        jt.update("CALL RHSP_UPDATE_VIS_NOTIFICATION (?)", id);
    }
    
    public void leido(String id) {
        jt.update("CALL RHSP_UPDATE_REA_NOTIFICATION (?)", id);
    }
    
    public List<String> PrevSteps(String id) {
        sql = "SELECT  US_CREACION FROM RHTV_AUTORIZACION WHERE ID_DGP=?";
        return jt.queryForList(sql, String.class,id);
    }

}
