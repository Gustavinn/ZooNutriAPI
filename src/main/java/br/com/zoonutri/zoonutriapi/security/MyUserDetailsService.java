package br.com.zoonutri.zoonutriapi.security;

import br.com.zoonutri.zoonutriapi.domain.MyUserDetails;
import br.com.zoonutri.zoonutriapi.domain.User;
import br.com.zoonutri.zoonutriapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static br.com.zoonutri.zoonutriapi.util.GeneralUtil.getMessage;

@Service
public class MyUserDetailsService implements UserDetailsService {

    public static final String MSG_ERROR_AUTHENTICATION_01 = "msg.error.authentication.01";

    @Autowired
    UserRepository userRepository;

    @Override
    public MyUserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmail(email);

        user.orElseThrow(() -> new UsernameNotFoundException(getMessage(MSG_ERROR_AUTHENTICATION_01)));

        return new MyUserDetails(user.get());
    }

}
