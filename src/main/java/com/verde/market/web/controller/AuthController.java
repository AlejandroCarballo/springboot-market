package com.verde.market.web.controller;

import com.verde.market.domain.dto.AuthenticationRequest;
import com.verde.market.domain.dto.AuthenticationRespone;
import com.verde.market.domain.service.VerdeUserDetailService;
import com.verde.market.web.security.JWTUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private VerdeUserDetailService verdeUserDetailService;

    @Autowired
    private JWTUtil JWTUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationRespone> createToken(@RequestBody AuthenticationRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
            UserDetails userDetails = verdeUserDetailService.loadUserByUsername(request.getUsername());
            String jwt = JWTUtil.generateToken(userDetails);

            return new ResponseEntity<>(new AuthenticationRespone(jwt), HttpStatus.OK);
        } catch (BadCredentialsException e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }


    }
}
