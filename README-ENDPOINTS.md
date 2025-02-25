# Documentação das Controllers

## 1. ADMController

### Rota Base: `/adm`

### Endpoints:
- `POST /criar` - Cria um novo administrador (Requer permissão ADMIN).
  ```
    curl -v -X POST http://localhost:8080/adm/criar \
     -H "Content-Type: application/json" \
     -H "Authorization: Bearer SEU_NOVO_TOKEN_JWT" \
     --data-raw '{
           "emailADM": "admin@email.com",
           "senhadADM": "SenhaSegura123"
     }'
  ```
- `PUT /atualizar/{id}` - Atualiza um administrador existente pelo ID (Requer permissão ADMIN).
  ```
    curl -X PUT "http://localhost:8080/adm/atualizar/{id} \
     -H "Content-Type: application/json" \
     -d '{
       "emailADM": "adminnovo@email"
     }'
  ```
- `DELETE /delete/{id}` - Remove um administrador pelo ID (Requer permissão ADMIN).
  ```
    curl -X DELETE "http://localhost:8080/adm/delete/{id}"
  ```
---

## 2. AuthADMController

### Rota Base: `/auth/adm`

### Endpoints:
- `POST /login` - Autentica um administrador e retorna um token JWT.
  ```
    curl -X POST http://localhost:8080/auth/adm/login \
     -H "Content-Type: application/json" \
     --data-raw '{
           "email": "admin@email.com",
           "senha": "SenhaSegura123"
     }`
  ```
---

## 3. ProfissionalController

### Rota Base: `/profissional`

### Endpoints:
- `GET /consultar/{id}` - Busca um profissional pelo ID.
  ```
    curl -X GET http://localhost:8080/profissional/consultar/{id}
  ```
- `GET /consultar/todos` - Lista todos os profissionais.
  ```
    curl -X GET http://localhost:8080/profissional/consultar/todos
  ```
- `GET /consultar/documento/{documento}` - Busca um profissional pelo documento.
  ```
    curl -X GET "http://localhost:8080/profissional/consultar/documento" \
     -H "Content-Type: application/json" \
     -d '{"numeroDocumento": "12345678900", "tipoDocumento": "CRM", "estadoDocumento": "SP"}'
  ```
- `GET /consultar/nome/{nome}` - Busca um profissional pelo nome.
  ```
    curl -G --data-urlencode "nome=João da Silva Ferreiro" "http://localhost:8080/profissional/consultar/nome"
  ```
- `POST /criar` - Cadastra um novo profissional.
  ```
    curl -X POST "http://localhost:8080/profissional/criar" \
     -H "Content-Type: application/json" \
     -d '{
       "documento": {
         "numeroDocumento": "987654321",
         "tipoDocumento": "CRM",
         "estadoDocumento": "RJ"
       },
       "nomeProfissional": "Carlos Eduardo",
       "funcao": "Enfermeiro",
       "emailprofissional": "carlos.eduardo@example.com",
       "senhaProfissional": "senhaForte456"
     }'
  ```
- `PUT /atualizar/{id}` - Atualiza os dados de um profissional.
  ```
    curl -X PUT "http://localhost:8080/profissional/atualizar/{id}" \
     -H "Content-Type: application/json" \
     -d '{
       "id": 4,
       "documento": {
         "numeroDocumento": "987654321",
         "tipoDocumento": "CRM",
         "estadoDocumento": "PR"
       },
       "nomeProfissional": "João da Silva Ferreiro",
       "funcao": "Cirurgião",
       "pacientes": [],
       "emailprofissional": "joao.silva.souza@example.com",
       "senhaProfissional": "novaSenhaSegura123"
     }'
  ```
- `DELETE /deletar/{id}` - Remove um profissional do sistema.
  ```
    curl -X DELETE http://localhost:8080/profissional/deletar/{id} \
     -H "Authorization: Bearer SEU_TOKEN_AQUI"
  ```

## 4. AuthProfissionalController

### Rota Base: `/auth/profissional`

Gerencia a autenticação dos profissionais.

### Endpoints:
- `POST /login` - Autentica um profissional e retorna um token JWT.
  ```
    curl -X POST "http://localhost:8080/auth/profissional/login" \
     -H "Content-Type: application/json" \
     -d '{
       "email": "joao.silva.souza@example.com",
       "senha": "novaSenhaSegura123"
     }'
  ```

---

## 5. PacienteController

### Rota Base: `/pacientes`

### Endpoints:
- `GET /consultar/{id}` - Busca um paciente pelo ID.
  ```
    curl -X GET http://localhost:8080/pacientes/consultar/{id}
  ```
- `GET /consultar/todos` - Lista todos os pacientes.
- `GET /consultar/cpf/{cpf}` - Busca um paciente pelo CPF.
- `GET /consultar/nome/{nome}` - Busca um paciente pelo nome.
- `GET /consultar/responsavel/{nome}` - Lista pacientes por nome do responsável.
- `GET /consultar/profissional/{nome}` - Lista pacientes atendidos por um profissional.
- `POST /criar` - Cria um novo paciente.
  ```
    curl -X POST "http://localhost:8080/pacientes/criar" \
     -H "Content-Type: application/json" \
     -d '{
       "nomePaciente": "João Oliveira",
       "cpfPaciente": "52998224725", 
       "diagnostico": "Hipertensão",
       "leito": "A10",
       "grauSeveridade": "Moderado",
       "responsavel": {
         "nomeResponsavel": "Maria Oliveira",
         "cpfResponsavel": "98765432100"
       },
       "profissional": {
         "id": 4 (precisa de um ID de um médico criado)
       }
     }'
  ```
- `PUT /atualizar/{id}` - Atualiza os dados de um paciente.
  ```
    curl -X PUT "http://localhost:8080/pacientes/atualizar/8" \
     -H "Content-Type: application/json" \
     -d '{
       "nomePaciente": "Eduardo Lima",
       "cpfPaciente": "98765432100", 
       "diagnostico": "Bronquite",
       "leito": "C15",
       "grauSeveridade": "Grave",
       "responsavel": {
         "nomeResponsavel": "Roberto Lima",
         "cpfResponsavel": "22334455667"
       },
       "profissional": {
         "id": 4 (precisa de um ID de um médico criado)
       }
     }'
  ```
- `DELETE /deletar/{id}` - Remove um paciente do sistema.
  ```
    curl -X DELETE "http://localhost:8080/pacientes/deletar/9{id} \
     -H "Authorization: Bearer INSIRA_SEU_TOKEN_JWT_AQUI" \
     -H "Content-Type: application/json" \
     -v
  ```

---

## 6. AvaliacaoPewsController

### Rota Base: `/avaliacao/pews`

### Endpoints:
- `POST /criar` - Cria uma nova avaliação PEWS.
  ```
    curl -X POST http://localhost:8080/avalicao/pews/criar \
     -H "Content-Type: application/json" \
     -d '{
           "avaliacaoNeurologica": "AN2",
           "avaliacaoCardiovascular": "AC1",
           "avaliacaoRespiratoria": "AR3",
           "emese": "EmeseSIM",
           "nebulizacao": "NebulisacaoNAO"
         }'
  ```
- `GET /listar` - Retorna a lista de avaliações ordenadas por pontuação.
  ```
    curl -X GET http://localhost:8080/avalicao/pews/listar
  ```
- `GET /{id}` - Busca uma avaliação PEWS pelo ID.
  ```
    curl -X GET http://localhost:8080/avalicao/pews/{id}
---
