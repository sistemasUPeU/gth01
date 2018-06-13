package pe.edu.upeu.gth.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class GenerarPapeletaDAO {
	private JdbcTemplate jt;
	private String sql = "";

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public GenerarPapeletaDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> generarPapaleta(String idtra) {
		sql = "select tf.ID_TRABAJADOR, tf.NO_TRABAJADOR, tf.AP_PATERNO, tf.AP_MATERNO, tf.NO_DEP ,tf.NO_SECCION, tf.NO_AREA, vtc.NO_PUESTO,\n"
				+ "to_char(trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))-trunc(to_date(dsv.FECHA_INICIO,'DD/MM/YYYY hh24:mi:ss'))+1) as NU_VAC,\n"
				+ "t.NU_DOC, to_char(dsv.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(dsv.FECHA_FIN,'DD/MM/YYYY') as FECHA_FIN, tf.LI_CONDICION,\n"
				+ "vtc.NO_USUARIO, trim(dsv.ID_DET_VACACIONES) as ID_DET_VACACIONES, sv.ID_VACACIONES, dsv.FIRMA_ENTRADA, dsv.FIRMA_SALIDA,\n"
				+ "TRIM(to_char(dsv.FECHA_INICIO, 'Month')) as FEC_INI_MON, TRIM(to_char(dsv.FECHA_FIN, 'Month')) as FEC_FIN_MON\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\n"
				+ "and sv.ESTADO=1\n" + "and tf.ESTADO=1\n" + "and dsv.ESTADO<>0\n" + "and hd.ESTADO=1\n"
				+ "and hd.EVALUACION=3\n" + "and hd.ID_PASOS='PAS-000052'\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\n" + "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\n"
				+ "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\n" + "and co.ES_CONTRATO=1 and tf.ID_TRABAJADOR='" + idtra
				+ "'\n" + "and trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss'))=\n"
				+ "(SELECT MIN(trunc(to_date(dsv.FECHA_FIN,'DD/MM/YYYY hh24:mi:ss')))\n"
				+ "from RHTM_TRABAJADOR t, RHMV_VACACIONES sv, RHMV_TRABAJADOR_FILTRADO tf,\n"
				+ "RHMV_DET_VACACIONES dsv, RHTM_contrato co, RHVV_TRABAJADOR_CONTRATO vtc, RHMV_HIST_DETALLE hd\n"
				+ "where sv.ID_VACACIONES=dsv.ID_VACACIONES\n" + "and vtc.ID_TRABAJADOR=t.ID_TRABAJADOR\n"
				+ "and sv.ESTADO=1\n" + "and tf.ESTADO=1\n" + "and dsv.ESTADO<>0\n" + "and hd.ESTADO=1\n"
				+ "and hd.EVALUACION=3\n" + "and hd.ID_PASOS='PAS-000052'\n"
				+ "and hd.ID_DET_VACACIONES=dsv.ID_DET_VACACIONES\n"
				+ "and tf.ID_TRABAJADOR_FILTRADO=sv.ID_TRABAJADOR_FILTRADO\n" + "and tf.ID_TRABAJADOR=t.ID_TRABAJADOR\n"
				+ "and t.ID_TRABAJADOR=co.ID_TRABAJADOR\n" + "and co.ES_CONTRATO=1 and tf.ID_TRABAJADOR='" + idtra
				+ "')";
		return jt.queryForList(sql);
	}
}
