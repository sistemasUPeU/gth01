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
public class Detalle_PrivilegioDAO {
    JdbcTemplate jt=new JdbcTemplate();
    String sql="";
    public Detalle_PrivilegioDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    
    public void Registrar_Detalle_Priv(String id_rol, String nu_orden, String id_priv, String es_detalle_priv) {
        String id_det_priv = "";
        jt.update("CALL RHSP_INSERT_DET_PRIV(?,?,?,?,?)",id_det_priv.trim(),id_rol.trim(),nu_orden.trim(),id_priv.trim(),es_detalle_priv.trim());
    }
    
    public void Mod_Detalle_Priv(String id_det_pri, int nu_orden, String id_priv, String es_detalle_priv) {
        jt.update("CALL RHSP_MOD_DET_PRIV(?,?,?,?)",id_det_pri,id_priv,nu_orden,es_detalle_priv);
    }
    
    public void Elim_Detalle_Priv(String ID_pri_rol) {
        jt.update("CALL RHSP_ELIMINAR_DET_PRI(?)",ID_pri_rol);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String,Object>> List_det_pr_id(String id_pr) {
        sql = "select * from RHTD_DETALLE_PRIVILEGIO where "
                + "ID_DETALLE_PRIVILEGIO =?";
        return jt.queryForList(sql, id_pr);
    }
    /*CHECK USE OF THIS METHOD (DTO OBJECTS WERE REPlACED, WE USE MAP OBJECTS INSTEAD)*/
    public List<Map<String,Object>> List_det_pri_id(String id_pr) {
        sql = "select d.*,r.NO_ROL from RHTD_DETALLE_PRIVILEGIO d, "
                + "RHTR_ROL r where d.ID_ROL=r.ID_ROL and "
                + "d.ID_DETALLE_PRIVILEGIO =?";
        return jt.queryForList(sql, id_pr);
    }
    
    public void Desc_r_pr(String id_pr_r) {
        jt.update("CALL RHSP_DESACTIVAR_DET_PRI(?)",id_pr_r);
    }
    
    public void Act_r_pr(String id_pr_r) {
        jt.update("CALL RHSP_ACTIVAR_DET_PRI(?)",id_pr_r);
    }
}
