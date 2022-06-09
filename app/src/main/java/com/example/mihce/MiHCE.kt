package com.example.mihce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_mihce.*
import org.json.JSONArray
import org.json.JSONObject

class MiHCE : AppCompatActivity(), ConsultaAdapter.OnItemClickListener {
    private val list = ArrayList<C_item>()
    private lateinit var jo: JSONObject
    private var idConsultaArray = JSONArray()
    private val adapter = ConsultaAdapter(list, this)
    private var idUsuario = String()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mihce)

        val bundle = intent.extras
        this.idUsuario = bundle?.getString("idUsuario").toString()

        val queue = Volley.newRequestQueue(this)
        val url = Constant.URL_SERVER + "select_lista_consultas.php"
        val sr = object : StringRequest(
            Method.POST, url,
            Response.Listener<String> { response ->
                if (response.isNotEmpty()) {
                    setContentView(R.layout.activity_mihce)

                    //Divide String
                    val replace = response.replace("}{", "}#*·*#{")
                    //Array of Strings
                    val vector = replace.split("#*·*#")

                    //Iterate array
                    for (i in vector.indices){
                        //Convert String to Object
                        jo = JSONObject(vector[i])
                        //Add item to Activity List
                        val item = C_item("" + jo["especialista"])

                        list += item
                        idConsultaArray.put(jo["id"])
                    }

                    //Send to Adapter
                    RV_MiHCE.adapter = adapter
                    RV_MiHCE.layoutManager = LinearLayoutManager(this)
                    RV_MiHCE.setHasFixedSize(true)

                    btn_goHome.setOnClickListener{
                        val goHome = Intent(this, Home::class.java)
                        goHome.putExtra("idUsuario", idUsuario)
                        startActivity(goHome)
                    }

                } else {
                    Toast.makeText(this,"No hay consultas cargadas", Toast.LENGTH_LONG).show()
                    val goHome = Intent(this, Home::class.java)
                    goHome.putExtra("idUsuario", idUsuario)
                    startActivity(goHome)
                }
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() })
        {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val params = HashMap<String, String>()
                params.put("idUsuario", idUsuario)
                return params
            }
        }
        queue.add(sr)
    }

    override fun onItemClick(position: Int) {
        adapter.notifyItemChanged(position)

        val idConsulta = idConsultaArray[position]

        val goConsultaVer = Intent(this, ConsultaVer::class.java)
        goConsultaVer.putExtra("idConsulta", idConsulta.toString())
        goConsultaVer.putExtra("idUsuario", idUsuario)
        startActivity(goConsultaVer)
    }

}