plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:metadata.jvm"))
    api(project(":core:deserialization.common"))
    implementation(project(":core:compiler.common.jvm"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
