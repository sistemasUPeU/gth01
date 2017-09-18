/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.config.AppConfig;

/**
 *
 * @author Leandro Burgos
 */
public class CUniversitarioDAO {

    String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    private final JdbcTemplate jt;

    public CUniversitarioDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    public ArrayList<Map<String, ?>> listar_c_cu(String id_req) {
        ArrayList<Map<String, ?>> lista = new ArrayList<>();
        sql = "select (select a.NO_DEP from RHTX_DEPARTAMENTO a where ID_DEPARTAMENTO=ro.ID_DEPARTAMENTO) departamento, "
                + " ro.ID_DEPARTAMENTO DEPARTAMENTO_ID,COUNT(ro.ID_DEPARTAMENTO) cantidad "
                + " from RHTV_AUTORIZACION ra,RHTM_DGP rd,RHTR_DETALLE_REQ_PROCESO rr,RHTR_REQUERIMIENTO rt, "
                + " RHTR_PUESTO rp,RHTR_SECCION rs,RHTD_AREA re,RHTX_DEPARTAMENTO ro "
                + " where ra.ID_DGP=rd.ID_DGP "
                + " and ra.ID_DETALLE_REQ_PROCESO=rr.ID_DETALLE_REQ_PROCESO "
                + " and rr.ID_REQUERIMIENTO=rt.ID_REQUERIMIENTO "
                + " and rd.ID_PUESTO=rp.ID_PUESTO "
                + " and rp.ID_SECCION=rs.ID_SECCION "
                + " and rs.ID_AREA=re.ID_AREA "
                + " and re.ID_DEPARTAMENTO=ro.ID_DEPARTAMENTO "
                + " and rt.ID_REQUERIMIENTO=? "
                + " group by ro.ID_DEPARTAMENTO ";

        try {
            ps = d.getConnection().prepareStatement(sql);
            ps.setString(1, id_req);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> s = new HashMap<>();
                s.put("no_dep", rs.getString("DEPARTAMENTO"));
                s.put("id_dep", rs.getString("DEPARTAMENTO_ID"));
                s.put("cantidad", rs.getString("CANTIDAD"));
                lista.add(s);
            }
        } catch (Exception e) {
            System.out.println("Error al listar abc : " + e);
        } finally {
            try {
                d.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(CUniversitarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return lista;
    }
}
