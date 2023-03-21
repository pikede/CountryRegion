package com.example.countryregion

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import com.example.countryregion.databinding.CountryItemBinding
import com.example.countryregion.models.CountryResponse
import kotlin.random.Random

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {

    private var countryDataset = ArrayList<CountryResponse>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = CountryItemBinding.inflate(layoutInflater, parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countryDataset[position])
        (holder.itemView as LinearLayout).setBackgroundColor(randomColor())
    }

    override fun getItemCount(): Int = countryDataset.size

    fun updateCountryDataset(newDataset: List<CountryResponse>) {
        countryDataset.clear()
        countryDataset.addAll(newDataset)
        notifyDataSetChanged()
    }

    // Used to show different countries as all white is confusing
    private fun randomColor() = Color.rgb(
        Random.nextInt(100) + 45,
        Random.nextInt(150) + 44,
        Random.nextInt(1650) + 30
    )

    class CountryViewHolder(private val itemViewBinding: CountryItemBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(country: CountryResponse) {
            with(country) {
                name?.let { itemViewBinding.tvName.text = "$it, " }
                region?.let { itemViewBinding.tvRegion.text = it }
                code?.let { itemViewBinding.tvCode.text = it }
                capital?.let { itemViewBinding.tvCapital.text = it }
            }
        }
    }
}