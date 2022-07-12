plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(project(":core:metadata"))
    api(project(":core:deserialization.common"))
    api(project(":core:util.runtime"))
    api(project(":core:descriptors"))
    api(commonDependency("javax.inject"))
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
