package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.gth.config.AppConfig;

@Repository
public class AdelantoVacacionesDAO {
	JdbcTemplate JDBC;

	public AdelantoVacacionesDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}

	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RT.ID_TRABAJADOR, RT.AP_PATERNO, RT.AP_MATERNO, RT.NO_TRABAJADOR, RD.NO_DEP, RA.NO_AREA, RS.NO_SECCION, RC.LI_CONDICION, RC.FE_DESDE, RC.FE_HASTA, RT.DI_CORREO_INST, RT.DI_CORREO_PERSONAL FROM RHTM_TRABAJADOR RT, RHTM_CONTRATO RC, RHTR_PUESTO RP, RHTR_SECCION RS, RHTD_AREA RA, RHTX_DEPARTAMENTO RD WHERE RT.ID_TRABAJADOR = RC.ID_TRABAJADOR AND RC.ID_PUESTO = RP.ID_PUESTO AND RC.ES_CONTRATO = 1 AND RP.ID_SECCION = RS.ID_SECCION AND RS.ID_AREA = RA.ID_AREA AND RA.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO AND NOT RT.ID_TRABAJADOR IN (SELECT ID_TRABAJADOR FROM RHMV_TRABAJADOR_FILTRADO) ORDER BY RT.AP_PATERNO";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
		
	}
	public int INSERTAR_ADELANTO (String idtra) {

		try {
			DataSource DS = AppConfig.getDataSource();
			CallableStatement CST = DS.getConnection().prepareCall("{call RHSP_INSERT_ADELANTO (?,?)}");
			CST.registerOutParameter(1,  Types.NUMERIC);
			CST.setString(2, idtra);
			CST.execute();
			int i = CST.getInt(1);
			return i;
		} catch (SQLException E) {
			E.printStackTrace();
			System.out.println("ERROR: " + E);
			return 0;
		}
	}
}
