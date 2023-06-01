package com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer

import com.khalekuzzaman.just.cse.gmailclone.R
class Destinations {
    companion object {
        const val ALL_INBOXES = "All inboxes"
        const val PRIMARY = "Primary"
        const val PROMOTIONS = "Promotions"
        const val SOCIAL = "Social"
        const val UPDATES = "Updates"
        const val FORUMS = "Forums"
        const val IMAP_TRASH = "[Imap]/Trash"
        const val SMS = "SMS"
        const val STARTED = "Started"
        const val SNOOZED = "Snoozed"
        const val IMPORTANT = "Important"
        const val SENT = "Sent"
        const val SCHEDULED = "Scheduled"
        const val OUTBOX = "Outbox"
        const val DRAFT = "Draft"
        const val ALL_MAIL = "All Mail"
        const val SPAM = "Spam"
        const val BIN = "Bin"
        const val IMAP_SENT = "[Imap]/Sent"
        const val CALL_LOG = "Call log"
        const val CALENDAR = "Calendar"
        const val CONTACT = "Contact"
        const val SETTINGS = "Settings"
        const val HELP_AND_FEEDBACK = "Help and feedback"
    }
}

object DrawerItemsProvider {
     val drawerGroups = listOf(
        DrawerGroup("Group 1", this.getAllInboxes(), false),
        DrawerGroup("Group 1", this.getGroup01(), false),
        DrawerGroup("Recent labels", this.getGroup02(), true),
        DrawerGroup("All Labels", this.getAllLabels(), true),
        DrawerGroup("Google apps", this.getGoogleApps(), true),
        DrawerGroup("Last Group", this.getLastGroup(), false),
    )

    private fun getAllInboxes(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.ALL_INBOXES, R.drawable.ic_all_inboxes, 0),
    )

    private fun getGroup01(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.PRIMARY, R.drawable.ic_primary, 0),
        ModalDrawerItem(Destinations.PROMOTIONS, R.drawable.ic_promotion, 0),
        ModalDrawerItem(Destinations.SOCIAL, R.drawable.ic_social, 0),
        ModalDrawerItem(Destinations.UPDATES, R.drawable.ic_updates, 0),
        ModalDrawerItem(Destinations.FORUMS, R.drawable.ic_forums, 0)
    )

    private fun getGroup02(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.IMAP_TRASH, R.drawable.ic_label, 0),
        ModalDrawerItem(Destinations.SMS, R.drawable.ic_label, 0)
    )

    private fun getAllLabels(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.STARTED, R.drawable.ic_started, 0),
        ModalDrawerItem(Destinations.SNOOZED, R.drawable.ic_snoozed, 0),
        ModalDrawerItem(Destinations.IMPORTANT, R.drawable.ic_important, 0),
        ModalDrawerItem(Destinations.SENT, R.drawable.ic_sent, 0),
        ModalDrawerItem(Destinations.SCHEDULED, R.drawable.ic_schedule, 0),
        ModalDrawerItem(Destinations.OUTBOX, R.drawable.ic_outbox, 0),
        ModalDrawerItem(Destinations.DRAFT, R.drawable.ic_draft, 0),
        ModalDrawerItem(Destinations.ALL_MAIL, R.drawable.ic_sent, 0),
        ModalDrawerItem(Destinations.SPAM, R.drawable.ic_spam, 0),
        ModalDrawerItem(Destinations.BIN, R.drawable.ic_bin, 0),
        ModalDrawerItem(Destinations.IMAP_SENT, R.drawable.ic_label, 0),
        ModalDrawerItem(Destinations.IMAP_TRASH, R.drawable.ic_label, 0),
        ModalDrawerItem(Destinations.CALL_LOG, R.drawable.ic_label, 0),
        ModalDrawerItem(Destinations.SMS, R.drawable.ic_label, 0),
    )

    private fun getGoogleApps(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.CALENDAR, R.drawable.ic_calendar, 0),
        ModalDrawerItem(Destinations.CONTACT, R.drawable.ic_contact, 0),
    )

    private fun getLastGroup(): List<ModalDrawerItem> = listOf(
        ModalDrawerItem(Destinations.SETTINGS, R.drawable.ic_setting, 0),
        ModalDrawerItem(Destinations.HELP_AND_FEEDBACK, R.drawable.ic_help_and_feedback, 0),
    )

}