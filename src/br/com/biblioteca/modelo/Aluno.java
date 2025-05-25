package br.com.biblioteca.modelo;

import java.util.Date;

/**
 * Classe que representa um aluno no sistema de biblioteca.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class Aluno extends EntidadeBase {
    
    private String nome;
    private String matricula;
    private Date dataNascimento;
    
    public Aluno() {
        super();
    }
    
    public Aluno(String nome, String matricula, Date dataNascimento) {
        super();
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }
    
    public Aluno(Long id, String nome, String matricula, Date dataNascimento) {
        super(id);
        this.nome = nome;
        this.matricula = matricula;
        this.dataNascimento = dataNascimento;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getMatricula() {
        return matricula;
    }
    
    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
    
    public Date getDataNascimento() {
        return dataNascimento;
    }
    
    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }
    
    @Override
    public String toString() {
        return "Aluno [ID=" + getId() + ", Nome=" + nome + ", Matrícula=" + matricula + "]";
    }
}
