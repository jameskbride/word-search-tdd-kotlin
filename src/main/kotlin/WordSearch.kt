class WordSearch(val words: List<String>, val puzzle: Array<Array<String>>) {
    fun search(): List<String> {
        val output: MutableList<String?> = mutableListOf()

        output.addAll(words.flatMap {  word -> HorizontalSearch(word, puzzle).execute()})
        output.addAll(words.flatMap {  word -> VerticalSearch(word, puzzle).execute()})

        return output.filterNotNull()
    }
}