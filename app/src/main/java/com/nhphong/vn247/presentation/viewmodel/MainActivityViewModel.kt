package com.nhphong.vn247.presentation.viewmodel

import io.reactivex.Observable
import javax.inject.Inject

interface MainActivityViewModel {
  fun getGreetings(): Observable<String>
}

class MainActivityViewModelImpl @Inject constructor(): MainActivityViewModel {
  override fun getGreetings(): Observable<String> {
    return Observable.just("Hello Phong. Have a nice day")
  }
}
