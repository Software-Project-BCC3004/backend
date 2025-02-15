# Relatório
Descrição dos Endpoints da aplicação.
# API de Pacientes - Documentação de Endpoints

## Base URL

A base URL para acessar os endpoints desta API é:
```
http://localhost:8080/pacientes
```

---

## Endpoints Disponíveis

### **1. Consultar Paciente por ID**

**Endpoint:**
```
GET /consultar/{id}
```

**Descrição:** Retorna os dados de um paciente com base no ID fornecido.

**Exemplo de Requisição:**
```
GET /consultar/1
```

**Parâmetros:**
- `id` (Long): Identificador único do paciente.

**Resposta:**
- 200 OK: Detalhes do paciente.
- 404 Not Found: Paciente não encontrado.

---

### **2. Consultar Todos os Pacientes**

**Endpoint:**
```
GET /consultar/todos
```

**Descrição:** Retorna uma lista com todos os pacientes cadastrados.

**Exemplo de Requisição:**
```
GET /consultar/todos
```

**Resposta:**
- 200 OK: Lista de pacientes.

---

### **3. Consultar Paciente por CPF**

**Endpoint:**
```
GET /consultar/cpf/{cpf}
```

**Descrição:** Retorna os dados de um paciente com base no CPF fornecido.

**Exemplo de Requisição:**
```
GET /consultar/cpf/12345678901
```

**Parâmetros:**
- `cpf` (String): CPF do paciente.

**Resposta:**
- 200 OK: Detalhes do paciente.
- 404 Not Found: Paciente não encontrado.

---

### **4. Consultar Paciente por Nome**

**Endpoint:**
```
GET /consultar/nome/{nome}
```

**Descrição:** Retorna os dados de um paciente com base no nome fornecido.

**Exemplo de Requisição:**
```
GET /consultar/nome/Joao
```

**Parâmetros:**
- `nome` (String): Nome do paciente.

**Resposta:**
- 200 OK: Detalhes do paciente.
- 404 Not Found: Paciente não encontrado.

---

### **5. Consultar Pacientes pelo Nome do Responsável**

**Endpoint:**
```
GET /consultar/responsavel/{nome}
```

**Descrição:** Retorna uma lista de pacientes associados ao nome de um responsável.

**Exemplo de Requisição:**
```
GET /consultar/responsavel/Maria
```

**Parâmetros:**
- `nome` (String): Nome do responsável.

**Resposta:**
- 200 OK: Lista de pacientes.
- 404 Not Found: Nenhum paciente encontrado.

---

### **6. Consultar Pacientes pelo Nome do Profissional**

**Endpoint:**
```
GET /consultar/profissional/{nome}
```

**Descrição:** Retorna uma lista de pacientes associados a um profissional pelo nome.

**Exemplo de Requisição:**
```
GET /consultar/profissional/Dr.Joao
```

**Parâmetros:**
- `nome` (String): Nome do profissional.

**Resposta:**
- 200 OK: Lista de pacientes.
- 404 Not Found: Nenhum paciente encontrado.

---

### **7. Criar Novo Paciente**

**Endpoint:**
```
POST /criar
```

**Descrição:** Cria um novo paciente no sistema.

**Exemplo de Requisição:**
```
POST /criar
Content-Type: application/json

{
  "nome": "Carlos Silva",
  "cpf": "12345678901",
  "idade": 30,
  "profissional": {
    "id": 1,
    "nome": "Dr. Joao"
  }
}
```

**Corpo da Requisição:**
- Dados do paciente a ser criado.

**Resposta:**
- 201 Created: Detalhes do paciente criado.

---

### **8. Atualizar Paciente**

**Endpoint:**
```
PUT /atualizar/{id}
```

**Descrição:** Atualiza os dados de um paciente existente.

**Exemplo de Requisição:**
```
PUT /atualizar/1
Content-Type: application/json

{
  "nome": "Carlos Silva",
  "idade": 31
}
```

**Parâmetros:**
- `id` (Long): Identificador do paciente.

**Resposta:**
- 200 OK: Detalhes do paciente atualizado.
- 404 Not Found: Paciente não encontrado.

---

### **9. Deletar Paciente**

**Endpoint:**
```
DELETE /deletar/{id}
```

**Descrição:** Remove um paciente do sistema.

**Exemplo de Requisição:**
```
DELETE /deletar/1
```

**Parâmetros:**
- `id` (Long): Identificador do paciente.

**Resposta:**
- 200 OK: Paciente deletado com sucesso.
- 404 Not Found: Paciente não encontrado.

---

# Profissional API Documentation

Esta documentação detalha as rotas e funcionalidades do controlador de profissionais.

## Base URL

```
/profissional
```

---

### 1. Consultar Profissional por ID

**Rota:**
```
GET /consultar/{id}
```
**Descrição:** Retorna os dados de um profissional pelo seu ID.

**Parâmetros:**
- `id` (Path): ID do profissional a ser consultado.

**Resposta:**
- 200 OK: Profissional encontrado.
- 404 Not Found: Profissional não encontrado.

---

### 2. Consultar Todos os Profissionais

**Rota:**
```
GET /consultar/todos
```
**Descrição:** Retorna uma lista com todos os profissionais cadastrados.

**Resposta:**
- 200 OK: Lista de profissionais.

---

### 3. Consultar Profissional por Documento

**Rota:**
```
GET /consultar/documento/{documento}
```
**Descrição:** Retorna os dados de um profissional com base no documento informado.

**Parâmetros:**
- `documento` (Path): Documento do profissional a ser consultado.

**Resposta:**
- 200 OK: Profissional encontrado.
- 404 Not Found: Profissional não encontrado.

---

### 4. Consultar Profissional por Nome

**Rota:**
```
GET /consultar/nome/{nome}
```
**Descrição:** Retorna os dados de um profissional com base no nome informado.

**Parâmetros:**
- `nome` (Path): Nome do profissional a ser consultado.

**Resposta:**
- 200 OK: Profissional encontrado.
- 404 Not Found: Profissional não encontrado.

---

### 5. Criar Novo Profissional

**Rota:**
```
POST /criar
```
**Descrição:** Cria um novo profissional com base nos dados fornecidos.

**Corpo da Requisição:**
```json
{
  "nome": "string",
  "documento": {
    "tipo": "string",
    "numero": "string"
  },
  "especialidade": "string"
}
```

**Resposta:**
- 201 Created: Profissional criado com sucesso.
- 400 Bad Request: Dados inválidos.

---

### 6. Atualizar Profissional

**Rota:**
```
PUT /atualizar/{id}
```
**Descrição:** Atualiza os dados de um profissional existente.

**Parâmetros:**
- `id` (Path): ID do profissional a ser atualizado.

**Corpo da Requisição:**
```json
{
  "nome": "string",
  "documento": {
    "tipo": "string",
    "numero": "string"
  },
  "especialidade": "string"
}
```

**Resposta:**
- 200 OK: Profissional atualizado com sucesso.
- 400 Bad Request: Dados inválidos.
- 404 Not Found: Profissional não encontrado.

---

### 7. Deletar Profissional

**Rota:**
```
DELETE /deletar/{id}
```
**Descrição:** Remove um profissional do sistema.

**Parâmetros:**
- `id` (Path): ID do profissional a ser removido.

**Resposta:**
- 200 OK: Profissional removido com sucesso.
- 404 Not Found: Profissional não encontrado.

