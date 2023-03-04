package com.readthefuckingmanual.fuckukk.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readthefuckingmanual.fuckukk.data.model.menu.ListMenuResponse
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.MenuRemoteDataSource

object MenuRepository {
    // implements the methods from the MenuDataSources

    private val _keranjang : MutableLiveData<ArrayList<MenuModel>> = MutableLiveData(ArrayList())
    val keranjang : LiveData<ArrayList<MenuModel>> = _keranjang

    fun addToKeranjang(menuModel: MenuModel) {

        _keranjang.value?.add(menuModel)

    }
    fun clearKeranjang() {
        _keranjang.value?.clear()
    }
    fun removeFromKeranjang(menuModel: MenuModel) {

        _keranjang.value?.remove(menuModel)

    }

    fun getAllMenus(token : String) : LiveData<ListMenuResponse?> {
        MenuRemoteDataSource.apply {
            getListMenu(token)
            return listMenu
        }
    }

    fun getMenuById(token : String, id_menu : Int) : LiveData<MenuModel?> {
        MenuRemoteDataSource.apply {
            getMenuById(token, id_menu)
            return _menu
        }
    }

//    fun addMenu(token : String, menuModel: MenuModel) : LiveData<MenuModel?> {
//        MenuRemoteDataSource.apply {
//            addMenu(token, menuModel)
//            return _menu
//        }
//    }

}