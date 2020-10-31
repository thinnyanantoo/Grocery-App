package com.padc.grocery.mvp.views

import android.graphics.Bitmap
import com.padc.grocery.data.vos.GroceryVO

interface MainView : BaseView {
    fun showGroceryData(groceryList : List<GroceryVO>)
    fun showGroceryDialog(name : String, description: String , amount: String ,  image: String)
    fun showErrorMessage(message : String)
    fun openGallery()
    fun showUserName(name: String)
    fun displayToolbarTitle(title: String)
    fun showViewType(type : String)
}