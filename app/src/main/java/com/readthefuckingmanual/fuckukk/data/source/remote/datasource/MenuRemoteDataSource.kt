package com.readthefuckingmanual.fuckukk.data.source.remote.datasource

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readthefuckingmanual.fuckukk.data.model.menu.ListMenuResponse
import com.readthefuckingmanual.fuckukk.data.model.menu.MenuModel
import com.readthefuckingmanual.fuckukk.data.model.menu.UpdateMenuModel
import com.readthefuckingmanual.fuckukk.data.source.remote.BasicResponse
import com.readthefuckingmanual.fuckukk.data.source.remote.retrofit.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MenuRemoteDataSource {
    //TODO


    private val _listMenu : MutableLiveData<ListMenuResponse?> = MutableLiveData()
    val listMenu : LiveData<ListMenuResponse?> = _listMenu

    private val _menuModel : MutableLiveData<MenuModel?> = MutableLiveData()
    val _menu : LiveData<MenuModel?> = _menuModel

    private val _updateMenu : MutableLiveData<UpdateMenuModel?> = MutableLiveData()
    val updateMenu : MutableLiveData<UpdateMenuModel?> = _updateMenu

    private val _addMenu : MutableLiveData<MenuModel?> = MutableLiveData()
    val addMenu : MutableLiveData<MenuModel?> = _addMenu

    private val _messages : MutableLiveData<BasicResponse?> = MutableLiveData()
    val messages : LiveData<BasicResponse?> = _messages
    fun getListMenu(token : String) {
        RetrofitConfig.ApiService.getAllMenus("Bearer $token")?.enqueue(
            object : Callback<ListMenuResponse?> {
                override fun onResponse(
                    call: Call<ListMenuResponse?>,
                    response: Response<ListMenuResponse?>
                ) {
                    if (response.isSuccessful){
                        _listMenu?.value = response.body()
                    }
                }

                override fun onFailure(call: Call<ListMenuResponse?>, t: Throwable) {
                    //_listMenu?.postValue(null)
                    Log.d("MenuRemoteDataSource", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun getMenuById(token : String, id_menu : Int) {
        RetrofitConfig.ApiService.getMenuById("Bearer $token", id_menu)?.enqueue(
            object : Callback<MenuModel?> {
                override fun onResponse(
                    call: Call<MenuModel?>,
                    response: Response<MenuModel?>
                ) {
                    if (response.isSuccessful){
                        _menuModel.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MenuModel?>, t: Throwable) {
                    //_listMenu?.postValue(null)
                    Log.d("MenuRemoteDataSource", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun addMenuDataSource(token: String, nama_menu: String, deskripsi: String, kategori: String, gambar: String?, harga: String) {
        Log.d("ADD MENU DATA SOURCE", "IS CALLED")
        RetrofitConfig.ApiService.addMenu("Bearer $token", nama_menu , kategori, deskripsi, gambar, harga).enqueue(
            object : Callback<MenuModel?> {
                override fun onResponse(
                    call: Call<MenuModel?>,
                    response: Response<MenuModel?>
                ) {
                    if (response.isSuccessful){
                        _menuModel.value = response.body()
                    }
                }

                override fun onFailure(call: Call<MenuModel?>, t: Throwable) {
                    //_listMenu?.postValue(null)
                    Log.d("MenuRemoteDataSource", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun deleteMenu(token : String, id_menu : Int) {
        RetrofitConfig.ApiService.deleteMenu("Bearer $token", id_menu).enqueue(
            object : Callback<BasicResponse?> {
                override fun onResponse(
                    call: Call<BasicResponse?>,
                    response: Response<BasicResponse?>
                ) {
                    if (response.isSuccessful){
                        _messages.value = response.body()
                    }
                }

                override fun onFailure(call: Call<BasicResponse?>, t: Throwable) {
                    //_listMenu?.postValue(null)
                    Log.d("MenuRemoteDataSource", "onFailure: ${t.message}")
                }
            }
        )
    }

    fun updateMenuDataSource(token: String, id_menu: Int, nama_menu: String, jenis: String, deskripsi: String, gambar: String?, harga: String) {
        RetrofitConfig.ApiService.updateMenu("Bearer $token", id_menu, nama_menu, jenis, deskripsi,  //gambar,
            harga
        ).enqueue(
            object : Callback<UpdateMenuModel?> {
                override fun onResponse(
                    call: Call<UpdateMenuModel?>,
                    response: Response<UpdateMenuModel?>
                ) {
                    if (response.isSuccessful){
                        _updateMenu.value = response.body()
                    }
                }

                override fun onFailure(call: Call<UpdateMenuModel?>, t: Throwable) {
                    //_listMenu?.postValue(null)
                    Log.d("MenuRemoteDataSource", "onFailure: ${t.message}")
                }
            }
        )
    }
}