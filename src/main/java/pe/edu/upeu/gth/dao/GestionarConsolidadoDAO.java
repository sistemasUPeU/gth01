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

public class GestionarConsolidadoDAO {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public GestionarConsolidadoDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> listarConsolidadoSinAprobar() {
		sql = "select tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_SECCION, tf.NO_DEP,\r\n"
				+ "trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1 as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES, sv.ID_VACACIONES, sv.URL, dsv.FIRMA_ENTRADA, dsv.FIRMA_SALIDA\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd \r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1 \r\n" + "and tf.ESTADO=1 \r\n" + "and dsv.ESTADO<>0 \r\n" + "and hd.ESTADO=1\r\n"
				+ "and hd.EVALUACION=3\r\n" + "and hd.ID_PASOS='PAS-000054'\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES \r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO \r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> listarConsolidadoAprobado() {
		sql = "select tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_DEP ,tf.NO_SECCION, tf.NO_AREA, vtc.NO_PUESTO,\r\n"
				+ "to_char(trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1) as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES, sv.ID_VACACIONES, dsv.FIRMA_ENTRADA, dsv.FIRMA_SALIDA,\r\n"
				+ "TRIM(to_char(dsv.FECHA_INICIO, 'Month')) as FEC_INI_MON, TRIM(to_char(dsv.FECHA_FIN, 'Month')) as FEC_FIN_MON\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n" + "and dsv.ESTADO<>0\r\n" + "and hd.ESTADO=1\r\n"
				+ "and (hd.EVALUACION=3 " + "or hd.EVALUACION=2)\r\n" + "and (hd.ID_PASOS='PAS-000052' "
				+ "or hd.ID_PASOS='PAS-000090' " + "or hd.ID_PASOS='PAS-000092')\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> getCorreoConsolidado() {
		sql = "select DISTINCT vtc.DI_CORREO_PERSONAL\r\n" + "from RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n"
				+ "and dsv.ESTADO <> 0\r\n" + "and hd.ESTADO=1\r\n" + "and hd.EVALUACION=3\r\n"
				+ "and hd.ID_PASOS='PAS-000054'\r\n" + "and sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and tf.ID_TRABAJADOR=vtc.ID_TRABAJADOR\r\n" + "and vtc.DI_CORREO_PERSONAL != '--'\r\n"
				+ "and vtc.DI_CORREO_PERSONAL !='-'\r\n" + "AND vtc.DI_CORREO_PERSONAL IS NOT NULL";
		return jt.queryForList(sql);
	}

	public int insertHistorial(String usuario, String[] id_det, String paso_ant, int eva_ant, String paso, int eva) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_HISTORIAL (?,?,?,?,?,?)}");
			cst.setString(1, usuario);
			cst.setArray(2, idarr);
			cst.setString(3, paso_ant);
			cst.setInt(4, eva_ant);
			cst.setString(5, paso);
			cst.setInt(6, eva);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<Map<String, Object>> readFechas(String id) {
		sql = "select to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN\r\n"
				+ ", dsv.FIRMA_ENTRADA, dsv.FIRMA_SALIDA, dsv.ID_DET_VACACIONES, t.ID_TRABAJADOR, sv.ID_VACACIONES "
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd \r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1 \r\n" + "and tf.ESTADO=1 \r\n" + "and dsv.ESTADO<>0 \r\n" + "and hd.ESTADO=1\r\n"
				+ "and (hd.EVALUACION=3 " + "or hd.EVALUACION=2)\r\n" + "and (hd.ID_PASOS='PAS-000054' "
				+ "or hd.ID_PASOS='PAS-000052' " + "or hd.ID_PASOS='PAS-000090' " + "or hd.ID_PASOS='PAS-000092')\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES \r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO \r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1 " + "AND dsv.ID_DET_VACACIONES='" + id + "' ORDER BY dsv.ID_DET_VACACIONES";

		return jt.queryForList(sql);
	}

	public int updateFechas(String id, int inicio, int fin) {
		sql = "UPDATE RHMV_DET_VACACIONES SET FIRMA_SALIDA = ?, FIRMA_ENTRADA = ? WHERE ID_DET_VACACIONES = ?";
		try {
			jt.update(sql, new Object[] { inicio, fin, id });
			return 1;
		} catch (Exception e) {
			System.out.println("Error: " + e);
			return 0;
		}
	}

	public List<Map<String, Object>> readFile(String traba, String id_det) {
		sql = "select tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_SECCION,\r\n"
				+ "dsv.URL as URL_PAPELETA, sv.URL as URL_SOLICITUD, dsv.ID_DET_VACACIONES, sv.ID_VACACIONES\r\n"
				+ "from RHMV_TRABAJADOR_FILTRADO tf, RHMV_VACACIONES sv,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\r\n"
				+ "where tf.ID_TRABAJADOR=vtc.ID_TRABAJADOR\r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\r\n"
				+ "and sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\r\n"
				+ "and sv.ESTADO=1\r\n" + "and tf.ESTADO=1\r\n" + "and dsv.ESTADO <> 0\r\n" + "and (hd.EVALUACION=3 "
				+ "or hd.EVALUACION=2)\r\n" + "and (hd.ID_PASOS='PAS-000054' " + "or hd.ID_PASOS='PAS-000052' "
				+ "or hd.ID_PASOS='PAS-000090' " + "or hd.ID_PASOS='PAS-000092')\r\n" + "and hd.ESTADO = 1\r\n"
				+ "and vtc.ID_TRABAJADOR='" + traba + "'\r\n" + "and dsv.ID_DET_VACACIONES='" + id_det + "'";

		return jt.queryForList(sql);
	}

	public int subirDocumento(String url, String idvac, String id_det, int value) {
		int x = 0;
		String sql = "";

		if (value == 0) {
			sql = "UPDATE RHMV_VACACIONES SET URL='" + url + "' WHERE ID_VACACIONES='" + idvac + "'";
		} else if (value == 1) {
			sql = "UPDATE RHMV_DET_VACACIONES SET URL='" + url + "' WHERE ID_DET_VACACIONES='" + id_det + "'";
		}
		try {
			jt.update(sql);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
}
