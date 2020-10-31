package com.padc.grocery.mvp.presenters.impls

import android.graphics.Bitmap
import androidx.lifecycle.LifecycleOwner
import com.padc.grocery.data.models.AuthenticationModel
import com.padc.grocery.data.models.impls.AuthenticationModelImpl
import com.padc.grocery.data.models.impls.GroceryModelImpl
import com.padc.grocery.data.vos.GroceryVO
import com.padc.grocery.mvp.presenters.AbstractBasePresenter
import com.padc.grocery.mvp.presenters.MainPresenter
import com.padc.grocery.mvp.views.MainView

class MainPresenterImpl : MainPresenter, AbstractBasePresenter<MainView>() {
    private val mGroceryModel = GroceryModelImpl
    val grocery = GroceryVO()
    private var mChosenGroceryForFileUpload: GroceryVO? = null


    override fun onTapAddGrocery(name: String, description: String, amount: Int, bitmap: Bitmap?) {
        mGroceryModel.addGrocery(name, description, amount , bitmap)
    }

    override fun onPhotoTaken(bitmap: Bitmap) {
        mChosenGroceryForFileUpload?.let {
            mGroceryModel.uploadImageAndUpdateGrocery(it, bitmap)
        }
    }

    override fun onTapImageUpload(onSuccess: () -> Unit, onFailure: (String) -> Unit) {
        mView.openGallery()
    }

    override fun onTapEditGrocery(name: String, description: String, amount: Int, image: String) {
        mView.showGroceryDialog(name, description, amount.toString(), image)
    }

    override fun onTapFileUpload(grocery: GroceryVO) {
        mChosenGroceryForFileUpload = grocery
        mView.openGallery()
    }

    private var mauthenticationmodel: AuthenticationModel = AuthenticationModelImpl

    override fun onUiReady(owner: LifecycleOwner) {
        val userName = mauthenticationmodel.getUserName()
        mView.showUserName(userName)
        mGroceryModel.getGroceries(
            onSuccess = {
                mView.showGroceryData(it)
            },
            onFaiure = {
                mView.showErrorMessage(it)
            }
        )
        mView.displayToolbarTitle(mGroceryModel.getAppNameFromRemoteConfig())
        mView.showViewType(mGroceryModel.getAppLayoutFromRemoteConfig())
    }

    override fun onTapDeleteGrocery(name: String) {
        mGroceryModel.removeGrocery(name)
    }
}