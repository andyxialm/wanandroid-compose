package cn.refactor.wancompose.arch.ext

import cn.refactor.wancompose.BuildConfig
import cn.refactor.wancompose.arch.WanApplication
import cn.refactor.wancompose.arch.network.Api
import cn.refactor.wancompose.arch.network.SerializationConverter
import com.drake.net.NetConfig
import com.drake.net.cookie.PersistentCookieJar
import com.drake.net.okhttp.setConverter
import com.drake.net.okhttp.setDebug
import okhttp3.Cache
import java.util.concurrent.TimeUnit

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
fun WanApplication.initNetwork(): WanApplication {
    NetConfig.initialize(Api.HOST, this) {
        cookieJar(PersistentCookieJar(this@initNetwork))
        connectTimeout(30, TimeUnit.SECONDS)
        readTimeout(30, TimeUnit.SECONDS)
        writeTimeout(30, TimeUnit.SECONDS)
        cache(Cache(cacheDir, 1024 * 1024 * 128))
        setDebug(BuildConfig.DEBUG)
        setConverter(SerializationConverter())
    }
    return this
}