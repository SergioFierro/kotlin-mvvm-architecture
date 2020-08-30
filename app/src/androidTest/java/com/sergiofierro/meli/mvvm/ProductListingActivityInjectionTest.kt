package com.sergiofierro.meli.mvvm

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.sergiofierro.meli.mvvm.product.ProductListingActivity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@HiltAndroidTest
@RunWith(AndroidJUnit4::class)
class ProductListingActivityInjectionTest {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @ExperimentalCoroutinesApi
  @Test
  fun verifyInjection() {
    ActivityScenario.launch(ProductListingActivity::class.java).use {
      it.moveToState(Lifecycle.State.CREATED)
      it.onActivity { activity ->
        assertThat(activity.viewModel).isNotNull()
        activity.viewModel.productListLiveData.observe(
          activity,
          Observer { products ->
            assertThat(products).isNotNull()
          }
        )
      }
    }
  }
}
