package com.readthefuckingmanual.fuckukk.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.readthefuckingmanual.fuckukk.data.model.meja.ListMejaResponse
import com.readthefuckingmanual.fuckukk.data.model.meja.MejaModel
import com.readthefuckingmanual.fuckukk.data.source.remote.BasicResponse
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.MejaRemoteDataSource

object MejaRepository {


    fun addMeja(token :String, noMeja : String): MutableLiveData<BasicResponse?> {
        MejaRemoteDataSource.apply {
            addMeja(token, noMeja)
            return  mejaResponse
        }
    }
//    private val _listMeja = MutableLiveData<ListMejaResponse?>()
//    val listMeja get() = _listMeja
//
//    fun getListMeja(token : String) {
//        val mejaList = MutableLiveData<ListMejaResponse?>()
//        MejaRemoteDataSource.apply {
//            getListMeja(token)
//            _listMeja.
//
//            value = mejaList.value
//        }
//    }
}