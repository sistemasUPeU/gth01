package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class ConfiguracionDAO {

	JdbcTemplate jt = new JdbcTemplate();
	String sql = "";

	public ConfiguracionDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public String consolidadoExists() {
		String ls = null;
		try {
			sql = "SELECT TRIM(ID_CONSOLIDADO) FROM RHMV_CONSOLIDADO WHERE ESTADO = 1";
			ls = jt.queryForObject(sql, String.class);
			// @SuppressWarnings("unchecked")
			// String ls= (String) jt.queryForObject(sql, new Object[] {},
			// new BeanPropertyRowMapper<String>(String.class));

			// String list = ;
			//// System.out.println("lista show> "+ls);
			// if(list.isEmpty()) {
			// res = "";

			// System.out.println(res);
		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}

	public int crearConsolidado(String alerta) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONSOLIDADO (ESTADO, FECHA_INICIO, FECHA_FINAL, ALERTA_MENSUAL) VALUES (?, ?, ?,?)";
			ls = jt.update(sql,1, "01/01/18", "31/12/18",  alerta);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	
	public int crearConfigPrograma(String fecha) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONF_PROGRAMA(ESTADO, FECHA_PLAZO) VALUES (?,?)";
			ls = jt.update(sql,1, fecha);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	public int crearConfigSolicitud(String fecha) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONF_SOLICITUD(ESTADO, FECHA_PLAZO) VALUES (?,?)";
			ls = jt.update(sql,1, fecha);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	public int crearConfiguracion(String fecha_solicitud, String fecha_programa) {
		System.out.println("dao configuracion insertar " + fecha_solicitud +" , " + fecha_programa);
		int i = 0;
		try {
			DataSource d = AppConfig.getDataSource();
			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_INSERT_DETALLE_CONFIG (?,?,?)}");
			cst.setString(1, fecha_solicitud);
			cst.setString(2, fecha_programa);
			
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.execute();
			System.out.println(cst.getString(3));

			i = Integer.parseInt(cst.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
		

	}
	
	public List<Map<String, Object>> listarDepartamento() {
		String sql = null;
		
		List<Map<String, Object>> lista = new ArrayList<>();
		try {
			sql = "SELECT * FROM RHTX_DEPARTAMENTO WHERE ES_DEPARTAMENTO = 1";
			lista = jt.queryForList(sql);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("listar departmento configuraciones dao> " + e);
			lista= null;

		}
//		System.out.println(lista);
		return lista;
	}


}
