package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Detalle_motivo;
import pe.edu.upeu.gth.dto.Renuncia;

public class Detalle_motivoDAO {

	String sql;
	PreparedStatement ps;
	CallableStatement cs;
	ResultSet rs;
	Connection cn;
	DataSource d = AppConfig.getDataSource();

	private static JdbcTemplate jt;

	public Detalle_motivoDAO(DataSource dataSource) {
		jt = new JdbcTemplate(dataSource);
	}

	public int insertarOtros(Detalle_motivo r) {
		int x = 0;
		String sql = "UPDATE RA_DETALLE_MOTIVO SET OTROS=? WHERE ID_DETALLE_MOTIVO=(SELECT MAX(ID_DETALLE_MOTIVO) FROM RA_DETALLE_MOTIVO)";
		try {
			jt.update(sql, new Object[] { r.getOtros() });
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

}
