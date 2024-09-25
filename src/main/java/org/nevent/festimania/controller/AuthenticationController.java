package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nevent.festimania.usuario.Usuario;
import org.nevent.festimania.usuario.UsuarioRepository;
import org.nevent.festimania.usuario.servicios.AuthenticationRequest;
import org.nevent.festimania.usuario.servicios.AuthenticationResponse;
import org.nevent.festimania.usuario.servicios.AuthenticationService;
import org.nevent.festimania.usuario.servicios.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    @Autowired
    private final AuthenticationService service;

    public AuthenticationController(AuthenticationService service) {
        this.service = service;
    }


    @PostMapping("/register")
    @Operation(summary = "register")
    public ResponseEntity<AuthenticationResponse> register(@RequestBody RegisterRequest request){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/login")
    @Operation(summary = "login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest request){
        return ResponseEntity.ok(service.authenticate(request));
    }


}
