package com.Ramalho.AutoPecasApp.Services;


import com.Ramalho.AutoPecasApp.Core.ProdutoRepository;
import com.Ramalho.AutoPecasApp.Domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarTodos(){
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto){
        if (produtoRepository.existsByNome(produto.getNome())) {
            throw new RuntimeException("Produto "+ produto.getNome() + "já está cadastrado no banco de dados!");
        } else{
            return produtoRepository.save(produto);
        }
    }

    public void deleteProduct(Integer id) {
        produtoRepository.deleteById(id);

    }

}
