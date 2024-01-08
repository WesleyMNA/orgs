package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.carregarImagem

class FormularioImagemDialog(
    private val context: Context
) {

    fun show(
        urlPadrao: String? = null,
        urlLoaded: (url: String) -> Unit
    ) {
        FormularioImagemBinding.inflate(LayoutInflater.from(context)).apply {
            urlPadrao?.let {
                formularioImagemImageview.carregarImagem(it)
                fomularioImageUrl.setText(it)
            }
            formularioImagemBotaoCarregar.setOnClickListener {
                val url = fomularioImageUrl.text.toString()
                formularioImagemImageview.carregarImagem(url)
            }
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = fomularioImageUrl.text.toString()
                    urlLoaded(url)
                }
                .setNegativeButton("Cancelar") { _, _ -> }
                .show()
        }
    }
}