# Documentação das Controllers

## 1. ADMController

### Rota Base: `/adm`

Gerencia os administradores da aplicação.

### Endpoints:
- `POST /criar` - Cria um novo administrador (Requer permissão ADMIN).
- `PUT /atualizar/{id}` - Atualiza um administrador existente pelo ID (Requer permissão ADMIN).
- `DELETE /delete/{id}` - Remove um administrador pelo ID (Requer permissão ADMIN).

---

## 2. AuthADMController

### Rota Base: `/auth/adm`

Gerencia a autenticação dos administradores.

### Endpoints:
- `POST /login` - Autentica um administrador e retorna um token JWT.

---

## 3. AuthProfissionalController

### Rota Base: `/auth/profissional`

Gerencia a autenticação dos profissionais.

### Endpoints:
- `POST /login` - Autentica um profissional e retorna um token JWT.

---

## 4. AvaliacaoPewsController

### Rota Base: `/avaliacao/pews`

Gerencia as avaliações PEWS (Pediatric Early Warning Score).

### Endpoints:
- `POST /criar` - Cria uma nova avaliação PEWS.
- `GET /listar` - Retorna a lista de avaliações ordenadas por pontuação.
- `GET /{id}` - Busca uma avaliação PEWS pelo ID.

---

## 5. PacienteController

### Rota Base: `/pacientes`

Gerencia os pacientes cadastrados na aplicação.

### Endpoints:
- `GET /consultar/{id}` - Busca um paciente pelo ID.
- `GET /consultar/todos` - Lista todos os pacientes.
- `GET /consultar/cpf/{cpf}` - Busca um paciente pelo CPF.
- `GET /consultar/nome/{nome}` - Busca um paciente pelo nome.
- `GET /consultar/responsavel/{nome}` - Lista pacientes por nome do responsável.
- `GET /consultar/profissional/{nome}` - Lista pacientes atendidos por um profissional.
- `POST /criar` - Cria um novo paciente.
- `PUT /atualizar/{id}` - Atualiza os dados de um paciente.
- `DELETE /deletar/{id}` - Remove um paciente do sistema.

---

## 6. ProfissionalController

### Rota Base: `/profissional`

Gerencia os profissionais da saúde cadastrados.

### Endpoints:
- `GET /consultar/{id}` - Busca um profissional pelo ID.
- `GET /consultar/todos` - Lista todos os profissionais.
- `GET /consultar/documento/{documento}` - Busca um profissional pelo documento.
- `GET /consultar/nome/{nome}` - Busca um profissional pelo nome.
- `POST /criar` - Cadastra um novo profissional.
- `PUT /atualizar/{id}` - Atualiza os dados de um profissional.
- `DELETE /deletar/{id}` - Remove um profissional do sistema.

