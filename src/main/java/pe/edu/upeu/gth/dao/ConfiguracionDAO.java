package pe.edu.upeu.gth.dao;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

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
	
	

}
