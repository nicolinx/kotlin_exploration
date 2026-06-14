package com.example.bookshelfapp.network

import com.example.bookshelfapp.BuildConfig
import com.example.bookshelfapp.model.Book

interface BooksRepository {
    suspend fun getBooks(query: String): List<Book>
    suspend fun getBookDetail(id: String): Book
}

class NetworkBooksRepository(
    private val apiService: BooksApiService
) : BooksRepository {
    override suspend fun getBooks(query: String): List<Book> {
        return apiService.getBooks(query = query, key = BuildConfig.API_KEY).items
    }

    override suspend fun getBookDetail(id: String): Book {
        return apiService.getBookDetail(id = id, key = BuildConfig.API_KEY)
    }
}