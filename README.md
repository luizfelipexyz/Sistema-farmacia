📋 Descrição
Este projeto é um sistema de farmácia voltado para controle de estoque, venda e cadastro de produtos.
A aplicação simula três tipos de usuário: Atendente, Farmacêutico e Gerente.

🎯 Objetivos
Simular uma farmácia com funções específicas para cada cargo, aplicando conceitos de programação orientada a objetos.

🛠️ Funcionalidades Principais

✅ Cadastro, remoção e alteração de produtos em estoque.

✅ Venda de produtos.

✅ Consulta de produtos por categoria ou tipo.

✅ Geração de relatório de vendas.

🧩 Principais Classes

Produto
Classe base genérica.
Atributos: código, nome, preço, categoria.
Métodos: vender(), toString() etc.

RemedioOTC e RemedioTarjaPreta
Estendem a classe Produto.
RemedioTarjaPreta possui o atributo adicional: número da receita.

Estoque
Contém uma lista de produtos.
Métodos para adicionar, remover, listar e buscar produtos.

Vendas e VendasController
Controlam o registro e exibição das vendas realizadas.

PrincipalView
Gerencia a interação com o usuário e os menus da aplicação via console.

🔄 Relações Entre Classes

Herança: RemedioOTC e RemedioTarjaPreta herdam de Produto.
Agregação: A classe Estoque possui uma lista de Produto.
Associação: VendasController se relaciona com a classe Vendas.

▶️ Como Executar
Clone o repositório com o comando:

git clone https://github.com/luizfelipexyz/Sistema-farmacia

Compile e execute a classe PrincipalView.
Interaja com o sistema pelo console da sua IDE ou terminal.

🤖 Uso do ChatGPT
O ChatGPT foi utilizado no projeto para:

✅ Oferecer ideias para métodos e atributos das classes.

✅ Sugestões de estrutura de código, organização e fluxo dos menus.

✅ Ajudar na correção de bugs e exceções.

✅ Melhorar a estrutura e a documentação do projeto.    

✅ Implementar algumas funcionalidades, como, por exemplo, a lógica da busca por categoria.
