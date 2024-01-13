package br.com.alura.orgs.ui.activity

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import br.com.alura.orgs.R
import br.com.alura.orgs.dao.ProdutoDao
import br.com.alura.orgs.databinding.ActivityFormularioProdutoBinding
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.carregarImagem
import br.com.alura.orgs.models.Produto
import br.com.alura.orgs.ui.dialog.FormularioImagemDialog
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity() {

    private var url: String? = null
    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
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
        setContentView(binding.root)
    }

    private fun configurarBotaoSalvar() {
        val botaoSalvar = binding.botaoSalvar
        val dao = ProdutoDao()
        botaoSalvar.setOnClickListener {
            val produto = criarProduto()
            dao.adicionar(produto)
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
            nome = nome,
            descricao = descricao,
            valor = valor,
            imagem = url
        )
    }
}