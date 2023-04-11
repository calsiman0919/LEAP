package com.application.leapapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.application.leapapp.R
import com.example.searchviewkotlin.LanguageAdapter
import com.example.searchviewkotlin.LanguageData
import java.util.*

class CallFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.list_view, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        searchView = requireView().findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        addDataToList()
        adapter = LanguageAdapter(mList)
        recyclerView.adapter = adapter

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterList(newText)
                return true
            }

        })

    }



    private fun filterList(query: String?) {

        if (query != null) {
            val filteredList = ArrayList<LanguageData>()
            for (i in mList) {
                if (i.title.lowercase(Locale.ROOT).contains(query)) {
                    filteredList.add(i)
                }
            }

            if (filteredList.isEmpty()) {
                Toast.makeText(activity, "No Data found", Toast.LENGTH_SHORT).show()
            } else {
                adapter.setFilteredList(filteredList)
            }
        }
    }

    private fun addDataToList() {
        mList.add(LanguageData("Dixie Normous", R.drawable.ic_person))
        mList.add(LanguageData("Mallik Dattoe", R.drawable.ic_person))
        mList.add(LanguageData("Jane Doe", R.drawable.ic_person))
        mList.add(LanguageData("John Doe", R.drawable.ic_person))
        mList.add(LanguageData("Kalinga Scripted", R.drawable.ic_person))
        mList.add(LanguageData("Bontoc Mtn Prov", R.drawable.ic_person))
        mList.add(LanguageData("Jiminhoo Esteves", R.drawable.ic_person))
        mList.add(LanguageData("Daddy Joed", R.drawable.ic_person))
    }

}