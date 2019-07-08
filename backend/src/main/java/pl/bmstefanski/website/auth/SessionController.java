package pl.bmstefanski.website.auth;

import java.io.IOException;
import java.security.Principal;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.bmstefanski.website.model.Role;

@RestController
public class SessionController {

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @GetMapping("sessions/github/callback")
  public void githubCallback(HttpServletResponse response) throws IOException {
    response.sendRedirect("http://localhost:8080/#/");
  }

  @GetMapping("sessions/token")
  public ResponseEntity<?> token(Principal principal) {
    if (principal != null) {
      return ResponseEntity.ok(jwtTokenProvider.createToken(((OAuth2Authentication) principal).getUserAuthentication().getPrincipal().toString(),
                                                            Collections.singletonList(Role.ROLE_USER)));
    }
    return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "unauthorized"));
  }

  @GetMapping("sessions/whoami")
  public ResponseEntity<?> whoami(HttpServletRequest req) {
    return ResponseEntity.ok(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
  }

  @GetMapping("sessions/refresh")
  public ResponseEntity<?> refreshToken(HttpServletRequest req) {
    return ResponseEntity.ok(jwtTokenProvider.createToken(req.getRemoteUser(), Collections.singletonList(Role.ROLE_USER)));
  }
}
