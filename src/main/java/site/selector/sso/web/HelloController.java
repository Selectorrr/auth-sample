package site.selector.sso.web;

import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Stepan Litvinov on 24/04/2019.
 */
@RestController
public class HelloController {

    @GetMapping("/")
    public ResponseEntity<OAuth2AuthenticationToken> hello(OAuth2AuthenticationToken currentUser) {
        return ResponseEntity.ok(currentUser);
    }
}
