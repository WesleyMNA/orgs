package br.com.alura.orgs.ui.dialog

import android.content.Context
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import br.com.alura.orgs.databinding.FormularioImagemBinding
import br.com.alura.orgs.extensions.carregarImagem

class FormularioImagemDialog(
    private val context: Context
) {

    fun show(urlLoaded: (url: String) -> Unit) {
        val binding = FormularioImagemBinding.inflate(LayoutInflater.from(context))
        binding.formularioImagemBotaoCarregar.setOnClickListener {
            val url = binding.fomularioImageUrl.text.toString()
            binding.formularioImagemImageview.carregarImagem(url)
        }
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") {_, _ ->
                val url = binding.fomularioImageUrl.text.toString()
                urlLoaded(url)
            }
            .setNegativeButton("Cancelar") {_, _ ->}
            .show()
    }
}