package com.nhphong.vn247

import android.app.Activity
import android.app.Application
import com.nhphong.vn247.di.DaggerVn247AppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class Vn247Application: Application(), HasActivityInjector {
  companion object {
    lateinit var instance: Vn247Application
      private set
  }

  @Inject
  lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Activity>

  override fun onCreate() {
    super.onCreate()
    instance = this
    DaggerVn247AppComponent.create().inject(this)
  }

  override fun activityInjector(): AndroidInjector<Activity> {
    return dispatchingAndroidInjector
  }
}
