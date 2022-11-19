package com.example.graphsoduku.common

import kotlinx.coroutines.Job

// to use an abstract method
// and also to use an protected variable(by default an interface is public)
// use generic types for EVENT
// Job here is to provide SubLogic classes with their own scope of coroutines and to cancel jobs
abstract class BaseLogic<EVENT> {
    protected lateinit var jobTracker: Job
    abstract fun onEvent(event: EVENT)
}