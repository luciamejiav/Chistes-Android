package net.azarquiel.recyclerviewpajaros.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import net.azarquiel.chistes.R
import net.azarquiel.chistes.model.Categoria
import net.azarquiel.chistes.model.Chiste

class AdapterChiste(val context: Context,
                    val layout: Int
                    ) : RecyclerView.Adapter<AdapterChiste.ViewHolder>() {

    private var dataList: List<Chiste> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val viewlayout = layoutInflater.inflate(layout, parent, false)
        return ViewHolder(viewlayout, context)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = dataList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    internal fun setChistes(chistes: List<Chiste>) {
        this.dataList = chistes
        notifyDataSetChanged()
    }


    class ViewHolder(viewlayout: View, val context: Context) : RecyclerView.ViewHolder(viewlayout) {
        fun bind(dataItem: Chiste){
            // itemview es el item de diseño
            // al que hay que poner los datos del objeto dataItem
            val ivrowchiste = itemView.findViewById(R.id.ivrowchiste) as ImageView
            val tvrowcontenidochiste = itemView.findViewById(R.id.tvrowcontenidochiste) as TextView

            tvrowcontenidochiste.text = "${dataItem.contenido.substring(0, 30)}..."

            // foto de internet a traves de Picasso
            //val foto = "http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.id}.png"
            // Picasso.get().load(foto).into(ivrowcat)
            Picasso.get().load("http://www.ies-azarquiel.es/paco/apichistes/img/${dataItem.idcategoria}.png").into(ivrowchiste)

            itemView.tag = dataItem

        }

    }
}