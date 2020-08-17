package com.guagua.epoxytest.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.guagua.epoxytest.*
import com.guagua.epoxytest.ui.main.list.ItemGroupController
import com.guagua.epoxytest.ui.extension.toCarousel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.main_fragment.*

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel>()

    private val itemGroupController = ItemGroupController()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        epoxyRecyclerView.setController(itemGroupController)

        viewModel.categories.observe(viewLifecycleOwner, Observer {
            it ?: return@Observer

            itemGroupController.setData(it.map { category ->
                category.toCarousel()
            })
        })
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.fetchTopPage()
    }
}
