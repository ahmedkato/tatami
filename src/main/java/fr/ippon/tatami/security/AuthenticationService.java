package fr.ippon.tatami.security;

import fr.ippon.tatami.domain.User;
import fr.ippon.tatami.repository.UserRepository;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

/**
 * This service is user to find the current user.
 *
 * @author Julien Dubois
 */
@Service
public class AuthenticationService {

    @Inject
    private UserRepository userRepository;

    public User getCurrentUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        org.springframework.security.core.userdetails.UserDetails springSecurityUser =
                (org.springframework.security.core.userdetails.UserDetails) securityContext
                        .getAuthentication().getPrincipal();

        return userRepository.findUserByLogin(springSecurityUser.getUsername());
    }

    public boolean hasAuthenticatedUser() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return (securityContext.getAuthentication() != null);
    }
}