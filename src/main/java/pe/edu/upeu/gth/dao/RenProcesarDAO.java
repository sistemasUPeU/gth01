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
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;

import pe.edu.upeu.gth.dto.Renuncia;
//import pe.edu.upeu.gth.config.AppConfig;
import pe.edu.upeu.gth.interfaz.CRUDOperations;

public class RenProcesarDAO implements CRUDOperations{
	private static final String Connection = null;
	String sql;
	String resultado;
	String query;
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
	//ESTADO
	public List<Map<String, Object>> Autorizado() {
		sql = "select* from REN_VIEW_RENUNCIA WHERE ESTADO='Autorizado'";
		return jt.queryForList(sql);
	}

	public List<Map<String,Object>> Procesar() {
    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,DNI FROM REN_VIEW_TRABAJADOR";
        return jt.queryForList(sql);
    }
	
	public List<Map<String,Object>> DetalleProcesar() {
    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM REN_VIEW_RENUNCIA";
        return jt.queryForList(sql);
    }
	
	public List<Map<String, Object>> Buscar_DetalleTrabajador(String idc) {
		sql = "select ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD FROM REN_VIEW_RENUNCIA";

		sql += " where ID_CONTRATO='" + idc + "' ";

		return jt.queryForList(sql);
	}
	
//	public List<Map<String,Object>> DetalleAutorizar() {
//    	sql = "select ID_CONTRATO,PATERNO,MATERNO,NOMBRES,NOM_PUESTO,NOM_AREA,NOM_DEPA,TIPO_CONTRATO,FECHA_CONTRATO,ANTECEDENTES,CERTI_SALUD,ARCHIVO FROM REN_VIEW_RENUNCIA";
//        return jt.queryForList(sql);
//    }
//	
//	public List<Map<String, Object>> Buscar_DetalleTrabajador(String idc) {
//		sql = "select ID_CONTRATO,NOMBRES,PATERNO,MATERNO,FECHA_NAC,DOMICILIO,DNI,FECHA_CONTRATO,NOM_DEPA,NOM_AREA,NOM_SECCION,NOM_PUESTO,CENTRO_COSTO,TIPO_CONTRATO,ANTECEDENTES,CERTI_SALUD FROM REN_VIEW_RENUNCIA";
//
//		sql += " where ID_CONTRATO='" + idc + "' ";
//
//		return jt.queryForList(sql);
//	}

	
	// falta
			public int ProcesarRenuncia(Renuncia r) {
				int x = 0;
				String sql = "UPDATE REN_RENUNCIA SET ESTADO WHERE ID_CONTRATO=? ";
				try {
					jt.update(sql, new Object[] { r.getId_contrato()});
					x = 1;
				} catch (Exception e) {
					// TODO: handle exception
					System.out.println("Error: " + e);
				}
				return x;
			}
}
