package com.khalekuzzaman.just.cse.gmailclone.utils

class UrlFinder {
    fun findUrls(email: String): List<Pair<Int, Int>> {
        val urlRegex = "\\b(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]".toRegex()
        val urlIndices = mutableListOf<Pair<Int, Int>>()

        urlRegex.findAll(email).forEach { matchResult ->
            val startIndex = matchResult.range.first
            val endIndex = matchResult.range.last
            urlIndices.add(startIndex to endIndex)
        }

        return urlIndices
    }
}