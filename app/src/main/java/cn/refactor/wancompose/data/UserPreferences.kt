package cn.refactor.wancompose.data

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

/**
 * Created on 2022/11/7.
 *
 * @author andy
 */
private const val USER_PREFERENCES_NAME = "user_preferences"

val Context.userDataStore by preferencesDataStore(USER_PREFERENCES_NAME)
