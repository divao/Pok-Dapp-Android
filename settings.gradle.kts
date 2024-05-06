pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        jcenter()
        mavenLocal()
        maven {
            url = uri("https://jitpack.io")
        }
    }
}

rootProject.name = "PokeDapp"
include(":app")
include(":domain")
