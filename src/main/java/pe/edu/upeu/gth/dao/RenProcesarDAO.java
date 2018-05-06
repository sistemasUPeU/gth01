package pe.edu.upeu.gth.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;
//import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class RenProcesarDAO implements CRUDOperations{
	String sql;
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
	
	public List<Map<String, Object>> readAll() {
		List<Map<String, Object>> readAll =  new ArrayList<>();
		sql="SELECT * FROM RHTC_PASOS";
		
		return readAll;
	
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
	//LISTA TODOS LOS TRABAJADORES CON ESTADO AUTORIZADO
	public List<Map<String, Object>> Autorizado(String depa)  {
      sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='0' AND rap.ID_PASOS='PAS-000436' OR rap.ID_PASOS='PAS-000437'";		
		sql +="and NOM_DEPA='"+depa+"' AND ESTADO='0' ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
		
    //LISTAR 
	public List<Map<String,Object>> Procesar() {
    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
        return jt.queryForList(sql);
    }
	
//	public List<Map<String,Object>> DetalleProcesar() {
//    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM REN_VIEW_RENUNCIA";
//        return jt.queryForList(sql);
//    }
	
	
	//AL SELECCIONAR DETALLE LISTA LOS DATOS DEL TRABAJADOR
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String idc) {
		sql = "select ID_RENABAN,ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD,TIPO FROM RA_VIEW_RENABAN";

		sql += " where ID_CONTRATO='" + idc + "' ";

		return jt.queryForList(sql);
	}
	
	
	
	//LISTA TODOS LOS TRABAJADORES CON ESTADO PROCESADO
	public List<Map<String, Object>> Procesado() {
		sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='1' AND rap.ID_PASOS='PAS-000436' OR rap.ID_PASOS='PAS-000437' ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
	
	// PROCESAR RENUNCIA
	public int ProcesarRenuncia(Renuncia r, String idusuario,String tipo) {
		int x = 0;
//		String sql = "INSERT INTO RA_RENABAN_PASOS(ID_RENABAN,ID_PASOS,ID_USUARIO,FECHA_MOD) VALUES(?,?,?,?)";
		String sql2 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000436' AND ID_RENABAN=?";
		String sql3 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000437' AND ID_RENABAN=?";
//		Date date = new Date();
//		
//		//obtenerhora y fecha y salida por pantalla con formato:
//		DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		Date fechon = new java.sql.Date(System.currentTimeMillis());
		System.out.println(fechon);
		try {
			if(tipo.equals("R")) {
//				jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000436",idusuario,fechon});
				jt.update(sql2,new Object[] { r.getId_renuncia()});
			}else {
//				jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000437",idusuario,fechon});
				jt.update(sql3,new Object[] { r.getId_renuncia()});
			}
			
			x = 1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error: " + e);
		}
		return x;
	}
	
	// RECHAZAR RENUNCIA
	public int RechazarRenuncia(Rechazo ob) {
		int x = 0;
		String sql = "call REN_UPDATE_RENUNCIA( ? , ?)";
//		String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?, FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
		try {
		 jt.update(sql,new Object[] {ob.getId_renaban(),ob.getObservaciones()});
		 x=1;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error:" + e);
		}
		return x;
		
		
		
	}
}
