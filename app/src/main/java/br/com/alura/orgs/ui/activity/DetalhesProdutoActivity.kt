package br.com.alura.orgs.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import br.com.alura.orgs.R
import br.com.alura.orgs.databinding.ActivityDetalhesProdutoBinding
import br.com.alura.orgs.db.config.AppDatabase
import br.com.alura.orgs.extensions.carregarImagem
import br.com.alura.orgs.extensions.formataParaMoedaBrasileira
import br.com.alura.orgs.models.Produto

class DetalhesProdutoActivity : AppCompatActivity() {

    private var produtoId: Long = 0L
    private var produto: Produto? = null
    private val binding by lazy {
        ActivityDetalhesProdutoBinding.inflate(layoutInflater)
    }
    private val db by lazy {
        AppDatabase.getInstance(this)
    }
    private val dao by lazy {
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tentaCarregarProduto()
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        produto = dao.buscaPorId(produtoId)
        produto?.let {
            preencheCampos(it)
        } ?: finish()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detalhes_produto, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (produto != null) {
            val db = AppDatabase.getInstance(this)
            val dao = db.produtoDao()

            when (item.itemId) {
                R.id.menu_editar_produto -> {
                    Intent(this, FormularioProdutoActivity::class.java).apply {
                        putExtra("produtoId", produtoId)
                        startActivity(this)
                    }
                }
                R.id.menu_remover_produto -> {
                    produto?.let { dao.deletar(it) }
                    finish()
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun tentaCarregarProduto() {
        produtoId = intent.getLongExtra("produtoId", 0L)
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        with(binding) {
            activityDetalhesProdutoImagem.carregarImagem(produtoCarregado.imagem)
            activityDetalhesProdutoNome.text = produtoCarregado.nome
            activityDetalhesProdutoDescricao.text = produtoCarregado.descricao
            activityDetalhesProdutoValor.text =
                produtoCarregado.valor.formataParaMoedaBrasileira()
        }
    }
}