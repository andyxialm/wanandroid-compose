package cn.refactor.wancompose.arch.ext

/**
 * Created on 2022/11/1.
 *
 * @author andy
 */
fun <T> List<T>.optGet(index: Int, defaultValue: T? = null): T? {
    if (size > index) {
        return get(index)
    }
    return defaultValue
}