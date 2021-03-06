package com.guagua.epoxytest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.guagua.epoxytest.R
import com.guagua.epoxytest.ui.extension.toCarousel
import com.guagua.epoxytest.ui.main.list.HomePageController
import com.guagua.epoxytest.view.data.ListItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : Fragment(), HomePageController.AdapterCallbacks {

    private val viewModel by viewModels<MainViewModel>()

    @Inject
    lateinit var homePageController: HomePageController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        epoxyRecyclerView.setController(homePageController)

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer

            homePageController.setData(it.map { category ->
                category.toCarousel()
            })
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchCategories()
    }

    override fun onListItemClick(item: ListItem) {
        viewModel.onListItemClick(item)
    }

    override fun onSeeMoreClickListener(groupId: String, groupTitle: String) {
        viewModel.onSeeMoreClick(groupId, groupTitle)
    }
}
