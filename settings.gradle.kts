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
    }
}
rootProject.name = "example-file-manager"
include(":app")
include(":navigation")
include(":core-base")
include(":core-components")
include(":core-ui")
include(":core-db")
include(":feature-file-list-api")
include(":feature-file-list-impl")
include(":feature-file-manager")
include(":feature-modified-file-list")
