package com.Ramalho.AutoPecasApp.Services;


import com.Ramalho.AutoPecasApp.Core.ProdutoRepository;
import com.Ramalho.AutoPecasApp.Domain.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Produto atualizarProduto(Integer id,Produto newproduto){
        //Verificar se o produto existe cirando um Optional da classe Produto e proucura o produto no Repository via ID
        Optional<Produto> produtoExists = produtoRepository.findById(id);
        // Depois vem a condição de existencia do produto
        if (produtoExists.isPresent()) { //Se o produto realmente existir ele passa pro processo de subtituição de nome e preço
            Produto produto = produtoExists.get(); //Aki um produto da classe Produto é criado para manipular o produto já existente na API
            produto.setNome(newproduto.getNome()); //Aki a substituição do nome existente pelo nome do newproduto
            produto.setPreco(newproduto.getPreco()); //Aki a substituição do preço existente pelo preço do newproduto
            return produtoRepository.save(produto); //Depois ele é salvo no banco de dados já com seus atributos modificados e teoricamente ele permaneçe com o mesmo ID
        } else {
            throw new RuntimeException("Produto com id: "+id+" não existe!");
        }
    }
}
