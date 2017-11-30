package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;

public class SolicitudVacacionesDAO {
	private JdbcTemplate jt;

	DataSource d = AppConfig.getDataSource();
	Connection cn;

	public SolicitudVacacionesDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> llenar_solicitud(String idt) {
		List<Map<String, Object>> lista = new ArrayList<>();
		try {
			String sql = "SELECT a.ID_TRABAJADOR_FILTRADO, a.ID_CONSOLIDADO, b.ID_TRABAJADOR, b.AP_PATERNO, b.AP_MATERNO, b.NO_TRABAJADOR,b.NU_DOC , c.ID_VACACIONES, c.TIPO, c.ESTADO,\r\n"
					+ "d.ID_DET_VACACIONES, d.FECHA_INICIO, d.FECHA_FIN, D.ESTADO\r\n"
					+ "FROM RHMV_TRABAJADOR_FILTRADO A\r\n"
					+ "JOIN RHTM_TRABAJADOR B ON A.ID_TRABAJADOR = B.ID_TRABAJADOR\r\n"
					+ "JOIN RHMV_VACACIONES C  ON C.ID_TRABAJADOR_FILTRADO = A.ID_TRABAJADOR_FILTRADO\r\n"
					+ "JOIN RHMV_DET_VACACIONES D ON C.ID_VACACIONES = D.ID_VACACIONES\r\n" + "AND C.ESTADO = 1\r\n"
					+ "AND B.ID_TRABAJADOR = '" + idt + "'";

			lista = jt.queryForList(sql);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - dao:" + e);
		}

		return lista;
	}

	public int trabajador_filtrado(String id) {
		int x;
		try {
			String sql = "select * from rhmv_trabajador_filtrado a where a.ID_TRABAJADOR= '" + id + "'";
			jt.queryForList(sql);
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error - dao - soli:" + e);
			x = 0;
		}
		return x;
	}

	public int validarTipoSolicitud(String codigo) {
		int i = 0;
		try {
			DataSource d = AppConfig.getDataSource();
			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_VALIDAR_SOLICITUD1 (?,?)}");
			cst.setString(1, codigo);
			cst.registerOutParameter(2, Types.NUMERIC);
			cst.execute();
			System.out.println(cst.getInt(2));

			i = cst.getInt(2);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

	public int insertarSolicitud(String[] inicio, String[] fin, String idt, String tipo, String user) {

		int i = 0;
		try {
			
			
			cn = d.getConnection();
			Array array_inicio = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_FE_INICIO", inicio);
			Array array_fin = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_FE_FINAL", fin);
			

			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_VAC_INSERT_VACACIONES (?,?,?,?,?,?)}");
			cst.setArray(1, array_inicio);
			cst.setArray(2, array_fin);
			cst.setString(3, idt);
			cst.setString(4, tipo);
			cst.setString(5, user);
			cst.registerOutParameter(6, Types.NUMERIC);
			cst.execute();
			System.out.println(cst.getInt(6));
//			cn.commit();
			cn.close();
			

			i = cst.getInt(6);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
	}

}
