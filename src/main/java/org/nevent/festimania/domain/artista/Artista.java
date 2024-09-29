package org.nevent.festimania.domain.artista;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Setter
@Getter
@Document(collection = "artistas")
public class Artista {
    @Id
    @Indexed(unique = true)
    String id;
    String nombre;
    String genero;
    ArrayList<String> canciones;
    ArrayList<String> albums;
}
