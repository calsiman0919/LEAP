package com.application.leapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var rvUserList: RecyclerView
    private lateinit var userList: ArrayList<UserList>
    private lateinit var adapter: UserAdapter
    private lateinit var mDbRef: DatabaseReference



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        mDbRef = FirebaseDatabase.getInstance().getReference()

        userList = ArrayList()
        adapter = UserAdapter(this, userList)

        rvUserList = findViewById(R.id.rvUserlist)

        rvUserlist.layoutManager = LinearLayoutManager (this)
        rvUserList.adapter = adapter

        mDbRef.child("user").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                userList.clear()
                for (postSnapshopt in snapshot.children){

                    val currentUser = postSnapshopt.getValue(UserList::class.java)

                    if(mAuth.currentUser?.uid != currentUser?.uid){
                        userList.add(currentUser!!)
                    }

                }

                adapter.notifyDataSetChanged()

            }

            override fun onCancelled(error: DatabaseError) {

            }

        })


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
/*
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
*/

        return true
    }
}