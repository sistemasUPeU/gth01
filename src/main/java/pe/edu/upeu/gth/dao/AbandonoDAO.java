package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

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
	
	public int insertarAbandono(Abandono r) {
		int x = 0;
		String sql = "INSERT INTO RA_RENABAN(ID_CONTRATO,TI_ARCHIVO,NO_ARCHIVO,FECHA_CARTA,ID_USUARIO,TIPO) VALUES(?,?,?,?,?,'A')";
		try {
			jt.update(sql, new Object[] { r.getId_contrato(), r.getTi_archivo(), r.getNo_archivo(), r.getFecha(),r.getId_usuario() });
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}

}
