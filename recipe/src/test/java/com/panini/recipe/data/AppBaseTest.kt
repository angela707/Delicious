package com.panini.recipe.data

import android.content.Context
import com.panini.recipe.CoroutineProvider
import com.panini.recipe.CoroutineTest
import com.panini.recipe.CoroutineTestExtension
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestScope
import org.junit.jupiter.api.extension.ExtendWith

@OptIn(ExperimentalCoroutinesApi::class)
@ExtendWith(CoroutineTestExtension::class)
open class AppBaseTest : CoroutineTest {

    override lateinit var testCoroutineProvider: CoroutineProvider
    override lateinit var testScope: TestScope

    val mockContext = mockk<Context>(relaxed = true)
}