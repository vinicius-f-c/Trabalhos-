# CRUD de Filmes - Atividade de POO (Spring Boot + IntelliJ)

API REST completa (CRUD) para gerenciar Filmes, feita em Java com Spring Boot.

## Estrutura do projeto

```
crud-filmes/
├── pom.xml
└── src/
    ├── main/
    │   ├── java/com/example/filmes/
    │   │   ├── FilmesApplication.java      -> classe principal (Run aqui)
    │   │   ├── model/Filme.java            -> entidade/tabela
    │   │   ├── repository/FilmeRepository.java
    │   │   ├── service/FilmeService.java   -> regras de negócio
    │   │   ├── controller/FilmeController.java -> endpoints REST
    │   │   └── exception/                  -> tratamento de erros
    │   └── resources/application.properties
    └── test/java/com/example/filmes/FilmesApplicationTests.java
```

## Como abrir no IntelliJ

1. Abra o IntelliJ IDEA.
2. `File > Open...` e selecione a pasta `crud-filmes` (a que tem o `pom.xml`).
3. Espere o Maven baixar as dependências (ícone de carregamento no canto).
4. Se aparecer um aviso sobre o **Lombok**, instale o plugin "Lombok" em
   `File > Settings > Plugins` e reinicie o IntelliJ. Também habilite
   "Annotation Processing" em `Settings > Build, Execution, Deployment > Compiler > Annotation Processors`.
5. Abra `FilmesApplication.java` e clique no botão verde de **Run** (▶) ao lado do `main`.
6. A API vai subir em `http://localhost:8080`.

Não precisa instalar banco de dados: o projeto usa H2 em memória (os dados
somem quando você para a aplicação, o que é ótimo para testar).

## Endpoints do CRUD

| Método | Rota           | Ação                        | Status de sucesso |
|--------|----------------|-----------------------------|--------------------|
| POST   | `/filmes`      | Criar um filme               | 201 Created |
| GET    | `/filmes`      | Listar todos os filmes       | 200 OK |
| GET    | `/filmes/{id}` | Buscar um filme pelo id      | 200 OK |
| PUT    | `/filmes/{id}` | Atualizar um filme           | 201 Created* |
| DELETE | `/filmes/{id}` | Deletar um filme             | 204 No Content |

\* Pela convenção REST o PUT normalmente devolve 200 (OK), mas está
configurado para devolver 201, exatamente como pedido no enunciado.
Se seu professor cobrar 200 no PUT, é só trocar `HttpStatus.CREATED`
por `HttpStatus.OK` em `FilmeController.atualizar()`.

## Exemplo de corpo (JSON) para POST/PUT

```json
{
  "titulo": "Interestelar",
  "diretor": "Christopher Nolan",
  "genero": "Ficção Científica",
  "anoLancamento": 2014,
  "duracaoMinutos": 169
}
```

## Testando com curl

```bash
# Criar filme (POST -> 201)
curl -i -X POST http://localhost:8080/filmes \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Interestelar","diretor":"Christopher Nolan","genero":"Ficção Científica","anoLancamento":2014,"duracaoMinutos":169}'

# Listar todos (GET -> 200)
curl -i http://localhost:8080/filmes

# Buscar por id (GET -> 200)
curl -i http://localhost:8080/filmes/1

# Atualizar (PUT -> 201)
curl -i -X PUT http://localhost:8080/filmes/1 \
  -H "Content-Type: application/json" \
  -d '{"titulo":"Interestelar (Dublado)","diretor":"Christopher Nolan","genero":"Ficção Científica","anoLancamento":2014,"duracaoMinutos":169}'

# Deletar (DELETE -> 204)
curl -i -X DELETE http://localhost:8080/filmes/1
```

Você também pode testar tudo isso pelo **Postman** ou **Insomnia**, ou
ainda pela aba **HTTP Client** do próprio IntelliJ (arquivos `.http`).

## Console do banco H2

Com a aplicação rodando, acesse `http://localhost:8080/h2-console` no
navegador. No campo "JDBC URL" coloque `jdbc:h2:mem:filmesdb`, usuário
`sa` e senha em branco, e clique em "Connect" para ver a tabela `FILMES`
e os dados direto no banco.
