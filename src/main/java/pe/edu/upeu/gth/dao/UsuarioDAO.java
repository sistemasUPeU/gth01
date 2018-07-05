package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Map;
import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.gth.config.AppConfig;

@Repository
public class UsuarioDAO {

	String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();

    private JdbcTemplate jt;

    public UsuarioDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }
	
	public ArrayList<Map<String, Object>> listar() {
		sql = "SELECT * FROM RHTC_USUARIO";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql);
	}

	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean edit(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public ArrayList<Map<String, Object>> validar(String usuario, String pass) {
        sql = "select * from rhvd_usuario where trim(no_usuario)=? and trim(pw_usuario)=?";
        return (ArrayList<Map<String, Object>>) jt.queryForList(sql, usuario, pass);
    }
	public Map<String, Object> getByUserName(String usuario) {
        sql = "select * from rhvd_usuario where trim(no_usuario)=?";
        return jt.queryForMap(sql, usuario);
    }
    
}
