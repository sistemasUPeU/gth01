package pe.edu.upeu.gth.dao;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class Tipo_DocumentoDAO {

	JdbcTemplate jt = new JdbcTemplate();
    String sql = "";
    
    public Tipo_DocumentoDAO(DataSource datasource) {
        jt = new JdbcTemplate(datasource);
    }
	
    
    public Map<String,Object> Listar_tipo_doc(){
        sql = "select * from rhtx_tipo_doc_ident order by ID_TIPO_DOC_IDENT";
        return jt.queryForMap(sql);
    }
    
}
