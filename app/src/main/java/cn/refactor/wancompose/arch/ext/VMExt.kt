package cn.refactor.wancompose.arch.ext

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.AndroidViewModel
import cn.refactor.wancompose.arch.WanApplication
import cn.refactor.wancompose.data.PKeys
import cn.refactor.wancompose.data.userDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */
fun AndroidViewModel.loggedFlow(): Flow<Boolean> {
    val keyName = booleanPreferencesKey(PKeys.LOGGED)
    return getApplication<WanApplication>().userDataStore
        .data
        .map {
            it[keyName] ?: false
        }
}

suspend fun AndroidViewModel.saveLogged(logged: Boolean) {
    val keyName = booleanPreferencesKey(PKeys.LOGGED)
    getApplication<WanApplication>().userDataStore.edit {
        it[keyName] = logged
    }
}

fun AndroidViewModel.loggedUsernameFlow(): Flow<String?> {
    val keyName = stringPreferencesKey(PKeys.USERNAME)
    return getApplication<WanApplication>().userDataStore
        .data
        .map { it[keyName] }
}

suspend fun AndroidViewModel.saveLoggedUsername(username: String?) {
    val keyName = stringPreferencesKey(PKeys.USERNAME)
    getApplication<WanApplication>().userDataStore.edit {
        if (username.isNullOrEmpty()) {
            it.remove(keyName)
        } else {
            it[keyName] = username
        }
    }
}