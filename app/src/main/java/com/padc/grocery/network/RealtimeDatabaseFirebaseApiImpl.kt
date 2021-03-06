package com.padc.grocery.network

import android.graphics.Bitmap
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.padc.grocery.data.vos.GroceryVO

object RealtimeDatabaseFirebaseApiImpl : FirebaseApi{

    private val database: DatabaseReference = Firebase.database.reference
    private val storage = FirebaseStorage.getInstance()
     val storageReference = storage.reference

    override fun getGroceries(
        onSuccess: (groceries: List<GroceryVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        database.child("groceries").addValueEventListener(object : ValueEventListener {
            override fun onCancelled(error: DatabaseError) {
                onFailure(error.message)
            }

            override fun onDataChange(snapshot: DataSnapshot) {
                val groceryList = arrayListOf<GroceryVO>()
                snapshot.children.forEach { dataSnapShot ->
                    dataSnapShot.getValue(GroceryVO::class.java)?.let {
                        groceryList.add(it)
                    }
                }
                onSuccess(groceryList)
            }
        })
    }

    override fun addGrocery(name: String, description: String, amount: Int, image: String) {
        database.child("groceries").child(name).setValue(GroceryVO(name, description, amount,image.toString()))
    }

    override fun deleteGrocery(name: String) {
        database.child("groceries").child(name).removeValue()
    }

    override fun uploadImageAndEditGrocery(image: Bitmap, grocery: GroceryVO) {
        UploadingImage(image,grocery)
    }
}