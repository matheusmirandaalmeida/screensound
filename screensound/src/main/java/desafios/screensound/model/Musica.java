package desafios.screensound.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "musicas")
public class Musica {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String nome;

    private int tempoDeDuracao;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "artista_id")
    private Artista artista;
}
