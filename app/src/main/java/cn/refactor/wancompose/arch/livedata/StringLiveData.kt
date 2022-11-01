package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class StringLiveData : MutableLiveData<String>() {
    override fun getValue(): String {
        return super.getValue() ?: ""
    }
}