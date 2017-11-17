package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

//import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class RenProcesarDAO implements CRUDOperations{
	private static final String Connection = null;
	String sql;
	String resultado;
	String query;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
//    DataSource d = AppConfig.getDataSource();

    private static JdbcTemplate jt;

    public RenProcesarDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

	@Override
	public ArrayList<Map<String, Object>> listar() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean edit(Object o) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Object o) {
		// TODO Auto-generated method stub
		return false;
	}
	
	public List<Map<String,Object>> Procesar() {
		sql = "select ID_TRABAJADOR,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
		
        return jt.queryForList(sql);
    }

}
