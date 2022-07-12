plugins {
    kotlin("jvm")
    id("jps-compatible")
}

// Should this project be removed altogether ?

dependencies {
    api(kotlinStdlib())
    testApi(project(":kotlin-test:kotlin-test-jvm"))
}

sourceSets {
    "main" { }
    "test" { projectDefault() }
}

testsJar {}
