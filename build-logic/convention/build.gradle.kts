plugins {
    `kotlin-dsl`
}

group = "com.panini.delicious.buildlogic"

dependencies {
    compileOnly(libs.android.gradlePlugin)
    compileOnly(libs.android.tools.common)
    compileOnly(libs.kotlin.gradlePlugin)
    compileOnly(libs.ksp.gradlePlugin)
    compileOnly(libs.room.gradlePlugin)
}

gradlePlugin {
    plugins {
        register("androidApplication"){
            id = "delicious.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }

        register("androidApplicationCompose") {
            id = "delicious.android.application.compose"
            implementationClass = "AndroidApplicationComposeConventionPlugin"
        }

        register("androidLibrary") {
            id = "delicious.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }

        register("androidLibraryCompose") {
            id = "delicious.android.library.compose"
            implementationClass = "AndroidLibraryComposeConventionPlugin"
        }

        register("androidFeatureUi") {
            id = "delicious.android.feature.ui"
            implementationClass = "AndroidFeatureUiConventionPlugin"
        }

        register("androidRoom") {
            id = "delicious.android.room"
            implementationClass = "AndroidRoomConventionPlugin"
        }

        register("jvmLibrary") {
            id = "delicious.jvm.library"
            implementationClass = "JvmLibraryConventionPlugin"
        }

        register("jvmKtor") {
            id = "delicious.jvm.ktor"
            implementationClass = "JvmKtorConventionPlugin"
        }
    }
}