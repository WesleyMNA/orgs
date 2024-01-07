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
import br.com.alura.orgs.models.Produto
import coil.load
import java.math.BigDecimal

class FormularioProdutoActivity : AppCompatActivity(R.layout.activity_formulario_produto) {

    private val binding by lazy {
        ActivityFormularioProdutoBinding.inflate(layoutInflater)
    }
    private var url: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        configurarBotaoSalvar()
        val imagem = findViewById<ImageView>(R.id.activity_formulario_imagem)
        imagem.setOnClickListener {
            val formularioImagemBinding = FormularioImagemBinding.inflate(layoutInflater)
            formularioImagemBinding.formularioImagemBotaoCarregar.setOnClickListener {
                val url = formularioImagemBinding.fomularioImageUrl.text.toString()
                formularioImagemBinding.formularioImagemImageview.load(url)
            }
            AlertDialog.Builder(this)
                .setView(formularioImagemBinding.root)
                .setPositiveButton("Confirmar") {_, _ ->
                    url = formularioImagemBinding.fomularioImageUrl.text.toString()
                    binding.activityFormularioImagem.load(url)
                }
                .setNegativeButton("Cancelar") {_, _ ->}
                .show()
        }
    }

    private fun configurarBotaoSalvar() {
        val botaoSalvar = findViewById<Button>(R.id.botao_salvar)
        val dao = ProdutoDao()
        botaoSalvar.setOnClickListener {
            val produto = criarProduto()
            dao.adicionar(produto)
            finish()
        }
    }

    private fun criarProduto(): Produto {
        val campoNome = findViewById<EditText>(R.id.activity_formulario_produto_nome)
        val nome = campoNome.text.toString()
        val campoDescricao = findViewById<EditText>(R.id.activity_formulario_produto_descricao)
        val descricao = campoDescricao.text.toString()
        val campoValor = findViewById<EditText>(R.id.activity_formulario_produto_valor)
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