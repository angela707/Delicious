import androidx.room.gradle.RoomExtension
import com.panini.convention.getPluginId
import com.panini.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies

class AndroidRoomConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        target.run {
            pluginManager.run {
                apply(getPluginId("ksp"))
                apply(getPluginId("room"))

                extensions.configure<RoomExtension> {
                    schemaDirectory("$projectDir/schemas")
                }

                dependencies {
                    "implementation"(libs.findLibrary("room.runtime").get())
                    "implementation"(libs.findLibrary("room.ktx").get())
                    "ksp"(libs.findLibrary("room.compiler").get())
                }
            }
        }
    }
}