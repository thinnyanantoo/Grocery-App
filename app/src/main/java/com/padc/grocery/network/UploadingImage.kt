package com.padc.grocery.network

import android.graphics.Bitmap
import com.padc.grocery.data.models.impls.GroceryModelImpl.impl
import com.padc.grocery.data.vos.GroceryVO
import java.io.ByteArrayOutputStream
import java.util.*
import java.util.Arrays.equals

fun UploadingImage(
     image: Bitmap,
     grocery: GroceryVO
 ) {

     val baos = ByteArrayOutputStream()
     image.compress(Bitmap.CompressFormat.JPEG, 100, baos)
     val data = baos.toByteArray()

     val imageRef = impl.storageReference.child("images/${UUID.randomUUID()}")
     val uploadTask = imageRef.putBytes(data)
     uploadTask.addOnFailureListener {

     }.addOnSuccessListener {

     }

     val urlTask = uploadTask.continueWithTask {
         return@continueWithTask imageRef.downloadUrl
     }.addOnCompleteListener{
             task ->
         val imageUrl = task.result?.toString()
         val groceryVO : GroceryVO? = null
        imageUrl?.let { grocery.image = imageUrl }
         impl.addGrocery(
             grocery.name ?: "",
             grocery.description ?: "",
             grocery.amount ?: 0,
             imageUrl  ?: ""
         )
     }
 }