package com.example.mihce

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_home.*
import org.json.JSONObject

class Home : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        var idUsuario = ""
        val bundle = intent.extras
        val usuario = bundle?.getString("usuario")

        if (bundle?.getString("idUsuario").isNullOrEmpty()){
            val url = Constant.URL_SERVER + "usuarioGetIdUsuario.php"
            val queue = Volley.newRequestQueue(this)
            val sr: StringRequest = object : StringRequest(
                Method.POST, url,
                Response.Listener { response ->
                    val jo = JSONObject(response)
                    idUsuario = jo["id_usuario"].toString()
                    //Toast.makeText(this,"IDUSUARIO: "+idUsuario, Toast.LENGTH_LONG).show()
                },
                Response.ErrorListener { error ->
                    Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                }) {
                @Throws(AuthFailureError::class)
                override fun getParams(): Map<String, String> {
                    val parametros: MutableMap<String, String> = HashMap()
                    parametros["mail"] = usuario.toString()
                    return parametros
                }
            }
            queue.add(sr)
        }else
            idUsuario = bundle?.getString("idUsuario").toString()

        //Menu Buttons
        btn_datos.setOnClickListener {
            val goMisDatos = Intent(this, MisDatos::class.java)
            goMisDatos.putExtra("idUsuario", idUsuario)
            startActivity(goMisDatos)
        }
        btn_mihce.setOnClickListener {
            val goMiHCE = Intent(this, MiHCE::class.java)
            goMiHCE.putExtra("idUsuario", idUsuario)
            startActivity(goMiHCE)
        }
        btn_farmacos.setOnClickListener {
            val goFarmacos = Intent(this, Farmacos::class.java)
            goFarmacos.putExtra("idUsuario", idUsuario)
            startActivity(goFarmacos)
        }
    }
}