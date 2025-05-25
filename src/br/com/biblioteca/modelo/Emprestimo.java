package br.com.biblioteca.modelo;

import java.util.Date;

/**
 * Classe que representa um empréstimo de livro para um aluno.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class Emprestimo extends EntidadeBase {
    
    public static final String STATUS_ATIVO = "ATIVO";
    public static final String STATUS_DEVOLVIDO = "DEVOLVIDO";
    public static final String STATUS_ATRASADO = "ATRASADO";
    
    private Aluno aluno;
    private Livro livro;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoEfetiva;
    private String status;
    
    public Emprestimo() {
        super();
        this.dataEmprestimo = new Date();
        this.status = STATUS_ATIVO;
    }
    
    public Emprestimo(Aluno aluno, Livro livro, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        super();
        this.aluno = aluno;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.status = STATUS_ATIVO;
    }
    
    public Emprestimo(Long id, Aluno aluno, Livro livro, Date dataEmprestimo, 
                      Date dataDevolucaoPrevista, Date dataDevolucaoEfetiva, String status) {
        super(id);
        this.aluno = aluno;
        this.livro = livro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
        this.status = status;
    }
    
    public Aluno getAluno() {
        return aluno;
    }
    
    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }
    
    public Livro getLivro() {
        return livro;
    }
    
    public void setLivro(Livro livro) {
        this.livro = livro;
    }
    
    public Date getDataEmprestimo() {
        return dataEmprestimo;
    }
    
    public void setDataEmprestimo(Date dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    
    public Date getDataDevolucaoPrevista() {
        return dataDevolucaoPrevista;
    }
    
    public void setDataDevolucaoPrevista(Date dataDevolucaoPrevista) {
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }
    
    public Date getDataDevolucaoEfetiva() {
        return dataDevolucaoEfetiva;
    }
    
    public void setDataDevolucaoEfetiva(Date dataDevolucaoEfetiva) {
        this.dataDevolucaoEfetiva = dataDevolucaoEfetiva;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public boolean estaAtrasado() {
        if (this.status.equals(STATUS_DEVOLVIDO)) {
            return false;
        }
        return new Date().after(dataDevolucaoPrevista);
    }
    
    public boolean realizarDevolucao() {
        if (this.status.equals(STATUS_DEVOLVIDO)) {
            return false;
        }
        
        this.dataDevolucaoEfetiva = new Date();
        this.status = STATUS_DEVOLVIDO;
        
        this.livro.incrementarEstoque();
        
        return true;
    }
    
    public long calcularDiasAtraso() {
        if (!estaAtrasado()) {
            return 0;
        }
        
        long diffTime = new Date().getTime() - dataDevolucaoPrevista.getTime();
        return diffTime / (24 * 60 * 60 * 1000);
    }
    
    @Override
    public String toString() {
        return "Empréstimo [ID=" + getId() + ", Aluno=" + aluno.getNome() + 
               ", Livro=" + livro.getTitulo() + ", Status=" + status + "]";
    }
}
