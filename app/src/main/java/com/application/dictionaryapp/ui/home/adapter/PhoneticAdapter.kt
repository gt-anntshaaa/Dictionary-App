package com.application.dictionaryapp.ui.home.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.application.dictionaryapp.databinding.ItemPhoneticBinding
import com.application.dictionaryapp.networks.DictionaryResponse
import com.application.dictionaryapp.ui.home.OnListener

class PhoneticAdapter(private val listener: OnListener) : RecyclerView.Adapter<PhoneticAdapter.PhoneticViewHolder>() {

    private val listPhonetic: MutableList<DictionaryResponse.DictionaryResponseItem.Phonetic> = mutableListOf()

    inner class PhoneticViewHolder(private val binding: ItemPhoneticBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(phonetic: DictionaryResponse.DictionaryResponseItem.Phonetic){
            binding.tvPhonetic.text = phonetic.text
            binding.imageButtonAudio.setOnClickListener {
                listener.onSound(phonetic)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhoneticViewHolder {
        return PhoneticViewHolder(ItemPhoneticBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        ))
    }

    override fun getItemCount(): Int {
        return listPhonetic.size
    }

    override fun onBindViewHolder(holder: PhoneticViewHolder, position: Int) {
        holder.bind(listPhonetic[position])
    }

    @SuppressLint("NotifyDataSetChanged")
    fun submit(data : List<DictionaryResponse.DictionaryResponseItem.Phonetic>){
        listPhonetic.apply {
            clear()
            addAll(data)
            notifyDataSetChanged()
        }
    }




}