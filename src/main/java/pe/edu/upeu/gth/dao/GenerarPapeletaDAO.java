package pe.edu.upeu.gth.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class GenerarPapeletaDAO {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public GenerarPapeletaDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> generarPapaleta(String idtra) {
		try {
			sql = "SELECT DISTINCT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,\n"
					+ "tf.no_dep,tf.no_seccion,tf.no_area,pst.no_puesto,\n"
					+ "TO_CHAR(trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) - \n"
					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1) AS nu_vac,\n"
					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,tf.li_condicion,\n"
					+ "usr.no_usuario,TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones,\n"
					+ "sv.id_vacaciones,dsv.firma_entrada,dsv.firma_salida,\n"
					+ "TRIM(TO_CHAR(dsv.fecha_inicio,'Month') ) AS fec_ini_mon,\n"
					+ "TRIM(TO_CHAR(dsv.fecha_fin,'Month') ) AS fec_fin_mon\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,\n"
					+ "rhmv_det_vacaciones dsv,rhtm_contrato co,rhtr_puesto pst,rhtc_usuario usr,rhtd_empleado emp,\n"
					+ "rhmv_hist_detalle hd\n" + "WHERE sv.id_vacaciones = dsv.id_vacaciones\n"
					+ "AND co.id_puesto = pst.id_puesto\n" + "AND emp.id_trabajador = t.id_trabajador\n"
					+ "AND emp.id_empleado = usr.id_empleado\n" + "AND sv.estado = 1\n" + "AND tf.estado = 1\n"
					+ "AND dsv.estado <> 0\n" + "AND hd.estado = 1\n" + "AND hd.evaluacion = 3\n"
					+ "AND hd.id_pasos = 'PAS-000052'\n" + "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\n"
					+ "AND tf.id_trabajador = t.id_trabajador\n" + "AND t.id_trabajador = co.id_trabajador\n"
					+ "AND co.es_contrato = 1\n" + "AND tf.id_trabajador = '" + idtra + "'\n"
					+ "AND trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) = (\n"
					+ "SELECT MIN(trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) )\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,\n"
					+ "rhmv_det_vacaciones dsv,rhtm_contrato co,rhmv_hist_detalle hd\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\n" + "AND sv.estado = 1\n" + "AND tf.estado = 1\n"
					+ "AND dsv.estado <> 0\n" + "AND hd.estado = 1\n" + "AND hd.evaluacion = 3\n"
					+ "AND hd.id_pasos = 'PAS-000052'\n" + "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\n"
					+ "AND tf.id_trabajador = t.id_trabajador\n" + "AND t.id_trabajador = co.id_trabajador\n"
					+ "AND co.es_contrato = 1\n" + "AND tf.id_trabajador = '" + idtra + "' )";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}
}
