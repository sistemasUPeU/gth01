package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class Notificacion_VacDAO {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public Notificacion_VacDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> GetNoti(String depa, String traba) {
		try {
			sql = "SELECT DISTINCT men.texto, noti.id_notificaciones\n"
					+ "FROM rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,rhtm_contrato co,\n"
					+ "rhtm_trabajador t,rhmv_hist_detalle hd,rhmv_mensaje men,rhmv_notificaciones noti\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\n" + "AND sv.estado = 1\n" + "AND tf.estado = 1\n"
					+ "AND dsv.estado <> 0\n" + "AND noti.estado = 1\n" + "AND sv.id_vacaciones = dsv.id_vacaciones\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\n" + "AND dsv.id_det_vacaciones = (\n"
					+ "SELECT distinct dsv.id_det_vacaciones\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,\n"
					+ "rhtm_contrato co,rhmv_hist_detalle hd,rhmv_mensaje men,rhmv_notificaciones noti\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\n" + "AND sv.estado = 1\n" + "AND tf.estado = 1\n"
					+ "AND dsv.estado <> 0\n" + "AND hd.estado = 1\n" + "AND noti.estado = 1\n"
					+ "AND men.id_mensaje = noti.id_mensaje\n" + "AND dsv.id_det_vacaciones = noti.id_det_vacaciones\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\n"
					+ "AND tf.id_trabajador = t.id_trabajador\n" + "AND t.id_trabajador = co.id_trabajador\n"
					+ "AND t.id_trabajador = '" + traba + "'\n" + "AND co.es_contrato = 1\n" + "AND tf.no_dep = '"
					+ depa + "'\n" + "AND ROWNUM = 1)\n" + "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\n"
					+ "AND t.id_trabajador = co.id_trabajador\n" + "AND co.es_contrato = 1\n" + "AND tf.no_dep = '"
					+ depa + "'\n" + "AND men.id_mensaje = noti.id_mensaje\n"
					+ "AND dsv.id_det_vacaciones = noti.id_det_vacaciones\n" + "AND t.id_trabajador = '" + traba + "'";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public int updateNoti(String idnoti) {
		int i = 0;
		try {
			cn = d.getConnection();

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_UPDATE_NOTIFICACION (?)}");
			cst.setString(1, idnoti);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}
}
