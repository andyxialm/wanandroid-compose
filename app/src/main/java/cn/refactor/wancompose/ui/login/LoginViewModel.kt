package cn.refactor.wancompose.ui.login

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.scopeLife
import cn.refactor.wancompose.arch.ext.saveLogged
import cn.refactor.wancompose.arch.ext.saveLoggedUsername
import cn.refactor.wancompose.arch.livedata.BooleanLiveData
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.model.LoginResponse
import com.drake.net.Post

/**
 * Created on 2022/11/6.
 *
 * @author andy
 */
class LoginViewModel(context: Application) : AndroidViewModel(context) {
    val logged = BooleanLiveData()

    fun login(username: String, password: String) {
        scopeLife {
            val response = Post<LoginResponse>(Api.LOGIN) {
                param("username", username)
                param("password", password)
            }.await()
            if (response.errorCode == 0) {
                saveLogged(true)
                saveLoggedUsername(username)
                logged.value = true
            }
        }
    }
}

