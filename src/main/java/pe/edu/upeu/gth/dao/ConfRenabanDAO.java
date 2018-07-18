package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import pe.edu.upeu.gth.config.AppConfig;

public class ConfRenabanDAO {
	String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();
    
    private static JdbcTemplate jt;

    public ConfRenabanDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }
    
    //Lista todos los trabajadores que estan renunciando o abandonando
    public List<Map<String, Object>> Renuncia() {
//		System.out.println(tipo);
		sql = "SELECT r.PATERNO,r.MATERNO,r.NOMBRES, r.NOM_DEPA,r.DNI,e.ESTADO,r.TIPO FROM RA_VIEW_RENABAN r , RA_RENABAN e where r.ID_RENABAN=e.ID_RENABAN and r.TIPO='R'";
		return jt.queryForList(sql);
	}
    
    public List<Map<String, Object>> Abandono() {
//		System.out.println(tipo);
		sql = "SELECT r.PATERNO,r.MATERNO,r.NOMBRES, r.NOM_DEPA,r.DNI,e.ESTADO,r.TIPO FROM RA_VIEW_RENABAN r , RA_RENABAN e where r.ID_RENABAN=e.ID_RENABAN and r.TIPO='A'";
		return jt.queryForList(sql);
	}
}
