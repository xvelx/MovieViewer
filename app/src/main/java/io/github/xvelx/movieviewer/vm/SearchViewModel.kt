package io.github.xvelx.movieviewer.vm

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import io.github.xvelx.movieviewer.network.MvApi
import io.github.xvelx.movieviewer.network.ds.SearchDataSourceFactory
import io.github.xvelx.movieviewer.network.dto.SearchItem

class SearchViewModel @ViewModelInject constructor(
    private val mvApi: MvApi
) : ViewModel() {

    private var itemPagedList: LiveData<PagedList<SearchItem>>? = null


    fun getSearchItemPagedList(query: String, titleType: String): LiveData<PagedList<SearchItem>> {
        val dataSourceFactory =
            SearchDataSourceFactory(
                query,
                titleType,
                mvApi
            );
        val pagedListConfig =
            PagedList.Config.Builder().setEnablePlaceholders(false).setPageSize(1).build()
        itemPagedList = LivePagedListBuilder(
            dataSourceFactory,
            pagedListConfig
        ).build()
        return itemPagedList!!
    }
}