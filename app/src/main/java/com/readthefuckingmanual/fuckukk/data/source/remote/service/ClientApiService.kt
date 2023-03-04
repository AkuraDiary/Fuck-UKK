package com.readthefuckingmanual.fuckukk.data.source.remote.service

import com.readthefuckingmanual.fuckukk.data.model.user.UserModel
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface ClientApiService {

    //AUTH
    @FormUrlEncoded
    @POST("login")
    fun doLogin(
        @Field("email") username : String,
        @Field("password") password : String
    ): Call<UserModel>
    //AUTH

    /*NEED BEARER TOKEN AS HEADER AUTH*/

    //MENUS

    //MENUS

    //TRANSAKSI

    //TRANSAKSI

    //MEJA

    //MEJA
}