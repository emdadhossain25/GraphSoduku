package com.example.graphsoduku.common

import kotlin.coroutines.CoroutineContext

// this class is used for
/*
Coroutines!=threads but
we can use these dispatchers to tell our coroutines
-> which threads to run on

for example writing to a file is IO context
 */

// we are using this interface to make this easier to test on jvm
// also switch to production (UI and IO) in real app
interface DispatcherProvider {
    fun provideUIContext(): CoroutineContext
    fun provideIOContext(): CoroutineContext

}