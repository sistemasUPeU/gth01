package pe.edu.upeu.gth.dao;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.mail.javamail.JavaMailSender;

import pe.edu.upeu.gth.config.AppConfig;

public class ReporteVacacionesDAO {

	
	private JdbcTemplate jt;

	DataSource d = AppConfig.getDataSource();
	Connection cn;
	
	
	@Autowired
	JavaMailSender mailSender;

	public ReporteVacacionesDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}
	
	
	
	
	public List<Map<String, Object>> reportePorDepartamento(String fecha1, String fecha2) {

		String sql = "SELECT COUNT(C.NO_DEP)AS NRO, D.NO_DEP  FROM (\r\n" + 
				"SELECT B.NO_DEP, TO_CHAR(A.FECHA_CREACION, 'DD/MM/YYYY') FROM RHMV_VACACIONES A \r\n" + 
				"JOIN RHMV_TRABAJADOR_FILTRADO B ON A.ID_TRABAJADOR_FILTRADO = B.ID_TRABAJADOR_FILTRADO\r\n" + 
				"WHERE TO_CHAR(A.FECHA_CREACION, 'DD/MM/YYYY') BETWEEN TO_DATE(?, 'DD/MM/YYYY') AND TO_DATE(?, 'DD/MM/YY')) C \r\n" + 
				"RIGHT JOIN RHTX_DEPARTAMENTO D\r\n" + 
				"ON C.NO_DEP = D.NO_DEP\r\n" + 
				"GROUP BY D.NO_DEP\r\n" + 
				"ORDER BY NRO DESC";

		return jt.queryForList(sql, fecha1, fecha2);

	}
	public List<Map<String, Object>> reportePorMeses() {

		String sql = "";

		return jt.queryForList(sql);

	}

	
}
