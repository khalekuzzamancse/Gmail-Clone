package com.khalekuzzaman.just.cse.gmailclone.ui.modal_drawer


data class DrawerGroup(
    val groupName: String,
    val items: List<ModalDrawerItem>,
    val showGroupName: Boolean = true,
)