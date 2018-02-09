package com.michel.team.epicture

import android.content.Intent
import android.os.Bundle
import android.os.StrictMode
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.Toast

/**
 * Created by hubert_i on 08/02/2018.
 */
class LoginActivity : AppCompatActivity() {
    private var instagram: Instagram? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        InstagramApiContext.init(this)
        instagram = InstagramApiContext.instagram

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()

        StrictMode.setThreadPolicy(policy)

        val btClickMe = findViewById<Button>(R.id.signInButton)
        btClickMe.setOnClickListener {
            signIn()
        }

        instagram?.prepare()
        if (instagram!!.isLogin) {
            val intentMain = Intent(this@LoginActivity,
                    MainActivity::class.java)
            this@LoginActivity.startActivity(intentMain)
        }

    }

    fun signIn() {
        val login = findViewById<TextInputEditText>(R.id.login_input)
        val password = findViewById<TextInputEditText>(R.id.password_input)

        println("login " + login.text + " & password " + password.text);

        instagram?.username = "epicture42"
        instagram?.password = "epitech42"
        instagram?.prepare()
        if (!instagram!!.isLogin)
            instagram?.login()
        if (instagram!!.isLogin) {
            val intentMain = Intent(this@LoginActivity,
                    MainActivity::class.java)
            this@LoginActivity.startActivity(intentMain)
        }
        else
            Toast.makeText(this,"Invalid user or password" , Toast.LENGTH_LONG).show()
    }
}