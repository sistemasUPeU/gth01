package pe.edu.upeu.gth.dao;

import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class TipoHoraPagoDAO {
	private JdbcTemplate jt;
    private String sql="";
    public TipoHoraPagoDAO(DataSource datasource){
        jt=new JdbcTemplate(datasource);
    }
    
    public List<Map<String,Object>> listTiHoraPagoByIdTrabajador(String idtr) {
    	sql = " select *  from  RHTD_TI_HORA_PAGO where id_trabajador=?";
    	return jt.queryForList(sql,idtr);
    }
}
