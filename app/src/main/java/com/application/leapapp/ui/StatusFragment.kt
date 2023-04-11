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
import com.example.searchviewkotlin.LanguageAdapter2
import com.example.searchviewkotlin.LanguageData
import java.util.*

class StatusFragment : Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var searchView: SearchView
    private var mList = ArrayList<LanguageData>()
    private lateinit var adapter: LanguageAdapter2



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.list_view2, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = requireView().findViewById<RecyclerView>(R.id.recyclerView)
        searchView = requireView().findViewById(R.id.searchView)

        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        addDataToList()
        adapter = LanguageAdapter2(mList)
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
        mList.add(LanguageData("realkankanaey", R.drawable.ic_person))
        mList.add(LanguageData("Bantay ti Igorot", R.drawable.ic_person))
        mList.add(LanguageData("ifugao", R.drawable.ic_person))
        mList.add(LanguageData("bontoc main", R.drawable.ic_person))
        mList.add(LanguageData("Kalinga Scripted", R.drawable.ic_person))
        mList.add(LanguageData("Bontoc Mtn Prov", R.drawable.ic_person))
        mList.add(LanguageData("CAR language learner", R.drawable.ic_person))
        mList.add(LanguageData("corndog ti buguias", R.drawable.ic_person))
        mList.add(LanguageData("test1", R.drawable.ic_person))
        mList.add(LanguageData("test2", R.drawable.ic_person))
        mList.add(LanguageData("test3", R.drawable.ic_person))
        mList.add(LanguageData("test4", R.drawable.ic_person))
        mList.add(LanguageData("test5", R.drawable.ic_person))
        mList.add(LanguageData("admin1", R.drawable.ic_person))
    }

}