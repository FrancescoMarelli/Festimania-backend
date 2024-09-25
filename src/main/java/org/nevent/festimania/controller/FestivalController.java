package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.nevent.festimania.domain.artista.Artista;
import org.nevent.festimania.domain.artista.ArtistaRepository;
import org.nevent.festimania.domain.festival.Festival;
import org.nevent.festimania.domain.festival.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/festival")
public class FestivalController {

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    @Operation(summary = "findAll")
    public ResponseEntity<List<Festival>> findAll(){
        List<Festival> festivales = festivalRepository.findAll();
        return ResponseEntity.ok(festivales);
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public ResponseEntity<Festival> findById(@PathVariable Integer id){
        return ResponseEntity.of(festivalRepository.findById(id));
    }
    @PostMapping
    @Operation(summary = "create")
    public ResponseEntity<Festival> create(@RequestBody Festival festival){
        return ResponseEntity.ok(festivalRepository.save(festival));
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public ResponseEntity<Festival> update(@PathVariable Integer id, @RequestBody Festival festival){
        return ResponseEntity.of(festivalRepository.findById(id).map(f -> {
            f.setNombre(festival.getNombre());
            f.setLugar(festival.getLugar());
            f.setFecha(festival.getFecha());
            f.setArtistas(festival.getArtistas());
            return festivalRepository.save(f);
        }));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        festivalRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "addArtist")
    public ResponseEntity<Festival> agregarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Artista artista = artistaRepository.findById(idArtista).get();
        festival.getArtistas().add(artista);
        return ResponseEntity.ok(festivalRepository.save(festival));
    }

    @DeleteMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "deleteArtist")
    public ResponseEntity<Festival> eliminarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Artista artista = artistaRepository.findById(idArtista).get();
        festival.getArtistas().remove(artista);
        return ResponseEntity.ok(festivalRepository.save(festival));
    }

}
