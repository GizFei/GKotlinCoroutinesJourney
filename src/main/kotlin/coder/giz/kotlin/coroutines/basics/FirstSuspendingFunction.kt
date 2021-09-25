package coder.giz.kotlin.coroutines.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * [Extract function refactoring]https://kotlinlang.org/docs/coroutines-basics.html#extract-function-refactoring)
 */
private suspend fun firstSuspendingFunction() {
    delay(1000L)
    println("Kotlin Coroutines! - my first suspending function.")
}

/**
 * 如果直接调用 [firstSuspendingFunction] ，则 [delay] 不会发生作用。
 * 输出：
 * Kotlin Coroutines! - my first suspending function.
 * Hello
 */
private fun firstCoroutineProgramWrong() = runBlocking {
    firstSuspendingFunction()
    println("Hello")
}

/**
 * 在 [launch] 的协程块中调用 [firstSuspendingFunction] 函数，[delay] 有效。
 * 输出：
 * Hello
 * Kotlin Coroutines! - my first suspending function.（延迟一秒输出）
 */
private fun firstCoroutineProgram() = runBlocking {
    launch { firstSuspendingFunction() }
    println("Hello")
}

fun main() {
    firstCoroutineProgramWrong()
    firstCoroutineProgram()
}