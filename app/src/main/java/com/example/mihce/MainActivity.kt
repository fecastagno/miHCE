package com.example.mihce


import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Registration Button
        registration.setOnClickListener {
            val goRegist = Intent(this, UserRegistration::class.java)
            startActivity(goRegist)
        }

        //Login Validation
        login.setOnClickListener {
            val mail: String = email.text.toString()
            val pass: String = password.text.toString()

            if (mail.isEmpty() || pass.isEmpty())
                Toast.makeText(
                    applicationContext,
                    "Debe completar todos los campos",
                    Toast.LENGTH_LONG
                ).show()
            else {
                val url = Constant.URL_SERVER + "validar_usuario.php"
                val queue = Volley.newRequestQueue(this)

                val sr: StringRequest = object : StringRequest(
                    Method.POST,
                    url,
                    Response.Listener { response ->
                        if (!response.isEmpty()) {
                            val jo = JSONObject(response)
                            if (jo["password"] == pass){
                                Toast.makeText(applicationContext, "Ingreso con éxito!", Toast.LENGTH_LONG).show()
                                //redireccionar al Home
                                val goHome = Intent(this, Home::class.java)
                                goHome.putExtra("usuario", mail)
                                startActivity(goHome)
                            }else{
                                Toast.makeText(this,"Password erróneo, intente nuevamente",Toast.LENGTH_LONG).show()
                            }
                        } else {
                            Toast.makeText(this,"Datos erróneos, pruebe nuevamente",Toast.LENGTH_LONG).show()
                        }
                    },
                    Response.ErrorListener { error ->
                        Toast.makeText(this, error.toString(), Toast.LENGTH_LONG).show()
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val parametros: MutableMap<String, String> =
                            HashMap()
                        parametros["mail"] = mail
                        return parametros
                    }
                }
                queue.add(sr)
                //END
            }
        }
    }
}