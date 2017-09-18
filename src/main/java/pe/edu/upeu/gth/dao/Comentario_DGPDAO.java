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
public class Comentario_DGPDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public Comentario_DGPDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    public void INSERT_COMENTARIO_DGP(String ID_COMENTARIO_DGP, String ID_DGP, String ID_AUTORIZACION, String CM_COMENTARIO, String US_CREACION, String FE_CREACION, String US_MODIFICACION, String FE_MODIFICACION, String ES_COMENTARIO_DGP) {
        jt.update("CALL RHSP_INSERT_COMENTARIO_DGP( ?, ?, ?, ?, ?, ?, ?, ?)", ID_COMENTARIO_DGP, ID_DGP, ID_AUTORIZACION, CM_COMENTARIO, US_CREACION, US_MODIFICACION, FE_MODIFICACION, ES_COMENTARIO_DGP);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS IN CONTROLLERS IF NECESSARY)*/
    public List<Map<String, Object>> List_Comentario_DGP(String id_dgp) {
        sql = "select * from rhtr_comentario_dgp cm, "
                + "rhvd_usuario u where cm.us_creacion=u.id_usuario "
                + "and cm.id_dgp=? order by "
                + "cm.id_comentario_dgp ASC";
        return jt.queryForList(sql,id_dgp);
    }
    
    public String Comentario_dgp_aut(String iddgp, String id_aut) {
        sql = "SELECT (CM_COMENTARIO||'/'||US_CREACION||'/'||to_char"
                + "(FE_CREACION,'dd-mm-yy'))AS INF FROM "
                + "RHTR_COMENTARIO_DGP WHERE ID_DGP=? "
                + "and ID_AUTORIZACION=?";
        return jt.queryForObject(sql, String.class,iddgp.trim(),id_aut.trim());
    }
}
