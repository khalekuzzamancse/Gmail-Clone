package com.khalekuzzaman.just.cse.gmailclone.utils

class TextFinder {
    fun findUrls(email: String): List<Pair<Int, Int>> {
        val urlRegex =
            "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
        val urlIndices = mutableListOf<Pair<Int, Int>>()

        urlRegex.findAll(email).forEach { matchResult ->
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last
            urlIndices.add(startIndex to endIndex)
        }

        return urlIndices
    }

    fun findText(text: String, key: String): List<Pair<Int, Int>> {

        val originalText=text.lowercase()
        val searchText=key.lowercase().toRegex()
        val indices = mutableListOf<Pair<Int, Int>>()

        searchText.findAll(originalText).forEach { matchResult ->
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last
            indices.add(startIndex to endIndex)
        }
        return indices
    }
}