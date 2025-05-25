package br.com.biblioteca;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

import br.com.biblioteca.dao.AlunoDAO;
import br.com.biblioteca.dao.EmprestimoDAO;
import br.com.biblioteca.dao.LivroDAO;
import br.com.biblioteca.modelo.Aluno;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.modelo.Livro;
import br.com.biblioteca.util.ConversorData;

/**
 * Classe principal do Sistema de Gerenciamento de Biblioteca Escolar
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class BibliotecaApp {

    private static Scanner scanner = new Scanner(System.in);
    private static AlunoDAO alunoDAO = new AlunoDAO();
    private static LivroDAO livroDAO = new LivroDAO();
    private static EmprestimoDAO emprestimoDAO = new EmprestimoDAO();
    
    /**
     * Método principal que inicia o programa.
     * 
     * @param args Argumentos de linha de comando (não utilizados)
     */
    public static void main(String[] args) {
        exibirCabecalho();
        
        boolean sair = false;
        
        while (!sair) {
            exibirMenuPrincipal();
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    menuAlunos();
                    break;
                case 2:
                    menuLivros();
                    break;
                case 3:
                    menuEmprestimos();
                    break;
                case 4:
                    menuRelatorios();
                    break;
                case 0:
                    sair = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
        
        System.out.println("Sistema encerrado. Obrigado por utilizar!");
        scanner.close();
    }
    
    /**
     * Exibe o cabeçalho do sistema.
     */
    private static void exibirCabecalho() {
        System.out.println("=================================================================");
        System.out.println("      SISTEMA DE GERENCIAMENTO DE BIBLIOTECA ESCOLAR");
        System.out.println("            Escola Municipal \"Aprender Mais\"");
        System.out.println("=================================================================");
        System.out.println("Data: " + ConversorData.obterDataAtualFormatada());
        System.out.println("=================================================================");
    }
    
    /**
     * Exibe o menu principal do sistema.
     */
    private static void exibirMenuPrincipal() {
        System.out.println("\n----- MENU PRINCIPAL -----");
        System.out.println("1 - Gerenciar Alunos");
        System.out.println("2 - Gerenciar Livros");
        System.out.println("3 - Gerenciar Empréstimos");
        System.out.println("4 - Relatórios");
        System.out.println("0 - Sair do Sistema");
        System.out.print("Escolha uma opção: ");
    }
    
    /**
     * Exibe e gerencia o menu de alunos.
     */
    private static void menuAlunos() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n----- GERENCIAMENTO DE ALUNOS -----");
            System.out.println("1 - Cadastrar Aluno");
            System.out.println("2 - Buscar Aluno");
            System.out.println("3 - Listar Alunos");
            System.out.println("4 - Atualizar Aluno");
            System.out.println("5 - Excluir Aluno");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarAluno();
                    break;
                case 2:
                    buscarAluno();
                    break;
                case 3:
                    listarAlunos();
                    break;
                case 4:
                    atualizarAluno();
                    break;
                case 5:
                    excluirAluno();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    /**
     * Exibe e gerencia o menu de livros.
     */
    private static void menuLivros() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n----- GERENCIAMENTO DE LIVROS -----");
            System.out.println("1 - Cadastrar Livro");
            System.out.println("2 - Buscar Livro");
            System.out.println("3 - Listar Livros");
            System.out.println("4 - Atualizar Livro");
            System.out.println("5 - Excluir Livro");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    cadastrarLivro();
                    break;
                case 2:
                    buscarLivro();
                    break;
                case 3:
                    listarLivros();
                    break;
                case 4:
                    atualizarLivro();
                    break;
                case 5:
                    excluirLivro();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    /**
     * Exibe e gerencia o menu de empréstimos.
     */
    private static void menuEmprestimos() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n----- GERENCIAMENTO DE EMPRÉSTIMOS -----");
            System.out.println("1 - Novo Empréstimo");
            System.out.println("2 - Registrar Devolução");
            System.out.println("3 - Listar Empréstimos Ativos");
            System.out.println("4 - Listar Empréstimos Atrasados");
            System.out.println("5 - Buscar Empréstimos por Aluno");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    novoEmprestimo();
                    break;
                case 2:
                    registrarDevolucao();
                    break;
                case 3:
                    listarEmprestimosAtivos();
                    break;
                case 4:
                    listarEmprestimosAtrasados();
                    break;
                case 5:
                    buscarEmprestimosPorAluno();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    /**
     * Exibe e gerencia o menu de relatórios.
     */
    private static void menuRelatorios() {
        boolean voltar = false;
        
        while (!voltar) {
            System.out.println("\n----- RELATÓRIOS -----");
            System.out.println("1 - Livros em Estoque");
            System.out.println("2 - Livros Emprestados");
            System.out.println("3 - Histórico de Empréstimos");
            System.out.println("4 - Alunos com Empréstimos Ativos");
            System.out.println("5 - Alunos com Empréstimos Atrasados");
            System.out.println("0 - Voltar ao Menu Principal");
            System.out.print("Escolha uma opção: ");
            
            int opcao = lerOpcao();
            
            switch (opcao) {
                case 1:
                    relatorioLivrosEmEstoque();
                    break;
                case 2:
                    relatorioLivrosEmprestados();
                    break;
                case 3:
                    relatorioHistoricoEmprestimos();
                    break;
                case 4:
                    relatorioAlunosComEmprestimosAtivos();
                    break;
                case 5:
                    relatorioAlunosComEmprestimosAtrasados();
                    break;
                case 0:
                    voltar = true;
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        }
    }
    
    // ========== MÉTODOS PARA GERENCIAR ALUNOS ==========
    
    /**
     * Cadastra um novo aluno no sistema.
     */
    private static void cadastrarAluno() {
        System.out.println("\n----- CADASTRO DE ALUNO -----");
        
        System.out.print("Nome do aluno: ");
        String nome = scanner.nextLine();
        
        System.out.print("Matrícula: ");
        String matricula = scanner.nextLine();
        
        // Verifica se a matrícula já existe
        Aluno alunoExistente = alunoDAO.buscarPorMatricula(matricula);
        if (alunoExistente != null) {
            System.out.println("ERRO: Já existe um aluno com esta matrícula!");
            return;
        }
        
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        Date dataNascimento = ConversorData.converterStringParaData(scanner.nextLine());
        
        if (dataNascimento == null) {
            System.out.println("ERRO: Data de nascimento inválida!");
            return;
        }
        
        // Cria um novo aluno
        Aluno aluno = new Aluno(nome, matricula, dataNascimento);
        
        // Insere no banco de dados
        long id = alunoDAO.inserir(aluno);
        
        if (id > 0) {
            System.out.println("Aluno cadastrado com sucesso! ID: " + id);
        } else {
            System.out.println("Erro ao cadastrar aluno. Verifique os dados e tente novamente.");
        }
    }
    
    /**
     * Busca um aluno pelo ID ou matrícula.
     */
    private static void buscarAluno() {
        System.out.println("\n----- BUSCA DE ALUNO -----");
        
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Buscar por Matrícula");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        
        Aluno aluno = null;
        
        switch (opcao) {
            case 1:
                System.out.print("Informe o ID do aluno: ");
                try {
                    long id = Long.parseLong(scanner.nextLine());
                    aluno = alunoDAO.buscarPorId(id);
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido!");
                    return;
                }
                break;
                
            case 2:
                System.out.print("Informe a matrícula do aluno: ");
                String matricula = scanner.nextLine();
                aluno = alunoDAO.buscarPorMatricula(matricula);
                break;
                
            default:
                System.out.println("Opção inválida!");
                return;
        }
        
        if (aluno != null) {
            System.out.println("\nDados do Aluno:");
            exibirAluno(aluno);
        } else {
            System.out.println("Aluno não encontrado!");
        }
    }
    
    /**
     * Lista todos os alunos cadastrados.
     */
    private static void listarAlunos() {
        System.out.println("\n----- LISTA DE ALUNOS -----");
        
        List<Aluno> alunos = alunoDAO.listarTodos();
        
        if (alunos.isEmpty()) {
            System.out.println("Nenhum aluno cadastrado!");
            return;
        }
        
        System.out.println("ID | Nome | Matrícula | Data de Nascimento");
        System.out.println("--------------------------------------------");
        
        for (Aluno aluno : alunos) {
            System.out.printf("%d | %s | %s | %s%n", 
                    aluno.getId(), 
                    aluno.getNome(), 
                    aluno.getMatricula(),
                    ConversorData.converterDataParaString(aluno.getDataNascimento()));
        }
    }
    
    /**
     * Atualiza os dados de um aluno existente.
     */
    private static void atualizarAluno() {
        System.out.println("\n----- ATUALIZAÇÃO DE ALUNO -----");
        
        System.out.print("Informe o ID do aluno: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            
            Aluno aluno = alunoDAO.buscarPorId(id);
            
            if (aluno == null) {
                System.out.println("Aluno não encontrado!");
                return;
            }
            
            System.out.println("Dados atuais do aluno:");
            exibirAluno(aluno);
            
            System.out.println("\nInforme os novos dados (ou deixe em branco para manter o valor atual):");
            
            System.out.print("Novo nome [" + aluno.getNome() + "]: ");
            String nome = scanner.nextLine();
            if (!nome.isEmpty()) {
                aluno.setNome(nome);
            }
            
            System.out.print("Nova matrícula [" + aluno.getMatricula() + "]: ");
            String matricula = scanner.nextLine();
            if (!matricula.isEmpty()) {
                // Verifica se a matrícula já existe
                Aluno alunoExistente = alunoDAO.buscarPorMatricula(matricula);
                if (alunoExistente != null && !alunoExistente.getId().equals(aluno.getId())) {
                    System.out.println("ERRO: Já existe outro aluno com esta matrícula!");
                    return;
                }
                aluno.setMatricula(matricula);
            }
            
            System.out.print("Nova data de nascimento [" + ConversorData.converterDataParaString(aluno.getDataNascimento()) + "]: ");
            String dataStr = scanner.nextLine();
            if (!dataStr.isEmpty()) {
                Date dataNascimento = ConversorData.converterStringParaData(dataStr);
                if (dataNascimento == null) {
                    System.out.println("ERRO: Data de nascimento inválida!");
                    return;
                }
                aluno.setDataNascimento(dataNascimento);
            }
            
            // Atualiza no banco de dados
            if (alunoDAO.atualizar(aluno)) {
                System.out.println("Aluno atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar aluno. Verifique os dados e tente novamente.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Exclui um aluno do sistema.
     */
    private static void excluirAluno() {
        System.out.println("\n----- EXCLUSÃO DE ALUNO -----");
        
        System.out.print("Informe o ID do aluno: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            
            Aluno aluno = alunoDAO.buscarPorId(id);
            
            if (aluno == null) {
                System.out.println("Aluno não encontrado!");
                return;
            }
            
            // Verifica se o aluno possui empréstimos ativos
            List<Emprestimo> emprestimos = emprestimoDAO.listarPorAluno(id);
            boolean possuiEmprestimosAtivos = false;
            
            for (Emprestimo emp : emprestimos) {
                if (emp.getStatus().equals(Emprestimo.STATUS_ATIVO) || emp.getStatus().equals(Emprestimo.STATUS_ATRASADO)) {
                    possuiEmprestimosAtivos = true;
                    break;
                }
            }
            
            if (possuiEmprestimosAtivos) {
                System.out.println("ERRO: Este aluno possui empréstimos ativos. Não é possível excluir.");
                return;
            }
            
            System.out.println("Dados do aluno:");
            exibirAluno(aluno);
            
            System.out.print("\nConfirma a exclusão? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                if (alunoDAO.excluir(id)) {
                    System.out.println("Aluno excluído com sucesso!");
                } else {
                    System.out.println("Erro ao excluir aluno.");
                }
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Exibe os detalhes de um aluno.
     * 
     * @param aluno Aluno a ser exibido
     */
    private static void exibirAluno(Aluno aluno) {
        System.out.println("ID: " + aluno.getId());
        System.out.println("Nome: " + aluno.getNome());
        System.out.println("Matrícula: " + aluno.getMatricula());
        System.out.println("Data de Nascimento: " + ConversorData.converterDataParaString(aluno.getDataNascimento()));
    }
    
    // ========== MÉTODOS PARA GERENCIAR LIVROS ==========
    
    /**
     * Cadastra um novo livro no sistema.
     */
    private static void cadastrarLivro() {
        System.out.println("\n----- CADASTRO DE LIVRO -----");
        
        System.out.print("Título: ");
        String titulo = scanner.nextLine();
        
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        
        System.out.print("Ano de publicação: ");
        int anoPublicacao;
        try {
            anoPublicacao = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Ano de publicação inválido!");
            return;
        }
        
        System.out.print("Quantidade em estoque: ");
        int quantidadeEstoque;
        try {
            quantidadeEstoque = Integer.parseInt(scanner.nextLine());
            if (quantidadeEstoque < 0) {
                System.out.println("ERRO: A quantidade em estoque não pode ser negativa!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("ERRO: Quantidade inválida!");
            return;
        }
        
        // Cria um novo livro
        Livro livro = new Livro(titulo, autor, anoPublicacao, quantidadeEstoque);
        
        // Insere no banco de dados
        long id = livroDAO.inserir(livro);
        
        if (id > 0) {
            System.out.println("Livro cadastrado com sucesso! ID: " + id);
        } else {
            System.out.println("Erro ao cadastrar livro. Verifique os dados e tente novamente.");
        }
    }
    
    /**
     * Busca um livro pelo ID ou título.
     */
    private static void buscarLivro() {
        System.out.println("\n----- BUSCA DE LIVRO -----");
        
        System.out.println("1 - Buscar por ID");
        System.out.println("2 - Buscar por Título");
        System.out.print("Escolha uma opção: ");
        
        int opcao = lerOpcao();
        
        switch (opcao) {
            case 1:
                System.out.print("Informe o ID do livro: ");
                try {
                    long id = Long.parseLong(scanner.nextLine());
                    Livro livro = livroDAO.buscarPorId(id);
                    
                    if (livro != null) {
                        System.out.println("\nDados do Livro:");
                        exibirLivro(livro);
                    } else {
                        System.out.println("Livro não encontrado!");
                    }
                } catch (NumberFormatException e) {
                    System.out.println("ID inválido!");
                }
                break;
                
            case 2:
                System.out.print("Informe o título (ou parte do título): ");
                String titulo = scanner.nextLine();
                List<Livro> livros = livroDAO.buscarPorTitulo(titulo);
                
                if (livros.isEmpty()) {
                    System.out.println("Nenhum livro encontrado com este título!");
                } else {
                    System.out.println("\nLivros encontrados:");
                    System.out.println("ID | Título | Autor | Ano | Estoque");
                    System.out.println("--------------------------------------------");
                    
                    for (Livro livro : livros) {
                        System.out.printf("%d | %s | %s | %d | %d%n", 
                                livro.getId(), 
                                livro.getTitulo(), 
                                livro.getAutor(),
                                livro.getAnoPublicacao(),
                                livro.getQuantidadeEstoque());
                    }
                }
                break;
                
            default:
                System.out.println("Opção inválida!");
        }
    }
    
    /**
     * Lista todos os livros cadastrados.
     */
    private static void listarLivros() {
        System.out.println("\n----- LISTA DE LIVROS -----");
        
        List<Livro> livros = livroDAO.listarTodos();
        
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
            return;
        }
        
        System.out.println("ID | Título | Autor | Ano | Estoque");
        System.out.println("--------------------------------------------");
        
        for (Livro livro : livros) {
            System.out.printf("%d | %s | %s | %d | %d%n", 
                    livro.getId(), 
                    livro.getTitulo(), 
                    livro.getAutor(),
                    livro.getAnoPublicacao(),
                    livro.getQuantidadeEstoque());
        }
    }
    
    /**
     * Atualiza os dados de um livro existente.
     */
    private static void atualizarLivro() {
        System.out.println("\n----- ATUALIZAÇÃO DE LIVRO -----");
        
        System.out.print("Informe o ID do livro: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            
            Livro livro = livroDAO.buscarPorId(id);
            
            if (livro == null) {
                System.out.println("Livro não encontrado!");
                return;
            }
            
            System.out.println("Dados atuais do livro:");
            exibirLivro(livro);
            
            System.out.println("\nInforme os novos dados (ou deixe em branco para manter o valor atual):");
            
            System.out.print("Novo título [" + livro.getTitulo() + "]: ");
            String titulo = scanner.nextLine();
            if (!titulo.isEmpty()) {
                livro.setTitulo(titulo);
            }
            
            System.out.print("Novo autor [" + livro.getAutor() + "]: ");
            String autor = scanner.nextLine();
            if (!autor.isEmpty()) {
                livro.setAutor(autor);
            }
            
            System.out.print("Novo ano de publicação [" + livro.getAnoPublicacao() + "]: ");
            String anoStr = scanner.nextLine();
            if (!anoStr.isEmpty()) {
                try {
                    int ano = Integer.parseInt(anoStr);
                    livro.setAnoPublicacao(ano);
                } catch (NumberFormatException e) {
                    System.out.println("ERRO: Ano de publicação inválido!");
                    return;
                }
            }
            
            System.out.print("Nova quantidade em estoque [" + livro.getQuantidadeEstoque() + "]: ");
            String quantStr = scanner.nextLine();
            if (!quantStr.isEmpty()) {
                try {
                    int quantidade = Integer.parseInt(quantStr);
                    if (quantidade < 0) {
                        System.out.println("ERRO: A quantidade em estoque não pode ser negativa!");
                        return;
                    }
                    livro.setQuantidadeEstoque(quantidade);
                } catch (NumberFormatException e) {
                    System.out.println("ERRO: Quantidade inválida!");
                    return;
                }
            }
            
            // Atualiza no banco de dados
            if (livroDAO.atualizar(livro)) {
                System.out.println("Livro atualizado com sucesso!");
            } else {
                System.out.println("Erro ao atualizar livro. Verifique os dados e tente novamente.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Exclui um livro do sistema.
     */
    private static void excluirLivro() {
        System.out.println("\n----- EXCLUSÃO DE LIVRO -----");
        
        System.out.print("Informe o ID do livro: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            
            Livro livro = livroDAO.buscarPorId(id);
            
            if (livro == null) {
                System.out.println("Livro não encontrado!");
                return;
            }
            
            // Verifica se há empréstimos ativos deste livro
            List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
            boolean possuiEmprestimosAtivos = false;
            
            for (Emprestimo emp : emprestimos) {
                if (emp.getLivro().getId().equals(livro.getId()) && 
                    (emp.getStatus().equals(Emprestimo.STATUS_ATIVO) || emp.getStatus().equals(Emprestimo.STATUS_ATRASADO))) {
                    possuiEmprestimosAtivos = true;
                    break;
                }
            }
            
            if (possuiEmprestimosAtivos) {
                System.out.println("ERRO: Este livro possui empréstimos ativos. Não é possível excluir.");
                return;
            }
            
            System.out.println("Dados do livro:");
            exibirLivro(livro);
            
            System.out.print("\nConfirma a exclusão? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                if (livroDAO.excluir(id)) {
                    System.out.println("Livro excluído com sucesso!");
                } else {
                    System.out.println("Erro ao excluir livro.");
                }
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Exibe os detalhes de um livro.
     * 
     * @param livro Livro a ser exibido
     */
    private static void exibirLivro(Livro livro) {
        System.out.println("ID: " + livro.getId());
        System.out.println("Título: " + livro.getTitulo());
        System.out.println("Autor: " + livro.getAutor());
        System.out.println("Ano de Publicação: " + livro.getAnoPublicacao());
        System.out.println("Quantidade em Estoque: " + livro.getQuantidadeEstoque());
    }
    
    // ========== MÉTODOS PARA GERENCIAR EMPRÉSTIMOS ==========
    
    /**
     * Registra um novo empréstimo.
     */
    private static void novoEmprestimo() {
        System.out.println("\n----- NOVO EMPRÉSTIMO -----");
        
        // Busca o aluno
        System.out.print("Informe a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }
        
        System.out.println("Aluno: " + aluno.getNome());
        
        // Busca o livro
        System.out.print("Informe o ID do livro: ");
        try {
            long idLivro = Long.parseLong(scanner.nextLine());
            
            Livro livro = livroDAO.buscarPorId(idLivro);
            if (livro == null) {
                System.out.println("ERRO: Livro não encontrado!");
                return;
            }
            
            System.out.println("Livro: " + livro.getTitulo());
            
            // Verifica se há estoque disponível
            if (livro.getQuantidadeEstoque() <= 0) {
                System.out.println("ERRO: Não há exemplares disponíveis deste livro!");
                return;
            }
            
            // Define as datas
            Date dataEmprestimo = new Date();
            Date dataDevolucaoPrevista = ConversorData.adicionarDias(dataEmprestimo, 14); // 14 dias para devolução
            
            System.out.println("Data do empréstimo: " + ConversorData.converterDataParaString(dataEmprestimo));
            System.out.println("Data prevista para devolução: " + ConversorData.converterDataParaString(dataDevolucaoPrevista));
            
            // Confirma o empréstimo
            System.out.print("\nConfirmar empréstimo? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                // Cria o objeto empréstimo
                Emprestimo emprestimo = new Emprestimo(aluno, livro, dataEmprestimo, dataDevolucaoPrevista);
                
                // Insere no banco de dados
                long id = emprestimoDAO.inserir(emprestimo);
                
                if (id > 0) {
                    System.out.println("Empréstimo realizado com sucesso! ID: " + id);
                } else {
                    System.out.println("Erro ao registrar empréstimo. Verifique os dados e tente novamente.");
                }
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Registra a devolução de um empréstimo.
     */
    private static void registrarDevolucao() {
        System.out.println("\n----- REGISTRAR DEVOLUÇÃO -----");
        
        System.out.print("Informe o ID do empréstimo: ");
        try {
            long id = Long.parseLong(scanner.nextLine());
            
            Emprestimo emprestimo = emprestimoDAO.buscarPorId(id);
            if (emprestimo == null) {
                System.out.println("ERRO: Empréstimo não encontrado!");
                return;
            }
            
            // Verifica se já foi devolvido
            if (Emprestimo.STATUS_DEVOLVIDO.equals(emprestimo.getStatus())) {
                System.out.println("ERRO: Este empréstimo já foi devolvido!");
                return;
            }
            
            System.out.println("Dados do empréstimo:");
            exibirEmprestimo(emprestimo);
            
            // Verifica se está atrasado
            boolean atrasado = emprestimo.estaAtrasado();
            if (atrasado) {
                long diasAtraso = emprestimo.calcularDiasAtraso();
                System.out.println("ATENÇÃO: Devolução com " + diasAtraso + " dias de atraso!");
            }
            
            // Confirma a devolução
            System.out.print("\nConfirmar devolução? (S/N): ");
            String confirmacao = scanner.nextLine();
            
            if (confirmacao.equalsIgnoreCase("S")) {
                if (emprestimoDAO.realizarDevolucao(id, new Date())) {
                    System.out.println("Devolução registrada com sucesso!");
                } else {
                    System.out.println("Erro ao registrar devolução.");
                }
            } else {
                System.out.println("Operação cancelada pelo usuário.");
            }
            
        } catch (NumberFormatException e) {
            System.out.println("ID inválido!");
        }
    }
    
    /**
     * Lista todos os empréstimos ativos.
     */
    private static void listarEmprestimosAtivos() {
        System.out.println("\n----- EMPRÉSTIMOS ATIVOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarPorStatus(Emprestimo.STATUS_ATIVO);
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo no momento!");
            return;
        }
        
        System.out.println("ID | Aluno | Livro | Data Empréstimo | Data Devolução Prevista");
        System.out.println("----------------------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.printf("%d | %s | %s | %s | %s%n", 
                    emprestimo.getId(), 
                    emprestimo.getAluno().getNome(), 
                    emprestimo.getLivro().getTitulo(),
                    ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()));
        }
    }
    
    /**
     * Lista todos os empréstimos atrasados.
     */
    private static void listarEmprestimosAtrasados() {
        System.out.println("\n----- EMPRÉSTIMOS ATRASADOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarAtrasados();
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo atrasado no momento!");
            return;
        }
        
        System.out.println("ID | Aluno | Livro | Data Empréstimo | Data Devolução Prevista | Dias de Atraso");
        System.out.println("------------------------------------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.printf("%d | %s | %s | %s | %s | %d%n", 
                    emprestimo.getId(), 
                    emprestimo.getAluno().getNome(), 
                    emprestimo.getLivro().getTitulo(),
                    ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()),
                    emprestimo.calcularDiasAtraso());
        }
    }
    
    /**
     * Busca empréstimos por aluno.
     */
    private static void buscarEmprestimosPorAluno() {
        System.out.println("\n----- BUSCAR EMPRÉSTIMOS POR ALUNO -----");
        
        System.out.print("Informe a matrícula do aluno: ");
        String matricula = scanner.nextLine();
        
        Aluno aluno = alunoDAO.buscarPorMatricula(matricula);
        if (aluno == null) {
            System.out.println("ERRO: Aluno não encontrado!");
            return;
        }
        
        System.out.println("Aluno: " + aluno.getNome());
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarPorAluno(aluno.getId());
        
        if (emprestimos.isEmpty()) {
            System.out.println("Este aluno não possui empréstimos registrados!");
            return;
        }
        
        System.out.println("\nEmpréstimos encontrados:");
        System.out.println("ID | Livro | Data Empréstimo | Status | Devolução Prevista | Devolução Efetiva");
        System.out.println("-----------------------------------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.printf("%d | %s | %s | %s | %s | %s%n", 
                    emprestimo.getId(), 
                    emprestimo.getLivro().getTitulo(),
                    ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()),
                    emprestimo.getStatus(),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()),
                    emprestimo.getDataDevolucaoEfetiva() != null ? 
                            ConversorData.converterDataParaString(emprestimo.getDataDevolucaoEfetiva()) : "Não devolvido");
        }
    }
    
    /**
     * Exibe os detalhes de um empréstimo.
     * 
     * @param emprestimo Empréstimo a ser exibido
     */
    private static void exibirEmprestimo(Emprestimo emprestimo) {
        System.out.println("ID: " + emprestimo.getId());
        System.out.println("Aluno: " + emprestimo.getAluno().getNome() + " (Matrícula: " + emprestimo.getAluno().getMatricula() + ")");
        System.out.println("Livro: " + emprestimo.getLivro().getTitulo() + " (Autor: " + emprestimo.getLivro().getAutor() + ")");
        System.out.println("Data do Empréstimo: " + ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()));
        System.out.println("Data Prevista para Devolução: " + ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()));
        System.out.println("Status: " + emprestimo.getStatus());
        
        if (emprestimo.getDataDevolucaoEfetiva() != null) {
            System.out.println("Data de Devolução Efetiva: " + ConversorData.converterDataParaString(emprestimo.getDataDevolucaoEfetiva()));
        }
        
        if (emprestimo.estaAtrasado()) {
            System.out.println("Situação: ATRASADO (" + emprestimo.calcularDiasAtraso() + " dias de atraso)");
        }
    }
    
    // ========== MÉTODOS PARA RELATÓRIOS ==========
    
    /**
     * Gera relatório de livros em estoque.
     */
    private static void relatorioLivrosEmEstoque() {
        System.out.println("\n----- RELATÓRIO: LIVROS EM ESTOQUE -----");
        
        List<Livro> livros = livroDAO.listarTodos();
        
        if (livros.isEmpty()) {
            System.out.println("Nenhum livro cadastrado!");
            return;
        }
        
        System.out.println("Título | Autor | Ano | Quantidade Disponível");
        System.out.println("----------------------------------------------");
        
        int totalDisponivel = 0;
        
        for (Livro livro : livros) {
            if (livro.getQuantidadeEstoque() > 0) {
                System.out.printf("%s | %s | %d | %d%n", 
                        livro.getTitulo(), 
                        livro.getAutor(),
                        livro.getAnoPublicacao(),
                        livro.getQuantidadeEstoque());
                
                totalDisponivel += livro.getQuantidadeEstoque();
            }
        }
        
        System.out.println("\nTotal de exemplares disponíveis: " + totalDisponivel);
    }
    
    /**
     * Gera relatório de livros emprestados.
     */
    private static void relatorioLivrosEmprestados() {
        System.out.println("\n----- RELATÓRIO: LIVROS EMPRESTADOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarPorStatus(Emprestimo.STATUS_ATIVO);
        emprestimos.addAll(emprestimoDAO.listarAtrasados());
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum livro emprestado no momento!");
            return;
        }
        
        System.out.println("Livro | Autor | Aluno | Data Empréstimo | Data Devolução Prevista | Situação");
        System.out.println("--------------------------------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            String situacao = emprestimo.estaAtrasado() ? 
                    "ATRASADO (" + emprestimo.calcularDiasAtraso() + " dias)" : 
                    "Em dia";
            
            System.out.printf("%s | %s | %s | %s | %s | %s%n", 
                    emprestimo.getLivro().getTitulo(), 
                    emprestimo.getLivro().getAutor(),
                    emprestimo.getAluno().getNome(),
                    ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()),
                    situacao);
        }
        
        System.out.println("\nTotal de livros emprestados: " + emprestimos.size());
    }
    
    /**
     * Gera relatório com histórico completo de empréstimos.
     */
    private static void relatorioHistoricoEmprestimos() {
        System.out.println("\n----- RELATÓRIO: HISTÓRICO DE EMPRÉSTIMOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarTodos();
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo registrado!");
            return;
        }
        
        System.out.println("ID | Livro | Aluno | Data Empréstimo | Status | Devolução");
        System.out.println("-----------------------------------------------------------");
        
        int ativos = 0;
        int devolvidos = 0;
        int atrasados = 0;
        
        for (Emprestimo emprestimo : emprestimos) {
            String devolucao = emprestimo.getDataDevolucaoEfetiva() != null ? 
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoEfetiva()) : 
                    "Não devolvido";
            
            System.out.printf("%d | %s | %s | %s | %s | %s%n", 
                    emprestimo.getId(), 
                    emprestimo.getLivro().getTitulo(), 
                    emprestimo.getAluno().getNome(),
                    ConversorData.converterDataParaString(emprestimo.getDataEmprestimo()),
                    emprestimo.getStatus(),
                    devolucao);
            
            if (Emprestimo.STATUS_ATIVO.equals(emprestimo.getStatus())) {
                ativos++;
            } else if (Emprestimo.STATUS_DEVOLVIDO.equals(emprestimo.getStatus())) {
                devolvidos++;
            } else if (Emprestimo.STATUS_ATRASADO.equals(emprestimo.getStatus())) {
                atrasados++;
            }
        }
        
        System.out.println("\nResumo:");
        System.out.println("Total de empréstimos: " + emprestimos.size());
        System.out.println("Empréstimos ativos: " + ativos);
        System.out.println("Empréstimos devolvidos: " + devolvidos);
        System.out.println("Empréstimos atrasados: " + atrasados);
    }
    
    /**
     * Gera relatório de alunos com empréstimos ativos.
     */
    private static void relatorioAlunosComEmprestimosAtivos() {
        System.out.println("\n----- RELATÓRIO: ALUNOS COM EMPRÉSTIMOS ATIVOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarPorStatus(Emprestimo.STATUS_ATIVO);
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo ativo no momento!");
            return;
        }
        
        System.out.println("Aluno | Matrícula | Livro | Data Devolução Prevista");
        System.out.println("----------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.printf("%s | %s | %s | %s%n", 
                    emprestimo.getAluno().getNome(), 
                    emprestimo.getAluno().getMatricula(),
                    emprestimo.getLivro().getTitulo(),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()));
        }
    }
    
    /**
     * Gera relatório de alunos com empréstimos atrasados.
     */
    private static void relatorioAlunosComEmprestimosAtrasados() {
        System.out.println("\n----- RELATÓRIO: ALUNOS COM EMPRÉSTIMOS ATRASADOS -----");
        
        List<Emprestimo> emprestimos = emprestimoDAO.listarAtrasados();
        
        if (emprestimos.isEmpty()) {
            System.out.println("Nenhum empréstimo atrasado no momento!");
            return;
        }
        
        System.out.println("Aluno | Matrícula | Livro | Dias de Atraso | Data Devolução Prevista");
        System.out.println("-----------------------------------------------------------------");
        
        for (Emprestimo emprestimo : emprestimos) {
            System.out.printf("%s | %s | %s | %d | %s%n", 
                    emprestimo.getAluno().getNome(), 
                    emprestimo.getAluno().getMatricula(),
                    emprestimo.getLivro().getTitulo(),
                    emprestimo.calcularDiasAtraso(),
                    ConversorData.converterDataParaString(emprestimo.getDataDevolucaoPrevista()));
        }
    }
    
    // ========== MÉTODOS AUXILIARES ==========
    
    /**
     * Lê uma opção do usuário.
     * 
     * @return Número da opção escolhida
     */
    private static int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }
}
