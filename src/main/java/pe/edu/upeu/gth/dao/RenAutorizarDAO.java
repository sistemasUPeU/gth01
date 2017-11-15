package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class RenAutorizarDAO implements CRUDOperations{
	String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    private static JdbcTemplate jt;

    public RenAutorizarDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

	@Override
	 public ArrayList<Map<String, Object>> listar() {
        sql = "SELECT con.ID_TRABAJADOR,tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO,pues.NO_PUESTO,area.NO_AREA,dep.NO_DEP,tipocon.DE_TI_CONTRATO, MIN(con.FE_DESDE) AS FECHA_CONTRATO,tra.NU_DOC, dgp.ES_MFL\r\n" + 
        		"FROM RHTM_CONTRATO con\r\n" + 
        		"LEFT JOIN RHTX_TIPO_CONTRATO tipocon ON con.TI_CONTRATO=tipocon.ID_TIPO_CONTRATO\r\n" + 
        		"LEFT JOIN  RHTR_PUESTO pues ON con.ID_PUESTO=pues.ID_PUESTO \r\n" + 
        		"LEFT JOIN RHTR_SECCION sec ON pues.ID_SECCION=sec.ID_SECCION\r\n" + 
        		"LEFT JOIN RHTD_AREA area ON sec.ID_AREA=area.ID_AREA\r\n" + 
        		"LEFT JOIN RHTX_DEPARTAMENTO dep ON area.ID_DEPARTAMENTO=dep.ID_DEPARTAMENTO\r\n" + 
        		"LEFT JOIN RHTR_DEPART_CENTRO_COSTO depcc ON dep.ID_DEPARTAMENTO=depcc.ID_DEPARTAMENTO\r\n" + 
        		"LEFT JOIN  RHTM_DGP dgp ON con.ID_DGP=dgp.ID_DGP \r\n" + 
        		"LEFT JOIN RHTM_TRABAJADOR tra ON  con.ID_TRABAJADOR=tra.ID_TRABAJADOR\r\n" + 
        		"LEFT JOIN RHTX_REGIMEN_LABORAL reg ON con.ID_REGIMEN_LABORAL=reg.ID_REGIMEN_LABORAL\r\n" + 
        		"LEFT JOIN RHTX_SUB_MODALIDAD sub ON con.ID_SUB_MODALIDAD=sub.ID_SUB_MODALIDAD\r\n" + 
        		"LEFT JOIN RHTX_GRUPO_OCUPACION gr ON con.ID_GRUPO_OCUPACION=gr.ID_GRUPO_OCUPACION \r\n" + 
        		"LEFT JOIN RHTR_TIPO_PLANILLA tipopla ON con.ID_TIPO_PLANILLA=tipopla.ID_TIPO_PLANILLA\r\n" + 
        		"LEFT JOIN RHTD_DETALLE_HORARIO dethor ON con.ID_DETALLE_HORARIO=dethor.ID_DETALLE_HORARIO\r\n" + 
        		"LEFT JOIN RHTC_PLANTILLA_CONTRACTUAL placon ON con.ID_PLANTILLA_CONTRACTUAL=placon.ID_PLANTILLA_CONTRACTUAL\r\n" + 
        		"LEFT JOIN RHTR_SITUACION_ESPECIAL sitesp ON con.ID_SITUACION_ESPECIAL=sitesp.ID_SITUACION_ESPECIAL \r\n" + 
        		" GROUP BY con.ID_TRABAJADOR,tra.NO_TRABAJADOR,tra.AP_PATERNO, tra.AP_MATERNO,pues.NO_PUESTO,area.NO_AREA,dep.NO_DEP,tipocon.DE_TI_CONTRATO,tra.NU_DOC, dgp.ES_MFL";
        ArrayList<Map<String, Object>> lista = new ArrayList<>();
        try {
            cn = d.getConnection();
            ps = cn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Map<String, Object> m = new HashMap<>();
                m.put("ID_TRABAJADOR", rs.getString("ID_TRABAJADOR"));
                m.put("no_trabajador", rs.getString("no_trabajador"));
                m.put("ap_paterno", rs.getString("ap_paterno"));
                m.put("ap_materno", rs.getString("ap_materno"));
                m.put("NO_PUESTO", rs.getString("NO_PUESTO"));
                m.put("NO_AREA", rs.getString("NO_AREA"));
                m.put("NO_DEP", rs.getString("NO_DEP"));
                m.put("DE_TI_CONTRATO", rs.getString("DE_TI_CONTRATO"));
                m.put("FECHA_CONTRATO", rs.getString("FECHA_CONTRATO"));
                m.put("NU_DOC", rs.getString("NU_DOC"));
                m.put("ES_MFL", rs.getString("ES_MFL"));
                lista.add(m);

            }
        } catch (Exception e) {
            System.out.println("Error al Listar Autorizar Renuncia" + e);
        } finally {
            try {
                cn.close();
            } catch (SQLException ex) {
                Logger.getLogger(RolDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return lista;
    }

	public List<Map<String, Object>> readAll() {
		List<Map<String, Object>> readAll =  new ArrayList<>();
		sql="SELECT * FROM RHTC_PASOS";
		
		return readAll;
	}
	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

}
