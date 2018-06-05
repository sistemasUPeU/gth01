package pe.edu.upeu.gth.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HistorialTramiteDAO {
	JdbcTemplate JDBC;

	public HistorialTramiteDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}
	
	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RTF.ID_TRABAJADOR_FILTRADO, RDV.ID_DET_VACACIONES, RTF.NO_TRABAJADOR, RTF.AP_PATERNO, RTF.AP_MATERNO, RTF.NO_AREA, RTF.NO_SECCION, RHD.EVALUACION, RHD.ID_PASOS, TO_CHAR(RDV.FECHA_INICIO,'DD/MM/YYYY') AS FECHA_INICIO, TO_CHAR(RDV.FECHA_FIN,'DD/MM/YYYY') AS FECHA_FIN, RDV.FIRMA_SALIDA, RDV.FIRMA_ENTRADA, RV.URL AS URL_SOLICITUD, RDV.URL AS URL_PAPELETA\r\n" + 
					"FROM RHMV_TRABAJADOR_FILTRADO RTF \r\n" + 
					"LEFT JOIN  RHMV_VACACIONES RV ON RV.ID_TRABAJADOR_FILTRADO = RTF.ID_TRABAJADOR_FILTRADO\r\n" + 
					"LEFT JOIN RHMV_DET_VACACIONES RDV ON RDV.ID_VACACIONES = RV.ID_VACACIONES\r\n" + 
					"LEFT JOIN RHMV_HIST_DETALLE RHD ON RHD.ID_DET_VACACIONES = RDV.ID_DET_VACACIONES\r\n" + 
					"AND RTF.ESTADO = 1\r\n" + 
					"AND RHD.ESTADO = 1";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
}
