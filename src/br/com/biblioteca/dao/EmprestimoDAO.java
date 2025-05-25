package br.com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import br.com.biblioteca.modelo.Aluno;
import br.com.biblioteca.modelo.Emprestimo;
import br.com.biblioteca.modelo.Livro;

/**
 * Classe DAO (Data Access Object) para a entidade Empréstimo.
 * Implementa operações de CRUD (Create, Read, Update, Delete) para empréstimos.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class EmprestimoDAO {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private AlunoDAO alunoDAO = new AlunoDAO();
    private LivroDAO livroDAO = new LivroDAO();
    
    public long inserir(Emprestimo emprestimo) {
        String sql = "INSERT INTO Emprestimos (id_aluno, id_livro, data_emprestimo, data_devolucao_prevista, status) " +
                     "VALUES (?, ?, ?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            conexao.setAutoCommit(false);
            Livro livro = emprestimo.getLivro();
            if (!livro.decrementarEstoque()) {
                return -1;
            }
            
            boolean estoqueAtualizado = livroDAO.atualizarEstoque(livro.getId(), livro.getQuantidadeEstoque());
            if (!estoqueAtualizado) {
                conexao.rollback();
                return -1;
            }
            
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setLong(1, emprestimo.getAluno().getId());
            stmt.setLong(2, emprestimo.getLivro().getId());
            stmt.setString(3, sdf.format(emprestimo.getDataEmprestimo()));
            stmt.setString(4, sdf.format(emprestimo.getDataDevolucaoPrevista()));
            stmt.setString(5, emprestimo.getStatus());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    conexao.commit();
                    return rs.getLong(1);
                }
            }

            conexao.rollback();
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir empréstimo: " + e.getMessage());
            
            try {
                if (conexao != null) {
                    conexao.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao desfazer transação: " + ex.getMessage());
            }
            
            return -1;
        } finally {
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Erro ao restaurar auto-commit: " + e.getMessage());
                }
            }
            
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public boolean realizarDevolucao(long idEmprestimo, Date dataDevolucao) {
        String sql = "UPDATE Emprestimos SET data_devolucao_efetiva = ?, status = ? WHERE id_emprestimo = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            Emprestimo emprestimo = buscarPorId(idEmprestimo);
            if (emprestimo == null) {
                return false;
            }
            
            if (Emprestimo.STATUS_DEVOLVIDO.equals(emprestimo.getStatus())) {
                return false;
            }
            
            conexao = ConexaoBD.obterConexao();
            
            conexao.setAutoCommit(false);
            
            Livro livro = emprestimo.getLivro();
            livro.incrementarEstoque();
            
            boolean estoqueAtualizado = livroDAO.atualizarEstoque(livro.getId(), livro.getQuantidadeEstoque());
            if (!estoqueAtualizado) {
                conexao.rollback();
                return false;
            }
            
            stmt = conexao.prepareStatement(sql);
            
            if (dataDevolucao == null) {
                dataDevolucao = new Date();
            }
            
            stmt.setString(1, sdf.format(dataDevolucao));
            stmt.setString(2, Emprestimo.STATUS_DEVOLVIDO);
            stmt.setLong(3, idEmprestimo);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                conexao.commit();
                return true;
            }
            
            conexao.rollback();
            return false;
            
        } catch (SQLException e) {
            System.err.println("Erro ao realizar devolução: " + e.getMessage());
            
            try {
                if (conexao != null) {
                    conexao.rollback();
                }
            } catch (SQLException ex) {
                System.err.println("Erro ao desfazer transação: " + ex.getMessage());
            }
            
            return false;
        } finally {
            if (conexao != null) {
                try {
                    conexao.setAutoCommit(true);
                } catch (SQLException e) {
                    System.err.println("Erro ao restaurar auto-commit: " + e.getMessage());
                }
            }
            
            fecharRecursos(conexao, stmt, null);
        }
    }

    public Emprestimo buscarPorId(long id) {
        String sql = "SELECT * FROM Emprestimos WHERE id_emprestimo = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                return construirEmprestimo(rs);
            }
            
            return null;
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar empréstimo: " + e.getMessage());
            return null;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public List<Emprestimo> listarTodos() {
        String sql = "SELECT * FROM Emprestimos";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = construirEmprestimo(rs);
                if (emprestimo != null) {
                    emprestimos.add(emprestimo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return emprestimos;
    }
    
    public List<Emprestimo> listarPorStatus(String status) {
        String sql = "SELECT * FROM Emprestimos WHERE status = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, status);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = construirEmprestimo(rs);
                if (emprestimo != null) {
                    emprestimos.add(emprestimo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos por status: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return emprestimos;
    }
    
    public List<Emprestimo> listarPorAluno(long idAluno) {
        String sql = "SELECT * FROM Emprestimos WHERE id_aluno = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, idAluno);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = construirEmprestimo(rs);
                if (emprestimo != null) {
                    emprestimos.add(emprestimo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos por aluno: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return emprestimos;
    }
    
    public List<Emprestimo> listarAtrasados() {
        String sql = "SELECT * FROM Emprestimos WHERE status = 'ATIVO' AND data_devolucao_prevista < CURRENT_DATE";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Emprestimo> emprestimos = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Emprestimo emprestimo = construirEmprestimo(rs);
                if (emprestimo != null) {
                    emprestimo.setStatus(Emprestimo.STATUS_ATRASADO);
                    emprestimos.add(emprestimo);
                }
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar empréstimos atrasados: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return emprestimos;
    }
    
    private Emprestimo construirEmprestimo(ResultSet rs) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(rs.getLong("id_emprestimo"));
        
        Aluno aluno = alunoDAO.buscarPorId(rs.getLong("id_aluno"));
        if (aluno == null) {
            return null;
        }
        emprestimo.setAluno(aluno);
        
        Livro livro = livroDAO.buscarPorId(rs.getLong("id_livro"));
        if (livro == null) {
            return null;
        }
        emprestimo.setLivro(livro);
        
        String dataEmprestimoStr = rs.getString("data_emprestimo");
        if (dataEmprestimoStr != null) {
            try {
                emprestimo.setDataEmprestimo(sdf.parse(dataEmprestimoStr));
            } catch (ParseException e) {
                System.err.println("Erro ao converter data de empréstimo: " + e.getMessage());
            }
        }
        
        String dataDevolucaoPrevistaStr = rs.getString("data_devolucao_prevista");
        if (dataDevolucaoPrevistaStr != null) {
            try {
                emprestimo.setDataDevolucaoPrevista(sdf.parse(dataDevolucaoPrevistaStr));
            } catch (ParseException e) {
                System.err.println("Erro ao converter data de devolução prevista: " + e.getMessage());
            }
        }
        
        String dataDevolucaoEfetivaStr = rs.getString("data_devolucao_efetiva");
        if (dataDevolucaoEfetivaStr != null) {
            try {
                emprestimo.setDataDevolucaoEfetiva(sdf.parse(dataDevolucaoEfetivaStr));
            } catch (ParseException e) {
                System.err.println("Erro ao converter data de devolução efetiva: " + e.getMessage());
            }
        }
        
        emprestimo.setStatus(rs.getString("status"));
        
        return emprestimo;
    }
    
    private void fecharRecursos(Connection conexao, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar ResultSet: " + e.getMessage());
            }
        }
        
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar Statement: " + e.getMessage());
            }
        }
        
        ConexaoBD.fecharConexao(conexao);
    }
}
