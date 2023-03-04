package com.readthefuckingmanual.fuckukk.data.repository

import androidx.lifecycle.MutableLiveData
import com.readthefuckingmanual.fuckukk.data.model.meja.ListMejaResponse
import com.readthefuckingmanual.fuckukk.data.model.meja.MejaModel
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.MejaRemoteDataSource

object MejaRepository {

    fun getListMeja(token : String) : MutableLiveData<ListMejaResponse?> {
        val mejaList = MutableLiveData<ListMejaResponse?>()
        MejaRemoteDataSource.apply {
            getListMeja(token)
            return mejaList
        }


    }
}