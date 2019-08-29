package upp.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import upp.model.Korisnik;
import upp.repository.KorisnikRepository;

@Service
@Transactional
public class KorisnikService {

	@Autowired
	KorisnikRepository korisnikRepository;
	
	public Korisnik findByEmail(String email){
		return korisnikRepository.findByEmail(email);
	}
	
	public Korisnik save(Korisnik k){
		return korisnikRepository.save(k);
	}
	
	public List<Korisnik> findByCasopisId(Long casopisId){
		return korisnikRepository.findAllByCasopisId(casopisId);
	}
	
	public void setCurrentUser(Korisnik user) {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("Author"));
        Authentication authentication = new PreAuthenticatedAuthenticationToken(user.getId(), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

    public Korisnik getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        try {
            Long id = Long.parseLong(auth.getName());
            Optional<Korisnik> user = korisnikRepository.findById(id);
            Korisnik ret = user.orElseGet(null);
            return ret;
        } catch (Exception e) {
            return null;
        }
    }
	
    public Korisnik findById(Long id){
    	return korisnikRepository.findById(id).get();
    }
}
