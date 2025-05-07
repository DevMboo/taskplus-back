````markdown
# TaskPlus - Backend

### 📌 Descrição

TaskPlus é um microserviço backend desenvolvido em **Spring Boot** para gerenciar tarefas, usuários e equipes.
Ele oferece uma API REST segura usando JWT (JSON Web Tokens), preparada para ser consumida por frontends modernos como React.

---

### 👤 Desenvolvedores

- **Luan Chaves** (a.k.a. DevMboo)
- Contato: lchavesdesousa@gmail.com

---

### ⚙️ Tecnologias e recursos

✅ Spring Boot
✅ Spring Security + JWT
✅ JPA + H2 Database
✅ Maven
✅ REST API
✅ Modularização em microserviço
✅ Pronto para expansão com Swagger (documentação) e outros módulos

---

### 🛠️ Como rodar no IntelliJ IDEA

1️⃣ **Clone o repositório**
```bash
git clone https://github.com/DevMboo/taskplus-back
````

2️⃣ **Abra o projeto no IntelliJ IDEA**

* Vá em **File → Open** e selecione a pasta `taskplus`.

3️⃣ **Configure o JDK**

* Certifique-se de que você tem **Java 17** (ou versão compatível) configurado no IntelliJ.

4️⃣ **Compile o projeto**

* Use a aba **Maven** no IntelliJ e execute o comando:

```
clean install
```

5️⃣ **Rodar a aplicação**

* Clique com o botão direito em `TaskPlusApplication.java` e selecione **Run 'TaskPlusApplication'**
  OU
* Use o atalho: **Shift + F10**

---

### 🌐 Endereço e porta padrão

A aplicação rodará por padrão em:

```
http://localhost:8080
```

---

### 🔑 Endpoints principais

| Método | Rota        | Descrição                  |
| ------ | ----------- | -------------------------- |
| POST   | /auth/login | Autenticação (retorna JWT) |
| GET    | /users      | Lista todos os usuários    |
| POST   | /tasks      | Cria uma nova tarefa       |
| GET    | /tasks/{id} | Busca tarefa por ID        |

*(outros endpoints estarão documentados conforme forem implementados)*

---

### 🗃️ Banco de dados

Este projeto usa o **H2 Database** (banco em memória) para facilitar os testes e o desenvolvimento local.
Os dados de inicialização estão em:

```
src/main/resources/data.sql
```

A interface web do H2 fica disponível em:

```
http://localhost:8080/h2-console
```

* JDBC URL: `jdbc:h2:mem:testdb`
* Username: `sa`
* Password: (em branco)

---

### 💡 O que é um microserviço?

TaskPlus foi pensado para ser **independente**, ou seja, apenas cuida da lógica de usuários, tarefas e equipes, podendo ser acoplado a outros serviços externos em um ecossistema maior.
Isso significa que:

* Ele pode ser escalado ou substituído separadamente.
* Ele segue boas práticas de arquitetura, com responsabilidades bem separadas.

---

### 🚀 Próximos passos

* ✅ Finalizar endpoints REST
* ✅ Implementar Swagger para documentação
* ⬜ Adicionar integração com banco relacional (PostgreSQL)
* ⬜ Criar pipeline de CI/CD

---

### 📞 Contato

Para dúvidas, sugestões ou contribuições:
**Luan Chaves** / **DevMboo**

---

```