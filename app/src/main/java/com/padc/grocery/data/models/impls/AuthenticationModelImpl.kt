package com.padc.grocery.data.models.impls

import com.padc.grocery.data.models.AuthenticationModel
import com.padc.grocery.mvp.presenters.RegisterPresenter
import com.padc.grocery.network.auth.AuthManager
import com.padc.grocery.network.auth.FirebaseAuthManager

object AuthenticationModelImpl : AuthenticationModel {
    override var mAuthManager: AuthManager = FirebaseAuthManager

    override fun login(
        email: String,
        password: String,
        onSucces: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.login(email, password, onSucces, onFailure)
    }

    override fun register(
        email: String,
        password: String,
        userName: String,
        onSucces: () -> Unit,
        onFailure: (String) -> Unit
    ) {
        mAuthManager.register(email, password, userName,onSucces, onFailure)
    }

    override fun getUserName(): String {
        return mAuthManager.getUserName()
    }
}