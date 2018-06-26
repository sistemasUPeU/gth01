package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class ConfiguracionDAO {

	JdbcTemplate jt = new JdbcTemplate();
	String sql = "";

	public ConfiguracionDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public String consolidadoExists() {
		String ls = null;
		try {
			sql = "SELECT TRIM(ID_CONSOLIDADO) FROM RHMV_CONSOLIDADO WHERE ESTADO = 1";
			ls = jt.queryForObject(sql, String.class);
			// @SuppressWarnings("unchecked")
			// String ls= (String) jt.queryForObject(sql, new Object[] {},
			// new BeanPropertyRowMapper<String>(String.class));

			// String list = ;
			//// System.out.println("lista show> "+ls);
			// if(list.isEmpty()) {
			// res = "";

			// System.out.println(res);
		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}

	public int crearConsolidado(String alerta) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONSOLIDADO (ESTADO, FECHA_INICIO, FECHA_FINAL, ALERTA_MENSUAL) VALUES (?, ?, ?,?)";
			ls = jt.update(sql,1, "01/01/18", "31/12/18",  alerta);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	
	public int crearConfigPrograma(String fecha) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONF_PROGRAMA(ESTADO, FECHA_PLAZO) VALUES (?,?)";
			ls = jt.update(sql,1, fecha);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	public int crearConfigSolicitud(String fecha) {
		int ls = 0;
		try {
			sql = "INSERT INTO RHMV_CONF_SOLICITUD(ESTADO, FECHA_PLAZO) VALUES (?,?)";
			ls = jt.update(sql,1, fecha);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}

		return ls;
	}
	public int crearConfiguracion(String fecha_solicitud, String fecha_programa) {
		System.out.println("dao configuracion insertar " + fecha_solicitud +" , " + fecha_programa);
		int i = 0;
		try {
			DataSource d = AppConfig.getDataSource();
			CallableStatement cst = d.getConnection().prepareCall("{call RHSP_INSERT_DETALLE_CONFIG (?,?,?)}");
			cst.setString(1, fecha_solicitud);
			cst.setString(2, fecha_programa);
			
			cst.registerOutParameter(3, Types.VARCHAR);
			cst.execute();
			System.out.println(cst.getString(3));

			i = Integer.parseInt(cst.getString(3));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return i;
		
		

	}
	
	public List<Map<String, Object>> listarDepartamento() {
		String sql = null;
		
		List<Map<String, Object>> lista = new ArrayList<>();
		try {
			sql = "SELECT RT.ID_TRABAJADOR, RT.AP_PATERNO, RT.AP_MATERNO, RT.NO_TRABAJADOR,RT.DI_CORREO_PERSONAL,RT.NU_DOC,\r\n" + 
					"RD.NO_DEP, RD.ID_DEPARTAMENTO,\r\n" + 
					"RE.ID_EMPLEADO,RR.NO_ROL, TO_CHAR(RVC.FECHA_PLAZO,'dd/mm/yyyy') as FECHA_PROGRAMA, TO_CHAR(RVS.FECHA_PLAZO,'dd/mm/yyyy') AS FECHA_SOLICITUD\r\n" + 
					"FROM RHTM_TRABAJADOR RT\r\n" + 
					"LEFT JOIN RHTM_CONTRATO RC ON RT.ID_TRABAJADOR = RC.ID_TRABAJADOR\r\n" + 
					"LEFT JOIN RHTR_PUESTO RP ON RC.ID_PUESTO = RP.ID_PUESTO \r\n" + 
					"LEFT JOIN RHTR_SECCION RS ON RP.ID_SECCION = RS.ID_SECCION\r\n" + 
					"LEFT JOIN RHTD_AREA RA ON RS.ID_AREA = RA.ID_AREA \r\n" + 
					"LEFT JOIN RHTX_DEPARTAMENTO RD ON RA.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO\r\n" + 
					"LEFT JOIN RHTD_EMPLEADO RE ON RE.ID_TRABAJADOR = RT.ID_TRABAJADOR\r\n" + 
					"LEFT JOIN RHTC_USUARIO RU ON RE.ID_EMPLEADO = RU.ID_EMPLEADO\r\n" + 
					"LEFT JOIN RHTR_ROL RR ON RR.ID_ROL = RU.ID_ROL\r\n" + 
					"LEFT JOIN RHMV_DETALLE_CONFIG RVD ON RVD.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO\r\n" + 
					"LEFT JOIN RHMV_CONF_PROGRAMA RVC ON RVC.ID_CONF_PROGRAMA = RVD.ID_CONF_PROGRAMA\r\n" + 
					"LEFT JOIN RHMV_CONF_SOLICITUD RVS ON RVD.ID_CONF_SOLICITUD = RVS.ID_CONF_SOLICITUD\r\n" + 
					"WHERE RT.ID_TRABAJADOR = RC.ID_TRABAJADOR\r\n" + 
					"AND RC.ES_CONTRATO IN (1,2)\r\n" + 
					"AND RD.ES_DEPARTAMENTO=1\r\n" + 
					"AND RR.ID_ROL='ROL-0003'\r\n" + 
					"AND RVC.ESTADO=1\r\n" + 
					"AND RVS.ESTADO=1";
			lista = jt.queryForList(sql);

		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("listar departmento configuraciones dao> " + e);
			lista= null;

		}
//		System.out.println(lista);
		return lista;
	}
	
	
	
	public int insertar_nuevo_plazo(String iddep, String fecha, int tipo) {
		
		try {
			DataSource DS = AppConfig.getDataSource();
			CallableStatement cst = DS.getConnection().prepareCall("{call RHSP_VAC_INSERT_NUEVO_PLAZO (?,?,?,?)}");
			cst.setString(1, iddep);
			cst.setString(2,fecha);
			cst.setInt(3, tipo);
			cst.registerOutParameter(4,Types.NUMERIC);
			cst.execute();
			int i = cst.getInt(4);
			return i;
		} catch (SQLException E) {
			E.printStackTrace();
			System.out.println("ERROR INSERTAR NUEVO PLAZO: " + E);
			return 0;
		}
	}
	
	public List<Map<String, Object>> buscarTrabajador(String dni) {
		String sql = null;
		
		List<Map<String, Object>> lista = new ArrayList<>();
		try {
			sql = "   SELECT RT.ID_TRABAJADOR, RT.AP_PATERNO, RT.AP_MATERNO, RT.NO_TRABAJADOR, RT.DI_CORREO_PERSONAL,RT.NU_DOC, RT.VA_PRIVILEGIO,\r\n" + 
					" RS.ID_SECCION, RS.NO_SECCION, RA.ID_AREA, RA.NO_AREA, RD.ID_DEPARTAMENTO, RD.NO_DEP, RR.NO_ROL, RR.ID_ROL\r\n" + 
					"FROM RHTM_TRABAJADOR RT\r\n" + 
					"LEFT JOIN RHTM_CONTRATO RC ON RT.ID_TRABAJADOR = RC.ID_TRABAJADOR\r\n" + 
					"LEFT JOIN RHTR_PUESTO RP ON RC.ID_PUESTO = RP.ID_PUESTO \r\n" + 
					"LEFT JOIN RHTR_SECCION RS ON RP.ID_SECCION = RS.ID_SECCION\r\n" + 
					"LEFT JOIN RHTD_AREA RA ON RS.ID_AREA = RA.ID_AREA \r\n" + 
					"LEFT JOIN RHTX_DEPARTAMENTO RD ON RA.ID_DEPARTAMENTO = RD.ID_DEPARTAMENTO\r\n" + 
					"LEFT JOIN RHTD_EMPLEADO RE ON RE.ID_TRABAJADOR = RT.ID_TRABAJADOR\r\n" + 
					"LEFT JOIN RHTC_USUARIO RU ON RE.ID_EMPLEADO = RU.ID_EMPLEADO\r\n" + 
					"LEFT JOIN RHTR_ROL RR ON RR.ID_ROL = RU.ID_ROL\r\n" + 
					"WHERE RT.ID_TRABAJADOR = RC.ID_TRABAJADOR\r\n" + 
					"AND RC.ES_CONTRATO IN (1,2)\r\n" + 
					"AND RD.ES_DEPARTAMENTO=1\r\n" + 
					"AND RT.NU_DOC = '"+dni+"'";
			lista = jt.queryForList(sql);
			
		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("listar departmento configuraciones dao> " + e);
			lista= null;

		}
		return lista;
	}
	
	public int guardarPrivilegio(String idtrab, int privilegio) {
		int ls = 0;
		try {
			sql = "UPDATE RHTM_TRABAJADOR SET VA_PRIVILEGIO = ? WHERE ID_TRABAJADOR = ?";
			ls = jt.update(sql,privilegio, idtrab);
			
		} catch (Exception e) {
			// // TODO: handle exception
			System.out.println("dao conf> " + e);

		}
		System.out.println("dao respuesta guardar privilegio > " +ls);
		return ls;
	}
	

}
