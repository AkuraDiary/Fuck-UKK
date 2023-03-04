package com.readthefuckingmanual.fuckukk.data.source.remote.datasource

import androidx.lifecycle.MutableLiveData
import com.readthefuckingmanual.fuckukk.data.model.meja.ListMejaResponse
import com.readthefuckingmanual.fuckukk.data.source.remote.retrofit.RetrofitConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object MejaRemoteDataSource {

    private val _mejaList : MutableLiveData<ListMejaResponse?> = MutableLiveData()
    val mejaList : MutableLiveData<ListMejaResponse?> = _mejaList

    fun getListMeja(token : String){
        RetrofitConfig.ApiService.getAllMeja(token)?.enqueue(
            object : Callback<ListMejaResponse?> {
                override fun onResponse(
                    call: Call<ListMejaResponse?>,
                    response: Response<ListMejaResponse?>
                ) {
                    if (response.isSuccessful){
                        _mejaList.value = response.body()
                    }
                }

                override fun onFailure(call: retrofit2.Call<ListMejaResponse?>, t: Throwable) {
                    _mejaList.postValue(null)
                }
            })
    }

}