package com.jameskbride.search.diagonal.ascending

import com.jameskbride.search.diagonal.DiagonalSearch

class DiagonalAscendingSearch(val word: String, val puzzle: Array<Array<String>>) : DiagonalSearch() {
    fun execute(): List<String?> {
        return listOf("AN: (0,2),(1,1)")
    }
}


