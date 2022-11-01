package cn.refactor.wancompose.arch.livedata

import androidx.lifecycle.MutableLiveData

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class ByteLiveData : MutableLiveData<Byte>() {
    override fun getValue(): Byte {
        return super.getValue() ?: 0
    }
}