package com.example.movieapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var moviewViewModelFactory: MoviewViewModelFactory
    lateinit var recyclerView: RecyclerView
    lateinit var viewModel: MovieViewModel
    lateinit var adapter: MovieAdapter
    lateinit var searchView: SearchView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as MovieApplication).component.inject(this)
        adapter = MovieAdapter()
        viewModel = ViewModelProvider(this, moviewViewModelFactory).get(MovieViewModel::class.java)

        searchView = findViewById(R.id.movieSearch)
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.adapter=adapter.withLoadStateHeaderAndFooter(
            header = LoadingAdapter(),
            footer = LoadingAdapter()
        )
        observeData()

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query?.length != 0) {
                    filterMovies(query)
                } else {
                    observeData()
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText?.length != 0) {
                    filterMovies(newText)
                } else {
                    observeData()
                }
                return true
            }
        })

    }

    private fun filterMovies(query: String?) {
        // Update the PagingData with a new query
        lifecycleScope.launch {
            adapter.filter(query)
        }
    }

    private fun observeData() {
        viewModel.list.observe(this, {
            adapter.submitData(lifecycle, it)
        })
    }

}