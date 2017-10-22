package br.ufrn.reuse.dominio.comum;

/**
 * Classe que representa uma unidade da UFRN.
 *
 * @author Daniel
 *
 */
public class Unidade {

    /** Identificador da unidade*/
    private Long id;

    /** Código da unidade */
    private Long codigo;

    /** Nome da unidade */
    private String nome;

    /**Sigla da unidade*/
    private String sigla;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}