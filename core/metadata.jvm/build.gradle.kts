plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:metadata"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
