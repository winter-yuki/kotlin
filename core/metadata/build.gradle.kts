plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    api(protobufLite())
    api(kotlinStdlib())
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
