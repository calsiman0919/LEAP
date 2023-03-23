package com.application.leapapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.application.leapapp.adapter.ViewPagerAdapter
import com.application.leapapp.databinding.ActivityMainBinding
import com.application.leapapp.ui.CallFragment
import com.application.leapapp.ui.ChatFragment
import com.application.leapapp.ui.StatusFragment
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*


class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var rvUserList: RecyclerView
    private lateinit var userList: ArrayList<UserList>
    private lateinit var adapter: UserAdapter
    private lateinit var mDbRef: DatabaseReference
    private var binding: ActivityMainBinding? = null



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding!!.root)

        val fragmentArrayList = ArrayList<Fragment>()

        fragmentArrayList.add(ChatFragment())
        fragmentArrayList.add(StatusFragment())
        fragmentArrayList.add(CallFragment())

        val adapter = ViewPagerAdapter(this, supportFragmentManager, fragmentArrayList)

        binding!!.viewPager.adapter = adapter

        binding!!.Tabs.setupWithViewPager(binding!!.viewPager)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.miChat){
            val intent = Intent(this, ChatUser::class.java)
            startActivity(intent)
            return true

        } else if (item.itemId == R.id.miLogout){
            mAuth.signOut()
            val intent = Intent(this, LogIn::class.java)
            startActivity(intent)
            finish()
            Toast.makeText(this, "Logout Successful" , Toast.LENGTH_SHORT).show()
            return true
        }

        return true
    }
}