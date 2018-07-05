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
import org.springframework.stereotype.Repository;

import oracle.jdbc.driver.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;

@Repository
public class GestionarPrograVacacDAO {
	JdbcTemplate JDBC;
	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public GestionarPrograVacacDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}

	public List<Map<String, Object>> READALL(String depa) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT tf.ID_TRABAJADOR_FILTRADO, tf.ID_TRABAJADOR , tf.NO_TRABAJADOR, tf.ap_paterno, tf.ap_materno, \r\n" + 
					"tf.NO_AREA, tf.NO_SECCION,to_char(detva.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, \r\n" + 
					"to_char(detva.fecha_fin,'DD/MM/YYYY') as fecha_fin\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf \r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO \r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones\r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones \r\n" + 
					"where tf.no_dep ='"+depa+"' \r\n" + 
					"and tf.estado=1\r\n" + 
					"and detva.FECHA_INICIO is null and detva.FECHA_FIN is null";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
	public List<Map<String, Object>> TrabajadoresConSoli(String depa) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR,detva.ID_DET_VACACIONES, tf.ap_paterno, \r\n" + 
					"tf.ap_materno, tf.NO_AREA, tf.NO_SECCION,to_char(detva.FECHA_INICIO,'DD/MM/YYYY') as \r\n" + 
					"FECHA_INICIO, to_char(detva.fecha_fin,'DD/MM/YYYY') as fecha_fin\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf \r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO\r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones\r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones\r\n" + 
					"where tf.no_dep ='"+depa+"'  \r\n" + 
					"and tf.estado=1\r\n" + 
					"and hisde.EVALUACION=1\r\n" + 
					"and hisde.ESTADO=1\r\n" + 
					"and hisde.ID_PASOS='PAS-000055'\r\n" + 
					"and detva.ESTADO <> 0";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
	public List<Map<String, Object>> TrabajadoresAprobados(String depa) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR,  detva.ID_DET_VACACIONES,tf.ap_paterno, \r\n" + 
					"tf.ap_materno, tf.NO_AREA, tf.NO_SECCION,to_char(detva.FECHA_INICIO,'DD/MM/YYYY') as \r\n" + 
					"FECHA_INICIO, to_char(detva.fecha_fin,'DD/MM/YYYY') as fecha_fin\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf \r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO\r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones\r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones\r\n" + 
					"where tf.no_dep ='"+depa+"'  \r\n" + 
					"and tf.estado=1\r\n" + 
					"and hisde.EVALUACION=2\r\n" + 
					"and hisde.ESTADO=1\r\n" + 
					"and hisde.ID_PASOS='PAS-000055'\r\n" + 
					"and detva.ESTADO <> 0";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}


	public int apobarVac(String usuario, String[] id_det) {
		int i = 0;
		try {
			cn = d.getConnection();
			Array idarr = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_DET_VAC", id_det);
			CallableStatement cst = d.getConnection().prepareCall("{CALL RHSP_INSERT_APR_ENV (?,?)}");
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
	
	public List<Map<String, Object>> READFECHA(String id) {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT RD.ID_DET_VACACIONES ,TO_CHAR(RD.FECHA_INICIO, 'DD/MM/YYYY') AS FECHA_INICIO, TO_CHAR(RD.FECHA_FIN, 'DD/MM/YYYY') AS FECHA_FIN FROM RHMV_DET_VACACIONES RD WHERE RD.ID_DET_VACACIONES = ?";
			LST = JDBC.queryForList(SQL, id);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}
	
	public int UPDATEFECHA(String id, String inicio, String fin) {

		String SQL = "UPDATE RHMV_DET_VACACIONES SET FECHA_INICIO = ?, FECHA_FIN = ? WHERE ID_DET_VACACIONES = ?";
		try {
			JDBC.update(SQL, new Object[] { inicio, fin, id });
			return 1;
		} catch (Exception E) {
			System.out.println("ERROR: " + E);
			return 0;
		}
	}
}
