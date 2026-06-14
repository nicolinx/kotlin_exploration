package com.example.bookshelfapp.network

import com.example.bookshelfapp.model.Book
import com.example.bookshelfapp.model.BooksListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BooksApiService {
    @GET("v1/volumes")
    suspend fun getBooks(
        @Query("q") query: String,
        @Query("key") key: String
    ): BooksListResponse

    @GET("v1/volumes/{bookId}")
    suspend fun getBookDetail(
        @Query("key") key: String,
        @Path("bookId") id: String,
    ): Book
}