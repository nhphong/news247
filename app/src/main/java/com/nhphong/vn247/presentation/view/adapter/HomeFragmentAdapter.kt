package com.nhphong.vn247.presentation.view.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.nhphong.vn247.R
import com.nhphong.vn247.domain.entity.ArticlePreview
import com.nhphong.vn247.presentation.view.viewholder.ArticlePreviewHolder

class HomeFragmentAdapter: RecyclerView.Adapter<ArticlePreviewHolder>() {

  private val previews = mutableListOf<ArticlePreview>()

  fun refreshData(articlePreviews: List<ArticlePreview>) {
    previews.clear()
    previews.addAll(articlePreviews)
    notifyDataSetChanged()
  }

  override fun onCreateViewHolder(parent: ViewGroup, pos: Int): ArticlePreviewHolder {
    val view = LayoutInflater.from(parent.context).inflate(R.layout.article_preview, parent, false)
    return ArticlePreviewHolder(view)
  }

  override fun getItemCount(): Int {
    return previews.size
  }

  override fun onBindViewHolder(holder: ArticlePreviewHolder, pos: Int) {
    holder.bindView(previews[pos])
  }

  override fun onViewRecycled(holder: ArticlePreviewHolder) {
    holder.clear()
  }
}
