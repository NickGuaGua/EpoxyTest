package com.guagua.epoxytest

import android.app.Application
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.guagua.epoxytest.model.VideoRepository
import com.guagua.epoxytest.ui.main.MainViewModel
import com.guagua.epoxytest.ui.scheduler.TestSchedulerProviderImpl
import io.mockk.MockKAnnotations
import io.mockk.every
import io.mockk.impl.annotations.RelaxedMockK
import io.reactivex.rxjava3.core.Single
import junit.framework.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Response

class MainViewModelTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var viewModel: MainViewModel

    @RelaxedMockK
    private lateinit var repository: VideoRepository

    @RelaxedMockK
    private lateinit var context: Application

    @Before
    fun setup() {
        MockKAnnotations.init(this)
        viewModel = MainViewModel(context, repository, TestSchedulerProviderImpl())
    }

    @Test
    fun `test fetch categories`() {
        every { repository.getCategoriesObservable() } returns Single.just(Response.success(listOf()))

        viewModel.fetchCategories()

        assertEquals(0, viewModel.categories.value?.size)
    }
}