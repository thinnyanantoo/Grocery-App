package com.padc.grocery.network.remoteconfig

import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings

object FirebaseRemoteConfigManager {

    private val remoteConfig = Firebase.remoteConfig
    init {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }
        remoteConfig.setConfigSettingsAsync(configSettings)
    }

    fun setUpRemoteConfigWithDefaultValues() {
        val defaultValues : Map<String, Any> = hashMapOf(
            "mainScreenAppBarTitle" to "Grocery-App",
            "mainScreenlayout" to 0
        )
        remoteConfig.setDefaultsAsync(defaultValues)
    }

    fun fetchRemoteConfigs() {
        remoteConfig.fetch()
            .addOnCompleteListener {  task ->
                if(task.isSuccessful){
                    Log.d("Firebase", "Firebase Remote Config fetch success")
                    remoteConfig.activate().addOnCompleteListener {
                        Log.d("Firebase","Firebase Remote Config activitated")
                    }
                } else {
                    Log.d("Firebase","Firebase Remote Config Fetch failed")
                }
            }
    }

    fun getToolbarName(): String {
        return remoteConfig.getValue("mainScreenAppBarTitle").asString()
    }

    fun getRecyclerLayout() : String {
        return remoteConfig.getValue("mainScreenlayout").asString()
    }

}