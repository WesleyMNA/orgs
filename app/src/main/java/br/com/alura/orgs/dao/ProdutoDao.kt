package br.com.alura.orgs.dao

import br.com.alura.orgs.models.Produto

class ProdutoDao {

    fun adicionar(produto: Produto) {
        produtos.add(produto)
    }

    fun buscarTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf<Produto>()
    }
}