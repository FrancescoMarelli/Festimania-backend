package org.nevent.festimania.controller;

import org.nevent.festimania.artista.Artista;
import org.nevent.festimania.artista.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    public ArrayList<Artista> getArtistas(){
        return (ArrayList<Artista>) artistaRepository.findAll();
    }

    @GetMapping("/{id}")
    public Artista getArtista(@PathVariable Integer id){
        return artistaRepository.findById(id).get();
    }

    @PostMapping
    public void crearArtista(Artista artista){
        artistaRepository.save(artista);
    }

    @PutMapping("/{id}")
    public void modificarArtista(Artista artista){
        artistaRepository.save(artista);
    }

    @DeleteMapping("/{id}")
    public void borrarArtista(Artista artista){
        artistaRepository.delete(artista);
    }

}
