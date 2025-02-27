# 🚀 Rodando o Backend
1. Clone o repositório:
   ```sh
   git clone https://github.com/seu-usuario/seu-repositorio.git
   ```
2. Indo para o repositório onde o projeto foi clonado: `cd caminho-do-projeto`
3. Baixar o Java: [Java](https://www.java.com/pt-BR/download/ie_manual.jsp?locale=pt_BR)
4. Baixar o Maven: [Maven](https://maven.apache.org/download.cgi)
5. Baixar o MySQL: [MySQL](https://www.mysql.com/downloads/)
6. Se estiver usando MySQL localmente, crie o banco: `CREATE DATABASE nome_do_banco;`
7. Configurar as credenciais no application.properties
   ```
   spring.datasource.url=jdbc:mysql://localhost:3306/nome_do_banco?useSSL=false&serverTimezone=UTC
   spring.datasource.username=root
   spring.datasource.password=root
   ```
8. Alterar a roTa das chaves públicas e privadas em toda aplicação
9. Compilar e executar o projeto: `mvn clean package`, vai gerar uma pasta target com o arquivo .jar
10. Dentro do diretório raiz do projeto, execute: `mvn spring-boot:run`
11. Agora o backend está rodando: `localhost:8080`
