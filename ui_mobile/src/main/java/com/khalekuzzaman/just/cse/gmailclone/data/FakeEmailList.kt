package com.khalekuzzaman.just.cse.gmailclone.data

import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel

object FakeEmailList {
    fun getFakeEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailId = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "All:,This the subject field",
                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                        " and the other tool.",
                isBookMarked = false,
                timeOrDate = "13-03-23",
                profileImageId = R.drawable.profile_image
            )
            list.add(email)
        }
        return list
    }
    fun getFakePrimaryEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailId = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "Primary:,This the subject field",
                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                        " and the other tool.",
                isBookMarked = false,
                timeOrDate = "13-03-23",
                profileImageId = R.drawable.profile_image
            )
            list.add(email)
        }
        return list
    }
    fun getFakePromotionEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailId = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "Promotion:,This the subject field",
                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                        " and the other tool.",
                isBookMarked = false,
                timeOrDate = "13-03-23",
                profileImageId = R.drawable.profile_image
            )
            list.add(email)
        }
        return list
    }
    fun getFakeSocialEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailId = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "Social:,This the subject field",
                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                        " and the other tool.",
                isBookMarked = false,
                timeOrDate = "13-03-23",
                profileImageId = R.drawable.profile_image
            )
            list.add(email)
        }
        return list
    }
    fun getFakeUpdateEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailId = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "Update:,This the subject field",
                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                        " and the other tool.",
                isBookMarked = false,
                timeOrDate = "13-03-23",
                profileImageId = R.drawable.profile_image
            )
            list.add(email)
        }
        return list
    }
}