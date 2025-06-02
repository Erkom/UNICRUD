# Sistema de Gerenciamento de Biblioteca Escolar

## Descrição
Este projeto implementa um sistema de gerenciamento de biblioteca escolar conforme solicitado na atividade prática. O sistema controla o cadastro de alunos e livros, além de gerenciar empréstimos, devoluções e gerar relatórios importantes para a administração da biblioteca.

## Equipe
- Erick Moura dos Santos 1 (2415608)
- Henry Marx Sales Pereira 2 (2326939)
- João Gabriel Sampaio Bringel 3 (2424118)
- Carlos Alexsandro Da Silva Lima 4 (2425267)
- Francisco Fernandes Pinheiro Filho 5 (2318852)

## Estrutura do Projeto
- Pacote model: Classes de domínio (Aluno, Livro, Empréstimo)
- Pacote dao: Classes para persistência dos dados (AlunoDAO, LivroDAO, EmprestimoDAO)
- Pacote util: Classes utilitárias para operações comuns
- Classe BibliotecaApp: Classe principal com menu e funcionalidades do sistema

## Requisitos do Sistema
- Java JDK 11 ou superior
- MySQL 8.0 ou superior
- Conector JDBC para MySQL - Criei um script para baixar o conector e executar de forma automatizada, mas você pode baixar manualmente(é o que recomendo)

## Configuração do Ambiente
1. Clone este repositório
2. Importe o projeto em sua IDE Java preferida(No meu caso utilizei o VSCode) porém você pode utilizar o Eclipse ou IntelliJ que são IDEs recomendadas para rodar o projeto em Java.
3. Execute o script SQL `biblioteca.sql` para criar o banco de dados e tabelas
4. Ajuste as credenciais de conexão na classe `ConexaoBD.java`
5. Execute a classe `BibliotecaApp.java` para iniciar o sistema

## Funcionalidades Implementadas
### Gestão de Alunos
- Cadastro, consulta, atualização e exclusão de alunos
- Validação de matrícula única para cada aluno

### Gestão de Livros
- Cadastro, consulta, atualização e exclusão de livros
- Controle de estoque automático

### Gestão de Empréstimos
- Registro de novos empréstimos
- Validação de disponibilidade de livros
- Devolução de livros com atualização automática de estoque
- Identificação de empréstimos em atraso

### Relatórios
- Livros em estoque
- Livros emprestados
- Histórico completo de empréstimos
- Alunos com empréstimos ativos
- Alunos com empréstimos em atraso

## Modelo de Dados
O sistema utiliza um banco de dados MySQL com as seguintes tabelas:
- Alunos: Armazena dados dos alunos cadastrados
- Livros: Armazena informações sobre o acervo de livros
- Emprestimos: Registra os empréstimos e devoluções

## Como Usar o Sistema
1. Execute a aplicação
2. Navegue pelo menu principal para acessar as diferentes funcionalidades
3. Utilize os submenus para gerenciar alunos, livros e empréstimos
4. Gere relatórios para monitorar o acervo e os empréstimos
