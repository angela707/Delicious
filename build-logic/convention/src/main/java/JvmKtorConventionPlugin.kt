import com.panini.convention.getPluginId
import com.panini.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class JvmKtorConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.apply(getPluginId("kotlin-serialization"))

            dependencies {
                "implementation"(libs.findBundle("ktor").get())
            }
        }
    }
}