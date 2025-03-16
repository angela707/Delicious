import com.adimovska.convention.configureKotlinAndroid
import com.android.build.api.dsl.ApplicationExtension
import com.panini.convention.ExtensionType
import com.panini.convention.configureBuildTypes
import com.panini.convention.getPluginId
import com.panini.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

class AndroidApplicationConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply(getPluginId("android-application"))
                apply(getPluginId("kotlin-android"))
            }


            extensions.configure<ApplicationExtension> {
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()

                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }

                configureKotlinAndroid(commonExtension = this)
                configureBuildTypes(
                    commonExtension = this,
                    extensionType = ExtensionType.APPLICATION
                )
            }
        }
    }
}