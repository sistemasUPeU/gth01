package pe.edu.upeu.gth.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.dto.Legajo;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;

public class LegajoDAO {
	
	private static JdbcTemplate jt;

	public LegajoDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}
	
	public int insertarLegajo(Legajo r) {
		int x = 0;
		String sql = "INSERT INTO REN_LEGAJO(ID_TRABAJADOR,FECHA_LEGAJO,ID_TIPO_DOC,OTROS,DETALLE_OTROS) VALUES(?,SYSDATE,?,?,?)";
		try {
			jt.update(sql, new Object[] { r.getIdtrabajador(),  r.getId_tipo_doc(), r.getOtros(),r.getDetalle_otros()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int insertarMaxrRenuncia(Legajo r) {
		int x = 0;
		String sql = "INSERT INTO REN_LEGAJO(ID_RENUNCIA) VALUES(?)";
		try {
			jt.update(sql, new Object[] { r.getIdrenuncia()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	public int InsertarDocBenfSoc(Legajo ob) {
		int x = 0;
		String sql = "call REN_SP_INSERTAR_DOCUMENTOS(? , ? , ?)";
//		String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?, FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
		try {
		 jt.update(sql,new Object[] {ob.getNo_archivo(),ob.getId_tipo_doc(),ob.getTi_archivo()});
		 x=1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e);
		}
		return x;
		
	}
	
	public int EntregarRenuncia(Renuncia r) {
		int x = 0;
		String sql = "UPDATE REN_RENUNCIA SET ESTADO='Entregado' WHERE ID_RENUNCIA=?";
		try {
			jt.update(sql, new Object[] { r.getId_renuncia()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}



}
