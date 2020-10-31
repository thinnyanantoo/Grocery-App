package com.padc.grocery.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import com.padc.grocery.R
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.delegates.GroceryViewItemActionDelegate
import com.padc.grocery.viewholders.GroceryViewHolder

class GroceryAdapter(private val mDelegate : GroceryViewItemActionDelegate, val type : Int) : BaseRecyclerAdapter<GroceryViewHolder, GroceryVO>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GroceryViewHolder {
        val viewType = type
        when (viewType){
            0 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)
                return GroceryViewHolder(view,mDelegate)
            }
             1 -> {
            val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_grid_item,parent,false)
            return GroceryViewHolder(view,mDelegate)
        }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.view_holder_grocery_item,parent,false)
                return GroceryViewHolder(view,mDelegate)
            }
        }
    }
}