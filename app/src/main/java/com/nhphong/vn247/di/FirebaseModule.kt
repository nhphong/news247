package com.nhphong.vn247.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.nhphong.vn247.domain.data.FirebaseRepository
import com.nhphong.vn247.domain.data.FirebaseRepositoryImpl
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGatewayImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
abstract class FirebaseModule {

  @Module
  companion object {
    @JvmStatic
    @Provides
    @Singleton
    fun cloudFirestore(): FirebaseFirestore {
      return FirebaseFirestore.getInstance().apply {
        firestoreSettings = FirebaseFirestoreSettings.Builder()
          .setTimestampsInSnapshotsEnabled(true)
          .build()
      }
    }
  }

  @Binds
  @Singleton
  abstract fun cloudFirestoreGateway(cloudFirestoreGateway: CloudFirestoreGatewayImpl): CloudFirestoreGateway

  @Binds
  @Singleton
  abstract fun firebaseRepository(firebaseRepository: FirebaseRepositoryImpl): FirebaseRepository
}
