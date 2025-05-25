package br.com.biblioteca.modelo;

/**
 * Classe genérica para representar uma entidade no sistema.
 * 
 * @author Grupo 25 - N688-Ambiente de dados
 * @version 1.0
 */
public abstract class EntidadeBase {
    
    private Long id;
    private String dataCriacao;
    private String dataAtualizacao;
    
    public EntidadeBase() {
        // Inicialização padrão
    }
    
    public EntidadeBase(Long id) {
        this.id = id;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getDataCriacao() {
        return dataCriacao;
    }
    
    public void setDataCriacao(String dataCriacao) {
        this.dataCriacao = dataCriacao;
    }
    
    public String getDataAtualizacao() {
        return dataAtualizacao;
    }
    
    public void setDataAtualizacao(String dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
