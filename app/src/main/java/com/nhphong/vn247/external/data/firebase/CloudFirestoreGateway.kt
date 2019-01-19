package com.nhphong.vn247.external.data.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration
import com.nhphong.vn247.domain.entity.ArticlePreview
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS_DESCRIPTION
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS_IMAGE_URL
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS_PUBLISHED_AT
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS_SOURCE
import com.nhphong.vn247.external.data.firebase.CloudFirestoreGateway.Companion.ARTICLE_PREVIEWS_TITLE
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import javax.inject.Inject

interface CloudFirestoreGateway {
  fun articlePreviews(): Flowable<List<ArticlePreview>>

  companion object {
    const val ARTICLE_PREVIEWS = "article-previews"
    const val ARTICLE_PREVIEWS_TITLE = "title"
    const val ARTICLE_PREVIEWS_IMAGE_URL = "imageUrl"
    const val ARTICLE_PREVIEWS_DESCRIPTION = "description"
    const val ARTICLE_PREVIEWS_SOURCE = "source"
    const val ARTICLE_PREVIEWS_PUBLISHED_AT = "publishedAt"
  }
}

class CloudFirestoreGatewayImpl @Inject constructor(
  private val db: FirebaseFirestore
) : CloudFirestoreGateway {

  override fun articlePreviews(): Flowable<List<ArticlePreview>> {
    var listenerRegistration: ListenerRegistration? = null
    return Flowable.create<List<ArticlePreview>>({ emitter ->
      listenerRegistration = db.collection(ARTICLE_PREVIEWS)
        .addSnapshotListener { documents, exception ->
          if (documents != null && exception == null) {
            emitter.onNext(documents.map {
              ArticlePreview(
                it.getString(ARTICLE_PREVIEWS_TITLE) ?: "",
                it.getString(ARTICLE_PREVIEWS_IMAGE_URL) ?: "",
                it.getString(ARTICLE_PREVIEWS_DESCRIPTION) ?: "",
                it.getString(ARTICLE_PREVIEWS_SOURCE) ?: "",
                it.getString(ARTICLE_PREVIEWS_PUBLISHED_AT) ?: ""
              )
            })
          }

          exception?.run {
            emitter.onError(this)
          }
        }
    }, BackpressureStrategy.LATEST)
      .doOnCancel {
        listenerRegistration?.remove()
      }
  }
}
