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
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.interfaces.Operaciones;

/**
 *
 * @author Alfa003
 */
public class ejemploDAO implements Operaciones {

    String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    //-------------------------------
    private JdbcTemplate jt;

    public ejemploDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    // -------
    public ArrayList<Map<String, Object>> listajt(String id, String codigo) {

        sql = "select * from rhtr_puesto where ID_PUESTO = ? ";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, id.trim(), codigo.trim());
    }

    //---------------
    @Override
    public ArrayList<Map<String, Object>> listar() {
        sql = "select * from rhtr_puesto where id_puesto=?";
        ArrayList<Map<String, Object>> lista = new ArrayList<>();
        try {
            ps = d.getConnection().prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", rs.getString(1));
                m.put("puesto", rs.getString(2));
                m.put("nombre", rs.getString(3));
                m.put("estado", rs.getString(4));
                m.put("id_seccion", rs.getString(5));
                m.put("codigo", rs.getString(6));
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println("Error al Listar Puestos" + e);
        }
        return lista;
    }

    @Override
    public boolean add(Object o) {
        boolean p = false;
        sql = "INSERT INTO rhtr_puesto VALUES(null,?,?,?,?,?)";
        Map<String, Object> m = (Map<String, Object>) o;
        try {
            ps = d.getConnection().prepareStatement(sql);
            ps.setString(1, m.get("nombre").toString());
            ps.setString(2, m.get("corto").toString());
            ps.setString(3, m.get("estado").toString());
            ps.setString(4, m.get("id_seccion").toString());
            ps.setString(5, m.get("codigo").toString());
            int r = ps.executeUpdate();
            if (r > 0) {
                p = true;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al agregar Puesto " + e);
            p = false;
        }
        return p;
    }

    @Override
    public boolean edit(Object o) {
        boolean p = false;
        sql = "UPDATE rhtr_puesto SET ESTADO=? ";
        Map<String, Object> m = (Map<String, Object>) o;
        try {
            ps = d.getConnection().prepareStatement(sql);
            ps.setInt(1, Integer.parseInt(m.get("estado").toString()));
            int r = ps.executeUpdate();
            if (r > 0) {
                p = true;
            }
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al editar Puestos " + e);
        }
        return p;
    }

    @Override
    public boolean delete(Object o) {
        boolean p = true;
        sql = "delete from rhtr_puesto where id_puesto=?";
        try {
            ps = d.getConnection().prepareStatement(sql);
            ps.setString(1, o.toString());
            ps.executeUpdate();
        } catch (SQLException | NumberFormatException e) {
            System.out.println("Error al eliminar PUESTO " + e);
        }
        return p;
    }

    public int asignar(Object o) {
        int a = 0;
        String sql = "delete from rhtr_puesto where id_puesto=?";
        try {
            a = jt.update(sql, o.toString());
            a = 1;
        } catch (Exception e) {
            System.out.println("error");
        }
        return a;

    }

    public ArrayList<Map<String, Object>> listara(String id, String codigo) {
        sql = "select * from rhtr_puesto where id_puesto=? and co_grupo=?";
        ArrayList<Map<String, Object>> lista = new ArrayList<>();
        try {
            ps = d.getConnection().prepareStatement(sql);
            ps.setString(1, id);
            ps.setString(2, codigo);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("id", rs.getString(1));
                m.put("puesto", rs.getString(2));
                m.put("nombre", rs.getString(3));
                m.put("estado", rs.getString(4));
                m.put("id_seccion", rs.getString(5));
                m.put("codigo", rs.getString(6));
                lista.add(m);
            }
        } catch (Exception e) {
            System.out.println("Error al Listar Puestos" + e);
        }
        return lista;
    }
}
