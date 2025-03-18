plugins {
    alias(libs.plugins.delicious.android.feature.ui)
    alias(libs.plugins.delicious.jvm.ktor)
}

android {
    namespace = "com.panini.recipe_list"
}

dependencies {
    implementation(projects.core)

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}