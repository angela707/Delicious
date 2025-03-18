package com.panini.recipe_list.data

import android.content.Context
import com.panini.recipe_list.CoroutineProvider
import com.panini.recipe_list.CoroutineTest
import com.panini.recipe_list.CoroutineTestExtension
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