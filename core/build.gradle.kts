plugins {
    alias(libs.plugins.delicious.android.library.compose)
    alias(libs.plugins.delicious.jvm.ktor)
    alias(libs.plugins.delicious.android.room)
}

android {
    namespace = "com.panini.core"

    testOptions.unitTests.apply {
        all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(libs.bundles.koin)

    implementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(platform(libs.androidx.compose.bom))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.ui.tooling.preview)
    debugImplementation(libs.androidx.compose.ui.tooling)
    api(libs.androidx.compose.material3)

    testImplementation(libs.junit)
    testImplementation(libs.bundles.jupiter.test)
    testImplementation(libs.kotlinx.test.coroutine)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.cash.turbine)
    testImplementation(libs.mockk)

}