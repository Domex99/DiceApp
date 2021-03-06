package com.example.diceapplication

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.history_activity.*

class HistoryActivity: AppCompatActivity() {

    private val TAG: String = "xyz"

    private val mHistory = mutableListOf<Pair<Int, Int>>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)
        Clear.setOnClickListener { v -> onClickClear() }
        Log.d(TAG, "OnCreate")
    }

    private fun onClickClear() {
        Log.d(TAG, "Clear")
        mHistory.clear()

    }

    fun onClickBack(view: View) {
        finish()
    }
}