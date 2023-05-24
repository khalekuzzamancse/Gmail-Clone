package com.khalekuzzaman.just.cse.gmailclone

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.khalekuzzaman.just.cse.gmailclone.ui.theme.GmailCloneTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GmailCloneTheme {
                Column {
                    var emails by remember {
                        mutableStateOf(listOf(
                            EmailModel(
                                emailid = 1,
                                userName = "Abdur Razzak",
                                subject = "This the subjct of the email,that will be used for testing purpose",
                                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                                        " and the other tool.",
                                isBookMarked = false,
                                timeOrDate = "13-03-23",
                                profileImageId = R.drawable.profile_image
                            ),
                            EmailModel(
                                emailid = 2,
                                userName = "Md Khalekuzzaman",
                                subject = "This the subjct of the email,that will be used for testing purpose",
                                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
                                        " and the other tool.",
                                isBookMarked = false,
                                timeOrDate = "13-03-23",
                                profileImageId = R.drawable.profile_image
                            ),
                        ))
                    }

                    EmailList(emails = emails, onChangeBookmark = { itemID ->
                        val updatedEmailList = emails.toMutableList()
                        val index = updatedEmailList.indexOfFirst { it.emailid == itemID }
                        if (index != -1) {
                            val email = updatedEmailList[index]
                            val updatedEmail= email.copy(isBookMarked = !email.isBookMarked)
                            updatedEmailList[index] = updatedEmail
                            emails=updatedEmailList
                            Log.i(
                                "EmailItemTest:onBookmark()",
                                itemID.toString() + "->" + updatedEmail.isBookMarked.toString()
                            )
                        }


                    })
//                    var email by remember {
//                        mutableStateOf(
//                            EmailModel(
//                                itemID = Random.nextInt(),
//                                userName = "Abdur Razzak",
//                                subject = "This the subjct of the email,that will be used for testing purpose",
//                                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
//                                        " and the other tool.",
//                                isBookMarked = false,
//                                timeOrDate = "13-03-23",
//                                profileImageId = R.drawable.profile_image
//                            )
//                        )
//                    }
//                    var selected by remember {
//                        mutableStateOf(false)
//                    }
//
//
//                    var email2 by remember {
//                        mutableStateOf(
//                            EmailModel(
//                                itemID = Random.nextInt(),
//                                userName = "Md Khalekuzzaman",
//                                subject = "This the subjct of the email,that will be used for testing purpose",
//                                message = "Congratual Md,Abul ,this a gmail clone app,made using jetpack compose" +
//                                        " and the other tool.",
//                                isBookMarked = false,
//                                timeOrDate = "13-03-23",
//                                profileImageId = R.drawable.profile_image
//                            )
//                        )
//                    }
//
//                    //
//                    var selected2 by remember {
//                        mutableStateOf(false)
//                    }
//                    //
//                    EmailItem(
//                        info = email,
//                        isSelected = selected,
//                        onLongClick = {
//                            selected = !selected
//                            Log.i("onLongClick", selected.toString())
//
//                        },
//                        onChangeBookmark = { itemID ->
//                            email = email.copy(isBookMarked = !email.isBookMarked)
//                            Log.i(
//                                "EmailItemTest:onBookmark()",
//                                itemID.toString() + "->" + email.isBookMarked.toString()
//                            )
//                        }
//                    )
//                    //
//                    EmailItem(
//                        info = email2,
//                        isSelected = selected2,
//                        onLongClick = {
//                            selected2 = !selected2
//                            Log.i("onLongClick", selected2.toString())
//
//                        },
//                        onChangeBookmark = { itemID ->
//                            email2 = email2.copy(isBookMarked = !email2.isBookMarked)
//                            Log.i(
//                                "EmailItemTest:onBookmark()",
//                                itemID.toString() + "->" + email2.isBookMarked.toString()
//                            )
//                        }
//                    )
                }

//                val drawerGroups= listOf(
//                    DrawerGroup("Group 1", DrawerItemsProvider.getAllInboxes(),false),
//                    DrawerGroup("Group 1", DrawerItemsProvider.getGroup01(),false),
//                    DrawerGroup("Recent labels", DrawerItemsProvider.getGroup02(),true),
//                    DrawerGroup("All Labels", DrawerItemsProvider.getAllLabels(),true),
//                    DrawerGroup("Google apps", DrawerItemsProvider.getGoogleApps(),true),
//                    DrawerGroup("Last Group", DrawerItemsProvider.getLastGroup(),false),
//
//                    )
//                AppDrawer2(drawerGroups) {
//                }
            }
        }
    }

}

