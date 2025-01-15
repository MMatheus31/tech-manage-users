# TechManage RESTful API

Este projeto é uma API REST desenvolvida em **Spring Boot** para gerenciar usuários. A aplicação oferece endpoints para criar, atualizar, deletar e consultar usuários para e empresa TechManage. A API usa um banco de dados PostgreSQL para persistência dos dados e está configurada para rodar na porta `8080`.

## Requisitos

Certifique-se de ter os seguintes componentes instalados:

- Java 21 ou superior
- Maven 3.8 ou superior
- PostgreSQL

## Configuração do Banco de Dados

1. Crie um banco de dados chamado `TechManage` no PostgreSQL:
   ```sql
   CREATE DATABASE TechManage;
   ```
2. Crie a tabela Users no PostgreSQL:
   ```sql
   CREATE TABLE users (
   
     id SERIAL PRIMARY KEY, 
     full_name VARCHAR(255) NOT NULL, 
     email VARCHAR(255) NOT NULL UNIQUE,
     phone VARCHAR(20) NOT NULL, 
     birth_date DATE NOT NULL,
     user_type VARCHAR(10) NOT NULL
   
   );
   ```

2. Atualize as credenciais do banco de dados no arquivo `application.properties`:
   ```properties
    spring.application.name=users-rest-app
    spring.datasource.url=jdbc:postgresql://localhost:5432/TechManage
    spring.datasource.username=SEUUSUARIO
    spring.datasource.password=SUASENHA
    spring.jpa.hibernate.ddl-auto=update
    spring.jpa.show-sql=true
    spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
    spring.datasource.driver-class-name=org.postgresql.Driver
   ```

## Rodando a API

1. Clone este repositório:
   ```bash
   git clone <URL_DO_REPOSITORIO>
   cd <PASTA_DO_REPOSITORIO>
   ```

2. Compile e construa o projeto usando Maven:
   ```bash
   mvn clean install
   ```

3. Inicie a aplicação usando a classe de inicialização(UsersRestAppApplication.java) ou usando o comando abaixo:
   ```bash
   mvn spring-boot:run
   ```

4. Acesse a API através do Swagger UI:
   [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Endpoints Disponíveis

### **1. Criar Usuário**
**POST /createUser/users**

Cria um novo usuário no sistema.

#### Exemplo de Requisição
```json
{
  "id": 1,
  "fullName": "Matheus Lemes",
  "email": "matheus@example.com",
  "phone": "+55 11 99999-9999",
  "birthDate": "2004-05-31",
  "userType": "ADMIN"
}
```

#### Exemplo de Resposta
**HTTP 200 Created**
```json
{
  "id": 1,
  "fullName": "Matheus Lemes",
  "email": "matheus@example.com",
  "phone": "+55 11 99999-9999",
  "birthDate": "2004-05-31",
  "userType": "ADMIN"
}
```

### **2. Consultar Usuário por ID**
**GET getUserById/users/{id}**

Retorna os dados de um usuário especificado pelo `id`.

#### Exemplo de Requisição
```bash
GET /users/1
```

#### Exemplo de Resposta
**HTTP 200 OK**
```json
{
   "id": 1,
   "fullName": "Matheus Lemes",
   "email": "matheus@example.com",
   "phone": "+55 11 99999-9999",
   "birthDate": "2004-05-31",
   "userType": "ADMIN"
}
```

### **3. Atualizar Usuário**
**PUT updateUser/users/{id}**

Atualiza os dados de um usuário existente.

#### Exemplo de Requisição
```json
{
   "id": 1,
   "fullName": "Matheus Martins",
   "email": "matheus@example.com",
   "phone": "+55 11 99999-9999",
   "birthDate": "2004-05-31",
   "userType": "ADMIN"
}
```

#### Exemplo de Resposta
**HTTP 200 OK**
```json
{
   "id": 1,
   "fullName": "Matheus Martins",
   "email": "matheus@example.com",
   "phone": "+55 11 99999-9999",
   "birthDate": "2004-05-31",
   "userType": "ADMIN"
}
```

### **4. Deletar Usuário**
**DELETE deleteUser/users/{id}**

Remove um usuário do sistema.

#### Exemplo de Requisição
```bash
DELETE /users/1
```

#### Exemplo de Resposta
**HTTP 200 OK**

---