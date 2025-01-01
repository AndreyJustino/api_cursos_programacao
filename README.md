# API de Gerenciamento de Cursos de Programação

Este projeto é uma API fictícia desenvolvida para gerenciar cursos de programação. Ele foi criado como parte de um desafio técnico, utilizando o framework Spring Boot. A API permite realizar operações básicas de CRUD e oferece funcionalidades específicas para ativar/desativar cursos.

## Funcionalidades

- **Criação de um novo curso:**
  - Endpoint: `POST /cursos`
  - Cria um curso com as propriedades `name`, `category` e `status`, gerando automaticamente os campos `id`, `created_at` e `updated_at`.

- **Listagem de todos os cursos:**
  - Endpoint: `GET /cursos`
  - Retorna uma lista de todos os cursos cadastrados.

- **Atualização de um curso pelo ID:**
  - Endpoint: `PUT /cursos/:id`
  - Atualiza as informações de um curso existente.

- **Remoção de um curso pelo ID:**
  - Endpoint: `DELETE /cursos/:id`
  - Remove um curso baseado no seu identificador único.

- **Ativação/Desativação de um curso:**
  - Endpoint: `PATCH /cursos/:id/ativado`
  - Alterna o estado de ativação do curso entre `ativado` e `desativado`.

## Estrutura das Propriedades do Curso

| Propriedade     | Descrição                                      |
|-----------------|----------------------------------------------|
| `id`           | Identificador único de cada curso             |
| `name`         | Nome do curso                                 |
| `category`     | Categoria do curso                            |
| `status`       | Define se o curso está ativo ou não           |
| `created_at`   | Data de criação do curso                      |
| `updated_at`   | Data da última atualização do curso           |

## Tecnologias Utilizadas

- **Java 21**
- **Spring Boot**
  - `spring-boot-starter-data-jpa`
  - `spring-boot-starter-security`
  - `spring-boot-starter-validation`
  - `spring-boot-starter-web`
- **PostgreSQL** (Banco de dados relacional)
- **Docker** e **Docker Compose**
- **Lombok** (para reduzir boilerplate no código)

## Configuração do Ambiente

Certifique-se de ter o **Docker** e o **Docker Compose** instalados em sua máquina. Este projeto utiliza o PostgreSQL como banco de dados, e sua configuração é gerenciada pelo Docker Compose.

### Clonando o Repositório

```bash
git clone https://github.com/seu-usuario/seu-repositorio.git
cd seu-repositorio
```

### Subindo o Ambiente com Docker Compose

1. Configure as variáveis de ambiente no arquivo `.env` (exemplo abaixo):

   ```env
   DB_HOST=localhost
   DB_PORT=5432
   DB_NAME=curso_programacao
   DB_USER=postgres
   DB_PASSWORD=postgres
   ```

2. Execute o Docker Compose:

   ```bash
   docker-compose up -d
   ```

   Isso irá subir o banco de dados PostgreSQL.

### Executando a Aplicação

1. Compile e inicie a aplicação:

   ```bash
   ./mvnw spring-boot:run
   ```

2. Acesse a aplicação em: `http://localhost:8080`

## Exemplo de Requisições

### Criar um Curso

**POST /cursos**

```json
{
  "name": "Curso de Java",
  "category": "Backend",
  "status": "ativado"
}
```

### Listar Cursos

**GET /cursos**

Resposta:

```json
[
  {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Curso de Java",
    "category": "Backend",
    "status": "ATIVADO",
    "created_at": "2024-12-23T20:00:00",
    "updated_at": "2024-12-23T20:00:00"
  }
]
```

### Atualizar um Curso

**PUT /cursos/:id**

```json
{
  "name": "Curso de Spring Boot",
  "category": "Backend Avançado",
  "status": "DESATIVADO"
}
```

### Remover um Curso

**DELETE /cursos/:id**

Sem corpo de requisição.

### Ativar/Desativar um Curso

**PATCH /cursos/:id/desativado**

Resposta:

```json
{
  "message": "Status do curso atualizado com sucesso!",
  "object": {
    "id": "123e4567-e89b-12d3-a456-426614174000",
    "name": "Curso de Java",
    "category": "Backend",
    "status": "DESATIVADO",
    "created_at": "2024-12-23T20:00:00",
    "updated_at": "2024-12-23T21:00:00"
  }
}
```

## Testes

Este projeto inclui testes utilizando `spring-boot-starter-test`. Para executá-los:

```bash
./mvnw test
```

## Contribuição

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues e enviar pull requests.

## Licença

Este projeto é licenciado sob a MIT License. Consulte o arquivo [LICENSE](LICENSE) para mais detalhes.