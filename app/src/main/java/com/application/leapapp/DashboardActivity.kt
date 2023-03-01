package com.application.leapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference

class DashboardActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var rvUserList: RecyclerView
    private lateinit var userList: ArrayList<UserList>
    private lateinit var adapter: UserAdapter
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miLogout){
            mAuth.signOut()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout Successful" , Toast.LENGTH_SHORT).show()
            return true
        }

        fun button(view: View) {
            mAuth.signOut()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout Successful" , Toast.LENGTH_SHORT).show()
        }

        if (item.itemId == R.id.miChat){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }


        return true
    }

}