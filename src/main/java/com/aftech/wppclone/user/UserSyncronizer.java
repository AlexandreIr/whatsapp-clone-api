package com.aftech.wppclone.user;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserSyncronizer {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public void syncronizeWithIdp(Jwt token){
        log.info("Syncronizing user with idp");
        getUserFromEmail(token).ifPresent(userEmail -> {
            log.info("Syncronizing user having email {}", userEmail);
            Optional<User> optUser = userRepository.findByEmail(userEmail);
            User user = userMapper.fromTokenAttributes(token.getClaims());
            optUser.ifPresent(value-> user.setId(optUser.get().getId()));

            userRepository.save(user);
        });
    }

    private Optional<String> getUserFromEmail(Jwt token){
        Map<String, Object> attributes = token.getClaims();
        if(attributes.containsKey("email")){
            return Optional.of(attributes.get("email").toString());
        }

        return Optional.empty();
    }
}
