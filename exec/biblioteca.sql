-- Script SQL para criação do banco de dados e tabelas

-- Criação do banco de dados caso não exista
CREATE DATABASE IF NOT EXISTS biblioteca CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;

-- Usar o banco de dados criado
USE biblioteca;

-- Criação da tabela de alunos
CREATE TABLE Alunos (
  id_aluno INT AUTO_INCREMENT PRIMARY KEY,
  nome_aluno VARCHAR(100) NOT NULL,
  matricula VARCHAR(20) UNIQUE,
  data_nascimento DATE,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Criação da tabela de livros
CREATE TABLE Livros (
  id_livro INT AUTO_INCREMENT PRIMARY KEY,
  titulo VARCHAR(150) NOT NULL,
  autor VARCHAR(100),
  ano_publicacao INT,
  quantidade_estoque INT DEFAULT 0,
  data_criacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  data_atualizacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- Criação da tabela de empréstimos
CREATE TABLE Emprestimos (
  id_emprestimo INT AUTO_INCREMENT PRIMARY KEY,
  id_aluno INT,
  id_livro INT,
  data_emprestimo DATE DEFAULT (CURRENT_DATE),
  data_devolucao_prevista DATE,
  data_devolucao_efetiva DATE,
  status VARCHAR(20) DEFAULT 'ATIVO', -- ATIVO, DEVOLVIDO, ATRASADO
  FOREIGN KEY (id_aluno) REFERENCES Alunos(id_aluno),
  FOREIGN KEY (id_livro) REFERENCES Livros(id_livro)
);

-- Índices para melhorar a performance das consultas
CREATE INDEX idx_alunos_nome ON Alunos(nome_aluno);
CREATE INDEX idx_alunos_matricula ON Alunos(matricula);
CREATE INDEX idx_livros_titulo ON Livros(titulo);
CREATE INDEX idx_livros_autor ON Livros(autor);
CREATE INDEX idx_emprestimos_data ON Emprestimos(data_emprestimo);
CREATE INDEX idx_emprestimos_status ON Emprestimos(status);

-- Inserção de alguns dados de exemplo para os alunos
INSERT INTO Alunos (nome_aluno, matricula, data_nascimento) VALUES 
('Erick Moura', '2415608', '1995-02-20'),
('Henry Marx', '2326939', '2009-07-22'),
('João Gabriel', '2424118', '2011-01-10');
('Carlos Alexsandro', '2425267', '2011-01-10');

-- Inserção de alguns dados de exemplo para os livros
INSERT INTO Livros (titulo, autor, ano_publicacao, quantidade_estoque) VALUES 
('Algoritmos: Teoria e Prática', 'Thomas H. Cormen', 2009, 5),
('Código Limpo: Habilidades Práticas do Agile Software', 'Robert C. Martin', 2009, 3),
('Estrutura e Interpretação de Programas de Computador', 'Harold Abelson e Gerald Jay Sussman', 1996, 2);

-- Inserção de alguns dados de exemplo para os empréstimos realizados
INSERT INTO Emprestimos (id_aluno, id_livro, data_emprestimo, data_devolucao_prevista) VALUES 
(1, 1, CURRENT_DATE, DATE_ADD(CURRENT_DATE, INTERVAL 14 DAY)),
(2, 2, DATE_SUB(CURRENT_DATE, INTERVAL 10 DAY), DATE_ADD(DATE_SUB(CURRENT_DATE, INTERVAL 10 DAY), INTERVAL 14 DAY)),
(3, 3, DATE_SUB(CURRENT_DATE, INTERVAL 5 DAY), DATE_ADD(DATE_SUB(CURRENT_DATE, INTERVAL 5 DAY), INTERVAL 14 DAY));
