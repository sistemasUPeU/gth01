package pe.edu.upeu.gth.test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.AprobarProgramaVacaciones;

public class AprobarProgramaTest {
	Gson g = new Gson();
	DataSource ds = AppConfig.getDataSource();
	AprobarProgramaVacaciones t = new AprobarProgramaVacaciones(ds);

	@Test
	public void getSinAprobar() {
		String depa = "Gerencia Financiera";
		String res = g.toJson(t.listarSinAprobar(depa));
		String[] esperado = new String[0];
		assertEquals(g.toJson(esperado), res);
	}

	@Test
	public void getAprobados() {
		String depa = "Gerencia Financiera";
		String res = g.toJson(t.listarAprobados(depa));
		String[] esperado = new String[0];
		assertEquals(g.toJson(esperado), res);
	}

	@Test
	public void getRechazados() {
		String depa = "Gerencia Financiera";
		String res = g.toJson(t.listarRechazados(depa));
		String[] esperado = new String[0];
		assertEquals(g.toJson(esperado), res);
	}

	// @Test
	// public void guardarAprobar() {
	// String usuario = "hola";
	// String id_det = "hola,hola";
	// String[] idarray = id_det.split(",");
	// String res = g.toJson(t.apobarVac(usuario, idarray));
	// String esperado = "0";
	// assertEquals(esperado, res);
	// }

	@Test
	public void guardarObservar() {
		String usuario = "hola";
		String depa = "Gerencia Financiera";
		String id_det = "id_det";
		String text = "text";
		String emisor = "emisor";
		String receptor = "receptor";
		String res = g.toJson(t.observarVac(usuario, depa, id_det, text, emisor, receptor));
		String esperado = "1";
		assertEquals(esperado, res);
	}

	@Test
	public void getObservacion() {
		List<Map<String, Object>> lista_e = new ArrayList<>();
		List<Map<String, Object>> lista_s = new ArrayList<>();
		int c = 0;
		String depa = "Gerencia Financiera";
		String id_det = "DEV-00007";
		String receptor = "TRB-001420";
		lista_e = t.GetEmailEmployee(depa, id_det, receptor);
		lista_s = t.GetEmailSecre(depa);
		String fecha_ini = lista_e.get(0).get("FECHA_INICIO").toString();
		String fecha_fin = lista_e.get(0).get("FECHA_FIN").toString();
		String[] arrayEmail = new String[lista_s.size() + 1];
		System.out.println(g.toJson(lista_s));
		for (int i = 0; i < lista_s.size(); i++) {
			System.out.println(lista_s.get(i).get("DI_CORREO_PERSONAL"));
			arrayEmail[i] = lista_s.get(i).get("DI_CORREO_PERSONAL").toString();
			c = i;
		}
		arrayEmail[c + 1] = lista_e.get(0).get("DI_CORREO_PERSONAL").toString();
		String text = lista_e.get(0).get("TEXTO").toString();
		String format = "Su Jefe de departamento ha observado su programa de vacaciones:\r\n" + text + "\r\n"
				+ "El programa de vacaciones observado es del " + fecha_ini + " al " + fecha_fin;
		System.out.println(g.toJson(arrayEmail));
		String res = "Su Jefe de departamento ha observado su programa de vacaciones:\r\n" + "no sale sara por dos\r\n"
				+ "El programa de vacaciones observado es del 30/06/2018 al 29/07/2018";
		String esperado = format;
		assertEquals(esperado, res);

	}

}
