package br.com.alura.orgs.db.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import br.com.alura.orgs.models.Produto

@Dao
interface ProdutoDao {

    @Query("SELECT * FROM Produto")
    fun buscarTodos() : List<Produto>

    @Insert
    fun salva(vararg produto: Produto)

    @Update
    fun atualizar(vararg produto: Produto)

    @Delete
    fun deletar(produto: Produto)
}