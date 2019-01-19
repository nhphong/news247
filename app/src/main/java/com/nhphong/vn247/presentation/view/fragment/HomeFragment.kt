package com.nhphong.vn247.presentation.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.nhphong.vn247.R
import com.nhphong.vn247.presentation.view.adapter.HomeFragmentAdapter
import com.nhphong.vn247.presentation.viewmodel.ArticlePreviewViewModel
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import kotlinx.android.synthetic.main.fragment_home.view.recycler_view as recyclerView

class HomeFragment: Fragment() {

  @Inject
  lateinit var viewModel: ArticlePreviewViewModel

  private lateinit var adapter: HomeFragmentAdapter
  private lateinit var disposables: CompositeDisposable

  override fun onAttach(context: Context) {
    AndroidSupportInjection.inject(this)
    super.onAttach(context)
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    val view = inflater.inflate(R.layout.fragment_home, container, false)
    adapter = HomeFragmentAdapter()
    view.recyclerView.let {
      it.adapter = adapter
      it.layoutManager = LinearLayoutManager(it.context)
    }
    return view
  }

  override fun onResume() {
    super.onResume()
    disposables = CompositeDisposable()

    viewModel.articlePreviews()
      .subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe({
        adapter.refreshData(it)
      }, {
        handleError(it)
      })
      .let { disposables.add(it) }
  }

  override fun onPause() {
    super.onPause()
    disposables.clear()
  }

  private fun handleError(error: Throwable) {
    val errorMessage = "${error.javaClass.simpleName}(${error.message})"
    Toast.makeText(context, errorMessage, Toast.LENGTH_LONG).show()
    error.printStackTrace()
  }
}
