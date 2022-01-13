package com.example.quotesapp.ViewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quotesapp.Model.QuoteList
import com.example.quotesapp.Repo.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class QuoteViewModel(private val quoteRepository: Repository) :ViewModel(){

    init {
        viewModelScope.launch(Dispatchers.IO) {
            quoteRepository.getQuotes(page = 1)
        }

    }
    val quotes:LiveData<QuoteList>
    get() = quoteRepository.quotes

}