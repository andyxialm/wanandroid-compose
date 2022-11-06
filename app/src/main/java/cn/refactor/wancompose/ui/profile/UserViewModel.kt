package cn.refactor.wancompose.ui.profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.scopeLife
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.LoginResponse
import cn.refactor.wancompose.model.User
import com.drake.net.Get
import com.drake.net.Post

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class UserViewModel : ViewModel() {
    val user = MutableLiveData<User?>()

    fun login(username: String, password: String) {
        scopeLife {
            user.value = Post<LoginResponse>(Api.LOGIN) {
                param("username", username)
                param("password", password)
            }.await().data
        }
    }

    fun logout() {
        scopeLife {
            val response = Get<LoginResponse>(Api.LOGOUT).await()
            if (response.errorCode == 0) {
                user.value = null
            }
        }
    }
}