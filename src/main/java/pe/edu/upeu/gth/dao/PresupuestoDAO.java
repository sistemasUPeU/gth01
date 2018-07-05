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
public class PresupuestoDAO {

    JdbcTemplate jt = new JdbcTemplate();
    String sql = "";

    public PresupuestoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }

    public boolean Reg_PresupuestoArea(Object s) {
        Map<String, Object> m = (Map<String, Object>) s;
        return (jt.update("CALL INSERT_PRESUPUESTO (?,?,?,?,?)", m.get("idA").toString(),
                Double.parseDouble(m.get("PA").toString()), m.get("f_i").toString(),
                m.get("f_fin").toString(), Integer.parseInt(m.get("NT").toString())) != 0);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> comprobar(String idArea) {
        sql = "select count(dgp.ID_TRABAJADOR) ntr,sum(dgp.CA_SUELDO*12) nsu "
                + "from rhtm_contrato rc,rhtm_dgp dgp,rhtr_puesto rp,rhtr_seccion rs,rhtd_area ra,presupuesto pr "
                + "where dgp.ID_DGP=rc.ID_DGP "
                + "and dgp.ID_PUESTO=rp.ID_PUESTO "
                + "and rs.ID_SECCION=rp.ID_SECCION "
                + "and rs.ID_AREA=ra.ID_AREA "
                + "and pr.FE_DESDE < rc.FE_DESDE "
                + "and pr.F_HASTA > rc.FE_HASTA "
                + "and pr.IDAREA=ra.ID_AREA "
                + "and ra.ID_AREA=? ";
        return jt.queryForList(sql, idArea);
    }
    /*CHECK USE OF THIS METHOD (CHANGE KEY VALUES OF MAP OBJECTS 
    IN CONTROLLERS IF NECESSARY). ONCE EDITED, 
    PLEASE DELETE THESE COMMENTS IF THEY  ARE ALREADY NOT NECESSARY*/
    public List<Map<String, Object>> dataPresupuesto(String idArea) {
        sql = "select * from PRESUPUESTO where sysdate BETWEEN FE_DESDE and F_HASTA and IDAREA=?";
        return jt.queryForList(sql, idArea);
    }

}
