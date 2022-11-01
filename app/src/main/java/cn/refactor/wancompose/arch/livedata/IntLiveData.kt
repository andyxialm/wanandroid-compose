package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class IntLiveData : MutableLiveData<Int>() {
    override fun getValue(): Int {
        return super.getValue() ?: 0
    }
}