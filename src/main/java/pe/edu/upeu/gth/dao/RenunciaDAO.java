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
import pe.edu.upeu.gth.interfaces.Operaciones;

/**
 *
 * @author Brandux
 */
public class RenunciaDAO implements Operaciones {

    String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    private static JdbcTemplate jt;

    public RenunciaDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

    @Override
    public ArrayList<Map<String, Object>> listar() {
        sql = "SELECT * FROM RENUNCIA r,RHTM_DGP d,RHTM_TRABAJADOR t\n"
                + "where r.ID_DGP=d.ID_DGP\n"
                + "and d.ID_TRABAJADOR=t.ID_TRABAJADOR;";
        ArrayList<Map<String, Object>> lista = new ArrayList<>();
        try {
            cn = d.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("idrenuncia", rs.getString("idrenuncia"));
                //              m.put("no_trabajador", rs.getString("no_trabajador"));
//                m.put("ap_paterno", rs.getString("ap_paterno"));
//                m.put("ap_materno", rs.getString("ap_materno"));
//                m.put("id_contrato", rs.getString("id_contrato"));
                lista.add(m);

            }
        } catch (Exception e) {
            System.out.println("Error al Listar Renuncias" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

    @Override
    public boolean add(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean edit(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Map<String, Object>> listarEmpleados() { // lista a todos los empleados por departamento.
        sql = "select rt.ID_TRABAJADOR,rt.NO_TRABAJADOR,rt.AP_PATERNO,rt.AP_MATERNO, rt.NU_DOC  ,ra.NO_AREA,rs.NO_SECCION,rp.NO_PUESTO,rc.FE_DESDE,rc.FE_HASTA "
                + "from rhtr_puesto rp, rhtd_area ra, rhtr_seccion rs, rhtm_trabajador rt, rhtm_contrato rc,rhtx_departamento rd "
                + "where rp.ID_SECCION = rs.ID_SECCION and rs.ID_AREA = ra.ID_AREA and rc.ID_PUESTO = rp.ID_PUESTO  and rc.ID_TRABAJADOR = rt.ID_TRABAJADOR and rd.ID_DEPARTAMENTO = ra.ID_DEPARTAMENTO "
                + "       and rd.ID_DEPARTAMENTO='DPT-0017' "
                + "AND RC.FE_HASTA > SYSDATE "
                + "ORDER BY ( RA.NO_AREA)";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql);
    }

    public ArrayList<Map<String, Object>> DetalleEmp(String idTR) { // Esto Lista el detalle del Trabajador.
        String sql = "select rc.ID_CONTRATO , rc.ID_DGP , rt.ID_TRABAJADOR,rt.NO_TRABAJADOR,rt.AP_PATERNO,rt.AP_MATERNO, rt.NU_DOC , rt.CL_TRA   ,ra.NO_AREA,rs.NO_SECCION,rp.NO_PUESTO,TO_CHAR(rc.FE_DESDE, 'Month DD, YYYY')AS FEC_INI ,TO_CHAR(rc.FE_HASTA, 'Month DD, YYYY')AS FEC_FIN \n"
                + "from rhtr_puesto rp, rhtd_area ra, rhtr_seccion rs, rhtm_trabajador rt, rhtm_contrato rc,rhtx_departamento rd\n"
                + "where rp.ID_SECCION = rs.ID_SECCION and rs.ID_AREA = ra.ID_AREA and rc.ID_PUESTO = rp.ID_PUESTO  and rc.ID_TRABAJADOR = rt.ID_TRABAJADOR and rd.ID_DEPARTAMENTO = ra.ID_DEPARTAMENTO\n"
                + "       and rd.ID_DEPARTAMENTO='DPT-0017' and RT.ID_TRABAJADOR = ? ";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, idTR.trim());
    }

    public ArrayList<Map<String, Object>> DetRen() {
        String sql = "SELECT * FROM RENUNCIA r,RHTM_DGP d,RHTM_TRABAJADOR t\n"
                + "where r.ID_DGP=d.ID_DGP\n"
                + "and d.ID_TRABAJADOR=t.ID_TRABAJADOR";

        return (ArrayList<Map<String, Object>>) jt.queryForList(sql);

    }
    public void Renuncia(String idContr, String idDgp, String User_au, String DirecADj, String Nom_Adj, String Desc, String Size_Adj, String Type_Adj, String Opc) {
        String sql = "{CALL RHSP_INSERT_ADJUNTARRENUNCIA ( ? , ? , ? , ? , ? , ? , ? , ? , ? )}";
        jt.update(sql, idContr, idDgp, User_au, DirecADj, Nom_Adj, Desc, Size_Adj, Type_Adj, Opc);
    }
}
