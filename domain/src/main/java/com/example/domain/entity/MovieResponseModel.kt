package com.example.domain.entity

data class MovieResponseModel(
    val page: Int,
    val results: List<Movie>,
    val total_pages: Int,
    val total_results: Int
)