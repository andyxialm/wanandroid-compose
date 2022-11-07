package cn.refactor.wancompose.ui.profile

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.scopeLife
import cn.refactor.wancompose.arch.ext.loggedFlow
import cn.refactor.wancompose.arch.ext.loggedUsernameFlow
import cn.refactor.wancompose.arch.ext.saveLogged
import cn.refactor.wancompose.arch.ext.saveLoggedUsername
import cn.refactor.wancompose.arch.livedata.IntLiveData
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.LoginResponse
import cn.refactor.wancompose.model.User
import com.drake.net.Get

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */
class ProfileViewModel(context: Application) : AndroidViewModel(context) {

    val logged = loggedFlow()
    val usernameFlow = loggedUsernameFlow()
    val coinCount = IntLiveData()

    fun logout() {
        scopeLife {
            val response = Get<LoginResponse>(Api.LOGOUT).await()
            if (response.errorCode == 0) {
                saveLogged(false)
                saveLoggedUsername(null)
                coinCount.value = 0
            }
        }
    }

    fun fetchUserInfo() {
        scopeLife {
            val user = Get<User?>(Api.USERINFO).await()
            coinCount.value = user?.coinCount ?: 0
        }
    }
}