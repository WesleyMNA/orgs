package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.db.config.AppDatabase
import br.com.alura.orgs.extensions.carregarImagem
import br.com.alura.orgs.models.Produto
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private lateinit var produto: Produto
    private var url: String? = null
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var produtoId = 0L
    private val db by lazy {
        AppDatabase.getInstance(this)
    }
    private val dao by lazy {
        db.produtoDao()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configurarBotaoSalvar()
        title = "Cadastrar produto"
        val imagem = binding.activityFormularioImagem
        imagem.setOnClickListener {
            FormularioImagemDialog(this).show(url) { urlLoaded ->
                url = urlLoaded
                imagem.carregarImagem(url)
            }
        }
        produtoId = intent.getLongExtra("produtoId", 0L)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        dao.buscaPorId(produtoId)?.let {
            preencheCampos(it)
        }
    }

    private fun preencheCampos(produtoCarregado: Produto) {
        title = "Alterar produto"
        url = produtoCarregado.imagem
        binding.activityFormularioImagem.carregarImagem(produtoCarregado.imagem)
        binding.activityFormularioProdutoNome.setText(produtoCarregado.nome)
        binding.activityFormularioProdutoDescricao.setText(produtoCarregado.descricao)
        binding.activityFormularioProdutoValor.setText(produtoCarregado.valor.toPlainString())
    }

    private fun configurarBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        botaoSalvar.setOnClickListener {
            val produto = criarProduto()
            dao.salva(produto)
            finish()
        }
    }

    private fun criarProduto(): Produto {
        val campoNome = binding.activityFormularioProdutoNome
        val nome = campoNome.text.toString()
        val campoDescricao = binding.activityFormularioProdutoDescricao
        val descricao = campoDescricao.text.toString()
        val campoValor = binding.activityFormularioProdutoValor
        val valorTexto = campoValor.text.toString()

        val valor = if (valorTexto.isBlank())
            BigDecimal.ZERO
        else
            BigDecimal(valorTexto)

        return Produto(
            id = produtoId,
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}