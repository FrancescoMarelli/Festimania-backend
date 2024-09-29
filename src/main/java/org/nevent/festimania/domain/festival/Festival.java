package org.nevent.festimania.domain.festival;

import lombok.Getter;
import lombok.Setter;
import org.nevent.festimania.domain.artista.Artista;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
@Document(collection = "festivales")
public class Festival {
    @Id
    @Indexed(unique = true)
    String id;
    String nombre;
    String lugar;
    String fecha;
    ArrayList<Artista> artistas;
}
