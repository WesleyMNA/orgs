package br.com.alura.orgs.dao

import androidx.room.Dao
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
               nome = "Salada de frutas",
               descricao = "Laranja, maçãs e uva",
               valor = BigDecimal("19.99"),
               imagem = "https://images.pexels.com/photos/1132047/pexels-photo-1132047.jpeg"
            )
        )
    }
}