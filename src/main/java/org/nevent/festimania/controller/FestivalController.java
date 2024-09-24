package org.nevent.festimania.controller;

import org.nevent.festimania.artista.Artista;
import org.nevent.festimania.artista.ArtistaRepository;
import org.nevent.festimania.festival.Festival;
import org.nevent.festimania.festival.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/festival")
public class FestivalController {

    @Autowired
    private FestivalRepository festivalRepository;

    @Autowired
    private ArtistaRepository artistaRepository;

    @GetMapping
    public ArrayList<Festival> getFestivales(){
        return (ArrayList<Festival>) festivalRepository.findAll();
    }

    @GetMapping("/{id}")
    public Festival getFestival(@PathVariable Integer id){
        return festivalRepository.findById(id).get();
    }

    @PostMapping
    public void crearFestival(Festival festival){
        festivalRepository.save(festival);
    }

    @PutMapping("/{id}")
    public void modificarFestival(Festival festival){
        festivalRepository.save(festival);
    }

    @DeleteMapping("/{id}")
    public void borrarFestival(Festival festival){
        festivalRepository.delete(festival);
    }

    @PutMapping("/{id}/artista/{idArtista}")
    public void agregarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Artista artista = artistaRepository.findById(idArtista).get();
        festival.getArtistas().add(artista);
        festivalRepository.save(festival);
    }

    @DeleteMapping("/{id}/artista/{idArtista}")
    public void borrarArtista(@PathVariable Integer id, @PathVariable Integer idArtista){
        Festival festival = festivalRepository.findById(id).get();
        Artista artista = artistaRepository.findById(idArtista).get();
        festival.getArtistas().remove(artista);
        festivalRepository.save(festival);
    }

}
