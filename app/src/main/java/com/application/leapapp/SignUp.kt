package com.application.leapapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.application.leapapp.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_up.*

class SignUp : AppCompatActivity() {

    //private lateinit var edtFirstName: EditText
    //private lateinit var edtLastName: EditText
    //private lateinit var edtEmail: EditText
    //private lateinit var edtPassword: EditText
    //private lateinit var edtConfirmPassword: EditText
    //private lateinit var btnSignUp: Button
    private lateinit var binding: ActivitySignUpBinding
    private lateinit var mAuth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)

        supportActionBar?.hide()


        mAuth = FirebaseAuth.getInstance()

        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSignUp.setOnClickListener {
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()
            val confirmPassword = binding.edtConfirmPassword.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty() && confirmPassword.isNotEmpty()){
                if (password == confirmPassword){

                    mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
                        if (it.isSuccessful){
                            val intent = Intent(this, LogIn::class.java)
                            startActivity(intent)
                        }else{
                            Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
                        }
                    }

                }else{
                    Toast.makeText(this, "Password is not Matched" , Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "Fill up all the empty fields" , Toast.LENGTH_SHORT).show()
            }
        }


/*
        //edtFirstName = findViewById(R.id.edtFirstName)
        //edtLastName = findViewById(R.id.edtLastName)
        edtEmail = findViewById(R.id.edtEmail)
        edtPassword = findViewById(R.id.edtPassword)
        //edtConfirmPassword = findViewById(R.id.edtConfirmPassword)
        btnSignUp = findViewById(R.id.btnSignUp)

        btnSignUp.setOnClickListener {
            val email = edtEmail.text.toString()
            val password = edtPassword.text.toString()

            signUp(email,password)*/


        btnBack.setOnClickListener {
            finish()
        }


    }
    /*private fun signUp(email: String, password: String){

        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(this,"Signing up Unsuccessful",
                        Toast.LENGTH_SHORT).show()
                }
            }

    }*/

}