plugins {
    kotlin("jvm")
    id("jps-compatible")
}

dependencies {
    compileOnly(kotlinStdlib())
}

sourceSets {
    "main" { projectDefault() }
    "test" {}
}
