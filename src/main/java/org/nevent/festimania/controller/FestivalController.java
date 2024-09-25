package org.nevent.festimania.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.nevent.festimania.domain.artista.Artista;
import org.nevent.festimania.domain.artista.ArtistaRepository;
import org.nevent.festimania.domain.festival.Festival;
import org.nevent.festimania.domain.festival.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
    public ArrayList<Festival> getFestivales(){
        return (ArrayList<Festival>) festivalRepository.findAll();
    }

    @GetMapping("/{id}")
    @Operation(summary = "findById")
    public Festival getFestival(@PathVariable Integer id){
        return festivalRepository.findById(id).get();
    }

    @PostMapping
    @Operation(summary = "create")
    public void crearFestival(@RequestBody Festival festival){
        festivalRepository.save(festival);
    }

    @PutMapping("/{id}")
    @Operation(summary = "update")
    public void modificarFestival(Festival festival){
        festivalRepository.save(festival);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "delete")
    public void borrarFestival(Festival festival){
        festivalRepository.delete(festival);
    }

    @PutMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "addArtist")
    public void agregarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Optional<Artista> artista = artistaRepository.findById(idArtista);
       festival.getArtistas().add(artista.get());
        festivalRepository.save(festival);
    }

    @DeleteMapping("/{id}/artista/{idArtista}")
    @Operation(summary = "deleteArtist")
    public void borrarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Artista artista = artistaRepository.findById(idArtista).get();
        festival.getArtistas().remove(artista);
        festivalRepository.save(festival);
    }

}
