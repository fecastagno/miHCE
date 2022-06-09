package com.example.mihce

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_consulta_ver.*
import org.json.JSONObject

class ConsultaVer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consulta_ver)

        //recupero el dato enviado prev activity
        val bundle = intent.extras
        val idConsulta = bundle?.getString("idConsulta")
        val idUsuario = bundle?.getString("idUsuario")

        val queue = Volley.newRequestQueue(this)
        val url = Constant.URL_SERVER + "select_consulta.php"
        val sr = object : StringRequest(
            Method.POST, url,
            Response.Listener { response ->
                val jo = JSONObject(response)
                especialista.setText(jo["especialista"].toString())
                motivoConsulta.setText(jo["motivoConsulta"].toString())
                tratamiento.setText(jo["tratamiento"].toString())
                observaciones.setText(jo["observaciones"].toString())
            },
            Response.ErrorListener { volleyError -> Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show() }){
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val params = HashMap<String, String>()
                    params.put("idConsulta", idConsulta.toString())
                    return params
                }
            }
        queue.add(sr)
    }
}