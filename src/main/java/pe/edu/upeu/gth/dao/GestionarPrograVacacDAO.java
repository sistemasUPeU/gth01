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
			String SQL = "select tf.ID_TRABAJADOR_FILTRADO, tf.NO_TRABAJADOR, tf.ap_paterno, tf.ap_materno, tf.NO_AREA, tf.NO_SECCION,detva.ID_DET_VACACIONES,to_char(detva.FECHA_INICIO,'DD/MM/YYYY') as FECHA_INICIO, to_char(detva.fecha_fin,'DD/MM/YYYY') as fecha_fin\r\n" + 
					"from RHMV_TRABAJADOR_FILTRADO tf \r\n" + 
					"left join  RHMV_VACACIONES v on v.ID_TRABAJADOR_FILTRADO=tf.ID_TRABAJADOR_FILTRADO \r\n" + 
					"left join RHMV_DET_VACACIONES detva on detva.id_vacaciones=v.id_vacaciones \r\n" + 
					"left join RHMV_HIST_DETALLE hisde on hisde.id_det_vacaciones=detva.id_det_vacaciones \r\n" + 
					"where tf.estado=1 and hisde.EVALUACION=1 and hisde.ESTADO=1 and hisde.ID_PASOS='PAS-000055' and tf.no_dep ='"+depa+"'";
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
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
