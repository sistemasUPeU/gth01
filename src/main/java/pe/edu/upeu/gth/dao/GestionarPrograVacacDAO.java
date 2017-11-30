package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class GestionarPrograVacacDAO {
	JdbcTemplate JDBC;

	public GestionarPrograVacacDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}
	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "select tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR, tf.ap_paterno, tf.ap_materno, tf.NO_AREA, tf.NO_SECCION,detva.FECHA_INICIO, detva.fecha_fin from RHMV_TRABAJADOR_FILTRADO tf left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones where tf.estado=1";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
}
