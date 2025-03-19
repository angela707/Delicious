plugins {
    alias(libs.plugins.delicious.android.feature.ui)
    alias(libs.plugins.delicious.jvm.ktor)
}

android {
    namespace = "com.panini.recipe_list"

    testOptions.unitTests.apply {
        all {
            it.useJUnitPlatform()
        }
    }
}

dependencies {
    implementation(projects.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.compose.coil)

    testImplementation(libs.junit)
    testImplementation(libs.bundles.jupiter.test)
    testImplementation(libs.kotlinx.test.coroutine)
    testImplementation(libs.kotlin.test)
    testImplementation(libs.cash.turbine)
    testImplementation(libs.mockk)

    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}