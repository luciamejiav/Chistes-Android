package net.azarquiel.chistes.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import net.azarquiel.chistes.R
import net.azarquiel.chistes.model.Categoria
import net.azarquiel.chistes.model.Chiste
import net.azarquiel.chistes.model.Result
import net.azarquiel.recyclerviewpajaros.adapter.AdapterChiste
import java.net.URL

class ChistesActivity : AppCompatActivity() {


    private lateinit var adapter: AdapterChiste
    private lateinit var categoria: Categoria

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chistes)
        
        categoria = intent.getSerializableExtra("categoria") as Categoria

        initRV()
        getDatos()
    }

    private fun getDatos() {
        GlobalScope.launch() {
            val jsontxt = URL("http://www.ies-azarquiel.es/paco/apichistes/categoria/${categoria.id}/chistes").readText(Charsets.UTF_8)
            launch(Dispatchers.Main) {
                val result = Gson().fromJson(jsontxt, Result::class.java)
                adapter.setChistes(result.chistes)
            }
        }

    }

    private fun initRV() {
        val rvchiste = findViewById<RecyclerView>(R.id.rvchiste)
        adapter = AdapterChiste(this, R.layout.rowchiste)
        rvchiste.adapter = adapter
        rvchiste.layoutManager = LinearLayoutManager(this)
    }

    fun onclickChiste(v: View){
        val chiste = v.tag as Chiste

        val intent = Intent(this, ChisteDetailActivity::class.java)
        intent.putExtra("chiste", chiste)
        startActivity(intent)
    }

}