package com.khalekuzzaman.just.cse.gmailclone.data

import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel

class FakeEmailList {
    fun getFakeEmails(): List<EmailModel> {
        val list: MutableList<EmailModel> = mutableListOf()
        for (i in 1..10) {
            val email = EmailModel(
                emailid = i,
                userName = "Md Khalekuzzaman : $i",
                subject = "This the subjct of the email,that will be used for testing purpose",
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