package pe.edu.upeu.gth.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ReporteVacacionesDAO;

public class ReporteTest {

	ReporteVacacionesDAO rd;
	Gson g;

	@Before
	public void Start() {
		g = new Gson();
		DataSource ds = AppConfig.getDataSource();
		rd = new ReporteVacacionesDAO(ds);

	}

	@Test
	public void reportePorDepartamento() {
		
		assertNotEquals(null, rd.reportePorDepartamento("05/05/2018", "06/07/2018"));
	}

}
