package br.com.caelum.listaalunos.modelo;

/**
 * Created by android5519 on 06/01/16.
 */
public class Aluno {
    private String nome;
    private String telefone;
    private String endereco;
    private String site;
    private Double nota;
    private Long id;

    @Override
    public String toString(){
        return this.getId()+ "-"+this.getNome();
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
