package com.khalekuzzaman.just.cse.gmailclone.data

class FakeEmail {
    private val str = "We will hold AtCoder Grand Contest 062. This contest counts for GP30 scores.\n" +
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
        fun get():String{
            return str
        }

}