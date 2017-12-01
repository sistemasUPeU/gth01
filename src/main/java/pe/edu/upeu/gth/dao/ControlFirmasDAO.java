package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ControlFirmasDAO {
	JdbcTemplate JDBC;

	public ControlFirmasDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}
	
	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT * FROM RHVV_CONTROL_FIRMAS";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
	
	public List<Map<String, Object>> READFECHA() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RD.ID_DET_VACACIONES ,RD.FECHA_INICIO, RD.FECHA_FIN FROM RHMV_TRABAJADOR_FILTRADO RT, RHMV_VACACIONES RV, RHMV_DET_VACACIONES RD, RHMV_HIST_DETALLE RH WHERE RT.ID_TRABAJADOR_FILTRADO = RV.ID_TRABAJADOR_FILTRADO AND RV.ID_VACACIONES = RD.ID_VACACIONES AND RD.ID_DET_VACACIONES = RH.ID_DET_VACACIONES AND RH.EVALUACION = 2 AND RH.ID_PASOS = 'PAS-000052' AND RV.ID_VACACIONES = 'VAC-000002'";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
}
