package com.padc.grocery.mvp.presenters.impls

import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.data.models.AuthenticationModel
import com.padc.grocery.data.models.GroceryModel
import com.padc.grocery.data.models.impls.AuthenticationModelImpl
import com.padc.grocery.data.models.impls.GroceryModelImpl
import com.padc.grocery.mvp.presenters.AbstractBasePresenter
import com.padc.grocery.mvp.presenters.LoginPresenter
import com.padc.grocery.mvp.views.LoginView

class LoginPresenterImpl : LoginPresenter, AbstractBasePresenter<LoginView>() {

    private val mAuthenticationModel : AuthenticationModel = AuthenticationModelImpl
    private val mGroceryModel : GroceryModel = GroceryModelImpl
    override fun onTapLogin(email: String, password: String) {
        mAuthenticationModel.login(email, password,onSuccess = {
            mView.navigateToHomeScreen()
        }, onFailure = {
            mView.showError(it)
        })
    }

    override fun onTapRegister() {
        mView.navigateToRegisterScreen()
    }

    override fun onUiReady(owner: LifecycleOwner) {
        mGroceryModel.setUpRemoteConfigWithDefaultValues()
        mGroceryModel.fetchRemoteConfigs()
    }


}