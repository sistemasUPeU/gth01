package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class UbigeoDAO {
	JdbcTemplate jt = new JdbcTemplate();
	String sql = "";

	public UbigeoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
	
	public List<Map<String, Object>> List_Distrito() {
        sql = "select * from rhvd_Ubigeo";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> List_Provincia() {
        sql = "select * from rhtx_ub_provincia order by no_provincia";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> List_Departamento() {
        sql = "select * from rhtx_ub_departamento order by no_departamento";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> List_DistritoTra() {
        sql = "select * from rhtx_ub_distrito order by no_distrito";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> Departamento() {
        sql = "select id_departamento,no_departamento from rhtx_ub_departamento";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> Provincia(String id) {
        sql = "select id_provincia,no_provincia from rhtx_ub_provincia where id_departamento=?";
        return jt.queryForList(sql,id);
    }
	
	public List<Map<String, Object>> Distrito(String id) {
        sql = "select id_distrito,no_distrito from rhtx_ub_distrito where id_provincia =?";
        return jt.queryForList(sql,id);
    }
	
}
