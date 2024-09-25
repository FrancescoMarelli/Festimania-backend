package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nevent.festimania.artista.Artista;
import org.nevent.festimania.artista.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/api/v1/artista")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    @Operation(summary = "findAll")
    public ArrayList<Artista> getArtistas(){
        return (ArrayList<Artista>) artistaRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public Artista getArtista(@PathVariable Integer id){
        return artistaRepository.findById(id).get();
    }

    @PostMapping
    @Operation(summary = "create")
    public void crearArtista(@RequestBody Artista artista){
        artistaRepository.save(artista);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void modificarArtista(Artista artista){
        artistaRepository.save(artista);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void borrarArtista(Artista artista){
        artistaRepository.delete(artista);
    }

}
