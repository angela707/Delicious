import com.panini.convention.addFeatureLayerDependencies
import com.panini.convention.getPluginId
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class AndroidFeatureUiConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply(getPluginId("delicious-android-library-compose"))
            }

            dependencies {
                addFeatureLayerDependencies(target)
            }
        }
    }
}