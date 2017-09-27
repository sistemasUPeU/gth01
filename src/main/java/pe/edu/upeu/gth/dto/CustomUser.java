package pe.edu.upeu.gth.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class CustomUser implements UserDetails,Serializable {
	private String NO_USUARIO;
	private String PW_USUARIO;
	private String ID_USUARIO;
	private String ID_TRABAJADOR;
	private String ID_EMPLEADO;
	private String ID_ROL;
	private String ID_PUESTO;
	private String ID_AREA;
	private String NO_AREA;
	private String NO_DEP;
	private String ID_DEPARTAMENTO;
	private String NO_PUESTO;
	private String NO_DIRECCION;
	private String ES_SEXO;
	private String NOMBRE_AP;
	private ArrayList<Map<String, Object>> LIST_MODULO;
	boolean accountNonExpired;
	boolean AccountNonLocked;
	boolean CredentialsNonExpired;
	boolean Enabled;

	public CustomUser(String nousuario, String pwusuario, String idusuario, String idtrabajador, String idempleado,
			String idrol, String idpuesto, String idarea, String noarea, String nodep, String iddep, String nopuesto,
			String nodireccion, String essexo, String nombreap, ArrayList<Map<String, Object>> listmod) {
		this.NO_USUARIO = nousuario;
		this.PW_USUARIO = pwusuario;
		this.ID_USUARIO = idusuario;
		this.ID_TRABAJADOR = idtrabajador;
		this.ID_EMPLEADO = idempleado;
		this.ID_ROL = idrol;
		this.ID_PUESTO = idpuesto;
		this.ID_AREA = idarea;
		this.NO_AREA = noarea;
		this.NO_DEP = nodep;
		this.ID_DEPARTAMENTO = iddep;
		this.NO_PUESTO = nopuesto;
		this.NO_DIRECCION = nodireccion;
		this.ES_SEXO = essexo;
		this.NOMBRE_AP = nombreap;
		this.LIST_MODULO = listmod;

	}

	public CustomUser(Map<String, Object> userinfo, ArrayList<Map<String, Object>> listmod,
			boolean accountNonExpired, boolean AccountNonLocked, boolean CredentialsNonExpired, boolean Enabled) {
		this.NO_USUARIO = ((String) userinfo.get("NO_USUARIO")).trim();
		this.PW_USUARIO = ((String) userinfo.get("PW_USUARIO")).trim();
		this.ID_USUARIO = (String) userinfo.get("ID_USUARIO");
		this.ID_TRABAJADOR = (String) userinfo.get("ID_TRABAJADOR");
		this.ID_EMPLEADO = (String) userinfo.get("ID_EMPLEADO");
		this.ID_ROL = (String) userinfo.get("ID_ROL");
		this.ID_PUESTO = (String) userinfo.get("ID_PUESTO");
		this.ID_AREA = (String) userinfo.get("ID_AREA");
		this.NO_AREA = (String) userinfo.get("NO_AREA");
		this.NO_DEP = (String) userinfo.get("NO_DEP");
		this.ID_DEPARTAMENTO = (String) userinfo.get("ID_DEPARTAMENTO");
		this.NO_PUESTO = (String) userinfo.get("NO_PUESTO");
		this.NO_DIRECCION = (String) userinfo.get("NO_DIRECCION");
		this.ES_SEXO = (String) userinfo.get("ES_SEXO");
		this.NOMBRE_AP = (userinfo.get("AP_PATERNO") == null) ? " "
				: (String) userinfo.get("AP_PATERNO") + " " + (String) userinfo.get("AP_MATERNO") + " "
						+ (String) userinfo.get("NO_TRABAJADOR");
		this.LIST_MODULO = listmod;
		this.accountNonExpired = accountNonExpired;
		this.AccountNonLocked = AccountNonLocked;
		this.CredentialsNonExpired = CredentialsNonExpired;
		this.Enabled = Enabled;

	}

	public ArrayList<Map<String, Object>> getLIST_MODULO() {
		return this.LIST_MODULO;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.PW_USUARIO;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.NO_USUARIO;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return accountNonExpired;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return AccountNonLocked;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return CredentialsNonExpired;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return Enabled;
	}

	public String getID_USUARIO() {
		// TODO Auto-generated method stub
		return this.ID_USUARIO;
	}

	public String getID_TRABAJADOR() {
		// TODO Auto-generated method stub
		return this.ID_TRABAJADOR;
	}

	public String getID_EMPLEADO() {
		// TODO Auto-generated method stub
		return this.ID_EMPLEADO;
	}

	public String getID_ROL() {
		// TODO Auto-generated method stub
		return this.ID_ROL;
	}

	public String getID_PUESTO() {
		// TODO Auto-generated method stub
		return this.ID_PUESTO;
	}

	public String getID_AREA() {
		// TODO Auto-generated method stub
		return this.ID_AREA;
	}

	public String getNO_AREA() {
		// TODO Auto-generated method stub
		return this.NO_AREA;
	}

	public String getNO_DEP() {
		// TODO Auto-generated method stub
		return this.NO_DEP;
	}

	public String getID_DEPARTAMENTO() {
		// TODO Auto-generated method stub
		return this.ID_DEPARTAMENTO;
	}

	public String getNO_PUESTO() {
		// TODO Auto-generated method stub
		return this.NO_PUESTO;
	}

	public String getNO_DIRECCION() {
		// TODO Auto-generated method stub
		return this.NO_DIRECCION;
	}

	public String getES_SEXO() {
		// TODO Auto-generated method stub
		return this.ES_SEXO;
	}

	public String getNOMBRE_AP() {
		// TODO Auto-generated method stub
		return this.NOMBRE_AP;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}
