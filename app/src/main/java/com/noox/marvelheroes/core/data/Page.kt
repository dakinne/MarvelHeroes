package com.noox.marvelheroes.core.data

const val PAGE_LIMIT = 100

data class Page<T>(
    val items: List<T> = emptyList(),
    val currentPage: Int,
    val totalPages: Int
) {
    val isEmpty = items.isEmpty()
    val isFirstPage = currentPage == 0
    val hasMorePages = currentPage + 1 < totalPages
}
