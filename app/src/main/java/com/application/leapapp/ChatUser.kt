package com.application.leapapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_dasboard.*


class ChatUser : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var rvUserList: RecyclerView
    private lateinit var userList: ArrayList<UserList>
    private lateinit var adapter: UserAdapter
    private lateinit var mDbRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dasboard)

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
}