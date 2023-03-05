package com.readthefuckingmanual.fuckukk.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.readthefuckingmanual.fuckukk.data.model.menu.ListMenuResponse
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.MenuRemoteDataSource

object MenuRepository {
    // implements the methods from the MenuDataSources

    val keranjang : MutableLiveData<ArrayList<MenuModel>> = MutableLiveData(ArrayList())
//    val keranjang : LiveData<ArrayList<MenuModel>> = _keranjang

    var menuMakananAdapter: RecyclerView.Adapter<*>? = null
    var menuMinumanAdapter: RecyclerView.Adapter<*>? = null

    fun addToKeranjang(menuModel: MenuModel) {
        keranjang.value?.add(menuModel)
        menuMakananAdapter?.notifyDataSetChanged()
        menuMinumanAdapter?.notifyDataSetChanged()
    }
    fun clearKeranjang() {
        keranjang.value?.clear()
        menuMakananAdapter?.notifyDataSetChanged()
        menuMinumanAdapter?.notifyDataSetChanged()
    }
    fun removeFromKeranjang(menuModel: MenuModel) {
        keranjang.value?.remove(menuModel)
        menuMakananAdapter?.notifyDataSetChanged()
        menuMinumanAdapter?.notifyDataSetChanged()

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