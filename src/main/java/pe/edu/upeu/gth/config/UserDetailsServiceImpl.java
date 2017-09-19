package pe.edu.upeu.gth.config;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upeu.gth.dao.UsuarioDAO;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
	
	private UsuarioDAO ud=new UsuarioDAO(AppConfig.getDataSource());
	
	@Transactional(readOnly=true)
    @Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		//User user=ud.getByUserName(username)
		//String password=(String) user.get("pw_usuario");
		return null;
	}

}
