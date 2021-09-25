package coder.giz.kotlin.coroutines.basics

import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

/**
 * 官方文档：[Coroutines](https://kotlinlang.org/docs/coroutines-basics.html)
 */

/**
 * 第一个协程程序。
 * 输出：
 * Hello
 * Kotlin Coroutines!
 */
private fun firstCoroutineProgram() = runBlocking {
    // launch 函数是非阻塞的。
    launch {
        delay(1000L)
        println("Kotlin Coroutines!")
    }
    println("Hello")
}

/**
 * 测试 [runBlocking] 的阻塞性质。
 * 输出：
 * Hello
 * Kotlin Coroutines!
 * Print after runBlocking
 */
private fun testRunBlocking() {
    firstCoroutineProgram()
    println("Print after runBlocking")
}

fun main() {
    val time = measureTimeMillis {
        testRunBlocking()
    }
    println("Completed in $time ms.")
}

