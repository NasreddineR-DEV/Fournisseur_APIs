package ma.oncf.digital.security;

import ma.oncf.digital.entity.Connexion;
import ma.oncf.digital.repo.foo.ConnexionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
@Component
public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private ConnexionRepository connexionRepository;


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Connexion user = connexionRepository.findByLogin(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
				new ArrayList<>());
	}
}
