package desafios.screensound.repository;

import desafios.screensound.model.Artista;
import desafios.screensound.model.TipoArtista;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Long>{
    @EntityGraph(attributePaths = "musicas")
    Optional<Artista> findByNomeIgnoreCase(String nome);

    List<Artista> findByTipo(TipoArtista tipo);

    boolean existsByNomeIgnoreCase(String nome);
}
