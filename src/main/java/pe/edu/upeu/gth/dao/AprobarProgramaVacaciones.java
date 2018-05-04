package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.driver.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;

public class AprobarProgramaVacaciones {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public AprobarProgramaVacaciones(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> listarSinAprobar(String depa) {
		sql = "select DISTINCT tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO,tf.AP_MATERNO, tf.NO_SECCION,\r\n"
				+ "trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1 as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n" + "and dsv.ESTADO <> 0\r\n" + "and hd.ESTADO=1\r\n"
				+ "and hd.EVALUACION=2\r\n" + "and hd.ID_PASOS='PAS-000055'\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1" + "and tf.NO_DEP='" + depa + "'";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> listarAprobados(String depa) {
		sql = "select DISTINCT tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_SECCION,\r\n"
				+ "trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1 as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd \r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1 \r\n" + "and tf.ESTADO=1 \r\n" + "and dsv.ESTADO <> 0\r\n"
				+ "and hd.EVALUACION=3\r\n" + "and hd.ID_PASOS='PAS-000054'\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES \r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO \r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1" + "and tf.NO_DEP='" + depa + "'";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> listarRechazados(String depa) {
		sql = "select DISTINCT tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_SECCION,\r\n"
				+ "trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1 as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES, men.TEXTO\r\n"
				+ "from RHTM_TRABAJADOR t,  RHMV_TRABAJADOR_FILTRADO tf, RHMV_VACACIONES sv,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd,\r\n"
				+ "RHMV_MENSAJE men, RHMV_NOTIFICACIONES noti\r\n" + "where tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n"
				+ "and dsv.ID_DET_VACACIONES=noti.ID_DET_VACACIONES\r\n" + "and men.ID_MENSAJE=noti.ID_MENSAJE\r\n"
				+ "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n" + "and dsv.ESTADO <> 0\r\n" + "and hd.EVALUACION=4\r\n"
				+ "and hd.ID_PASOS='PAS-000054'\r\n" + "and hd.ESTADO = 1\r\n" + "and tf.no_dep ='" + depa + "'";
		return jt.queryForList(sql);
	}

	public int apobarVac(String usuario, String[] id_det) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_APR_VAC (?,?)}");
			cst.setString(1, usuario);
			cst.setArray(2, idarr);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public int observarVac(String usuario, String id_det, String text, String emisor) {
		int i = 0;
		try {
			cn = d.getConnection();

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_OBS_VAC (?,?,?,?)}");
			cst.setString(1, usuario);
			cst.setString(2, id_det);
			cst.setString(3, text);
			cst.setString(4, emisor);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<Map<String, Object>> GetEmailEmployee(String depa, String id_det, String traba) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "select DISTINCT men.TEXTO, vtc.DI_CORREO_PERSONAL, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO,"
					+ "to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN\r\n"
					+ "from RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
					+ "RHMV_DET_VACACIONES dsv, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd,\r\n"
					+ "RHMV_MENSAJE men, RHMV_NOTIFICACIONES noti\r\n" + "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n"
					+ "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n" + "and dsv.ESTADO <> 0\r\n"
					+ "and hd.EVALUACION=4\r\n" + "and hd.ID_PASOS='PAS-000054'\r\n"
					+ "and sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n"
					+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n" + "and dsv.ID_DET_VACACIONES='" + id_det
					+ "'\r\n" + "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
					+ "and tf.ID_TRABAJADOR=vtc.ID_TRABAJADOR\r\n" + "and vtc.ES_CONTRATO=1\r\n" + "and tf.no_dep ='"
					+ depa + "'\r\n" + "and men.ID_MENSAJE=noti.ID_MENSAJE\r\n"
					+ "and dsv.ID_DET_VACACIONES=noti.ID_DET_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR='" + traba
					+ "'\r\n" + "and vtc.DI_CORREO_PERSONAL != '--'\r\n" + "and vtc.DI_CORREO_PERSONAL !='-'\r\n"
					+ "AND vtc.DI_CORREO_PERSONAL IS NOT NULL";
			LST = jt.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}

	public List<Map<String, Object>> GetEmailSecre(String depa) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT DISTINCT DI_CORREO_PERSONAL FROM RHVV_TRABAJADOR_CONTRATO\r\n" + "where NO_DEP ='"
					+ depa + "'\r\n" + "and NO_ROL='Secretaria de Departamento'\r\n"
					+ "and DI_CORREO_PERSONAL != '--'\r\n" + "and DI_CORREO_PERSONAL !='-'\r\n"
					+ "AND DI_CORREO_PERSONAL IS NOT NULL";
			LST = jt.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
}
