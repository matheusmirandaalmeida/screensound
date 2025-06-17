package desafios.screensound.principal;

import desafios.screensound.model.Artista;
import desafios.screensound.model.Musica;
import desafios.screensound.model.TipoArtista;
import desafios.screensound.repository.ArtistaRepository;
import desafios.screensound.repository.MusicaRepository;

import java.util.List;
import java.util.Scanner;

public class Principal {
    private Scanner leitura = new Scanner(System.in);
    private final ArtistaRepository artistaRepository;
    private final MusicaRepository musicaRepository;


    public Principal(ArtistaRepository artistaRepository, MusicaRepository musicaRepository) {
        this.artistaRepository = artistaRepository;
        this.musicaRepository = musicaRepository;
    }

    public void exibeMenu() {
        var opcao = -1;

        while (opcao!= 9) {
            var menu = """
                    *** Screen Sound Músicas ***                    
                                        
                    1- Cadastrar artistas
                    2- Cadastrar músicas
                    3- Listar músicas
                    4- Buscar músicas por artistas
                    5- Pesquisar dados sobre um artista
                    6- Listar artistas
                                    
                    9 - Sair
                    """;

            System.out.println(menu);
            opcao = leitura.nextInt();
            leitura.nextLine();

            switch (opcao) {
                case 1:
                    cadastrarArtistas();
                    break;
                case 2:
                    cadastrarMusicas();
                    break;
                case 3:
                    listarMusicas();
                    break;
                case 4:
                    buscarMusicasPorArtista();
                    break;
                case 5:
                    pesquisarDadosDoArtista();
                    break;
                case 6:
                    ListarArtistas();
                    break;
                case 9:
                    System.out.println("Encerrando a aplicação!");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        }
    }

    private void cadastrarArtistas() {
        System.out.println("Digite o nome do artista: ");
        var nomeArtista = leitura.nextLine();
        System.out.println("Tipo de artista: ex: (SOLO, DUPLA ou BANDA): ");
        var tipoArtista = leitura.nextLine().toUpperCase();

        TipoArtista tipo;
        try {
            tipo = TipoArtista.valueOf(tipoArtista);
        } catch (IllegalArgumentException e) {
            System.out.println("Tipo inválido! use: SOLO, DUPLA ou BANDA");
            return;
        }

        Artista artista = new Artista();
        artista.setNome(nomeArtista);
        artista.setTipo(tipo);

        artistaRepository.save(artista);
        System.out.println("Artista cadastrado com sucesso!");
    }

    private void cadastrarMusicas() {
        System.out.println("Digite o nome da música do artista: ");
        var nomeMusica = leitura.nextLine().toUpperCase();
        System.out.println("Digite o nome do artista que pertece a música: ");
        var nomeArtista = leitura.nextLine().toUpperCase();

        var artistaOptional = artistaRepository.findByNomeIgnoreCase(nomeArtista);

        if (artistaOptional.isEmpty()){
            System.out.println("Artista não encontrado!");
            return;
        }

        if(musicaRepository.existsByNomeIgnoreCaseAndArtista_Id(nomeArtista, artistaOptional.get().getId())) {
            System.out.println("Essa música já esta cadastrada para esse artista!");
            return;
        }

        System.out.println("Digite o tempo de duração da música em segundos: ");
        var tempoDuracao = leitura.nextInt();
        leitura.nextLine();

        Musica musica = new Musica();
        musica.setNome(nomeMusica);
        musica.setTempoDeDuracao(tempoDuracao);
        musica.setArtista(artistaOptional.get());
        musicaRepository.save(musica);
        System.out.println("Musica cadastrada com sucesso!");
    }

    private void listarMusicas() {
        var musicas = musicaRepository.findAll();

        if(musicas.isEmpty()){
            System.out.println("Nenhuma música cadastrada! ");
            return;
        }

        System.out.println("Lista de músicas: ");
        for (Musica musica : musicas) {
            System.out.printf(" %s ( - %s, Duração: %d segundos)%n",
                    musica.getNome(),
                    musica.getArtista().getNome(),
                    musica.getTempoDeDuracao());
        }
    }

    private void ListarArtistas() {
        var artistas = artistaRepository.findAll();

        if(artistas.isEmpty()){
            System.out.println("Nenhuma artista cadastrada! ");
            return;
        }

        System.out.println("Lista de artistas: ");
        for (Artista artista : artistas) {
            System.out.printf(" Nome do artista: %s - Tipo: %s \n",artista.getNome(),
                    artista.getTipo());
        }
    }

    private void buscarMusicasPorArtista() {
        ListarArtistas();

        System.out.println("Nome do artista que você deseja buscar músicas: ");
        var nomeArtista = leitura.nextLine().trim();

        var artistaOptional = artistaRepository.findByNomeIgnoreCase(nomeArtista);

        if (artistaOptional.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        }

        var musicas = musicaRepository.findByArtista_NomeIgnoreCase(nomeArtista);

        if (musicas.isEmpty()) {
            System.out.println("Este artista não possui músicas cadastradas.");
            return;
        }

        System.out.println("Músicas do artista " + artistaOptional.get().getNome() + ":");
        for (Musica musica : musicas) {
            System.out.printf(" - %s (Duração: %d segundos)%n", musica.getNome(), musica.getTempoDeDuracao());
        }
    }

    private void pesquisarDadosDoArtista() {
        System.out.println("Digite o nome do artista para buscar os dados: ");
        var nomeArtista = leitura.nextLine().trim();

        var artistaOptional = artistaRepository.findByNomeIgnoreCase(nomeArtista);

        if (artistaOptional.isEmpty()) {
            System.out.println("Artista não encontrado!");
            return;
        }

        var artista = artistaOptional.get();

        System.out.println("=== Dados do Artista ===");
        System.out.println("Nome: " + artista.getNome());
        System.out.println("Tipo: " + artista.getTipo());

        List<Musica> musicas = artista.getMusicas();

        if (musicas == null || musicas.isEmpty()) {
            System.out.println("Este artista ainda não possui músicas cadastradas.");
        } else {
            System.out.println("Músicas:");
            for (Musica musica : musicas) {
                System.out.printf(" - %s (Duração: %d segundos)%n", musica.getNome(), musica.getTempoDeDuracao());
            }
        }
    }
}
