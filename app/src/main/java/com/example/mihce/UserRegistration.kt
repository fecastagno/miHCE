package com.example.mihce

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_user_registration.*
import org.json.JSONException
import org.json.JSONObject

class UserRegistration : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_registration)

        //Action Button
        btn_registration.setOnClickListener {

            val nombre: String = nombre.text.toString()
            val apellido: String = apellido.text.toString()
            val mail: String = email.text.toString()
            val pass: String = password.text.toString()

            //Validation
            if (nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || pass.isEmpty())
                Toast.makeText(applicationContext, "Debe completar todos los campos", Toast.LENGTH_LONG).show()
            else{
                val queue = Volley.newRequestQueue(this)
                val url = Constant.URL_SERVER + "insert_user.php"

                //Volley string request
                val sr = object : StringRequest(Request.Method.POST, url,
                    Response.Listener<String> { response ->
                        try {
                            val obj = JSONObject(response)
                            Toast.makeText(applicationContext, obj.getString("message"), Toast.LENGTH_LONG).show()
                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    },
                    object : Response.ErrorListener {
                        override fun onErrorResponse(volleyError: VolleyError) {
                            Toast.makeText(applicationContext, volleyError.message, Toast.LENGTH_LONG).show()
                        }
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params.put("nombre", nombre)
                        params.put("apellido", apellido)
                        params.put("mail", mail)
                        params.put("pass", pass)
                        return params
                    }
                }
                //Adding request to queue
                queue.add(sr)
                Toast.makeText(applicationContext, "Registro con éxito!", Toast.LENGTH_LONG).show()

                //Redirection
                val goHome = Intent(this, Home::class.java)
                goHome.putExtra("usuario", mail)
                startActivity(goHome)
            }
        }

    }
}