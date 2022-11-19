package com.example.graphsoduku.common

import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

// why use object?
// reason is object is singleTon will only have one of this at any given time
// they are also thread safe
// can actually inherit from interface
object ProductionDispatcherProvider : DispatcherProvider {
    override fun provideUIContext(): CoroutineContext {
        return Dispatchers.Main
    }

    override fun provideIOContext(): CoroutineContext {
        return Dispatchers.IO
    }


    //these will be used for jvm/ junit test environment
//    override fun provideUnconfinedContext(): CoroutineContext {
//        return Dispatchers.Unconfined
//    }


}