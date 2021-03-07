package com.example.diceapplication

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.GridLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.recyclerview.widget.RecyclerView
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class RecycleDiceRoll(private val dices: ArrayList<BEDiceRoll>) : RecyclerView.Adapter<RecycleDiceRoll.DiceViewHolder>() {

    val dicePictures = arrayOf(0, R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6)

    var diceRollList = ArrayList<BEDiceRoll>()

    init {
        //Reverses list so we get the newest rolls at the top of the view
        val list = dices.reversed()

        if(list.isNotEmpty())
        {
            diceRollList.clear()

            list.forEach { l ->
                diceRollList.add(l)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DiceViewHolder {
        // Inflating R.layout.name_item
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.history_activity, parent, false)
        return DiceViewHolder(view)
    }

    override fun onBindViewHolder(holder: DiceViewHolder, position: Int) {
        // Getting element from friend list at this position
        val element = diceRollList[position]

        //Background colors of the list
        val colours = intArrayOf(
                Color.parseColor("#E3E3E3"),
                Color.parseColor("#CCCCCC")
        )

        // Updating the text of the views in the cell view with this elements info
        val time: String = SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(element.date) + ": "
        //holder.txtDate.text = time

        //Gets the amount of dice pictures should be shown in each cell
        val amountOfDices = diceRollList[position].diceNumber.size-1

        //Removes all pictures in the gridlayout and sets them
        holder.gridLayout.removeAllViews()

        for (i in 0..amountOfDices) {
            val imgView = ImageView(holder.itemView.context)

            val lp = LinearLayout.LayoutParams(100, 100)
            lp.setMargins(2,0,2,0)
            imgView.layoutParams = lp

            val picture = diceRollList[position].diceNumber[i]

            imgView.setBackgroundResource(dicePictures[picture])

            holder.gridLayout.addView(imgView)
        }

        //Sets the background colors
        holder.itemView.setBackgroundColor(colours[position % colours.size])
    }

    override fun getItemCount(): Int {
        return diceRollList.size
    }

    //Finds all the views we want to fill with info
    class DiceViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        //val txtDate = itemView.findViewById(R.id.tvDate) as TextView
        val gridLayout = itemView.findViewById(R.id.glDices) as GridLayout
    }

    //Clears the list that is shown in the recycler
    fun clearList()
    {
        diceRollList.clear()
        notifyDataSetChanged()
    }

}