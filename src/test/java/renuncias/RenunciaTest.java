package renuncias;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Test;

import com.google.gson.Gson;

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dao.RenunciaDAO;
import pe.edu.upeu.gth.dto.Renuncia;


public class RenunciaTest {
	//TESTING BACKEND
	Gson gson = new Gson();
	
	//EL REGISTRO DE RENUNCIAS DEBE DURAR POR LO MUCHO UN SEGUNDO
	@Test(timeout=1000)
	public void probandoRenuncia() {
		DataSource d = AppConfig.getDataSource();
		Renuncia r = new Renuncia();
		
		r.setEstado("0");
		r.setFecha("06/08/2018");
		r.setId_contrato("CTO-002080");
		r.setNo_archivo("archivoPrueba1.png");
		r.setTi_archivo("png");
		RenunciaDAO rd = new RenunciaDAO(d);
		int esperado = rd.crearRenuncia(r);
		//EL VALOR ESPERADO ES 1
		assertEquals(esperado, 1);
	}
	
	//LA LISTA DE RENUNCIAS Y ABANDONOS DEBE DURAR POR LO MUCHO UN SEGUNDO
	@Test(timeout=1000)
	public void buscarRenuncias() {
		DataSource d = AppConfig.getDataSource();
		RenunciaDAO rd = new RenunciaDAO(d);
		List<Map<String, Object>> esperado= rd.buscarRenaban("MRA-000006");
		gson.toJson(esperado);
		
	}
	
	//LA LISTA DE RENUNCIAS Y ABANDONOS DEBE DURAR POR LO MUCHO UN SEGUNDO
	@Test(timeout=1000)
	public void listarRenuncias() {
		DataSource d = AppConfig.getDataSource();
		Renuncia r = new Renuncia();
		r.setEstado("0");
		r.setFecha("06/08/2018");
		r.setId_contrato("CTO-002080");
		r.setNo_archivo("archivox.png");
		RenunciaDAO rd = new RenunciaDAO(d);
		List<Map<String, Object>> esperado= rd.listarRenaban("DPT-0001");
		gson.toJson(esperado);
		
	}
	
	//LA DERIVACIÓN DE RENUNCIAS Y ABANDONOS DEBE DURAR POR LO MUCHO DOS SEGUNDOS
	@Test(timeout=2000)
	public void derivarRenuncias() {
		DataSource d = AppConfig.getDataSource();
		Renuncia r = new Renuncia();
		r.setEstado("0");
		r.setFecha("03/07/2018");
		r.setId_contrato("CTO-002080");
		r.setNo_archivo("archivox.png");
		String idra = "MRA-000009";
		String idusuario = "USR-001210";
		String tipo = "R";
		RenunciaDAO rd = new RenunciaDAO(d);
		int esperado = rd.DerivarRenuncia(r,idra,idusuario, tipo);
		//EL VALOR ESPERADO ES 1
		assertEquals(esperado, 1);
		
	}
	
	//EL ENVÍO DE CORREO DEBE SER NO MAYOR DE 4 SEGUNDOS
	@Test(timeout=4000)
	public void enviarCorreo() {
		DataSource d = AppConfig.getDataSource();
		RenunciaDAO rd = new RenunciaDAO(d);
		String de = "pruebagth@gmail.com";
		String clave = "GTH123456";
		String para = "jonathanromero@upeu.edu.pe";
		String mensaje = "mensaje prueba";
		String asunto ="asunto prueba";
		int esperado = rd.enviarCorreo(de,clave,para, mensaje,asunto);
		//EL VALOR ESPERADO ES 1
		assertEquals(esperado, 1);
	}

}
