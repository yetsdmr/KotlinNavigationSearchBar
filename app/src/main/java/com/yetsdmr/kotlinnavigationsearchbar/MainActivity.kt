package com.yetsdmr.kotlinnavigationsearchbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var adapter : ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.countries_array))
        lvListView.adapter = adapter
        lvListView.setOnItemClickListener { adapterView, view, position, id ->
            Toast.makeText(applicationContext,adapterView?.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show()
        }
        lvListView.emptyView = tvEmptyTextView
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)

        val search = menu?.findItem(R.id.nav_search)
        val searchView = search?.actionView as SearchView
        searchView.queryHint = "Search something!"

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                adapter.filter.filter(p0)
                return true
            }
        })
        return super.onCreateOptionsMenu(menu)
    }
}