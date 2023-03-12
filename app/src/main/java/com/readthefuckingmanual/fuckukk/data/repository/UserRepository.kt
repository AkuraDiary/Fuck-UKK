package com.readthefuckingmanual.fuckukk.data.repository

import androidx.lifecycle.LiveData
import com.readthefuckingmanual.fuckukk.data.model.user.ListUserAdminResponse
import com.readthefuckingmanual.fuckukk.data.source.remote.datasource.UserRemoteDataSource

object UserRepository {

    fun getAllUser() : LiveData<ListUserAdminResponse?>{
        UserRemoteDataSource.apply {
            getListUser()
            return userList
        }
    }
}