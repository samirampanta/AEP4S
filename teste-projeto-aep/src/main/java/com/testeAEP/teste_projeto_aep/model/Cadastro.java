// Define onde este arquivo está localizado no projeto.
package com.testeAEP.teste_projeto_aep.model;

// Importa a anotação que diz ao Spring que esta classe é uma tabela no banco de dados.
import jakarta.persistence.Entity;
// Configura como a chave primária será gerada.
import jakarta.persistence.GeneratedValue;
// Importa as opções para geração de ID.
import jakarta.persistence.GenerationType;
// Define como chave primária da tabela.
import jakarta.persistence.Id;

// Informa que está classe deve ser tratada como uma Tabela no banco de dados.
@Entity
public class Cadastro {

    @Id //Marca o campo id como a chave primária da tabela.
    // Define que o valor id será gerado automaticamente pelo banco de dados.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String senha;

    // Construtor vazio (obrigatório para o JPA)
    public Cadastro() {
    }

    // Construtor com campos
    public Cadastro(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }

    // --- Métodos Getters e Setters ---
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() { // NOVO GETTER
        return senha;
    }

    public void setSenha(String senha) { // NOVO SETTER
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "Cadastro{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}