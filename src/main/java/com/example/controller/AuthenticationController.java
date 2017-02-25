package com.example.controller;

import com.example.config.security.JwtTokenUtil;
import com.example.shared.JwtAuthenticationRequest;
import com.example.shared.JwtAuthenticationResponse;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping(value = "/login")
    public ResponseEntity<JwtAuthenticationResponse> generateToken(@NotNull @RequestBody JwtAuthenticationRequest jwtAuthenticationRequest){

        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        jwtAuthenticationRequest.getUsername(),
                        jwtAuthenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new JwtAuthenticationResponse(token));

    }

    @GetMapping(value = "/info")
    public ResponseEntity<Claims> info(@NotNull @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization){
        if (!authorization.startsWith("Bearer ")) {
            return (ResponseEntity<Claims>) ResponseEntity.badRequest();
        }
        String token = authorization.substring(7);
        Claims claimsFromToken = jwtTokenUtil.getClaimsFromToken(token);
        return ResponseEntity.ok(claimsFromToken);
    }

}
