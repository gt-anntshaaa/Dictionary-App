package com.application.dictionaryapp.ui.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.dictionaryapp.databinding.ItemMeaningsBinding
import com.application.dictionaryapp.networks.DictionaryResponse

class MeaningAdapter : RecyclerView.Adapter<MeaningAdapter.MeaningViewHolder>() {
    private val listMeaning: MutableList<DictionaryResponse.DictionaryResponseItem.Meaning> = mutableListOf()

    inner class MeaningViewHolder(private val binding: ItemMeaningsBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(meaning: DictionaryResponse.DictionaryResponseItem.Meaning){
            binding.tvPartOfSpeach.text = meaning.partOfSpeech
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeaningViewHolder {
        return MeaningViewHolder(
            ItemMeaningsBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return listMeaning.size
    }

    override fun onBindViewHolder(holder: MeaningViewHolder, position: Int) {
        holder.bind(listMeaning[position])
    }
}