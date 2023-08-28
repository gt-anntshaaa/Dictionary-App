package com.application.dictionaryapp.ui.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import com.application.dictionaryapp.R
import com.application.dictionaryapp.databinding.ActivityHomeBinding
import com.application.dictionaryapp.ui.home.adapter.PhoneticAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var phoneticAdapter: PhoneticAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSearchView()
        setupRecyclerPhonetic()
        setupRecyclerMeanings()
    }

    private fun setupRecyclerPhonetic() {
        phoneticAdapter = PhoneticAdapter()
    }

    private fun setupRecyclerMeanings() {
        TODO("Not yet implemented")
    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {

            }

            override fun onQueryTextChange(newText: String?): Boolean {
                TODO("Not yet implemented")
            }

        })
    }
}