package pe.edu.upeu.gth.dao;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import oracle.jdbc.OracleConnection;
import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Abandono;

public class AbandonoDAO {

	String sql;
	PreparedStatement ps;
	CallableStatement cs;
	ResultSet rs;
	Connection cn;
	DataSource d = AppConfig.getDataSource();

	private static JdbcTemplate jt;

	public AbandonoDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}
	
	//INSERTAR UN NUEVO ABANDONO
	public int insertarAbandono(Abandono a) {
		int x = 0;
		String sql = "INSERT INTO RA_RENABAN(ID_CONTRATO,TI_ARCHIVO,NO_ARCHIVO,FECHA_CARTA,ID_USUARIO,TIPO) VALUES(?,?,?,?,?,?)";
		try {
			jt.update(sql, new Object[] { a.getId_contrato(), a.getTi_archivo(), a.getNo_archivo(), a.getFecha(),a.getId_usuario(),a.getTipo()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	//LISTA LOS DETALLES DEL TRABAJADOR
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String dni,String depa) {
		sql = "select ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD FROM REN_VIEW_TRABAJADOR";

		sql += " where DNI='" + dni + "' and NOM_DEPA='"+depa+"'";

		return jt.queryForList(sql);
	}
	
	// LISTA DE MOTIVOS DE ABANDONO
		public List<Map<String, Object>> mostrarMotivo() {
			sql = "SELECT* FROM RA_MOTIVO";
			return jt.queryForList(sql);
		}
		
	// INSERTAR MOTIVOS
		// @SuppressWarnings("deprecation")
		public int insertarMotivos(String[] array) {
			int x = 0;
			try {
				cn = d.getConnection();
				Array arreglo = ((OracleConnection) cn).createOracleArray("GTH.ARRAY_ID_MOTIVO", array);
				cs = cn.prepareCall("call REN_SP_INSERTAR_MOTIVOS( ? )");
				cs.setArray(1, arreglo);
				x = 1;

				cs.execute();
				cn.commit();
				cn.close();
			} catch (Exception e) {
				System.out.println("Error al insertar motivos " + e);
			}
			return x;
		}
		public List<Map<String, Object>> cargarMotivo(String idtr) {
			sql = "SELECT * FROM REN_RENUNCIA r, RHTM_CONTRATO t WHERE r.ID_CONTRATO=t.ID_CONTRATO";

			sql += " and r.ID_CONTRATO='" + idtr + "' ";

			return jt.queryForList(sql);
		}
    
		
}
