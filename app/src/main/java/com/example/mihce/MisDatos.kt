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
import kotlinx.android.synthetic.main.activity_mis_datos.*
import kotlinx.android.synthetic.main.activity_mis_datos.btn_goHome
import org.json.JSONArray
import org.json.JSONObject

class MisDatos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bundle = intent.extras
        val idUsuario = bundle?.getString("idUsuario")

        //Get Datos
        val url = Constant.URL_SERVER + "usuarioMisDatos.php"
        val queue = Volley.newRequestQueue(this)

        val sr: StringRequest = object : StringRequest(Method.POST,url,
            Response.Listener { response ->
                if (response.isNotEmpty()) {
                    setContentView(R.layout.activity_mis_datos)

                    val jo = JSONObject(response)

                    val list = ArrayList<MDP_item>()
                    val keys: JSONArray = jo.names()
                    for (i in 2 until jo.length()){
                        var item: MDP_item

                        //Verify null
                        if (jo.getString(keys[i].toString()) != "null"){
                            when (keys[i]) {
                                "sexo" ->
                                    if (jo.getString(keys[i].toString()) == "0") {
                                        item = MDP_item("Femenino", "sexo")
                                    } else {
                                        item = MDP_item("Masculino", "sexo")
                                    }

                                "fuma" ->
                                    if (jo.getString(keys[i].toString()) == "0") {
                                        item = MDP_item("No", "Fuma")
                                    } else {
                                        item = MDP_item("Si", "Fuma")
                                    }

                                "bebeAlcohol" ->
                                    if (jo.getString(keys[i].toString()) == "0") {
                                        item = MDP_item("No", "Bebe Alcohol")
                                    } else {
                                        item = MDP_item("Si", "Bebe Alcohol")
                                    }

                                else -> {
                                    item = MDP_item("" + jo.getString(keys[i].toString()), "" + keys[i])
                                }
                            }
                            list += item
                        }
                    }

                    //Send to Adapter
                    RV_MisDatosPersonales.adapter = MisDatosAdapter(list)
                    RV_MisDatosPersonales.layoutManager = LinearLayoutManager(this)
                    RV_MisDatosPersonales.setHasFixedSize(true)

                    //Home Button
                    btn_goHome.setOnClickListener{
                        val goHome = Intent(this, Home::class.java)
                        goHome.putExtra("idUsuario", idUsuario)
                        startActivity(goHome)
                    }

                } else {
                    Toast.makeText(this,"Complete el siguiente formulario", Toast.LENGTH_LONG).show()

                    val goMisDatosForm = Intent(this, MisDatosForm::class.java)
                    goMisDatosForm.putExtra("idUsuario", idUsuario)
                    startActivity(goMisDatosForm)
                }
            },
            Response.ErrorListener { error ->
                Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
            }) {
            @Throws(AuthFailureError::class)
            override fun getParams(): Map<String, String> {
                val parametros: MutableMap<String, String> =
                    HashMap()
                parametros["idUsuario"] = idUsuario.toString()
                return parametros
            }
        }
        queue.add(sr)
        //END getMisDatos
    }
}