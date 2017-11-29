package pe.edu.upeu.gth.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.dto.Legajo;

public class LegajoDAO {
	
	private static JdbcTemplate jt;

	public LegajoDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}
	
	public int insertarLegajo(Legajo r) {
		int x = 0;
		String sql = "INSERT INTO REN_RENUNCIA(ID_TRABAJADOR,FECHA_LEGAJO,ID_TIPO_DOC,OTROS,DETALLE_OTROS) VALUES(?,SYSDATE,?,?,?)";
		try {
			jt.update(sql, new Object[] { r.getIdtrabajador(),  r.getId_tipo_doc(), r.getOtros(),r.getDetalle_otros()});
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}



}
