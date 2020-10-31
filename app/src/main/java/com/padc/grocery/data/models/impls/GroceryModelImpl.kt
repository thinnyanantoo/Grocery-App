package com.padc.grocery.data.models.impls

import android.graphics.Bitmap
import com.padc.grocery.data.models.GroceryModel
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.network.CloudFireStoreFirebaseApiImpl
import com.padc.grocery.network.FirebaseApi
import com.padc.grocery.network.RealtimeDatabaseFirebaseApiImpl
import com.padc.grocery.network.remoteconfig.FirebaseRemoteConfigManager

object GroceryModelImpl : GroceryModel {
      override var mFirebaseApi : FirebaseApi = CloudFireStoreFirebaseApiImpl
    var impl = CloudFireStoreFirebaseApiImpl


    override var mFirebaseRemoteConfigManager: FirebaseRemoteConfigManager =FirebaseRemoteConfigManager
    override fun getGroceries(onSuccess: (List<GroceryVO>) -> Unit, onFaiure: (String) -> Unit) {
        mFirebaseApi.getGroceries(onSuccess, onFaiure)
    }

    override fun addGrocery(name: String, description: String, amout: Int, image: Bitmap?) {
        image?.let {  mFirebaseApi.uploadImageAndEditGrocery(it,GroceryVO(name,description,amout)) }
            ?:  mFirebaseApi.addGrocery(name,description,amout,"")
    }

    override fun removeGrocery(name: String) {
        mFirebaseApi.deleteGrocery(name)
    }

    override fun uploadImageAndUpdateGrocery(grocery: GroceryVO, image: Bitmap) {
        mFirebaseApi.uploadImageAndEditGrocery(image,grocery)
    }
    override fun setUpRemoteConfigWithDefaultValues() {
        mFirebaseRemoteConfigManager.setUpRemoteConfigWithDefaultValues()
    }

    override fun fetchRemoteConfigs() {
        mFirebaseRemoteConfigManager.fetchRemoteConfigs()
    }

    override fun getAppNameFromRemoteConfig(): String {
        return mFirebaseRemoteConfigManager.getToolbarName()
    }

    override fun getAppLayoutFromRemoteConfig(): String {
        return mFirebaseRemoteConfigManager.getRecyclerLayout()
    }
}