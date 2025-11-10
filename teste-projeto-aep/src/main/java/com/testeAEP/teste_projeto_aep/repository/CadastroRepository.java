// Define que está interface pertence á camada de repositorio.
package com.testeAEP.teste_projeto_aep.repository;

// Importa sua classe Cadastro para que o repositorio saiba qual tabela ele deve gerenciar.
import com.testeAEP.teste_projeto_aep.model.Cadastro;
// Importa a interface principal do Spring Data JPA, responsavel por fornercer os métodos CRUD prontos.
import org.springframework.data.jpa.repository.JpaRepository;
// Indica que está interface é um componente gerenciado responsável pela interação com o banco de dados.
import org.springframework.stereotype.Repository;

// Marca a interface como camada de persistencia persistência.
@Repository
public interface CadastroRepository extends JpaRepository<Cadastro, Long> {

    // Este método ira ler o nome findByEmailAndSenha, e ira traduzir automaticamente para consultar o banco dedos que ira validar email e senha do Login.
    Cadastro findByEmailAndSenha(String email, String senha);

}