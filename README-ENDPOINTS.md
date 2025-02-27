# Documentação das Controllers

## 1. ADMController

### Rota Base: `/adm`

### Endpoints:
- `POST /criar` - Cria um novo administrador.
  - `URL: localhost:8080/adm/criar`
  ```json
  {
  "email": "admin@email.com",
  "senha": "senha123"
  }
  ```
---

## 2. AuthADMController

### Rota Base: `/auth/adm`

### Endpoints:
- `POST /login` - Autentica um administrador e retorna um token JWT.
  - `URL: localhost:8080/auth/adm/login`
  ```json
  {
    "email": "admin@email.com",
    "senha": "senha123"
  }
  ```
---

## 3. ProfissionalController

### Rota Base: `/profissional`

### Endpoints:
- `GET /consultar/{id}` - Busca um profissional pelo ID.
  - `URL: localhost:8080/profissional/consultar/{id}`

- `GET /consultar/todos` - Lista todos os profissionais.
  - `URL: localhost:8080/profissional/consultar/todos`

- `GET /consultar/documento/` - Busca um profissional pelo documento.
  - localhost:8080/profissional/consultar/documento/tipo/{tipoDocumento}
  - localhost:8080/profissional/consultar/documento/numero/{numeroDocumento}
  - localhost:8080/profissional/consultar/documento/estado/{estadoDocumento}

- `GET /consultar/nome/{nomeProfissional}` - Busca um profissional pelo nome.
  - `URL: localhost:8080/profissional/consultar/nome/Carlos`

- `POST /criar` - Cadastra um novo profissional.
  - `URL: localhost:8080/profissional/criar`
  ```json
  {
    "nomeProfissional": "João Neto",
    "funcao": "Médico",
    "email": "medico@email.com",
    "senha": "senha12345",
    "numeroDocumento": "123",
    "tipoDocumento": "CRM",
    "estadoDocumento": "RJ"
  }
  ```
- `PUT /atualizar/{id}` - Atualiza os dados de um profissional.
  - `URL: localhost:8080/profissional/atualizar/{id}`
  ```json
  {
    "nomeProfissional": "João Silva",
    "funcao": "Médico",
    "email": "joao@email.com",
    "senhaProfissional": "senha123",
    "numeroDocumento": "123456",
    "tipoDocumento": "CRM",
    "estadoDocumento": "RJ"
  }
  ```
- `DELETE /deletar/{id}` - Remove um profissional do sistema.
  - `URL: localhost:8080/profissional/deletar/{id}`
  ```json
  {
  }
  ```

## 4. AuthProfissionalController

### Rota Base: `/auth/profissional`

Gerencia a autenticação dos profissionais.

### Endpoints:
- `POST /login` - Autentica um profissional e retorna um token JWT.
  - `URL: localhost:8080/auth/profissional/login`
  ```json
  {
    "email": "joao@email.com",
    "senhaProfissional": "senha123"
  }
  ```

---

## 5. PacienteController

### Rota Base: `/pacientes`

### Endpoints:
- `GET /consultar/{id}` - Busca um paciente pelo ID.
  - `URL: localhost:8080/pacientes/consultar/{id}`
- `GET /consultar/todos` - Lista todos os pacientes.
  - `URL: localhost:8080/pacientes/consultar/todos`  
- `GET /consultar/cpf/{cpfPaciente}` - Busca um paciente pelo CPF.
  - `URL: localhost:8080/pacientes/consultar/cpf/{cpfPaciente}
- `GET /consultar/nome/{nome}` - Busca um paciente pelo nome.
  - `URL: localhost:8080/pacientes/consultar/nome/{nomePaciente}`
- `GET /consultar/responsavel/{nomeResponsavel}` - Lista paciente pelo nome do responsável.
  -`URL: localhost:8080/pacientes/consultar/responsavel/nome/{nomeResponsavel}`  
- `GET /consultar/responsavel/cpf/{cpfResponsavel}` - Lista paciente pelo CPF do responsável.
  - `URL: localhost:8080/pacientes/consultar/responsavel/cpf/{cpfResponsavel}`
 
- `POST /criar` - Cria um novo paciente.
  - `URL: localhost:8080/pacientes/criar`
  ```json
  {
    "nomePaciente": "Carlos Silva",
    "cpfPaciente": "529.982.247-25", // tem que ser um CPF válido
    "diagnostico": "Pneumonia",
    "leito": "A12",
    "grauSeveridade": "Moderado",
    "nomeResponsavel": "Maria Silva",
    "cpfResponsavel": "286.241.320-60" // tem que ser um CPF válido
  }
  ```
- `PUT /atualizar/{id}` - Atualiza os dados de um paciente.
  - `URL: localhost:8080/pacientes/atualizar/{id}`
  ```json
  {
    "nomePaciente": "Carlos Silva",
    "cpfPaciente": "529.982.247-25",
    "diagnostico": "Pneumonia",
    "leito": "A13",
    "grauSeveridade": "Moderado",
    "nomeResponsavel": "Maria Silva",
    "cpfResponsavel": "123.456.789-09" // tem que ser um CPF válido
  }
  ```
- `DELETE /deletar/{id}` - Remove um paciente do sistema.
  - `URL: localhost:8080/pacientes/deletar/{id}`
  ```
  {
  }
  ```

---

## 6. AvaliacaoPewsController

### Rota Base: `/avaliacao/pews`

### Endpoints:
- `POST /criar` - Cria uma nova avaliação PEWS.
  - `URL: localhost:8080/avaliacao/pews/criar`
  ```json
  {
      "avaliacao_neurologica": "AN2",
      "avaliacao_cardiovascular": "AC1",
      "avaliacao_respiratoria": "AR3",
      "emese": "EmeseSIM",
      "nebulizacao": "NebulisacaoNAO",
      "pontuacaoTotal": 8,
      "data_pews": "2025-02-26T15:30:00"
  }
  ```
- `GET /listar` - Retorna a lista de avaliações.
  -`URL: localhost:8080/avaliacao/pews/listar`

- `GET /{id}` - Busca uma avaliação PEWS pelo ID.
  -`URL: localhost:8080/avaliacao/pews/1`

- `PUT /atualizar`- Atualiza uma avaliação PEWS.
  - `URL: localhost:8080/avaliacao/pews/atualizar/{id}`
  ```json
    {
    "avaliacao_neurologica": "AN1",
    "avaliacao_cardiovascular": "AC1",
    "avaliacao_respiratoria": "AR3",
    "emese": "EmeseSIM",
    "nebulizacao": "NebulisacaoNAO",
    "pontuacaoTotal": 8,
    "data_pews": "2025-02-26T15:30:00"
  }
- `DELETE /deletar`- Deleta uma avaliação PEWS.
  -`URL: localhost:8080/avaliacao/pews/deletar/{id}`
  ```
    {
    }
  ```

