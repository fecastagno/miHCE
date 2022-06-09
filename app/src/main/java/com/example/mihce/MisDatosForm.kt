package com.example.mihce

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_mis_datos_form.*

class MisDatosForm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mis_datos_form)

        val bundle = intent.extras
        val idUsuario = bundle?.getString("idUsuario")

        //Mis Datos Button
        btn_misdatos.setOnClickListener {

            val RBS: Int = MD_sexo.checkedRadioButtonId
            val RBF: Int = MD_fuma.checkedRadioButtonId
            val RBB: Int = MD_bebeAlcohol.checkedRadioButtonId

            val RBSexo = findViewById<View>(RBS) as RadioButton
            val RBFuma = findViewById<View>(RBF) as RadioButton
            val RBBebe = findViewById<View>(RBB) as RadioButton

            val RBStringSexo = RBSexo.getText()
            val RBStringFuma = RBFuma.getText()
            val RBStringBebe = RBBebe.getText()

            //Validation
            if (MD_dni.text.isEmpty() || MD_fechaNacimiento.text.isEmpty() ||
                MD_edad.text.isEmpty() || MD_direccion.text.isEmpty() || MD_obraSocial.text.isEmpty() ||
                MD_peso.text.isEmpty() || MD_altura.text.isEmpty() || MD_nacionalidad.text.isEmpty() ||
                MD_ocupacion.text.isEmpty() || MD_estadoCivil.text.isEmpty())

                Toast.makeText(applicationContext,"Debe completar todos los campos",Toast.LENGTH_LONG).show()
            else {
                val dni: String = MD_dni.text.toString()
                val sexo = if(RBStringSexo.toString() == "Masculino") "1" else "0"
                val fechaNacimiento: String = MD_fechaNacimiento.text.toString()
                val edad: String = MD_edad.text.toString()
                val direccion: String = MD_direccion.text.toString()
                val obraSocial: String = MD_obraSocial.text.toString()
                val peso: String = MD_peso.text.toString()
                val altura: String = MD_altura.text.toString()
                val grupoSanguineo: String = MD_grupoSanguineo.text.toString()
                val fuma = if(RBStringFuma.toString() == "Sí") "1" else "0"
                val bebeAlcohol = if(RBStringBebe.toString() == "Sí") "1" else "0"
                val alergia: String = MD_alergia.text.toString()
                val medCronica: String = MD_medicacionCronica.text.toString()
                val nacionalidad: String = MD_nacionalidad.text.toString()
                val ocupacion: String = MD_ocupacion.text.toString()
                val estadoCivil: String = MD_estadoCivil.text.toString()

                val queue = Volley.newRequestQueue(this)
                val url = Constant.URL_SERVER + "insert_misdatos.php"

                //Insert in DB
                //Volley string request
                val sr = object : StringRequest(
                    Method.POST, url,
                    Response.Listener<String> { response ->
                        if (!response.isEmpty()) {
                            //Toast.makeText(applicationContext,"Response: "+ response.toString(),Toast.LENGTH_LONG).show()
                        }
                    },
                    object : Response.ErrorListener {
                        override fun onErrorResponse(volleyError: VolleyError) {
                            Toast.makeText(applicationContext,volleyError.message,Toast.LENGTH_LONG).show()
                        }
                    }) {
                    @Throws(AuthFailureError::class)
                    override fun getParams(): Map<String, String> {
                        val params = HashMap<String, String>()
                        params.put("idUsuario", idUsuario.toString())
                        params.put("dni", dni)
                        params.put("sexo", sexo)
                        params.put("fechaNacimiento", fechaNacimiento)
                        params.put("edad", edad)
                        params.put("direccion", direccion)
                        params.put("obraSocial", obraSocial)
                        params.put("peso", peso)
                        params.put("altura", altura)
                        params.put("grupoSanguineo", grupoSanguineo)
                        params.put("fuma", fuma)
                        params.put("bebeAlcohol", bebeAlcohol)
                        params.put("alergia", alergia)
                        params.put("medicacionCronica", medCronica)
                        params.put("nacionalidad", nacionalidad)
                        params.put("ocupacion", ocupacion)
                        params.put("estadoCivil", estadoCivil)
                        return params
                    }
                }
                //Adding request to queue
                queue.add(sr)
                Toast.makeText(applicationContext, "Datos Cargados!",Toast.LENGTH_LONG).show()
                val goMisDatos = Intent(this, MisDatos::class.java)

                goMisDatos.putExtra("idUsuario", idUsuario)
                startActivity(goMisDatos)
            }
        }
        //end listener
    }
}