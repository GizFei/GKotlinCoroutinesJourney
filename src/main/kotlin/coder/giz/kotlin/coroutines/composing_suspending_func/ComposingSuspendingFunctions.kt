package coder.giz.kotlin.coroutines.composing_suspending_func

import kotlinx.coroutines.*
import kotlin.system.measureTimeMillis

/**
 * 官方文档：[Composing suspending functions](https://kotlinlang.org/docs/composing-suspending-functions.html)
 */

private suspend fun doSomethingUsefulOne(): Int {
    delay(1000L)
    return 13
}

private suspend fun doSomethingUsefulTwo(): Int {
    delay(1000L)
    return 29
}

// region == Lazily started async ==
/**
 * [Lazily started async](https://kotlinlang.org/docs/composing-suspending-functions.html#lazily-started-async)
 * 延迟启动 [async] 操作。
 *
 * 在调用 [Deferred.await] 前先调用 [Deferred.start] 来启动 [async] 块。
 * 这样可以并行执行两段 [async] 代码。
 * 运行时间：1042ms。
 */
private fun lazilyStartedAsyncWithStart() = runBlocking {
    val time = measureTimeMillis {
        // 指定 async 函数为延迟启动
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 调用 start 启动 async 块
        one.start()
        two.start()
        // 调用 Deferred.await() 等待计算结果
        println("The answer is ${one.await() + two.await()}.")
    }
    println("lazilyStartedAsyncWithStart Completed in $time ms.")
}

/**
 * 只调用了 [Deferred.await]，这样只会在调用 [Deferred.await] 时，才启动 [async] 代码块。
 * 所以会导致 one、two 先后顺序执行，消除了 [async] 的作用。
 * 运行时间：2004ms。
 */
private fun lazilyStartedAsyncWithoutStart() = runBlocking {
    val time = measureTimeMillis {
        // 指定async函数为延迟启动
        val one = async(start = CoroutineStart.LAZY) { doSomethingUsefulOne() }
        val two = async(start = CoroutineStart.LAZY) { doSomethingUsefulTwo() }
        // 调用 Deferred.await() 等待计算结果
        println("The answer is ${one.await() + two.await()}.")
    }
    println("lazilyStartedAsyncWithoutStart Completed in $time ms.")
}

private fun lazilyStartedAsyncTestRun() {
    lazilyStartedAsyncWithStart()
    lazilyStartedAsyncWithoutStart()
}
// endregion

fun main() {
    lazilyStartedAsyncTestRun()
}