# Testes Automatizados de API com RestAssured

Este projeto contém testes automatizados para validar as operações da API do [JSONPlaceholder](https://jsonplaceholder.typicode.com/), especificamente no endpoint `/users`. Os testes cobrem as operações básicas de CRUD (Create, Read, Update, Delete), utilizando **RestAssured** para realizar as requisições e asserções.

## Tecnologias Utilizadas

- **Java**: Linguagem principal do projeto.
- **TestNG**: Framework de testes utilizado para estruturar e organizar os cenários.
- **RestAssured**: Biblioteca de testes para APIs RESTful.
- **JsonSchemaValidator**: Validação das respostas das requisições com base em JSON Schema.

## Estrutura do Projeto

Abaixo está a estrutura do projeto de testes:

├── src </br>│ ├── teste </br>│ │ └── java </br>│ │ ├── config </br>│ │ │ └── ApiSetup.java # Configuração da URL base da API </br>│ │ ├── tests </br>│ │ │ ├── GetUserTests.java # Testes de consultas (GET) </br>│ │ │ ├── PostUserTests.java # Testes de criação de usuários (POST) </br>│ │ │ ├── PutUserTests.java # Testes de atualização de usuários (PUT) </br>│ │ │ ├── DeleteUserTests.java # Testes de exclusão de usuários (DELETE) </br>│ │ └── utils </br>│ │ ├── CriarUsuario.java # Métodos para criação de dados de usuários </br>│ │ ├── schemaGetListaUsuarios.json # JSON Schema para validação de arrays de usuários </br>│ │ ├── schemaGetUsuarioUnico.json # JSON Schema para validação de um único usuário </br>│ │ └── schemaPutEnderecoUsuario.json# JSON Schema para validação da atualização de endereço

## Pré-requisitos

Para rodar este projeto, você precisará ter:

- **JDK 11+**: Para compilar e executar o código Java.
- **Maven**: Para gerenciar as dependências do projeto.

## Configuração e Execução

```bash
### Clonando o repositório
git clone https://github.com/seu-usuario/nome-do-projeto.git
cd nome-do-projeto

### Instalando as dependências
mvn clean install

### Executando os Testes
Para rodar todos os testes automatizados, basta executar o seguinte comando:
mvn test
```
## Testes Implementados

### 1. Testes GET

Os testes de consulta são definidos na classe `GetUserTests.java`:

-   **Consultar lista completa de usuários**: Valida se a API retorna a lista completa (10 usuários) e se o schema da resposta está de acordo com o arquivo `schemaGetListaUsuarios.json`.
-   **Consultar usuário por username**: Valida se a API retorna corretamente os dados de um usuário ao fornecer seu `username` e se o schema da resposta está de acordo com o arquivo `schemaGetListaUsuarios.json`.
-   **Consultar usuário por ID**: Valida se a API retorna corretamente os dados de um usuário ao fornecer seu `ID` e se o schema da resposta está de acordo com o arquivo `schemaGetUsuarioUnico.json`.
-   **Consultar usuário não cadastrado**: Valida o retorno da API quando um usuário com `ID` inexistente é solicitado.

### 2. Testes POST

A classe `PostUserTests.java` contém o teste para criar um novo usuário:

-   **Incluir usuário preenchendo todas as informações com dados válidos**: Envia requisição para incluir um usuário com todos os campos preenchidos e valida se o novo registro é criado com sucesso, verificando o JSON Schema da resposta e as informações retornadas no body.
-   **Incluir usuário sem envio de dados**: Envia requisição para incluir um usuário sem preenchimento e valida o retorno da API.
-   **Incluir usuário sem envio de dados obrigatórios**: Envia requisição para incluir um usuário sem preenchimento de campos obrigatórios como `name`e `username` e valida o retorno da API.
-   **Incluir usuário informando dados inválidos**: Envia requisição para incluir um usuário com preenchimento inválido de campos  como `email`e `phone` e valida o retorno da API.

### 3. Testes PUT

Na classe `PutUserTests.java`, o teste de atualização de usuário está implementado:

-   **Alterar endereço de um usuário**: Atualiza o endereço de um usuário existente e valida o status de sucesso (200) e se o JSON Schema da resposta está correto utilizando o arquivo `schemaPutEnderecoUsuario.json`.
-   **Alterar usuário não cadastrado**: Envia requisição para atualizar os dados de um usuário não cadastrado e valida o retorno da API.
-   **Alterar usuário informando dados inválidos**: Envia requisição para atualizar os dados de um usuário com preenchimento inválido de campos  como `email`e `phone` e valida o retorno da API.

### 4. Testes DELETE

A classe `DeleteUserTests.java` cobre a funcionalidade de exclusão:

-   **Deletar usuário**: Envia requisição para deletar um usuário e valida se a exclusão é realizada com sucesso.
-   **Deletar usuário não cadastrado**: Envia requisição para deletar um usuário não cadastrado e valida o retorno da API.

## Validações Realizadas

-   **Validação de Status Code**: Cada requisição verifica se o código de status HTTP retornado está correto.
-   **Validação de JSON Schema**: A estrutura das respostas é validada contra arquivos de esquema JSON localizados na pasta `utils/`. Isso garante que as respostas da API estejam no formato correto.
-   **Validação do Body**: Os dados retornados no body na requisições POST e PUT são validados, através dos dados enviados via HashMap.
----------

**Autor**: Angélica Rodrigues da Silva Gomes
