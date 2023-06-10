package com.khalekuzzaman.just.cse.gmailclone.data

import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel


object FakeEmailList {
    fun getFakeEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = FakeEmail.email.copy(emailId = i)
            list.add(email)
        }
        return list
    }

    fun getFakePrimaryEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = FakeEmail.email.copy(emailId = i)
            list.add(email)
        }
        return list
    }

    fun getFakePromotionEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = FakeEmail.email.copy(emailId = i)
            list.add(email)
        }
        return list
    }

    fun getFakeSocialEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = FakeEmail.email.copy(emailId = i)
            list.add(email)
        }
        return list
    }

    fun getFakeUpdateEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = FakeEmail.email.copy(emailId = i)
            list.add(email)
        }
        return list
    }
}