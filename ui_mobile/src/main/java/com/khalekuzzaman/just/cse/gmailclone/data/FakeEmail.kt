package com.khalekuzzaman.just.cse.gmailclone.data

import com.khalekuzzaman.just.cse.gmailclone.R
import com.khalekuzzaman.just.cse.gmailclone.ui.common.EmailModel
import kotlin.random.Random


object FakeEmail {
    private val message =
        "We will hold AtCoder Grand Contest 062. This contest counts for GP30 scores.\n" +
                "\n" +
                "    Contest URL: https://atcoder.jp/contests/agc062\n" +
                "    Start Time: http://www.timeanddate.com/worldclock/fixedtime.html?iso=20230521T2100&p1=248\n" +
                "    Duration: 180 minutes\n" +
                "    Number of Tasks: 6\n" +
                "    Writer: chinerist\n" +
                "    Tester: maspy, IH19980412\n" +
                "    Rated range: 1200 ~\n" +
                "\n" +
                "The point values will be 400-700-800-1100-1300-2000.\n" +
                "\n" +
                "We are looking forward to your participation!\n" +
                "\n" +
                "- How to Unsubscribe Email Newsletters\n" +
                "1: First, please sign in => https://atcoder.jp/login\n" +
                "2: Clear all \"Notification settings\" from => https://atcoder.jp/settings\n" +
                "-----\n" +
                "AtCoder Development Team\n" +
                "https://atcoder.jp/"
    private val subject = "A reminder about contest from at coder team."
    private val userName = "AtCoder Development Team"
    private val sender = "kbul90@gmail.com"
    private val time = "10 days ago"
    private val reciver = "abul90@gmail.com"

    val email = EmailModel(
        emailId = Random.nextInt(),
        subject = subject,
        message = message,
        sender = sender,
        receiver = reciver,
        timeOrDate = time,
        profileImageId = R.drawable.profileimage3,
        isBookMarked = false,
        userName = userName
    )



}