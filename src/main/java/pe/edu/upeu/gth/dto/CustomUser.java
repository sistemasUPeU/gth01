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
	private String ID_SECCION;
	private String NO_SECCION;
	private String NO_DEP;
	private String ID_DEPARTAMENTO;
	private String NO_PUESTO;
	private String NO_DIRECCION;
	private String ID_DIRECCION;
	private String ES_SEXO;
	private String NOMBRE_AP;
	public String getID_DIRECCION() {
		return ID_DIRECCION;
	}

	public void setID_DIRECCION(String iD_DIRECCION) {
		ID_DIRECCION = iD_DIRECCION;
	}

	private String AR_FOTO;
	private ArrayList<Map<String, Object>> LIST_MODULO;
	boolean accountNonExpired;
	boolean AccountNonLocked;
	boolean CredentialsNonExpired;
	boolean Enabled;

	public CustomUser(String nousuario, String pwusuario, String idusuario, String idtrabajador, String idempleado,
			String idrol, String idpuesto, String idarea, String noarea, String nodep, String iddep, String nopuesto,
			String nodireccion, String essexo, String nombreap, String iddir, ArrayList<Map<String, Object>> listmod,String noseccion,String idseccion) {
		this.NO_USUARIO = nousuario;
		this.PW_USUARIO = pwusuario;
		this.ID_USUARIO = idusuario;
		this.ID_TRABAJADOR = idtrabajador;
		this.ID_EMPLEADO = idempleado;
		this.ID_ROL = idrol;
		this.ID_PUESTO = idpuesto;
		this.ID_AREA = idarea;
		this.NO_AREA = noarea;
		this.ID_SECCION = idseccion;
		this.NO_SECCION = noseccion;
		this.NO_DEP = nodep;
		this.ID_DEPARTAMENTO = iddep;
		this.NO_PUESTO = nopuesto;
		this.NO_DIRECCION = nodireccion;
		this.ES_SEXO = essexo;
		this.NOMBRE_AP = nombreap;
		this.LIST_MODULO = listmod;
		this.ID_DIRECCION = iddir;

	}

	public CustomUser(Map<String, Object> userinfo, ArrayList<Map<String, Object>> listmod,
			boolean accountNonExpired, boolean AccountNonLocked, boolean CredentialsNonExpired, boolean Enabled) {
		this.NO_USUARIO = ((String) userinfo.get("NO_USUARIO")).trim();
		this.PW_USUARIO = ((String) userinfo.get("PW_USUARIO")).trim();
		this.ID_USUARIO = (String) userinfo.get("ID_USUARIO");
		this.ID_TRABAJADOR = (String) userinfo.get("ID_TRABAJADOR");
		this.ID_EMPLEADO = (String) userinfo.get("ID_EMPLEADO");
		this.ID_ROL = ((String) userinfo.get("ID_ROL")).trim();
		this.ID_PUESTO = (String) userinfo.get("ID_PUESTO");
		this.ID_AREA = (String) userinfo.get("ID_AREA");
		this.NO_AREA = (String) userinfo.get("NO_AREA");		
		this.ID_SECCION = (String) userinfo.get("ID_SECCION");
		this.NO_SECCION = (String) userinfo.get("NO_SECCION");		
		this.NO_DEP = (String) userinfo.get("NO_DEP");
		this.ID_DEPARTAMENTO = (String) userinfo.get("ID_DEPARTAMENTO");
		this.NO_PUESTO = (String) userinfo.get("NO_PUESTO");
		this.NO_DIRECCION = (String) userinfo.get("NO_DIRECCION");
		this.ES_SEXO = (String) userinfo.get("ES_SEXO");
		this.NOMBRE_AP = (userinfo.get("AP_PATERNO") == null) ? " "
				: ((String) userinfo.get("AP_PATERNO")).trim() + " " + ((String) userinfo.get("AP_MATERNO")).trim() + " "
						+ ((String) userinfo.get("NO_TRABAJADOR")).trim();
		this.LIST_MODULO = listmod;
		this.accountNonExpired = accountNonExpired;
		this.AccountNonLocked = AccountNonLocked;
		this.CredentialsNonExpired = CredentialsNonExpired;
		this.Enabled = Enabled;
		this.ID_DIRECCION = (String) userinfo.get("ID_DIRECCION");

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
		return ID_USUARIO;
	}

	public void setID_USUARIO(String iD_USUARIO) {
		ID_USUARIO = iD_USUARIO;
	}

	public String getID_TRABAJADOR() {
		return ID_TRABAJADOR;
	}

	public void setID_TRABAJADOR(String iD_TRABAJADOR) {
		ID_TRABAJADOR = iD_TRABAJADOR;
	}

	public String getID_EMPLEADO() {
		return ID_EMPLEADO;
	}

	public void setID_EMPLEADO(String iD_EMPLEADO) {
		ID_EMPLEADO = iD_EMPLEADO;
	}

	public String getID_ROL() {
		return ID_ROL;
	}

	public void setID_ROL(String iD_ROL) {
		ID_ROL = iD_ROL;
	}

	public String getID_PUESTO() {
		return ID_PUESTO;
	}

	public void setID_PUESTO(String iD_PUESTO) {
		ID_PUESTO = iD_PUESTO;
	}

	public String getID_AREA() {
		return ID_AREA;
	}

	public void setID_AREA(String iD_AREA) {
		ID_AREA = iD_AREA;
	}

	public String getNO_AREA() {
		return NO_AREA;
	}

	public void setNO_AREA(String nO_AREA) {
		NO_AREA = nO_AREA;
	}
		
	public String getID_SECCION() {
		// TODO Auto-generated method stub
		return this.ID_SECCION;
	}

	public String getNO_SECCION() {
		// TODO Auto-generated method stub
		return this.NO_SECCION;
	}	

	public String getNO_DEP() {
		return NO_DEP;
	}

	public void setNO_DEP(String nO_DEP) {
		NO_DEP = nO_DEP;
	}

	public String getID_DEPARTAMENTO() {
		return ID_DEPARTAMENTO;
	}

	public void setID_DEPARTAMENTO(String iD_DEPARTAMENTO) {
		ID_DEPARTAMENTO = iD_DEPARTAMENTO;
	}

	public String getNO_PUESTO() {
		return NO_PUESTO;
	}

	public void setNO_PUESTO(String nO_PUESTO) {
		NO_PUESTO = nO_PUESTO;
	}

	public String getNO_DIRECCION() {
		return NO_DIRECCION;
	}

	public void setNO_DIRECCION(String nO_DIRECCION) {
		NO_DIRECCION = nO_DIRECCION;
	}

	public String getES_SEXO() {
		return ES_SEXO;
	}

	public void setES_SEXO(String eS_SEXO) {
		ES_SEXO = eS_SEXO;
	}

	public String getNOMBRE_AP() {
		return NOMBRE_AP;
	}

	public void setNOMBRE_AP(String nOMBRE_AP) {
		NOMBRE_AP = nOMBRE_AP;
	}

	public String getAR_FOTO() {
		return AR_FOTO;
	}

	public void setAR_FOTO(String aR_FOTO) {
		AR_FOTO = aR_FOTO;
	}

	public void setLIST_MODULO(ArrayList<Map<String, Object>> lIST_MODULO) {
		LIST_MODULO = lIST_MODULO;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

}