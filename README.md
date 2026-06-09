# Monitoramento de Recursos da Base Lunar - API Backend

API REST desenvolvida em **Java + Spring Boot** para o controle e monitoramento dos
recursos de uma base lunar, permitindo o **cadastro, consulta, atualização e remoção**
(CRUD) de informações relacionadas a **sensores, reservatórios, consumo de energia,
climatização e alertas operacionais**.

Projeto da disciplina **Técnicas Avançadas de Programação** — FIAP, Engenharia Mecatrônica (2º ano).
A API serve de backend para o aplicativo mobile desenvolvido na disciplina de
*Advanced Programming And Mobile Dev*.

---

## Integrantes

| RM | Nome completo |
|----------|--------------------------------|
| RM564021 | Mateus Hipólito Bustamante |
| RM565268 | Erick Lima Barbosa |
| RM565575 | Pedro Lopez |

---

## Tecnologias

- Java 17
- Spring Boot 3.2.5 (Spring Web, Spring Data JPA, Bean Validation)
- Banco de dados **H2 em modo file** (persistência em disco)
- Maven

---

## Arquitetura em camadas

O projeto segue uma arquitetura simples em camadas, conforme exigido:

```
Controller  ->  recebimento e resposta às requisições HTTP
Service     ->  regras de negócio e lógica de operação
Repository  ->  acesso e persistência dos dados (Spring Data JPA)
Model       ->  representação das entidades do domínio
```

Pacote base: `com.fiap.mecatronica.baselunar`

```
src/main/java/com/fiap/mecatronica/baselunar/
├── BaseLunarApplication.java        # classe principal (Spring Boot)
├── model/                           # entidades JPA
│   ├── Sensor.java
│   ├── Reservatorio.java
│   ├── ConsumoEnergia.java
│   ├── Climatizacao.java
│   ├── AlertaOperacional.java
│   └── Recurso.java                 # recurso consumido pelo app mobile
├── repository/                      # interfaces JpaRepository
├── service/                         # regras de negócio
├── controller/                      # endpoints REST
├── exception/                       # tratamento de erros (404 / 400)
└── config/
    └── DataInitializer.java         # carga inicial de dados de exemplo
```

---

## Persistência (H2 em modo file)

Configurado em `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:h2:file:./database/base-lunar-db
spring.jpa.hibernate.ddl-auto=update
```

Os dados são gravados no arquivo `./database/base-lunar-db.mv.db` e **permanecem após
reiniciar a aplicação**, garantindo que as informações enviadas pelo app mobile sejam
armazenadas corretamente.

Console web do H2 (para inspeção): http://localhost:8080/h2-console
(JDBC URL: `jdbc:h2:file:./database/base-lunar-db`, usuário `sa`, senha em branco)

---

## Como executar

Pré-requisitos: Java 17 e Maven instalados.

```bash
# compilar e empacotar
mvn clean package

# executar
java -jar target/base-lunar-api-0.0.1-SNAPSHOT.jar
```

Ou, em modo de desenvolvimento:

```bash
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`. Na primeira execução o banco é populado
automaticamente com dados de exemplo.

---

## Endpoints (testáveis via Postman / Insomnia)

Todos os recursos expõem CRUD completo. URL base: `http://localhost:8080`

### Recursos (consumido pelo app mobile) — `/api/recursos`
Endpoint usado diretamente pelo aplicativo mobile (disciplina *Advanced Programming
And Mobile Dev*). Mantém um modelo simples (`nome`, `tipo`, `nivelAtual`, `status`)
no formato esperado pelas telas Home, Cadastro e Alertas.

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/recursos` | Lista todos (tela Home) |
| GET | `/api/recursos/{id}` | Busca por id |
| GET | `/api/recursos/status/{status}` | Filtra por status (NORMAL/CRITICO) — usado nos Alertas |
| GET | `/api/recursos/tipo/{tipo}` | Filtra por tipo/categoria |
| POST | `/api/recursos` | Cadastra (tela Cadastro) |
| PUT | `/api/recursos/{id}` | Atualiza |
| DELETE | `/api/recursos/{id}` | Remove |

**Exemplo de corpo (POST `/api/recursos`):**
```json
{
  "nome": "Tanque de Agua Setor Alpha",
  "tipo": "Agua",
  "nivelAtual": 85.0,
  "status": "NORMAL"
}
```
> Se o campo `status` não for enviado, a API o calcula a partir do `nivelAtual`
> (abaixo de 20% = `CRITICO`), seguindo a mesma regra do aplicativo mobile.

### Sensores — `/api/sensores`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/sensores` | Lista todos |
| GET | `/api/sensores/{id}` | Busca por id |
| GET | `/api/sensores/tipo/{tipo}` | Filtra por tipo |
| GET | `/api/sensores/modulo/{modulo}` | Filtra por módulo |
| GET | `/api/sensores/ativos` | Lista apenas ativos |
| POST | `/api/sensores` | Cadastra |
| PUT | `/api/sensores/{id}` | Atualiza |
| DELETE | `/api/sensores/{id}` | Remove |

### Reservatórios — `/api/reservatorios`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/reservatorios` | Lista todos |
| GET | `/api/reservatorios/{id}` | Busca por id |
| GET | `/api/reservatorios/recurso/{tipoRecurso}` | Filtra por tipo de recurso |
| GET | `/api/reservatorios/modulo/{modulo}` | Filtra por módulo |
| POST | `/api/reservatorios` | Cadastra |
| PUT | `/api/reservatorios/{id}` | Atualiza |
| DELETE | `/api/reservatorios/{id}` | Remove |

### Consumo de energia — `/api/consumos-energia`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/consumos-energia` | Lista todos |
| GET | `/api/consumos-energia/{id}` | Busca por id |
| GET | `/api/consumos-energia/setor/{setor}` | Filtra por setor |
| GET | `/api/consumos-energia/fonte/{fonte}` | Filtra por fonte (solar/nuclear/bateria) |
| POST | `/api/consumos-energia` | Cadastra |
| PUT | `/api/consumos-energia/{id}` | Atualiza |
| DELETE | `/api/consumos-energia/{id}` | Remove |

### Climatização — `/api/climatizacoes`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/climatizacoes` | Lista todas |
| GET | `/api/climatizacoes/{id}` | Busca por id |
| GET | `/api/climatizacoes/modulo/{modulo}` | Filtra por módulo |
| GET | `/api/climatizacoes/ativas` | Lista apenas ativas |
| POST | `/api/climatizacoes` | Cadastra |
| PUT | `/api/climatizacoes/{id}` | Atualiza |
| DELETE | `/api/climatizacoes/{id}` | Remove |

### Alertas operacionais — `/api/alertas`
| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/api/alertas` | Lista todos |
| GET | `/api/alertas/{id}` | Busca por id |
| GET | `/api/alertas/nivel/{nivel}` | Filtra por nível (baixo/medio/alto/critico) |
| GET | `/api/alertas/pendentes` | Lista não resolvidos |
| POST | `/api/alertas` | Cadastra |
| PUT | `/api/alertas/{id}` | Atualiza |
| PATCH | `/api/alertas/{id}/resolver` | Marca como resolvido |
| DELETE | `/api/alertas/{id}` | Remove |

---

## Exemplos de requisição

**Cadastrar um sensor (POST `/api/sensores`):**
```json
{
  "nome": "Sensor de Umidade UM-06",
  "tipo": "umidade",
  "modulo": "Estufa Hidroponica",
  "unidade": "%",
  "valorAtual": 70.0,
  "limiteMinimo": 40.0,
  "limiteMaximo": 80.0,
  "ativo": true
}
```

**Cadastrar um reservatório (POST `/api/reservatorios`):**
```json
{
  "nome": "Reservatorio de Agua Potavel",
  "tipoRecurso": "agua",
  "capacidadeMaxima": 5000.0,
  "nivelAtual": 3200.0,
  "unidade": "litros",
  "modulo": "Modulo de Suporte a Vida"
}
```

**Registrar um alerta (POST `/api/alertas`):**
```json
{
  "titulo": "Nivel de combustivel baixo",
  "descricao": "Tanque de hidrazina em 31% da capacidade.",
  "nivel": "alto",
  "origem": "Reservatorio - Plataforma de Pouso",
  "resolvido": false
}
```

---

## Tratamento de erros

- **404 Not Found** — quando um id não existe (`RecursoNaoEncontradoException`).
- **400 Bad Request** — quando os dados enviados violam as validações
  (campos obrigatórios, valores negativos etc.), com detalhamento dos campos inválidos.
