package com.example.blank.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class PageViewModel : ViewModel() {


    private val _index = MutableLiveData<Int>()
    val text: LiveData<String> = Transformations.map(_index) {
        if (it == 1) {
            "click on the right to see the current count, click on the middle button to reset the count to zero, and click on the left button the increment the count"
        } else {
            "page $it"
        }

    }

    fun setIndex(index: Int) {
        _index.value = index
    }
}