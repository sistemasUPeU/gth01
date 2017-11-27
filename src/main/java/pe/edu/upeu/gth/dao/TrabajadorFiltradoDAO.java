package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import pe.edu.upeu.gth.config.AppConfig;

@Repository
public class TrabajadorFiltradoDAO {
	JdbcTemplate JDBC;

	public TrabajadorFiltradoDAO(DataSource DS) {
		JDBC = new JdbcTemplate(DS);
	}

	public List<Map<String, Object>> READALL() {
		List<Map<String, Object>> LST = new ArrayList<>();
		try {
			String SQL = "SELECT * FROM RHVV_TRABAJADOR_CONTRATO";
			LST = JDBC.queryForList(SQL);
			return LST;
		} catch (Exception E) {
			System.out.println("ERROR:" + E);
			return null;
		}
	}

	public int CONFIRMAR () {

		try {
			DataSource DS = AppConfig.getDataSource();
			CallableStatement CST = DS.getConnection().prepareCall("{call RHSP_INSERT_TRAB_FIL}");
			CST.execute();
			return 1;
		} catch (SQLException E) {
			E.printStackTrace();
			System.out.println("ERROR: " + E);
			return 0;
		}
	}
}
