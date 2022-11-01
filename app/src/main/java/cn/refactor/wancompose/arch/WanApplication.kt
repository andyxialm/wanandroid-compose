package cn.refactor.wancompose.arch

import android.app.Application
import cn.refactor.wancompose.arch.ext.initNetwork

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
class WanApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initNetwork()
    }
}