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
import pe.edu.upeu.gth.interfaz.CRUDOperations;;

/**
 *
 * @author Leandro Burgos
 */
public class RolDAO implements CRUDOperations {

    String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    private final JdbcTemplate jt;

    public RolDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    public ArrayList<Map<String, Object>> listar() {
        sql = "select * from RHTR_ROL order by no_rol asc";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql);
    }

    public boolean add(Object o) {
        boolean p = false;
        sql = "{CALL RHSP_INSERT_ROL( null,?,?)}";
        Map<String, Object> m = (Map<String, Object>) o;
        try {
            cs = d.getConnection().prepareCall(sql);
            cs.setString(1, m.get("nombre").toString());
            cs.setString(2, m.get("estado").toString());
            int r = cs.executeUpdate();
            if (r > 0) {
                p = true;
            }
        } catch (SQLException e) {
            System.out.println("Error al agregar Rol " + e);
            p = false;
        } finally {
            try {
                d.getConnection().close();
            } catch (SQLException ex) {
                Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return p;
    }

    public boolean edit(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Map<String, Object>> List_rol(String idrol) {
        sql = "select * from RHTR_ROL where trim(id_rol)=?";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, idrol.trim());
    }

    public ArrayList<Map<String, Object>> List_Modulos(Object idrol) {
        sql = "select DISTINCT(ID_MODULO) AS ID_MODULO,NO_MODULO ,DE_MODULO, IC_MODULO,ID_ROL from  RHVD_PRIVILEGIO WHERE trim(id_rol)=?";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, idrol.toString().trim());
    }

    public ArrayList<Map<String, Object>> Listar_Rol_Privilegio(String idrol) {
        sql = "SELECT P.NO_LINK, e.ES_DETALLE_PRIVILEGIO,e.ID_DETALLE_PRIVILEGIO FROM RHTD_DETALLE_PRIVILEGIO e , RHTR_ROL r, RHTV_PRIVILEGIO WHERE e.ID_ROL = r.ID_ROL AND e.ID_PRIVILEGIO=P.ID_PRIVILEGIO AND e.ID_ROL =?";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, idrol.trim());
    }

}
