package com.coders.springtest.extension

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable


fun Disposable.add(compositeDisposable: CompositeDisposable) {
    compositeDisposable.add(this)
}