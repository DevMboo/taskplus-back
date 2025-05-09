# TaskPlus - Backend API Documentation

### 📌 Descrição

TaskPlus é um microserviço backend desenvolvido em **Spring Boot 3.4.5** para gerenciar tarefas, usuários e equipes. A API utiliza autenticação JWT (JSON Web Tokens) e banco de dados H2 em memória para desenvolvimento.

## 🚀 Primeiros Passos

### Pré-requisitos
- JDK 21
- Maven 3.8+
- IntelliJ IDEA (recomendado) ou outra IDE Java
- Java 21
- Spring 3.4.5

### 🔑 Primeiro Acesso
Para começar a usar a API, você precisa primeiro criar um usuário, utilize o POSTMAN ou Insomnia para isso, seguindo o padrão abaixo:

```http
POST /v1/users
Content-Type: application/json

{
    "name": "Seu nome",
    "email": "seu@email.com",
    "password": "suasenha",
    "teamId": 13,
    "perfil": "COLABORADOR"
}
```

**Nota:** Devido às restrições de segurança e uso do banco H2, o cadastro inicial de usuários é um recurso necessário para iniciar o sistema.

## 🔐 Autenticação

### Login
```http
POST /v1/auth/login
Content-Type: application/json

{
    "email": "seu@email.com",
    "password": "suaSenha"
}
```

**Resposta:** Retorna um token JWT em cookie HTTP-only

## 👥 Users Endpoints

| Método | Endpoint          | Descrição                              | Autenticação |
|--------|-------------------|----------------------------------------|--------------|
| POST   | /v1/users         | Cria novo usuário                      | Token JWT    |
| GET    | /v1/users         | Lista todos os usuários                | Token JWT    |
| GET    | /v1/users/{id}    | Obtém usuário por ID                   | Token JWT    |
| PUT    | /v1/users/{id}    | Atualiza usuário                       | Token JWT    |
| DELETE | /v1/users/{id}    | Remove usuário                         | Token JWT    |

## 📝 Tasks Endpoints

| Método | Endpoint                     | Descrição                              | Autenticação |
|--------|------------------------------|----------------------------------------|--------------|
| POST   | /v1/tasks                    | Cria nova tarefa                       | Token JWT    |
| GET    | /v1/tasks                    | Lista tarefas da equipe do usuário     | Token JWT    |
| GET    | /v1/tasks/{taskId}           | Obtém tarefa por ID                    | Token JWT    |
| GET    | /v1/tasks/filter             | Filtra tarefas por status/responsável  | Token JWT    |
| PATCH  | /v1/tasks/{taskId}/status    | Atualiza status da tarefa              | Token JWT    |
| PUT    | /v1/tasks/{taskId}           | Atualiza tarefa completa               | Token JWT    |
| DELETE | /v1/tasks/{taskId}           | Remove tarefa                          | Token JWT    |

## 👥 Teams Endpoints

| Método | Endpoint          | Descrição                              | Autenticação |
|--------|-------------------|----------------------------------------|--------------|
| POST   | /v1/teams         | Cria nova equipe                       | Token JWT    |
| GET    | /v1/teams         | Lista todas as equipes                 | Token JWT    |
| GET    | /v1/teams/{id}    | Obtém equipe por ID                    | Token JWT    |
| PUT    | /v1/teams/{id}    | Atualiza equipe                        | Token JWT    |
| DELETE | /v1/teams/{id}    | Remove equipe                          | Token JWT    |

## 🛠️ Tecnologias e Dependências

- **Spring Boot**: 3.4.5
- **Java**: JDK 21
- **Banco de Dados**: H2 (em memória)
- **Dependências Principais**:
  - Spring Security
  - Spring Data JPA
  - Lombok
  - Spring Web
  - JJWT (JSON Web Tokens)
  - H2 Database

## 🗃️ Banco de Dados H2

A interface web do H2 está disponível em:
```
http://localhost:8080/h2-console
```

**Credenciais:**
- JDBC URL: `jdbc:h2:mem:taskplusdb`
- Username: `sa`
- Password: (vazio)

## 🚀 Como Executar

1. Clone o repositório:
```bash
git clone https://github.com/DevMboo/taskplus-back
```

2. Compile o projeto:
```bash
mvn clean install
```

3. Execute a aplicação:
```bash
mvn spring-boot:run
```

## 📌 Notas Importantes

1. Todas as rotas (exceto `/v1/auth/login` e POST `/v1/users`) requerem autenticação via token JWT
2. O token é enviado como cookie HTTP-only chamado "token"
3. O primeiro usuário deve ser criado via POST `/v1/users` antes de qualquer operação
4. O banco H2 é reiniciado a cada execução da aplicação
5. Os times são adicionados novamente apos cada reinicialização

## 📞 Contato

Para dúvidas ou sugestões:
- **Luan Chaves** (DevMboo)
- Email: lchavesdesousa@gmail.com
- Repositório: https://github.com/DevMboo/taskplus-back