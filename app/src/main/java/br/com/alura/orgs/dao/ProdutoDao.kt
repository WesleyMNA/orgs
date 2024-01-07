package br.com.alura.orgs.dao

import br.com.alura.orgs.models.Produto
import java.math.BigDecimal

class ProdutoDao {

    fun adicionar(produto: Produto) {
        produtos.add(produto)
    }

    fun buscarTodos(): List<Produto> {
        return produtos.toList()
    }

    companion object {
        private val produtos = mutableListOf(
            Produto(
                "Salada de frutas",
                "Laranja, maçãs e uva",
                BigDecimal("19.99")
            )
        )
    }
}