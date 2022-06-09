val queue = Volley.newRequestQueue(this)
val jsonParams = JSONObject()

try {
    jsonParams.put("usuario", mail)
} catch (e: Exception) {
    e.printStackTrace()
}

val url = "http://192.168.0.179/mihce/login_user.php"
val jor = JsonObjectRequest(
    Request.Method.POST,
    url,
    jsonParams,
    Response.Listener { response ->
        val test = response.toString()
        Toast.makeText(applicationContext, test, Toast.LENGTH_SHORT).show()
        //username.setText(""+response["id_usuario"])
        if (response["password"] == pass)
            username.setText("SI ESTA Bien")
            username.setText(""+jsonParams)
    },
    Response.ErrorListener { volleyError : VolleyError ->
        Toast.makeText(
            applicationContext,
            "La comunicación falló " + volleyError.message,
            Toast.LENGTH_SHORT
        ).show()
        username.setText(volleyError.message)
        username.setText(""+jsonParams)
    }
)
queue.add(jor)