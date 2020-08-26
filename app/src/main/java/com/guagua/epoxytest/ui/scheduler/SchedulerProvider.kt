package com.guagua.epoxytest.ui.scheduler

import io.reactivex.rxjava3.core.Scheduler

interface SchedulerProvider {
    fun io(): Scheduler
    fun main(): Scheduler
}