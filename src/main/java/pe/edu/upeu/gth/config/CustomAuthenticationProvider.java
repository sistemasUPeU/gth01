package pe.edu.upeu.gth.config;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;

import pe.edu.upeu.gth.dto.CustomUser;

public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Autowired
	private UserDetailsServiceImpl userDetailsService=new UserDetailsServiceImpl();

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
   
          CustomUser user = userDetailsService.loadUserByUsername(username);
          System.out.println(user.getUsername());
   
          if (user == null || !user.getUsername().equalsIgnoreCase(username)) {
              throw new BadCredentialsException("Username not found.");
          }
   
          if (!password.equals(user.getPassword())) {
              throw new BadCredentialsException("Wrong password.");
          }
   
          Collection<? extends GrantedAuthority> authorities = user.getAuthorities();
          System.out.println(user.getAuthorities());
          UsernamePasswordAuthenticationToken upat=new UsernamePasswordAuthenticationToken(user, password, authorities);
   
          System.out.println(upat.getName());
          return upat;
  }

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}

	

}
