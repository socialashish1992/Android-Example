package com.ascra.rebelfoods

import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class RxBus {

    private val publisher = PublishSubject.create<List<User>>()

    fun publish(event: List<User>) {
        publisher.onNext(event)
    }

    fun listen(): Observable<List<User>> {
        return publisher
    }

    companion object {

        private var mInstance: RxBus? = null

        fun instance(): RxBus{
            if (mInstance == null) {
                mInstance = RxBus()
            }
            return mInstance as RxBus
        }
    }
}
