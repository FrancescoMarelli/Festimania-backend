package org.nevent.festimania.festival;

import lombok.Getter;
import lombok.Setter;
import org.nevent.festimania.artista.Artista;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
@Document(collection = "festivales")
public class Festival {
    @Id
    Integer id;
    String nombre;
    String lugar;
    String fecha;
    ArrayList<Artista> artistas;
}
