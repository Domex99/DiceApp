package com.example.diceapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class HistoryActivity : AppCompatActivity() {

    var diceList = ArrayList<BEDiceRoll>()
    lateinit var diceAdapter : RecycleDiceRoll

    var isClear : Boolean = false

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putSerializable("history", diceList)
        outState.putBoolean("isClear", isClear)

        super.onSaveInstanceState(outState)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.history_activity)

        if (savedInstanceState != null) {
            val isCleared = savedInstanceState.getBoolean("isClear")
            val historyList = savedInstanceState.getSerializable("history") as ArrayList<BEDiceRoll>

            diceList = historyList
            isClear = isCleared
        }

        else if (intent.extras != null) {
            val extras: Bundle = intent.extras!!
            val history = extras.getSerializable("history") as ArrayList<BEDiceRoll>

            diceList = history
        }

        //Find the RecyclerView and make a reference to it
        val recycler = findViewById<RecyclerView>(R.id.recyclerView)

        //Setting the recyclers layoutmanager to be a LinearLayoutManager
        recycler.layoutManager = LinearLayoutManager(this)

        //Adding the lines in between the rows
        recycler.addItemDecoration(
                DividerItemDecoration(
                        this,
                        DividerItemDecoration.VERTICAL
                )
        )

        //Sets the items in the recycler to have a fixed size
        recycler.setHasFixedSize(true)

        diceAdapter = RecycleDiceRoll(diceList)
        recycler.adapter = diceAdapter

    }

    //Checks if the list has been cleared, and sends that information
    //and the user back to the DiceRollActivity
    fun onClickBack(view: View)
    {
        if(isClear)
        {
            val intent = Intent()
            setResult(2, intent)
        }

        finish()
    }

    //Clears the history list
    fun onClickClear(view: View) {
        diceList.clear()
        diceAdapter.clearList()
        isClear = true
    }
}