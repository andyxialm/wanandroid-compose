package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class ListLiveData<T> : MutableLiveData<MutableList<T>>() {
    override fun getValue(): MutableList<T> {
        return super.getValue() ?: mutableListOf()
    }
}