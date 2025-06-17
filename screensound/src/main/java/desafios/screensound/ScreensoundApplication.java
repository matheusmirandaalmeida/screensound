package desafios.screensound;

import desafios.screensound.principal.Principal;
import desafios.screensound.repository.ArtistaRepository;
import desafios.screensound.repository.MusicaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScreensoundApplication implements CommandLineRunner {

	private final ArtistaRepository artistaRepository;
	private final MusicaRepository musicaRepository;

	public ScreensoundApplication(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
		this.artistaRepository = artistaRepository;
		this.musicaRepository = musicaRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(ScreensoundApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Principal principal = new Principal(artistaRepository, musicaRepository);
		principal.exibeMenu();
	}
}
