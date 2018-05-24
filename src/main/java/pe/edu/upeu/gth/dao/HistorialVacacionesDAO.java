package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HistorialVacacionesDAO {
	JdbcTemplate JDBC;

	public HistorialVacacionesDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}
	
	public List<Map<String, Object>> READHISTORIAL(String id) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RHD.ID_HIST_DETALLE, RHD.ID_PASOS, RHD.EVALUACION, RHD.ESTADO, RDV.NRO_PAPELETA, RDV.FIRMA_SALIDA, RDV.FIRMA_ENTRADA FROM RHMV_HIST_DETALLE RHD, RHMV_DET_VACACIONES RDV";
			LST = JDBC.queryForList(SQL);
//			LST = JDBC.queryForList(SQL, id);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
}