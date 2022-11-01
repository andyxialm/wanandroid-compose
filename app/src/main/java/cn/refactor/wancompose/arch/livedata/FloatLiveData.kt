package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class FloatLiveData : MutableLiveData<Float>() {
    override fun getValue(): Float {
        return super.getValue() ?: 0F
    }
}