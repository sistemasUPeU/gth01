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

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Justificacion;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.dto.Renuncia;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class RecepcionarDAO implements CRUDOperations{
	String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();
//    DataSource d = AppConfig.getDataSource();

    private static JdbcTemplate jt;

    public RecepcionarDAO(DataSource dataSource) {
        jt = new JdbcTemplate(dataSource);
    }

	@Override
	 public ArrayList<Map<String, Object>> listar() {
       
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
	//Listar trabajadores en estado pendiende
//	public List<Map<String, Object>> Pendiente(String depa) {
//		sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='0' AND rap.ID_PASOS='PAS-000432' OR rap.ID_PASOS='PAS-000433'";		
//		sql +="and NOM_DEPA='"+depa+"' AND ESTADO='0' ORDER BY ra.FECHA_RENABAN DESC";
//		return jt.queryForList(sql);
//	}
	public List<Map<String, Object>> Pendiente(String depa){
		System.out.println("Esto es nom depa: "+depa);
		sql="select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO=0 AND rap.ID_PASOS IN ('PAS-000432','PAS-000433') ORDER BY ra.FECHA_RENABAN DESC";
		return jt.queryForList(sql);
	}
	
	//LISTAR 
	public List<Map<String,Object>> Autorizar() {
    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
        return jt.queryForList(sql);
    }
	
//	public List<Map<String,Object>> DetalleAutorizar() {
//    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM REN_VIEW_RENUNCIA";
//        return jt.queryForList(sql);
//    }
	
	//AL SELECCIONAR DETALLE LISTA LOS DATOS DEL TRABAJADOR
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String idc) {
		sql = "select ID_RENABAN,ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM RA_VIEW_RENABAN";

		sql += " where ID_CONTRATO='" + idc + "' ";

		return jt.queryForList(sql);
	}
	
	//LISTA TODOS LOS TRABAJADORES CON ESTADO AUTORIZADO
		public List<Map<String, Object>> Autorizado() {
			sql = "select* from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN WHERE rap.ESTADO='1' AND rap.ID_PASOS='PAS-000432' OR rap.ID_PASOS='PAS-000433' ORDER BY ra.FECHA_RENABAN DESC";
			return jt.queryForList(sql);
		}
	
	// AUTORIZAR RENUNCIA
		public int AutorizarRenuncia(Renuncia r, String idr, String idusuario,String tipo) {
			int x = 0;
			String sql = "INSERT INTO RA_RENABAN_PASOS(ID_RENABAN,ID_PASOS,ID_USUARIO,FECHA_MOD) VALUES(?,?,?,?)";
			String sql2 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000432' AND ID_RENABAN=?";
			String sql3 = "UPDATE RA_RENABAN_PASOS SET ESTADO=1 WHERE ID_PASOS='PAS-000433' AND ID_RENABAN=?";
//			Date date = new Date();
//			
//			//obtenerhora y fecha y salida por pantalla con formato:
//			DateFormat hourdateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date fechon = new java.sql.Date(System.currentTimeMillis());
			System.out.println(fechon);
			try {
				if(tipo.equals("R")) {
					jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000434",idusuario,fechon});
					jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000436",idusuario,fechon});
					jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000438",idusuario,fechon});
					jt.update(sql2,new Object[] { r.getId_renuncia()});
				}else {
					jt.update(sql, new Object[] { r.getId_renuncia(),"PAS-000435",idusuario,fechon});
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
		public int RechazarRenuncia(Rechazo ob, String tipo1) {
			int x = 0;
			String sql = "call REN_RECEP_RENUNCIA( ? , ?)";
//			String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?, FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
			try {
			 jt.update(sql,new Object[] {ob.getId_renaban(),ob.getObservaciones()});
			 x=1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error:" + e);
			}
			return x;
			
		}
	
		//JUSTIFICAR RENUNCIA
		public int JustificarAbandono(Justificacion ju, String tipo2) {
			int x = 0;
			String sql = "call REN_RECEP_ABANDONO( ? , ?)";
			// String sql = "UPDATE REN_RENUNCIA SET ESTADO ='Rechazado', OBSERVACIONES=?,
			// FECHA_RECHAZO=SYSDATE WHERE ID_RENUNCIA =? ";
			try {
				jt.update(sql, new Object[] { ju.getId_renaban(),ju.getObservacion()});
				x = 1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error:" + e);
			}
			return x;

		}
		
		//LISTAR RENABAN - CRUD
		public List<Map<String, Object>> Renaban(String depa) {
			sql = "select ra.ID_CONTRATO as ID_CONTRATO,ra.ID_RENABAN as ID_RENABAN,ra.ID_TRABAJADOR as ID_TRABAJADOR, ra.MES as MES,";
		    sql +="ra.NOMBRES as NOMBRES,ra.PATERNO as PATERNO, ra.MATERNO as MATERNO, ra.FECHA_NAC as FECHA_NAC, ra.DOMICILIO as DOMICILIO,";
		    sql += "ra.DNI as DNI, ra.FECHA_CONTRATO as FECHA_CONTRATO, ra.NOM_DEPA as NOM_DEPA, ra.NOM_AREA as NOM_AREA,ra.NOM_SECCION as NOM_SECCION,";
		    sql += "ra.NOM_PUESTO as NOM_PUESTO, ra.CENTRO_COSTO as CENTRO_COSTO,ra.TIPO_CONTRATO as TIPO_CONTRATO,ra.DESCRIPCION as DESCRIPCION,";
		    sql += "ra.ANTECEDENTES as ANTECEDENTES,ra.CERTI_SALUD as CERTI_SALUD,ra.MFL as MFL, ra.ARCHIVO as ARCHIVO,ra.FECHA_RENABAN as FECHA_RENABAN,";
		    sql += "ra.CORREO as CORREO, ra.TIPO as TIPO,jus.OBSERVACION as JUSTIFICACION, re.OBSERVACIONES as RECHAZO";
			sql += " from RA_VIEW_RENABAN ra LEFT JOIN RA_RENABAN_PASOS rap ON ra.ID_RENABAN=rap.ID_RENABAN  ";
			sql += " LEFT JOIN RA_JUSTIFICACION jus ON ra.ID_RENABAN=jus.ID_RENABAN ";
			sql += " LEFT JOIN RA_RECHAZO re ON ra.ID_RENABAN=re.ID_RENABAN ";
			sql += " WHERE rap.ESTADO=0 and ra.NOM_DEPA='"+depa+"'";
			sql +=" AND rap.ID_PASOS IN ('PAS-000428','PAS-000429') ORDER BY ra.FECHA_RENABAN DESC";
			System.out.println("esto es depa" + depa);
			return jt.queryForList(sql);
		}
}
