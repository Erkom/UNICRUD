package br.com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.biblioteca.modelo.Livro;

/**
 * Classe DAO (Data Access Object) para a entidade Livro.
 * Implementa operações de CRUD (Create, Read, Update, Delete) para livros.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class LivroDAO {
    
    public long inserir(Livro livro) {
        String sql = "INSERT INTO Livros (titulo, autor, ano_publicacao, quantidade_estoque) VALUES (?, ?, ?, ?)";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQuantidadeEstoque());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            
            return -1;
            
        } catch (SQLException e) {
            System.err.println("Erro ao inserir livro: " + e.getMessage());
            return -1;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public Livro buscarPorId(long id) {
        String sql = "SELECT * FROM Livros WHERE id_livro = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                
                livro.setDataCriacao(rs.getString("data_criacao"));
                livro.setDataAtualizacao(rs.getString("data_atualizacao"));
                return livro;
            }
            return null;
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livro: " + e.getMessage());
            return null;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public List<Livro> listarTodos() {
        String sql = "SELECT * FROM Livros";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> livros = new ArrayList<>();
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                
                livro.setDataCriacao(rs.getString("data_criacao"));
                livro.setDataAtualizacao(rs.getString("data_atualizacao"));
                
                livros.add(livro);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar livros: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return livros;
    }
    
    public List<Livro> buscarPorTitulo(String titulo) {
        String sql = "SELECT * FROM Livros WHERE titulo LIKE ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Livro> livros = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, "%" + titulo + "%");
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Livro livro = new Livro();
                livro.setId(rs.getLong("id_livro"));
                livro.setTitulo(rs.getString("titulo"));
                livro.setAutor(rs.getString("autor"));
                livro.setAnoPublicacao(rs.getInt("ano_publicacao"));
                livro.setQuantidadeEstoque(rs.getInt("quantidade_estoque"));
                
                livro.setDataCriacao(rs.getString("data_criacao"));
                livro.setDataAtualizacao(rs.getString("data_atualizacao"));
                
                livros.add(livro);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar livros por título: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return livros;
    }
    
    public boolean atualizar(Livro livro) {
        String sql = "UPDATE Livros SET titulo = ?, autor = ?, ano_publicacao = ?, quantidade_estoque = ? WHERE id_livro = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, livro.getTitulo());
            stmt.setString(2, livro.getAutor());
            stmt.setInt(3, livro.getAnoPublicacao());
            stmt.setInt(4, livro.getQuantidadeEstoque());
            stmt.setLong(5, livro.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar livro: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conexao, stmt, null);
        }
    }
    
    public boolean atualizarEstoque(long idLivro, int quantidade) {
        String sql = "UPDATE Livros SET quantidade_estoque = ? WHERE id_livro = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            
            stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, quantidade);
            stmt.setLong(2, idLivro);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque do livro: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conexao, stmt, null);
        }
    }
    
    public boolean excluir(long id) {
        String sql = "DELETE FROM Livros WHERE id_livro = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir livro: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conexao, stmt, null);
        }
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
