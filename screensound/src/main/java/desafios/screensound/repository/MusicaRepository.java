package desafios.screensound.repository;

import desafios.screensound.model.Musica;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MusicaRepository extends JpaRepository<Musica, Long> {
    boolean existsByNomeIgnoreCaseAndArtista_Id(String nome, Long artistaId);

    List<Musica> findByArtista_NomeIgnoreCase(String nomeArtista);
}
