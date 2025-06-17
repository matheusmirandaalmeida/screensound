# screensound
Projeto One Brasil

# 🎵 ScreenSound

Um sistema simples de cadastro e gerenciamento de artistas e músicas usando **Java com Spring Boot** e **JPA/Hibernate**. Ideal para praticar conceitos de persistência, relacionamentos em banco de dados, uso de repositórios Spring Data e construção de uma aplicação de console interativa.

## 📋 Funcionalidades

- Cadastro de artistas (SOLO, DUPLA ou BANDA)
- Cadastro de músicas associadas a um artista
- Listagem de músicas
- Busca de músicas por artista
- Consulta detalhada de informações sobre artistas
- Listagem de todos os artistas cadastrados

## 🧱 Estrutura do Projeto

- `Artista`: Entidade JPA com relacionamento OneToMany para músicas
- `Musica`: Entidade JPA com relacionamento ManyToOne para artista
- `TipoArtista`: Enum com os tipos de artista permitidos
- `Principal`: Classe responsável pelo menu e interação com o usuário
- `Repository`: Interfaces que extendem JpaRepository para persistência automática

## 🧪 Tecnologias Usadas

- Java 17+
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Lombok
- Hibernate

## ⚙️ Configuração

No arquivo `application.properties`, as seguintes variáveis de ambiente devem ser definidas:

```properties
spring.datasource.url=jdbc:postgresql://${DB_HOST}/${DB_NAME}
spring.datasource.username=${DB_USER}
spring.datasource.password=${DB_PASSWORD}
spring.datasource.driver-class-name=org.postgresql.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.format-sql=true
