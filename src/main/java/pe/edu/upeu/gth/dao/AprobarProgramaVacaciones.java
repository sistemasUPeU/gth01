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

	public List<Map<String, Object>> listarSinAprobar(String depa, String id_vac) {
		try {
			sql = "SELECT TRIM(dsv.id_vacaciones) AS id_vacaciones, TRIM(hd.evaluacion) AS evaluacion,\r\n"
					+ "tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_seccion,\r\n"
					+ "trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) - \r\n"
					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1 AS nu_vac,\r\n"
					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'YYYY/MM/DD') AS fecha_inicio,TO_CHAR(dsv.fecha_fin,'YYYY/MM/DD') AS fecha_fin,\r\n"
					+ "tf.li_condicion,usr.no_usuario,TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones,sv.tipo\r\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,rhtm_contrato co,\r\n"
					+ "rhmv_hist_detalle hd,rhtc_usuario usr,rhtd_empleado emp\r\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
					+ "AND emp.id_empleado = usr.id_empleado\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
					+ "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n"
					+ "AND (hd.evaluacion = 1 OR hd.evaluacion = 2)\r\n" + "AND hd.id_pasos = 'PAS-000055'\r\n"
					+ "AND sv.id_vacaciones = '" + id_vac + "'\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND co.es_contrato = 1" + " AND tf.no_dep = '" + depa + "'\r\n" + "order by dsv.fecha_inicio";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> listarSinAprobarNombres(String depa) {
		try {
			sql = "SELECT distinct TRIM(sv.id_vacaciones) AS id_vacaciones,\r\n"
					+ "tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,\r\n"
					+ "tf.no_seccion,t.nu_doc,tf.li_condicion,sv.tipo\r\n"
					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,\r\n"
					+ "rhmv_det_vacaciones dsv,rhtm_contrato co,\r\n"
					+ "rhmv_hist_detalle hd,rhtc_usuario usr,rhtd_empleado emp\r\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
					+ "AND emp.id_empleado = usr.id_empleado\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
					+ "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n" + "AND hd.evaluacion = 2\r\n"
					+ "AND hd.id_pasos = 'PAS-000055'\r\n" + "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND co.es_contrato = 1" + " AND tf.no_dep = '" + depa + "'";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> listarAprobados(String depa) {
		try {
//			sql = "SELECT DISTINCT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_seccion,\r\n"
//					+ "trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) - \r\n"
//					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1 AS nu_vac,\r\n"
//					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,\r\n"
//					+ "tf.li_condicion,usr.no_usuario,TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones, sv.tipo\r\n"
//					+ "FROM rhtm_trabajador t,rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,\r\n"
//					+ "rhtm_contrato co,rhmv_hist_detalle hd,rhtc_usuario usr,rhtd_empleado emp\r\n"
//					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
//					+ "AND emp.id_empleado = usr.id_empleado\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
//					+ "AND dsv.estado <> 0\r\n" + "AND hd.estado = 1\r\n" + "AND hd.evaluacion = 3\r\n"
//					+ "AND hd.id_pasos = 'PAS-000054'\r\n" + "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
//					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
//					+ "AND tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
//					+ "AND co.es_contrato = 1\r\n" + "AND tf.no_dep = '" + depa + "'"
//					+ " AND RHFU_VAL_REQUEST(tf.ID_TRABAJADOR)<>1";
			
			sql = "SELECT DISTINCT tf.ID_TRABAJADOR ,tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR, tf.ap_paterno, tf.ap_materno, tf.NO_AREA, tf.NO_SECCION, v.TIPO , con.LI_CONDICION\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf\r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO\r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones\r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones\r\n" + 
					"left join RHTM_CONTRATO con on con.ID_TRABAJADOR = tf.ID_TRABAJADOR\r\n" + 
					"where tf.no_dep ='"+depa+"' \r\n" + 
					"and tf.estado=1 \r\n" + 
					"and hisde.EVALUACION=3 \r\n" + 
					"and hisde.ESTADO=1\r\n" + 
					"and hisde.ID_PASOS='PAS-000054'\r\n" + 
					"and detva.ESTADO <> 0 order by tf.ap_paterno asc";
			
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}
	
	public List<Map<String, Object>> listardetalleaprobados(String IDTRB) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT tf.ID_TRABAJADOR, tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR,detva.ID_DET_VACACIONES, tf.ap_paterno,\r\n" + 
					"tf.ap_materno, tf.NO_AREA, tf.NO_SECCION,to_char(detva.FECHA_INICIO,'DD/MM/YYYY') as\r\n" + 
					"FECHA_INICIO, to_char(detva.fecha_fin,'DD/MM/YYYY') as fecha_fin, detva.fecha_fin - detva.FECHA_INICIO +1 AS DIAS\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf \r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO\r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones\r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones\r\n" + 
					"where tf.estado=1\r\n" + 
					"and hisde.EVALUACION=3\r\n" + 
					"and hisde.ESTADO=1\r\n" + 
					"and hisde.ID_PASOS='PAS-000054'\r\n" + 
					"and detva.ESTADO <> 0\r\n" + 
					"and tf.ID_TRABAJADOR = '"+ IDTRB +"' ORDER BY detva.ID_DET_VACACIONES ASC";
			
			LST = jt.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}

	public List<Map<String, Object>> listarRechazadosDetalle(String depa, String idtr) {

		try {
			sql = "SELECT DISTINCT tf.id_trabajador,\r\n"
					+ "trunc(TO_DATE(dsv.fecha_fin,'DD/MM/YYYY hh24:mi:ss') ) -\r\n"
					+ "trunc(TO_DATE(dsv.fecha_inicio,'DD/MM/YYYY hh24:mi:ss') ) + 1 AS nu_vac,\r\n"
					+ "t.nu_doc,TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\r\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin,\r\n"
					+ "TRIM(dsv.id_det_vacaciones) AS id_det_vacaciones,men.texto, sv.tipo\r\n"
					+ "FROM rhtm_trabajador t,rhmv_trabajador_filtrado tf,rhmv_vacaciones sv,rhmv_det_vacaciones dsv,\r\n"
					+ "rhtc_usuario usr,rhtd_empleado emp,rhmv_hist_detalle hd,rhmv_mensaje men,rhmv_notificaciones noti\r\n"
					+ "WHERE tf.id_trabajador = t.id_trabajador\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
					+ "AND emp.id_empleado = usr.id_empleado\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n"
					+ "AND dsv.id_det_vacaciones = noti.id_det_vacaciones\r\n"
					+ "AND men.id_mensaje = noti.id_mensaje\r\n" + "AND sv.estado = 1\r\n" + "AND tf.estado = 1\r\n"
					+ "AND dsv.estado <> 0\r\n" + "AND hd.evaluacion = 4\r\n" + "AND hd.id_pasos = 'PAS-000054'\r\n"
					+ "AND hd.estado = 1\r\n" + "AND tf.no_dep = '" + depa + "'\r\n" + "AND tf.id_trabajador='" + idtr
					+ "'\r\n" + "order by id_det_vacaciones";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> listarRechazadosNombres(String depa) {

		try {
			sql = "SELECT DISTINCT tf.id_trabajador,tf.no_trabajador,tf.ap_paterno,tf.ap_materno,tf.no_seccion,\r\n"
					+ "tf.li_condicion, usr.no_usuario, sv.tipo, t.nu_doc\r\n"
					+ "FROM rhtm_trabajador t,rhmv_trabajador_filtrado tf,rhmv_vacaciones sv,rhmv_det_vacaciones dsv,\r\n"
					+ "rhtc_usuario usr,rhtd_empleado emp,rhmv_hist_detalle hd\r\n"
					+ "WHERE tf.id_trabajador = t.id_trabajador\r\n"
					+ "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND emp.id_trabajador = t.id_trabajador\r\n"
					+ "AND emp.id_empleado = usr.id_empleado\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n" + "AND sv.estado = 1\r\n"
					+ "AND tf.estado = 1\r\n" + "AND dsv.estado <> 0\r\n" + "AND hd.evaluacion = 4\r\n"
					+ "AND hd.id_pasos = 'PAS-000054'\r\n" + "AND hd.estado = 1\r\n" + "AND tf.no_dep = '" + depa + "'";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
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

	public int observarVac(String usuario, String depa, String[] id_det, String[] text, String emisor,
			String receptor) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);
			Array txtarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_MENSAJE_OBS", text);

			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_OBS_VAC (?,?,?,?,?,?)}");
			cst.setString(1, usuario);
			cst.setString(2, depa);
			cst.setArray(3, idarr);
			cst.setArray(4, txtarr);
			cst.setString(5, emisor);
			cst.setString(6, receptor);
			cst.execute();
			cn.close();
			i = 1;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return i;
	}

	public List<Map<String, Object>> GetEmailEmployee(String depa, String id_det, String id_traba) {
		try {
			sql = "SELECT DISTINCT men.texto,t.di_correo_personal,\r\n"
					+ "TO_CHAR(dsv.fecha_inicio,'DD/MM/YYYY') AS fecha_inicio,\r\n"
					+ "TO_CHAR(dsv.fecha_fin,'DD/MM/YYYY') AS fecha_fin\r\n"
					+ "FROM rhmv_vacaciones sv,rhmv_trabajador_filtrado tf,rhmv_det_vacaciones dsv,rhtm_contrato co,\r\n"
					+ "rhtm_trabajador t,rhmv_hist_detalle hd,rhmv_mensaje men,rhmv_notificaciones noti\r\n"
					+ "WHERE sv.id_vacaciones = dsv.id_vacaciones\r\n" + "AND sv.estado = 1\r\n"
					+ "AND tf.estado = 1\r\n" + "AND dsv.estado <> 0\r\n" + "AND hd.evaluacion = 4\r\n"
					+ "AND hd.id_pasos = 'PAS-000054'\r\n" + "AND sv.id_vacaciones = dsv.id_vacaciones\r\n"
					+ "AND hd.id_det_vacaciones = dsv.id_det_vacaciones\r\n" + "AND dsv.id_det_vacaciones = '" + id_det
					+ "'\r\n" + "AND tf.id_trabajador_filtrado = sv.id_trabajador_filtrado\r\n"
					+ "AND t.id_trabajador = co.id_trabajador\r\n" + "AND co.es_contrato = 1\r\n" + "AND tf.no_dep = '"
					+ depa + "'\r\n" + "AND men.id_mensaje = noti.id_mensaje\r\n"
					+ "AND dsv.id_det_vacaciones = noti.id_det_vacaciones\r\n" + "AND t.id_trabajador = '" + id_traba
					+ "'\r\n" + "AND t.di_correo_personal != '--'\r\n" + "AND t.di_correo_personal != '-'\r\n"
					+ "AND t.di_correo_personal IS NOT NULL";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}

	public List<Map<String, Object>> GetEmailSecre(String depa) {
		try {
			sql = "SELECT DISTINCT t.di_correo_personal\r\n"
					+ "FROM rhtm_contrato co,rhtm_trabajador t,rhmv_trabajador_filtrado tf,\r\n"
					+ "rhtd_empleado emp,rhtc_usuario usr,rhtr_rol rl\r\n"
					+ "WHERE tf.id_trabajador = t.id_trabajador\r\n" + "AND t.id_trabajador = co.id_trabajador\r\n"
					+ "AND emp.id_trabajador = t.id_trabajador\r\n" + "AND emp.id_empleado = usr.id_empleado\r\n"
					+ "AND rl.id_rol = usr.id_rol\r\n" + "AND tf.no_dep = '" + depa + "'\r\n"
					+ "AND co.es_contrato = 1\r\n" + "AND rl.no_rol = 'Secretaria de Departamento'\r\n"
					+ "AND t.di_correo_personal != '--'\r\n" + "AND t.di_correo_personal != '-'\r\n"
					+ "AND t.di_correo_personal IS NOT NULL";
			return jt.queryForList(sql);
		} catch (Exception e) {
			System.out.println("ERROR:" + e);
			return null;
		}
	}
}
