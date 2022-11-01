package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class BooleanLiveData : MutableLiveData<Boolean>() {
    override fun getValue(): Boolean {
        return super.getValue() ?: false
    }
}