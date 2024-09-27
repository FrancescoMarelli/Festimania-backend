package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.apache.coyote.Response;
import org.nevent.festimania.domain.artista.Artista;
import org.nevent.festimania.domain.artista.ArtistaRepository;
import org.nevent.festimania.domain.festival.Festival;
import org.nevent.festimania.domain.festival.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Festival> findById(@PathVariable String id){
        return ResponseEntity.of(festivalRepository.findById(id));
    }

    @PostMapping
    @Operation(summary = "create")
    public ResponseEntity<Festival> create(@RequestBody Festival festival) {
        if (festivalRepository.existsById(festival.getId())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(festivalRepository.save(festival));
    }


    @PutMapping("/{id}")
    @Operation(summary = "update")
    public ResponseEntity<Festival> update(@PathVariable String id, @RequestBody Festival festival){
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
    public ResponseEntity<Void> delete(@PathVariable String id) {
        if (!festivalRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        // Eliminar el festival
        festivalRepository.deleteById(id);
        return ResponseEntity.ok().build(); // 200 OK
    }

    @PutMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "addArtist")
    public ResponseEntity<Festival> agregarArtista(@PathVariable String id, @PathVariable String idArtista) {
        // Obtener el festival por su id
        Festival festival = festivalRepository.findById(id).orElse(null);

        if (festival == null) {
            return ResponseEntity.notFound().build();
        }
        Artista artista;
        if (artistaRepository.findById(idArtista).isEmpty()) {
            artista = new Artista();
            artista.setId(idArtista);
            artistaRepository.save(artista);
        } else {
            artista = artistaRepository.findById(idArtista).get();
        }

        // Comprobar si el artista ya está en el festival por ID
        boolean artistaYaEnFestival = festival.getArtistas().stream()
                .filter(a -> a.getId() != null) // Filtrar artistas sin ID
                .anyMatch(a -> a.getId().equals(artista.getId()));

        if (!artistaYaEnFestival) {
            festival.getArtistas().add(artista);
        } else {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
        return ResponseEntity.ok(festivalRepository.save(festival));
    }

    @DeleteMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "deleteArtist")
    public ResponseEntity<Festival> eliminarArtista(@PathVariable String id, @PathVariable String idArtista) {
        Festival festival = festivalRepository.findById(id).orElse(null);

        if (festival == null) {
            return ResponseEntity.notFound().build();
        }

        Artista artistaAEliminar = festival.getArtistas().stream()
                .filter(a -> a.getId().equals(idArtista))
                .findFirst()
                .orElse(null);

        if (artistaAEliminar == null) {
            return ResponseEntity.notFound().build(); // El artista no está en el festival
        } else {
            festival.getArtistas().remove(artistaAEliminar); // Eliminar el artista de la lista
            return ResponseEntity.ok(festivalRepository.save(festival)); // Guardar el festival actualizado
        }
    }


}
