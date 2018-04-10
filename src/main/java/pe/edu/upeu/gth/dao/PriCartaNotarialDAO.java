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

import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.dto.Abandono;
import pe.edu.upeu.gth.dto.Rechazo;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class PriCartaNotarialDAO implements CRUDOperations{
	String sql;
    PreparedStatement ps;
    CallableStatement cs;
    ResultSet rs;
    Connection cn;
    DataSource d = AppConfig.getDataSource();
    
    private static JdbcTemplate jt;
    
    public PriCartaNotarialDAO(DataSource dataSource) {
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
	
	public List<Map<String, Object>> Pendiente() {
		sql = "select* from RA_VIEW_RENABAN WHERE ESTADO='Autorizado'";
		return jt.queryForList(sql);
	}
	
	public List<Map<String,Object>> Autorizar() {
    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
        return jt.queryForList(sql);
    }
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String idc) {
		sql = "select ID_RENABAN,ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM RA_VIEW_RENABAN";

		sql += " where ID_CONTRATO='" + idc + "' ";

		return jt.queryForList(sql);
	}
	
	//LISTA TODOS LOS TRABAJADORES CON ESTADO AUTORIZADO
		public List<Map<String, Object>> Autorizado() {
			sql = "select* from RA_VIEW_RENABAN WHERE ESTADO='Autorizado'";
			return jt.queryForList(sql);
		}
		
		public int AutorizarRenuncia(Abandono r) {
			int x = 0;
			String sql = "UPDATE RA_RENABAN SET ESTADO='Autorizado' WHERE ID_RENABAN=? ";
			try {
				jt.update(sql, new Object[] { r.getIdabandono()});
				x = 1;
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Error: " + e);
			}
			return x;
		}
		
		public int RechazarRenuncia(Rechazo ob) {
			int x = 0;
			String sql = "call REN_UPDATE_RENUNCIA( ? , ?)";
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
}
