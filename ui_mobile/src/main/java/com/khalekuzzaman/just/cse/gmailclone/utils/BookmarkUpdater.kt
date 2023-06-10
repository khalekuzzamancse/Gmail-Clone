package com.khalekuzzaman.just.cse.gmailclone.utils

import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel


class BookmarkUpdater(private val emails: List<EmailModel>) {
    private val updatedEmailList: MutableList<EmailModel> = emails.toMutableList()

    fun update(emailId: Int): List<EmailModel> {
        val position = getEmailPositionById(emailId)
        val email = getEmailByPosition(position)
        val updatedEmail = email.copy(
            isBookMarked = updateBookmark(oldBookmark = email.isBookMarked)
        )
        updateEmailAtPosition(updatedEmail, position)
        return updatedEmailList
    }

    private fun getEmailPositionById(emailId: Int): Int {
        return emails.indexOfFirst { it.emailId == emailId }
    }

    private fun updateEmailAtPosition(updatedEmail: EmailModel, position: Int) {
        updatedEmailList[position] = updatedEmail
    }

    private fun getEmailByPosition(position: Int): EmailModel {
        return updatedEmailList[position]
    }

    private fun updateBookmark(oldBookmark: Boolean): Boolean {
        return !oldBookmark
    }
}