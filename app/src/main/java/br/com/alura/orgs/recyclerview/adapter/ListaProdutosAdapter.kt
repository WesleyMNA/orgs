package br.com.alura.orgs.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.alura.orgs.R
import br.com.alura.orgs.models.Produto
import coil.load
import java.text.NumberFormat
import java.util.Locale

class ListaProdutosAdapter(
    private val context: Context,
    produtos: List<Produto>
) : RecyclerView.Adapter<ListaProdutosAdapter.ViewHolder>() {

    private val dataSet = produtos.toMutableList()

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun vincula(produto: Produto) {
            val nome = itemView.findViewById<TextView>(R.id.produto_item_nome)
            nome.text = produto.nome
            val descricao = itemView.findViewById<TextView>(R.id.produto_item_descricao)
            descricao.text = produto.descricao
            val valor = itemView.findViewById<TextView>(R.id.produto_item_valor)
            val formatador = NumberFormat.getCurrencyInstance(Locale("pt", "br"))
            valor.text = formatador.format(produto.valor)
            val imagem = itemView.findViewById<ImageView>(R.id.produto_item_imagem)
             imagem.load(produto.imagem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.produto_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = dataSet.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val produto = dataSet[position]
        holder.vincula(produto)
    }

    fun atualiza(produtos: List<Produto>) {
        this.dataSet.clear()
        this.dataSet.addAll(produtos)
        notifyDataSetChanged()
    }
}
