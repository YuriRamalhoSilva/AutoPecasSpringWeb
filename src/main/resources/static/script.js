async function list_products() {
    try {
        const response = await fetch('http://localhost:8080/produto');
        const produtos = await response.json();

        console.log("Produtos retornados pela API:", produtos);

        const lista = document.getElementById('lista_produtos');
        lista.innerHTML = ""; // Limpa a lista atual

        produtos.forEach(produto => {
            const item = document.createElement('li');
            item.textContent = `${produto.nome} - R$ ${produto.preco}`;
            item.setAttribute('data-id', produto.id); // Define o ID corretamente
            lista.appendChild(item);
        });
        return produtos.map(produto => produto.id);
    } catch (e) {
        console.error("Erro ao buscar produto", e);
    }
}



document.getElementById('botao-adicionar').addEventListener('click', function(event) {
    event.preventDefault(); // Impede o comportamento padrão do formulário
   
    // Capturar os valores NOME e PRECO do formulário web
    const NomeProduto = document.getElementById('nome').value;
    const PrecoProduto = parseFloat(document.getElementById('preco').value);

    // Validar se os campos estão preenchidos
    if (!NomeProduto || isNaN(PrecoProduto)) {
        alert('Por favor, preencha todos os campos corretamente.');
        return;
    }

    // Criar um objeto com os atributos capturados
    const DadosProduto = {
        nome: NomeProduto,
        preco: PrecoProduto
    };

    // Enviar os Dados para a API
    fetch('http://localhost:8080/produto', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(DadosProduto)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Erro na requisição: ' + response.statusText);
            }
            return response.json();
        })
        .then(data => {
            console.log('Sucesso:', data);
            alert('Produto adicionado ao banco com sucesso!');
            list_products();
            document.getElementById('productForm').reset();
            
            
        })
        .catch((error) => {
            console.error('Erro:', error);
            
        });
});


// Seleciona a lista de produtos e o botão de excluir e botao de editar
const listaProdutos = document.getElementById('lista_produtos');
const botaoExcluir = document.getElementById('botao-excluir');
const botaoAtualizar = document.getElementById('botao-editar');

// Variável para armazenar o item selecionado
let itemSelecionado = null;

// Evento de clique para selecionar um item da lista
listaProdutos.addEventListener('click', function(event) {
    if (event.target.tagName === 'LI') {
        // Remove a classe 'selecionado' do item anterior (se houver)
        if (itemSelecionado) {
            itemSelecionado.classList.remove('selecionado');
        }

        // Adiciona a classe 'selecionado' ao item clicado
        event.target.classList.add('selecionado');

        // Armazena o item selecionado
        itemSelecionado = event.target;
    }
});

// Evento de clique para excluir o produto selecionado
botaoExcluir.addEventListener('click', async function() {
    if (itemSelecionado) {
        const produtoID = itemSelecionado.getAttribute('data-id');

        if (!produtoID) {
            console.error("ID do produto não foi definido no item selecionado.");
            alert("Erro: ID do produto não definido!");
            return;
        }

        const conf = confirm(`Tem certeza que deseja excluir o produto "${itemSelecionado.textContent}"?`);

        if (conf) {
            try {
                // Requisição DELETE para a API
                const response = await fetch(`http://localhost:8080/produto/${produtoID}`, {
                    method: 'DELETE',
                });

                if (!response.ok) {
                    throw new Error("Erro na requisição: " + response.statusText);
                }

                console.log(`Produto com ID ${produtoID} excluído com sucesso!`);
                alert("Produto excluído com sucesso!");

                await list_products();
                
                // Reseta a variável do item selecionado
                itemSelecionado = null;
                
            } catch (error) {
                console.error("Erro ao excluir produto:", error);
                alert("Erro ao excluir produto!");
            }
        }
    } else {
        alert('Nenhum item selecionado para excluir.');
    }
});

// Evento de clique para atualizar um produto

botaoAtualizar.addEventListener('click', async function () {
    
if(itemSelecionado){

} else {
    alert("Nenhum item selecionado para edição.");
}

})

// Carrega os produtos ao iniciar a página
list_products();

