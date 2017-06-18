package com.jameskbride

import com.jameskbride.search.cardinal.HorizontalSearch
import com.jameskbride.search.cardinal.VerticalSearch
import com.jameskbride.search.diagonal.DiagonalAscendingSearch
import com.jameskbride.search.diagonal.DiagonalDescendingSearch

class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        output.addAll(words.flatMap { word -> HorizontalSearch(word, puzzle).execute() })
        output.addAll(words.flatMap { word -> VerticalSearch(word, puzzle).execute() })
        output.addAll(words.flatMap { word -> DiagonalDescendingSearch(word, puzzle).execute() })
        output.addAll(words.flatMap { word -> DiagonalAscendingSearch(word, puzzle).execute() })

        return output.filterNotNull()
    }
}

