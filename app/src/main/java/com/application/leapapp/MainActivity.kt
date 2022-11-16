package com.application.leapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true





    }

    fun button(view: View) {
        mAuth.signOut()
        val intent = Intent(this, LogIn::class.java)
        startActivity(intent)
        finish()
        Toast.makeText(this, "Logout Successful" , Toast.LENGTH_SHORT).show()
    }

}