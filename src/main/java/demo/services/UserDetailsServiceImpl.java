package demo.services;

//import demo.dao.ClientDAO;
import demo.dao.repository.ClientEntityRepository;
import demo.entities.ClientEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private ClientEntityRepository clientEntityRepository;

    @Autowired
    public UserDetailsServiceImpl(ClientEntityRepository clientEntityRepository){
        this.clientEntityRepository = clientEntityRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        ClientEntity client = clientEntityRepository.findById(userName).get();

        if(client == null){
            throw new UsernameNotFoundException(userName + " not found");
        }

        Set<GrantedAuthority> setAuthority = new HashSet<>();
        setAuthority.add(new SimpleGrantedAuthority("ROLE_USER"));

        List<GrantedAuthority> authorities = new ArrayList<>(setAuthority);

        return new User(client.getEmail(), client.getPassword(), authorities);
    }
}