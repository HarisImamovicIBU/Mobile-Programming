package com.example.brainscript.vmodels

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


class HomeVModel : ViewModel(){
    private val menuItems = MutableStateFlow(listOf("Start Quiz", "View Questions", "Profile"))
    val items: StateFlow<List<String>> = menuItems
}