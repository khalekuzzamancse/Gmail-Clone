package com.khalekuzzaman.just.cse.gmailclone.ui.common

data class EmailModel(
    val emailId: Int,
    val userName: String,
    val subject: String,
    val message: String,
    val timeOrDate: String,
    val profileImageId: Int,
    val isBookMarked: Boolean,
    val sender: String,
    val receiver: String,
)