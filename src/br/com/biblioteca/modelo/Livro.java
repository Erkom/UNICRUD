package br.com.biblioteca.modelo;

/**
 * Classe que representa um livro no sistema de biblioteca.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public class Livro extends EntidadeBase {
    
    private String titulo;
    private String autor;
    private Integer anoPublicacao;
    private Integer quantidadeEstoque;
    
    public Livro() {
        super();
        this.quantidadeEstoque = 0;
    }
    
    public Livro(String titulo, String autor, Integer anoPublicacao, Integer quantidadeEstoque) {
        super();
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public Livro(Long id, String titulo, String autor, Integer anoPublicacao, Integer quantidadeEstoque) {
        super(id);
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public String getTitulo() {
        return titulo;
    }
    
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    
    public String getAutor() {
        return autor;
    }
    
    public void setAutor(String autor) {
        this.autor = autor;
    }
    
    public Integer getAnoPublicacao() {
        return anoPublicacao;
    }
    
    public void setAnoPublicacao(Integer anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
    
    public Integer getQuantidadeEstoque() {
        return quantidadeEstoque;
    }
    
    public void setQuantidadeEstoque(Integer quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }
    
    public void incrementarEstoque() {
        this.quantidadeEstoque++;
    }
    
    public boolean decrementarEstoque() {
        if (this.quantidadeEstoque > 0) {
            this.quantidadeEstoque--;
            return true;
        }
        return false;
    }
    
    public boolean estaDisponivel() {
        return this.quantidadeEstoque > 0;
    }
    
    @Override
    public String toString() {
        return "Livro [ID=" + getId() + ", Título=" + titulo + ", Autor=" + autor + 
               ", Ano=" + anoPublicacao + ", Estoque=" + quantidadeEstoque + "]";
    }
}
