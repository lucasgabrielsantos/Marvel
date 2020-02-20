package br.com.digitalhouse.projetomarvel.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import br.com.digitalhouse.projetomarvel.R
import br.com.digitalhouse.projetomarvel.pojo.Result
import br.com.digitalhouse.projetomarvel.view.Interface.OnClickDetails
import com.squareup.picasso.Picasso


class ComicsAdapter(private var listresult: List<Result>, private val listener: OnClickDetails)
    : RecyclerView.Adapter<ComicsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.itemlayout_card, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val resultlist = listresult[position]
        holder.onBind(resultlist)
        holder.itemView.setOnClickListener { listener.Onclick(resultlist) }
    }

    override fun getItemCount(): Int {
        return listresult.size
    }

    fun atualizalista(resultList: List<Result>) {
        listresult = resultList
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.marvellogo)
        private val textViewTitle: TextView = itemView.findViewById(R.id.idComics)
        fun onBind(result: Result) {
            Picasso.get().load(result.thumbnail.path + ".jpg").into(imageView)
            textViewTitle.text = result.title
        }

    }

}