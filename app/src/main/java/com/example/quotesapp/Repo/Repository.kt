package com.example.quotesapp.Repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quotesapp.Model.QuoteList
import com.example.quotesapp.api.QuoteService

class Repository(private val quoteService: QuoteService) {
    val quoteLiveData= MutableLiveData<QuoteList>()
    val quotes:LiveData<QuoteList>
    get()=quoteLiveData
    suspend fun getQuotes(page:Int) {
        val result = quoteService.getQuotes(page)
        if(result?.body() != null)
        {
            quoteLiveData.postValue(result.body())
        }
    }
}