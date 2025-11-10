package com.testeAEP.teste_projeto_aep.repository;

import com.testeAEP.teste_projeto_aep.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LivroRepository extends JpaRepository<Livro, Long> {

    // O JpaRepository cuida do CRUD dos metadados do livro

}