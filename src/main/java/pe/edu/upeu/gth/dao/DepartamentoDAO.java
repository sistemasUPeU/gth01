package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class DepartamentoDAO {
	private JdbcTemplate jt;
	private String sql = "";

	public DepartamentoDAO(DataSource datasource) {
		jt = new JdbcTemplate(datasource);
	}

	public List<Map<String, Object>> List_Departamento() {
		sql = "select * from rhtx_departamento ORDER BY NO_DEP";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> List_departamento_2() {
		sql = "select * from rhtx_departamento order by no_dep";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> dep_id(String id_pu) {
		sql = "SELECT ID_DEPARTAMENTO FROM RHVD_PUESTO_DIRECCION where ID_PUESTO='" + id_pu.trim() + "'";
		return jt.queryForList(id_pu.trim(), sql);
	}

	public List<Map<String, Object>> Listar_dep_id(String id) {
		sql = "SELECT ID_DEPARTAMENTO, NO_DEP FROM RHVD_PUESTO_DIRECCION WHERE ID_DIRECCION='" + id
				+ "' GROUP BY ID_DEPARTAMENTO, NO_DEP ORDER BY NO_DEP";
		return jt.queryForList(id, sql);
	}

	public List<Map<String, Object>> List_Departamento_Lima() {
		sql = "SELECT NO_DEP FROM RHVD_PUESTO_DIRECCION where ID_DIRECCION='DIR-0002' OR ID_DIRECCION='DIR-0001' OR ID_DIRECCION='DIR-0003' OR ID_DIRECCION='DIR-0004' GROUP BY NO_DEP ORDER by NO_DEP";
		return jt.queryForList(sql);
	}

	public List<Map<String, Object>> Listar_dep_x_dir(String id_de) {
		sql = "select d.NO_DEP, d.NO_CORTO_DEP, d.ES_DEPARTAMENTO, d.ID_DEPARTAMENTO from rhtx_departamento d, rhtx_direccion r where"
				+ "d.ID_DIRECCION=r.ID_DIRECCION and d.ID_DIRECCION='" + id_de.trim() + "' and r.ES_DIRECCION='1'";
		return jt.queryForList(id_de.trim(), sql);
	}

	public boolean Editar_Dep(String id, String nombre, String ncorto, String estado, String idDir) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL RHSP_MOD_DEPARTAMENTO( ?, ?, ?, ?,?)", id, nombre, ncorto, estado, idDir);
		if (i == 1) {
			x = true;
		}
		return x;
	}

	public boolean Crear_Dep(String nombre, String ncorto, String estado, String idDir) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL RHSP_INSERT_DEPARTAMENTO( ?, ?, ?, ?)", null, nombre, ncorto, estado, idDir);
		if (i == 1) {
			x = true;
		}
		return x;
	}

	public boolean Eliminar_Dep(String id) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL RHSP_ELIMINAR_DEPARTAMENTO(?)", id);
		if (i == 1) {
			x = true;
		}
		return x;
	}

	public boolean Activar_Dep(String id) {
		boolean x = false;
		int i = 0;
		i = jt.update("CALL RHSP_ACTIVAR_DEPARTAMENTO(?)", id);
		if (i == 1) {
			x = true;
		}
		return x;
	}
	
	public boolean Desactivar_Dep(String id) {
		boolean x=false;
		int i = 0;
		i = jt.update("CALL RHSP_DESACTIVAR_DEPARTAMENTO(?)", id);
		if (i == 1) {
			x = true;
		}
		return x;
	}
	
	public List<Map<String, Object>> Listar_dep_x_es(String idDir){
		sql="select  d.NO_DEP, d.NO_CORTO_DEP, d.ES_DEPARTAMENTO, d.ID_DEPARTAMENTO from rhtx_departamento d, rhtx_direccion r"
				+ "where d.ID_DIRECCION=r.ID_DIRECCION and d.ID_DIRECCION='"+idDir+"' and r.ES_DIRECCION='1' and d.ES_DEPARTAMENTO='1'";
		return jt.queryForList(idDir, sql);
	}
}
