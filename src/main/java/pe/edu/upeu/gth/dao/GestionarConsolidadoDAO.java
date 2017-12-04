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

	public List<Map<String, Object>> listarConsolidado() {
		sql = "select tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_SECCION,\r\n"
				+ "trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1 as NU_VAC,\r\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\r\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd \r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1 \r\n" + "and tf.ESTADO=1 \r\n" + "and dsv.ESTADO=1 \r\n" + "and hd.ESTADO=1\r\n"
				+ "and hd.EVALUACION=3\r\n" + "and hd.ID_PASOS='PAS-000054'\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES \r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO \r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1";
		return jt.queryForList(sql);
	}

	public int apobarVacCon(String usuario, String[] id_det) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_APRO_CONSOLIDADO (?,?)}");
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

	public List<Map<String, Object>> readFechas(String id) {
		sql = "select to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN\r\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\r\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd \r\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\r\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n"
				+ "and sv.ESTADO=1 \r\n" + "and tf.ESTADO=1 \r\n" + "and dsv.ESTADO=1 \r\n" + "and hd.ESTADO=1\r\n"
				+ "and hd.EVALUACION=3\r\n" + "and hd.ID_PASOS='PAS-000054'\r\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES \r\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO \r\n"
				+ "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\r\n" + "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\r\n"
				+ "and co.ES_CONTRATO=1" + "and hd.ID_DET_VACACIONES='" + id + "'";

		return jt.queryForList(sql);
	}
}
