package br.com.alura.orgs.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.models.Produto
import br.com.alura.orgs.recyclerview.adapter.ListaProdutosAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var recycledView = findViewById<RecyclerView>(R.id.recyclerView)
        recycledView.adapter = ListaProdutosAdapter(
            this, listOf(
                Produto("teste", "testee desc", BigDecimal("19.99")),
                Produto("teste1", "teste2 desc", BigDecimal("50.99")),
                Produto("teste1", "teste2 desc", BigDecimal("50.99")),
                Produto("teste1", "teste2 desc", BigDecimal("50.99")),
                Produto("teste1", "teste2 desc", BigDecimal("50.99")),
            )
        )
    }
}