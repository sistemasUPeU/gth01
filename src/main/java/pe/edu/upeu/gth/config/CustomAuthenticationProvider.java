package pe.edu.upeu.gth.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import pe.edu.upeu.gth.dto.CustomUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private UserDetailsServiceImpl userDetailsService = new UserDetailsServiceImpl();

	@Autowired
	public CustomAuthenticationProvider() {
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		System.out.println("Ingresando a CustomAuthenticationProvider");
		String username = authentication.getName();
		System.out.println(authentication.getName());
		String password = (String) authentication.getCredentials();
		System.out.println((String) authentication.getCredentials());
		CustomUser user;

		try{
			user = userDetailsService.loadUserByUsername(username);
		}catch (DataAccessException dae) {
			throw new BadCredentialsException("Username not found.");
		}
		System.out.println(user.getUsername());

		if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
			System.out.println("Usuario no encontrado");
			throw new BadCredentialsException("Username not found.");
		} else {

			if (!password.equals(user.getPassword())) {
				System.out.println("contraseña incorrecta");
				throw new BadCredentialsException("Wrong password.");
			}else {
				Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
				System.out.println(user.getAuthorities());
				UsernamePasswordAuthenticationToken upat = new UsernamePasswordAuthenticationToken(user, password, authorities);

				System.out.println(upat.getName());
				return upat;
			}
		}

		
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

}
