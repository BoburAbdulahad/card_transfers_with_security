package uz.bob.card_transfers_with_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MyAuthService implements UserDetailsService {

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

//    private final PasswordEncoder passwordEncoder;
//    public MyAuthService(@Lazy PasswordEncoder passwordEncoder) {
//        super();
//        this.passwordEncoder = passwordEncoder;
//    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<User> userList=new ArrayList<>(
                Arrays.asList(
                        new User("bobur", passwordEncoder.encode( "555"), new ArrayList<>()),
                        new User("ulugbek", passwordEncoder.encode(  "666"),new ArrayList<>()),
                        new User("azizbek", passwordEncoder.encode( "777"), new ArrayList<>()),
                        new User("husanboy", passwordEncoder.encode( "888"), new ArrayList<>()),
                        new User("jahon", passwordEncoder.encode( "999"), new ArrayList<>())
                )
        );
        for (User user : userList) {
            if (user.getUsername().equals(username))
                return user;
        }
        throw new UsernameNotFoundException("Username not found");

    }
}
