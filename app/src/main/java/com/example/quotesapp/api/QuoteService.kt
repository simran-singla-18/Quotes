package com.example.quotesapp.api

import com.example.quotesapp.Model.QuoteList
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Response


interface QuoteService {
    @GET("/quotes")
    suspend fun getQuotes(@Query ("page")page:Int):Response<QuoteList>
}