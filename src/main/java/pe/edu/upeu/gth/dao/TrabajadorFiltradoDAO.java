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
public class TrabajadorFiltradoDAO {
	JdbcTemplate JDBC;

	public TrabajadorFiltradoDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}

	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RT.ID_TRABAJADOR, RT.AP_PATERNO, RT.AP_MATERNO, RT.NO_TRABAJADOR, RD.NO_DEP, RA.NO_AREA, \n"
					+ "RS.NO_SECCION, RC.LI_CONDICION, RC.FE_DESDE, RC.FE_HASTA, FC_MESES_TRABAJO(RT.ID_TRABAJADOR) as meses, RT.DI_CORREO_INST, RT.DI_CORREO_PERSONAL\n"
					+ "FROM RHTM_TRABAJADOR RT, RHTM_CONTRATO RC, RHTR_PUESTO RP, RHTR_SECCION RS, RHTD_AREA RA, RHTX_DEPARTAMENTO RD \n"
					+ "WHERE RT.ID_TRABAJADOR = RC.ID_TRABAJADOR \n" + "AND RC.ID_PUESTO = RP.ID_PUESTO \n"
					+ "AND RC.ES_CONTRATO = 1 \n" + "AND RP.ID_SECCION = RS.ID_SECCION \n"
					+ "AND RS.ID_AREA = RA.ID_AREA\n" + "AND RA.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO\n"
					+ "AND FC_MESES_TRABAJO(RT.ID_TRABAJADOR) >= 11 AND NOT RT.ID_TRABAJADOR IN (SELECT ID_TRABAJADOR \n"
					+ "FROM RHMV_TRABAJADOR_FILTRADO) ORDER BY RT.AP_PATERNO";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}

	public List<Map<String, Object>> GetEmail() {
		List<Map<String, Object>> LST = new ArrayList<>();

		try {
			String SQL = "SELECT DI_CORREO_PERSONAL FROM RHTM_TRABAJADOR RT, RHTM_CONTRATO RC, RHTR_PUESTO RP, RHTR_SECCION RS, RHTD_AREA RA, RHTX_DEPARTAMENTO RD \n"
					+ "WHERE RT.ID_TRABAJADOR = RC.ID_TRABAJADOR \n" + "AND RC.ID_PUESTO = RP.ID_PUESTO \n"
					+ "AND RC.ES_CONTRATO = 1 \n" + "AND RP.ID_SECCION = RS.ID_SECCION \n"
					+ "AND RS.ID_AREA = RA.ID_AREA\n" + "AND RA.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO\n"
					+ " and DI_CORREO_PERSONAL != '--' AND DI_CORREO_PERSONAL !='-' AND DI_CORREO_PERSONAL IS NOT NULL "
					+ "AND FC_MESES_TRABAJO(RT.ID_TRABAJADOR) >= 11 AND NOT RT.ID_TRABAJADOR IN (SELECT ID_TRABAJADOR \n"
					+ "FROM RHMV_TRABAJADOR_FILTRADO) ORDER BY RT.AP_PATERNO";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}

	public int CONFIRMAR() {

		try {
			DataSource DS = AppConfig.getDataSource();
			CallableStatement CST = DS.getConnection().prepareCall("{call RHSP_INSERT_TRAB_FIL (?)}");
			CST.registerOutParameter(1, Types.NUMERIC);
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
