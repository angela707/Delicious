package com.panini.core.data

import android.content.Context
import com.panini.core.CoroutineProvider
import com.panini.core.CoroutineTest
import com.panini.core.CoroutineTestExtension
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