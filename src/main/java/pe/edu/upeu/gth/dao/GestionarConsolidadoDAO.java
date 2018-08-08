package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.driver.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;

public class GestionarConsolidadoDAO {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public GestionarConsolidadoDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> listarConsolidadoSinAprobar() {
		try {
			sql = "SELECT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_seccion,tf.no_dep,\r\n"
					+ "trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) - \r\n"
					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1 AS nu_vac,\r\n"
					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\r\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,tf.li_condicion,\r\n"
					+ "usr.no_usuario,TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones,\r\n"
					+ "sv.id_vacaciones,sv.url,dsv.firma_entrada,dsv.firma_salida\r\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,\r\n"
					+ "rhtm_contrato co,rhtc_usuario usr,rhtd_empleado emp,rhmv_hist_detalle hd\r\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
					+ "AND emp.id_empleado = usr.id_empleado\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
					+ "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n" + "AND hd.evaluacion = 3\r\n"
					+ "AND hd.id_pasos = 'PAS-000054'\r\n" + "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND co.es_contrato = 1";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> listarConsolidadoAprobado() {
		try {
			sql = "SELECT DISTINCT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_dep,tf.no_seccion,\r\n"
					+ "tf.no_area,pst.no_puesto,TO_CHAR(trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) - \r\n"
					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1) AS nu_vac,\r\n"
					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\r\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,tf.li_condicion,\r\n"
					+ "usr.no_usuario,TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones,\r\n"
					+ "sv.id_vacaciones,dsv.firma_entrada,dsv.firma_salida,\r\n"
					+ "TRIM(TO_CHAR(dsv.fecha_inicio,'Month') ) AS fec_ini_mon,\r\n"
					+ "TRIM(TO_CHAR(dsv.fecha_fin,'Month') ) AS fec_fin_mon\r\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,\r\n"
					+ "rhtr_puesto pst,rhtm_contrato co,rhtc_usuario usr,rhtd_empleado emp,rhmv_hist_detalle hd\r\n"
					+ "WHERE co.id_puesto = pst.id_puesto\r\n" + "AND sv.id_vacaciones = dsv.id_vacaciones\r\n"
					+ "AND emp.id_trabajador = t.id_trabajador\r\n" + "AND emp.id_empleado = usr.id_empleado\r\n"
					+ "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n" + "AND dsv.estado <> 0\r\n"
					+ "AND hd.estado = 1\r\n" + "AND (hd.evaluacion = 3\r\n" + "OR hd.evaluacion = 2\r\n"
					+ "OR hd.evaluacion >= 5\r\n" + ") AND ( hd.id_pasos = 'PAS-000052'\r\n"
					+ "OR hd.id_pasos = 'PAS-000090'\r\n" + "OR hd.id_pasos = 'PAS-000092'\r\n"
					+ ") AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND co.es_contrato = 1";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> getCorreoConsolidado() {
		try {
			sql = "SELECT DISTINCT t.di_correo_personal\r\n"
					+ "FROM rhtm_contrato co,rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,\r\n"
					+ "rhmv_det_vacaciones dsv,rhmv_hist_detalle hd\r\n"
					+ "WHERE tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
					+ "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n" + "AND hd.evaluacion = 3\r\n"
					+ "AND hd.id_pasos = 'PAS-000054'\r\n" + "AND sv.id_vacaciones = dsv.id_vacaciones\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.di_correo_personal != '--'\r\n"
					+ "AND t.di_correo_personal != '-'\r\n" + "AND t.di_correo_personal IS NOT NULL";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public int insertHistorial(String usuario, String[] id_det, String paso_ant, int eva_ant, String paso, int eva) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_HISTORIAL (?,?,?,?,?,?)}");
			cst.setString(1, usuario);
			cst.setArray(2, idarr);
			cst.setString(3, paso_ant);
			cst.setInt(4, eva_ant);
			cst.setString(5, paso);
			cst.setInt(6, eva);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<Map<String, Object>> readFechas(String id) {
		try {
			sql = "SELECT DISTINCT TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\r\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,dsv.firma_entrada,\r\n"
					+ "dsv.firma_salida,dsv.id_det_vacaciones,t.id_trabajador,sv.id_vacaciones\r\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,\r\n"
					+ "rhmv_det_vacaciones dsv,rhtm_contrato co,rhmv_hist_detalle hd\r\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND sv.estado = 1\r\n"
					+ "AND tf.estado = 1\r\n" + "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n"
					+ "AND ( hd.evaluacion = 3\r\n" + "OR hd.evaluacion = 2\r\n" + "OR hd.evaluacion >= 5\r\n"
					+ ") AND ( hd.id_pasos = 'PAS-000054'\r\n" + "OR hd.id_pasos = 'PAS-000052'\r\n"
					+ "OR hd.id_pasos = 'PAS-000090'\r\n" + "OR hd.id_pasos = 'PAS-000092'\r\n"
					+ ") AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND co.es_contrato = 1\r\n" + "AND dsv.id_det_vacaciones = '" + id + "'\r\n"
					+ "ORDER BY dsv.id_det_vacaciones";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public int updateFechas(String id, int inicio, int fin) {
		try {
			sql = "UPDATE RHMV_DET_VACACIONES SET FIRMA_SALIDA = ?, FIRMA_ENTRADA = ? WHERE ID_DET_VACACIONES = ?";
			jt.update(sql, new Object[] { inicio, fin, id });
			return 1;
		} catch (Exception e) {
			System.out.println("ERROR: " + e);
			return 0;
		}
	}

	public List<Map<String, Object>> readFile(String traba, String id_det) {
		try {
			sql = "SELECT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_seccion,\r\n"
					+ "dsv.url AS url_papeleta,sv.url AS url_solicitud,dsv.id_det_vacaciones,sv.id_vacaciones\r\n"
					+ "FROM rhmv_trabajador_filtrado tf,rhmv_vacaciones sv,rhmv_det_vacaciones dsv,\r\n"
					+ "rhmv_hist_detalle hd\r\n" + "WHERE tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND sv.id_vacaciones = dsv.id_vacaciones\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n" + "AND sv.estado = 1\r\n"
					+ "AND tf.estado = 1\r\n" + "AND dsv.estado <> 0\r\n" + "AND ( hd.evaluacion = 3\r\n"
					+ "OR hd.evaluacion = 2\r\n" + "OR hd.evaluacion >= 5\r\n"
					+ ") AND ( hd.id_pasos = 'PAS-000054'\r\n" + "OR hd.id_pasos = 'PAS-000052'\r\n"
					+ "OR hd.id_pasos = 'PAS-000090'\r\n" + "OR hd.id_pasos = 'PAS-000092'\r\n" + ") AND hd.estado = 1"
					+ "AND tf.ID_TRABAJADOR='" + traba + "'\r\n" + "AND dsv.ID_DET_VACACIONES='" + id_det + "'";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}

	}

	public int subirDocumento(String url, String idvac, String id_det, int value) {
		int x = 0;

		if (value == 0) {
			sql = "UPDATE RHMV_VACACIONES SET URL='" + url + "' WHERE ID_VACACIONES='" + idvac + "'";
		} else if (value == 1) {
			sql = "UPDATE RHMV_DET_VACACIONES SET URL='" + url + "' WHERE ID_DET_VACACIONES='" + id_det + "'";
		}
		try {
			jt.update(sql);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
}
