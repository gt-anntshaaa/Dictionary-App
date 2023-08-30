package com.application.dictionaryapp.ui.home

import android.annotation.SuppressLint
import android.media.AudioManager
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.application.dictionaryapp.R
import com.application.dictionaryapp.databinding.ActivityHomeBinding
import com.application.dictionaryapp.helper.Resource
import com.application.dictionaryapp.networks.DictionaryResponse
import com.application.dictionaryapp.ui.home.adapter.MeaningAdapter
import com.application.dictionaryapp.ui.home.adapter.PhoneticAdapter
import dagger.hilt.android.AndroidEntryPoint

@Suppress("UNCHECKED_CAST")
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var phoneticAdapter: PhoneticAdapter
    private lateinit var meaningAdapter: MeaningAdapter

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSearchView()
        setupRecyclerPhonetic()
        setupRecyclerMeanings()

        viewModel.result.observe(this, Observer { resource ->
            when (resource){
                is Resource.Loading -> {}
                is Resource.Success -> {

                    val phonetic = resource.data.flatMap {
                        it.phonetics
                    }

                    val meaning = resource.data.flatMap {
                        it.meanings
                    }

                    // menampilkan data phonetic
                    phoneticAdapter.submit(phonetic)
                    meaningAdapter.submit(meaning)
                }
                is Resource.Failure -> {
                    Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun setupRecyclerPhonetic() {
        binding.recyclerPhonetic.layoutManager = GridLayoutManager(this, 1)
        phoneticAdapter = PhoneticAdapter(object : OnListener(){
            @SuppressLint("LongLogTag")
            override fun onSound(
                phonetic: DictionaryResponse.DictionaryResponseItem.Phonetic
            ) {
                // sound on
                val mediaPlayer = MediaPlayer()
                mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

                try {
                    mediaPlayer.setDataSource("https:" + phonetic.audio)
                    mediaPlayer.setOnPreparedListener {
                        mediaPlayer.start() // Panggil start() setelah MediaPlayer siap
                    }
                    mediaPlayer.setOnErrorListener { _, _, _ ->
                        Toast.makeText(this@HomeActivity, "Error playing audio", Toast.LENGTH_SHORT).show()
                        false
                    }
                    mediaPlayer.prepareAsync() // Gunakan prepareAsync() untuk operasi asinkron
                    Toast.makeText(this@HomeActivity, "ON", Toast.LENGTH_SHORT).show()
                } catch (e: Exception) {
                   // Log.e(TAG, "onSound: ${e.message}")
                    Toast.makeText(this@HomeActivity, e.message, Toast.LENGTH_SHORT).show()
                }

            }


        })

        binding.recyclerPhonetic.adapter = phoneticAdapter
    }

    private fun setupRecyclerMeanings() {
        binding.recyclerMeanings.layoutManager = GridLayoutManager(this, 1)
        meaningAdapter = MeaningAdapter()

        binding.recyclerMeanings.adapter = meaningAdapter

    }

    private fun setupSearchView() {
        binding.searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    viewModel.fetchResult(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    companion object{
        private const val TAG = "HomeActivity::class.java"
    }
}