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
	
	public List<Map<String, Object>> READFECHA(String id) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RD.ID_DET_VACACIONES ,TO_CHAR(RD.FECHA_INICIO, 'DD/MM/YYYY') AS FECHA_INICIO, RD.FIRMA_SALIDA, TO_CHAR(RD.FECHA_FIN, 'DD/MM/YYYY') AS FECHA_FIN, RD.FIRMA_ENTRADA FROM RHMV_TRABAJADOR_FILTRADO RT, RHMV_VACACIONES RV, RHMV_DET_VACACIONES RD, RHMV_HIST_DETALLE RH WHERE RT.ID_TRABAJADOR_FILTRADO = RV.ID_TRABAJADOR_FILTRADO AND RV.ID_VACACIONES = RD.ID_VACACIONES AND RD.ID_DET_VACACIONES = RH.ID_DET_VACACIONES AND RH.EVALUACION = 2 AND RH.ID_PASOS = 'PAS-000052' AND RV.ID_VACACIONES = ? ORDER BY RD.ID_DET_VACACIONES";
			LST = JDBC.queryForList(SQL, id);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
	
	public int UPDATEFECHA(String id, int inicio, int fin) {

		String SQL = "UPDATE RHMV_DET_VACACIONES SET FIRMA_SALIDA = ?, FIRMA_ENTRADA = ? WHERE ID_DET_VACACIONES = ?";
		try {
			JDBC.update(SQL, new Object[] { inicio, fin, id });
			return 1;
		} catch (Exception E) {
			System.out.println("ERROR: " + E);
			return 0;
		}
	}
}
