package io.github.xvelx.movieviewer.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import io.github.xvelx.movieviewer.R
import io.github.xvelx.movieviewer.databinding.FragmentDetailBinding
import io.github.xvelx.movieviewer.network.dto.TitleDetail
import io.github.xvelx.movieviewer.vm.DetailViewModel

@AndroidEntryPoint
class DetailFragment : BaseFragment() {

    companion object {
        const val ARG_TITLE_ID = "ARG_TITLE_ID"
    }

    override val screenTitle
        get() = getString(R.string.detail_page_title)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.getString(ARG_TITLE_ID)?.let { titleId ->
            val viewModel = ViewModelProvider(this).get(DetailViewModel::class.java)
            showLoading()
            viewModel.getTitleDetail(titleId).observe(viewLifecycleOwner, Observer { titleDetail ->
                setData(titleDetail)
            })
        }
    }

    private fun setData(titleDetail: TitleDetail) {
        view?.let { rootView ->
            DataBindingUtil.bind<FragmentDetailBinding>(rootView)?.titleDetail = titleDetail

        }
        hideLoading()
    }
}