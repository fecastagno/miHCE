package com.example.mihce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_farmacos.*
import kotlinx.android.synthetic.main.activity_farmacos.btn_goHome
import org.json.JSONArray
import org.json.JSONObject

class Farmacos : AppCompatActivity(), FarmacosAdapter.OnItemClickListener {
    private val list = ArrayList<F_item>()
    private lateinit var jo: JSONObject
    private var idFarmacosArray = JSONArray()
    private val adapter = FarmacosAdapter(list, this)
    private var idUsuario = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_farmacos)

        //recupero el dato enviado prev activity
        val bundle = intent.extras
        this.idUsuario = bundle?.getString("idUsuario").toString()

        val url = Constant.URL_SERVER + "select_lista_farmacos.php"
        val queue = Volley.newRequestQueue(this)

        //Toast.makeText(this,"FARMACOS - USUARIO: $idUsuario", Toast.LENGTH_LONG).show()
        val sr: StringRequest = object : StringRequest(
            Method.POST, url, Response.Listener { response ->
                if (response.isNotEmpty()) {
                    setContentView(R.layout.activity_farmacos)

                    //dividir el string
                    val replace = response.replace("}{", "}#*·*#{")
                    //crear array de strings
                    val vector = replace.split("#*·*#")

                    var item: F_item
                    for (i in vector.indices){
                        //convertir cada string en objeto
                        jo = JSONObject(vector[i])

                        val fechaFin =
                            if (jo["fechaFin"].toString() == "null") "Sin definir" else jo["fechaFin"].toString()
                        item = F_item(jo["medicacion"].toString(), jo["dosis"].toString(), jo["fechaInicio"].toString(), fechaFin)
                        list += item
                        idFarmacosArray.put(jo["id"]) // guardo los id en un array
                    }

                    //envio al Adapter
                    RV_Farmacos.adapter = adapter
                    RV_Farmacos.layoutManager = LinearLayoutManager(this)
                    RV_Farmacos.setHasFixedSize(true)

                    btn_goHome.setOnClickListener{
                        val goHome = Intent(this, Home::class.java)
                        goHome.putExtra("idUsuario", idUsuario)
                        startActivity(goHome)
                    }

                } else {
                    Toast.makeText(this,"No hay ninguna medicación cargada", Toast.LENGTH_LONG).show()

                    val goHome = Intent(this, Home::class.java)
                    goHome.putExtra("idUsuario", idUsuario)
                    startActivity(goHome)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> =
                    HashMap()
                parametros["idUsuario"] = idUsuario
                return parametros
            }
        }
        queue.add(sr)
        //END
    }

    override fun onItemClick(position: Int) {
        //Toast.makeText(this, "item $position clicked", Toast.LENGTH_LONG).show()
        //Toast.makeText(this, "A VER: "+idFarmacosArray[position], Toast.LENGTH_LONG).show()
        //val clickedItem = list[position]
        //clickedItem.text1 = "Clicked"
        adapter.notifyItemChanged(position)
    }
}