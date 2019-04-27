package site.selector.sso.web;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Optional;

/**
 * Created by Stepan Litvinov on 24/04/2019.
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public String hello() {
        return getCurrentUserLogin().orElse("none");
    }

    @GetMapping("/user/me")
    public Principal user(Principal principal) {
        return principal;
    }


    public static Optional<String> getCurrentUserLogin() {
        SecurityContext securityContext = SecurityContextHolder.getContext();
        return Optional.ofNullable(securityContext.getAuthentication())
                .map(authentication -> {
                    if (authentication.getPrincipal() instanceof UserDetails) {
                        UserDetails springSecurityUser = (UserDetails) authentication.getPrincipal();
                        return springSecurityUser.getUsername();
                    } else if (authentication.getPrincipal() instanceof String) {
                        return (String) authentication.getPrincipal();
                    } else if (authentication.getPrincipal() instanceof DefaultOAuth2User) {
                        DefaultOAuth2User principal = (DefaultOAuth2User) authentication.getPrincipal();
                        return principal.getName();
                    }
                    return null;
                });
    }
}
