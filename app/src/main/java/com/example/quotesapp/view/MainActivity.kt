package com.example.quotesapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quotesapp.Adapter.AdapterClass
import com.example.quotesapp.Repo.Repository
import com.example.quotesapp.ViewModel.QuoteViewModel
import com.example.quotesapp.ViewModel.QuoteViewModelFactory
import com.example.quotesapp.api.QuoteService
import com.example.quotesapp.api.RetrofitHelper
import com.example.quotesapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var mainViewModel: QuoteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val quoteService = RetrofitHelper.getInstance().create(QuoteService::class.java)
        val repository = Repository(quoteService)
        mainViewModel = ViewModelProvider(this, QuoteViewModelFactory(repository)).get(QuoteViewModel::class.java)

        val adapter = AdapterClass()
        binding.rv.adapter = adapter
        //didnot do pagination for now
        mainViewModel.quotes.observe(this, Observer { quotelist ->
            binding.pagination.visibility = View.INVISIBLE
            adapter.differ.submitList(quotelist.results.toList())
        })
    }
}