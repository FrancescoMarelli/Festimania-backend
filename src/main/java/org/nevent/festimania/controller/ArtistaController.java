package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nevent.festimania.domain.artista.Artista;
import org.nevent.festimania.domain.artista.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/artista")
public class ArtistaController {

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<Artista>> findAll() {
        List<Artista> artistas = artistaRepository.findAll();
        return ResponseEntity.ok(artistas);
    }
    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ResponseEntity<Artista> findById(@PathVariable Integer id){
        return ResponseEntity.of(artistaRepository.findById(id));
    }

    @PostMapping
    @Operation(summary = "create")
    public ResponseEntity<Artista> create(@RequestBody Artista artista){
        return ResponseEntity.ok(artistaRepository.save(artista));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public ResponseEntity<Artista> update(@PathVariable Integer id, @RequestBody Artista artista){
        return ResponseEntity.of(artistaRepository.findById(id).map(a -> {
            a.setNombre(artista.getNombre());
            a.setGenero(artista.getGenero());
            a.setCanciones(artista.getCanciones());
            a.setAlbums(artista.getAlbums());
            return artistaRepository.save(a);
        }));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        artistaRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
