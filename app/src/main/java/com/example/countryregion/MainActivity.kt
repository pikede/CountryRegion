package com.example.countryregion

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countryregion.databinding.ActivityMainBinding
import com.example.countryregion.models.CountryResponse

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var countryAdapter: CountryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initUI()

        with(viewModel) {
            countries.observe(this@MainActivity, countriesObserver)
            errorMessage.observe(this@MainActivity, errorMessageObserver)
            getCountries()
        }
    }

    private fun initUI() {
        binding.rvCountries.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            countryAdapter = CountryAdapter()
            adapter = countryAdapter
        }
    }

    private val countriesObserver = Observer<List<CountryResponse>> {
        hideProgressBar()
        showToastLong(getString(R.string.showing_countries))
        countryAdapter.updateCountryDataset(it)
    }

    private val errorMessageObserver = Observer<String> {
        showToastLong(it)
    }

    private fun showToastLong(text: String) {
        hideProgressBar()
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_LONG).show()
    }

    private fun hideProgressBar() {
        binding.lytProgressBar.visibility = View.GONE
    }
}