package pe.edu.upeu.gth.test;

import static org.junit.Assert.*;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.ConfiguracionDAO;

public class ConfiguracionesTest {

	
	Gson g;
	ConfiguracionDAO cd;
	@Before
	public void Start() {
		g = new Gson();
		DataSource ds = AppConfig.getDataSource();
		cd = new ConfiguracionDAO(ds);
		
	}
	
	@Test
	public void consolidadoExists() {
		assertNotEquals(null, cd.consolidadoExists());
	}
	
	@Test
	public void crearConsolidado() {
		assertEquals(1, cd.crearConsolidado("nombre", 5));
	}

	@Test
	public void crearConfiguracion() {
		assertEquals(1, cd.crearConfiguracion("20/10/2018", "10/11/2018"));
	}
	
	@Test
	public void listarDepartamento() {
		assertNotEquals(null, cd.listarDepartamento());
	}
	
	@Test
	public void insertar_nuevo_plazo() {
		assertEquals(1, cd.insertar_nuevo_plazo("DPT-0088", "30/06/2018", 1));
	}
	
	
	@Test
	public void buscarTrabajador() {
		assertNotEquals(null, cd.buscarTrabajador("73258873"));
	}
	
//	@Test
//	public void guardarPrivilegio() {
//		assertEquals(1, cd.guardarPrivilegio("TRB-002364", 3));
//	}
	
}
