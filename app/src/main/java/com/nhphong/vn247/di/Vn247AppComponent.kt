package com.nhphong.vn247.di

import com.nhphong.vn247.Vn247Application
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
  AndroidInjectionModule::class,
  ActivityBindingModule::class,
  FirebaseModule::class
])
interface Vn247AppComponent {
  fun inject(vn247Application: Vn247Application)
}
