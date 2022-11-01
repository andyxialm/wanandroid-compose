package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class UiStateMutableLiveData<T: Any>(data: T) : MutableLiveData<T>(data) {
    override fun getValue(): T {
        return super.getValue()!!
    }
}