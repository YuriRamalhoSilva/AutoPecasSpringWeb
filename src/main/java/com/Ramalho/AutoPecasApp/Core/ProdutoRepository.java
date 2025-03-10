package com.Ramalho.AutoPecasApp.Core;


import com.Ramalho.AutoPecasApp.Domain.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    Integer id(Long id);
    boolean existsByNome(String nome);

}
