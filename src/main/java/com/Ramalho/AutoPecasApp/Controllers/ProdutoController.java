package com.Ramalho.AutoPecasApp.Controllers;

import com.Ramalho.AutoPecasApp.Domain.Produto;
import com.Ramalho.AutoPecasApp.Services.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produto")
@CrossOrigin(origins = "http://localhost:8080/index.html")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    // Listar produtos
    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    // Adicionar produtos
    @PostMapping
    public ResponseEntity<Produto> salvar(@RequestBody Produto produto) {
        Produto produtoSalvo = produtoService.salvarProduto(produto);
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoSalvo);
    }

    // Excluir produtos
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        try{
            produtoService.deleteProduct(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    // Editar produtos
    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable Integer id, @RequestBody Produto newproduto) {

        Produto produto = produtoService.atualizarProduto(id, newproduto);
        return ResponseEntity.ok(produto); // Retorna o produto atualizado com status 200 OK

    }
}
