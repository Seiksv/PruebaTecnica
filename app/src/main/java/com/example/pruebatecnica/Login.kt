package com.example.pruebatecnica

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pruebatecnica.databinding.ActivityLoginBinding


class Login : AppCompatActivity() {
    private var binding: ActivityLoginBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view: View = binding!!.root
        setContentView(view)

        binding!!.btnLogin.setOnClickListener {
            if (binding!!.usernameTF.text.toString()
                    .equals("admin") && binding!!.passwordTF.text.toString().equals("admin")
            ) {
                val i = Intent(this, MainActivity::class.java)
                startActivity(i)
            } else {
                val toast =
                    Toast.makeText(this, "Error de credenciales", Toast.LENGTH_SHORT) // in Activity
                toast.show()
            }
        }
    }
}