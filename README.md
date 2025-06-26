
# FleetHub - Backend

**FleetHub** é uma aplicação backend em desenvolvimento utilizando Java com Spring Boot, projetada para gerenciamento completo de frotas. Ela irá permitir o controle de motoristas, viagens, veículos, passageiros e abastecimentos. 

Esta aplicação serve como a base de dados e regras de negócio para o sistema completo, integrando-se com um frontend desenvolvido em Angular.

---

## 🚀 Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.5.0**
  - Spring Web
  - Spring Data JPA
  - Spring Security
- **MySQL** (produção)
- **H2 Database** (testes)
- **JWT (JSON Web Tokens)** para autenticação
- **Docker & Docker Compose**
- **Maven** para gerenciamento de dependências

---

## 📁 Estrutura de Diretórios

```
hub/
├── src/
│   ├── main/
│   │   ├── java/                → Código-fonte principal
│   │   └── resources/           → Configurações e templates
│   └── test/                    → Testes automatizados
├── .gitignore
├── Dockerfile                  → Dockerização da aplicação
├── docker-compose.yml         → Orquestração do container
├── pom.xml                    → Gerenciador Maven
```

---

## ⚙️ Como Executar Localmente

### Pré-requisitos

- Java 21
- Maven 3.8+
- MySQL 8.x (local ou remoto)

### Passos

```bash
# Clone o repositório
git clone <URL>

# Acesse o diretório
cd hub

# Configure src/main/resources/application.properties

# Compile o projeto
./mvnw clean install

# Rode a aplicação
./mvnw spring-boot:run
```

---

## 🐳 Executando com Docker

```bash
# Na raiz do projeto
docker-compose up --build
```

A aplicação estará disponível por padrão em:  
📍 `http://localhost:8080`

---

## 🔐 Autenticação

O projeto utiliza **JWT** para proteger as rotas. Após a autenticação via `/auth/login`, um token será retornado e deverá ser utilizado nas próximas requisições HTTP:

```
Authorization: Bearer <seu_token_jwt>
```

---

## 📚 Endpoints (Exemplos)

- `POST /auth/login` → Autenticação
- `GET /usuarios` → Lista de usuários
- `POST /viagens` → Cadastro de nova viagem
- `GET /veiculos/{id}` → Consulta de veículo
- `POST /abastecimentos` → Registro de abastecimento

> Recomenda-se utilizar ferramentas como Postman ou Swagger UI (se disponível) para explorar a API.

---

## 🧪 Executando os Testes

O projeto inclui testes automatizados. Para executá-los:

```bash
./mvnw test
```

---

## 🧑‍💻 Contribuindo

1. Faça um fork do projeto
2. Crie uma branch para sua feature: `git checkout -b minha-feature`
3. Faça commit das alterações: `git commit -m 'Minha nova feature'`
4. Push na branch: `git push origin minha-feature`
5. Abra um Pull Request

---

## 👤 Autor

Desenvolvido por **Igor dos Santos Coelho**

---

## 📄 Licença

Este projeto está sob a licença MIT - veja o arquivo `LICENSE` para detalhes.
