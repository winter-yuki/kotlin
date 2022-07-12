plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:util.runtime"))
    api(kotlinStdlib())
    api(project(":kotlin-annotations-jvm"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
