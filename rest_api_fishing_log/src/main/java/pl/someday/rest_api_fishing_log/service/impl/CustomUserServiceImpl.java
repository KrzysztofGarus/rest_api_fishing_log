package pl.someday.rest_api_fishing_log.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import pl.someday.rest_api_fishing_log.model.CustomUser;
import pl.someday.rest_api_fishing_log.model.User;
import pl.someday.rest_api_fishing_log.repository.UserRepository;
import pl.someday.rest_api_fishing_log.service.CustomUserService;


@Service
@RequiredArgsConstructor
public class CustomUserServiceImpl implements CustomUserService {

    private final UserRepository userRepository;

    @Override
    public UserDetailsService userDetailsService(){
        return new UserDetailsService() {
            @Override
            public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
                User user = userRepository.findByUsername(username)
                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));

                return new CustomUser(
                    user.getUsername(), 
                    user.getPassword(), 
                    user.getRole());
            }
        };
    }

    @Override
    public boolean UserExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }

    @Override
    public boolean isUsernameAlreadySigned(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
