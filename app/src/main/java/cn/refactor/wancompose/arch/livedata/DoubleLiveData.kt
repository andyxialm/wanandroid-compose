package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class DoubleLiveData : MutableLiveData<Double>() {
    override fun getValue(): Double {
        return super.getValue() ?: 0.0
    }
}