package br.com.biblioteca.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import br.com.biblioteca.modelo.Aluno;

/**
 * Classe DAO (Data Access Object) para a entidade Aluno.
 * Implementa operações de CRUD (Create, Read, Update, Delete) para alunos.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class AlunoDAO {
    
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    
    public long inserir(Aluno aluno) {
        String sql = "INSERT INTO Alunos (nome_aluno, matricula, data_nascimento) VALUES (?, ?, ?)";
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            
            stmt = conexao.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, sdf.format(aluno.getDataNascimento()));
            
            int linhasAfetadas = stmt.executeUpdate();
            
            if (linhasAfetadas > 0) {
                rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
            
            return -1;
        } catch (SQLException e) {
            System.err.println("Erro ao inserir aluno: " + e.getMessage());
            return -1;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public Aluno buscarPorId(long id) {
        String sql = "SELECT * FROM Alunos WHERE id_aluno = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome_aluno"));
                aluno.setMatricula(rs.getString("matricula"));
                String dataNascStr = rs.getString("data_nascimento");
                if (dataNascStr != null) {
                    try {
                        aluno.setDataNascimento(sdf.parse(dataNascStr));
                    } catch (ParseException e) {
                        System.err.println("Erro ao converter data de nascimento: " + e.getMessage());
                    }
                }
                
                aluno.setDataCriacao(rs.getString("data_criacao"));
                aluno.setDataAtualizacao(rs.getString("data_atualizacao"));
                
                return aluno;
            }
            
            return null;
            
        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno: " + e.getMessage());
            return null;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }

    public Aluno buscarPorMatricula(String matricula) {
        String sql = "SELECT * FROM Alunos WHERE matricula = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, matricula);
            rs = stmt.executeQuery();
            
            if (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome_aluno"));
                aluno.setMatricula(rs.getString("matricula"));

                String dataNascStr = rs.getString("data_nascimento");
                if (dataNascStr != null) {
                    try {
                        aluno.setDataNascimento(sdf.parse(dataNascStr));
                    } catch (ParseException e) {
                        System.err.println("Erro ao converter data de nascimento: " + e.getMessage());
                    }
                }
                
                aluno.setDataCriacao(rs.getString("data_criacao"));
                aluno.setDataAtualizacao(rs.getString("data_atualizacao"));
                
                return aluno;
            }

            return null;

        } catch (SQLException e) {
            System.err.println("Erro ao buscar aluno por matrícula: " + e.getMessage());
            return null;
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
    }
    
    public List<Aluno> listarTodos() {
        String sql = "SELECT * FROM Alunos";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Aluno> alunos = new ArrayList<>();
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while (rs.next()) {
                Aluno aluno = new Aluno();
                aluno.setId(rs.getLong("id_aluno"));
                aluno.setNome(rs.getString("nome_aluno"));
                aluno.setMatricula(rs.getString("matricula"));

                String dataNascStr = rs.getString("data_nascimento");
                if (dataNascStr != null) {
                    try {
                        aluno.setDataNascimento(sdf.parse(dataNascStr));
                    } catch (ParseException e) {
                        System.err.println("Erro ao converter data de nascimento: " + e.getMessage());
                    }
                }

                aluno.setDataCriacao(rs.getString("data_criacao"));
                aluno.setDataAtualizacao(rs.getString("data_atualizacao"));
                
                alunos.add(aluno);
            }
            
        } catch (SQLException e) {
            System.err.println("Erro ao listar alunos: " + e.getMessage());
        } finally {
            fecharRecursos(conexao, stmt, rs);
        }
        
        return alunos;
    }
    
    public boolean atualizar(Aluno aluno) {
        String sql = "UPDATE Alunos SET nome_aluno = ?, matricula = ?, data_nascimento = ? WHERE id_aluno = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setString(1, aluno.getNome());
            stmt.setString(2, aluno.getMatricula());
            stmt.setString(3, sdf.format(aluno.getDataNascimento()));
            stmt.setLong(4, aluno.getId());
            
            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao atualizar aluno: " + e.getMessage());
            return false;
        } finally {
            fecharRecursos(conexao, stmt, null);
        }
    }

    public boolean excluir(long id) {
        String sql = "DELETE FROM Alunos WHERE id_aluno = ?";
        
        Connection conexao = null;
        PreparedStatement stmt = null;
        
        try {
            conexao = ConexaoBD.obterConexao();
            stmt = conexao.prepareStatement(sql);
            stmt.setLong(1, id);

            int linhasAfetadas = stmt.executeUpdate();
            
            return linhasAfetadas > 0;
            
        } catch (SQLException e) {
            System.err.println("Erro ao excluir aluno: " + e.getMessage());
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
