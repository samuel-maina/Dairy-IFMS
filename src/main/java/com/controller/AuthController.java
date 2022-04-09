/**
 * AuthController class handles system-wide user registration and mobile only
 * user authentication for API
 *
 * @author Samuel Maina
 *
 * 1/22/2022
 *
 * @version 1.0
 *
 * AuthController.java
 */
package com.controller;

import com.model.ExceptionObject;
import com.model.User;
import com.securityConfig.JwtToken;
import com.securityConfig.JwtUtils;
import com.securityConfig.UserDetailsImpl;
import com.service.UserService;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;

@RestController

@RequestMapping("/api/auth")

public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserService userService;

    /**
     * Login process handler for mobile devices
     *
     * @param user object that contains user credentials
     * @param model object containing name of logged in user. Sent back using
     * the HttpResponse
     * @return ResponseEntity<> response object carried back to client device it
     * contains the JWT authentication token.
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody User user, Model model) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUserId(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        JwtToken jwtToken = new JwtToken();
        jwtToken.setType("JWT");
        jwtToken.setValue(jwt);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());
        model.addAttribute("name", authentication.getName());
        return new ResponseEntity<>(jwtToken, HttpStatus.OK);
    }

    /**
     * Sign up new member handler
     *
     * @param user object containing user details
     * @return user object containing user details
     */
    @PostMapping("/signup")
    public ResponseEntity<?> saveMember(@RequestBody User user) {
        String password = user.getPassword();
        user.setPassword(encoder.encode(password));
        user.setEnabled(false);
        return new ResponseEntity<>(userService.saveUser(user), HttpStatus.OK);
    }

    /**
     * error 401 handler
     *
     * @return exception details and response code 401
     */
    @GetMapping("/401")
    public ResponseEntity<?> error401() {
        ExceptionObject exceptionObject = new ExceptionObject();
        exceptionObject.setMessage("UnAuthorized");
        return new ResponseEntity<>(exceptionObject, HttpStatus.UNAUTHORIZED);
    }

    /**
     * checks whether a JWT token is less than seven hours to expiry only used
     * by mobile client for auto login if true login auto-login fails on the
     * mobile client device if false auto-login proceeds
     *
     * @param request a HTTP payload
     * @return HttpStatus code 401 if fail else HttpStatus code 200
     */
    @GetMapping("/is_expired")
    public ResponseEntity<?> isExpired(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if ("".equals(token)) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        token = token.substring(7, token.length());
        Date date = null;
        try {
            date = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getExpiration();
        } catch (Exception ex) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        long currentDate = new Date().getTime();
        long jwtTime = date.getTime();
        if ((jwtTime - currentDate) > 7 * 60 * 60 * 1000) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @Value("${app.jwtSecret}")
    private String jwtSecret;

}
