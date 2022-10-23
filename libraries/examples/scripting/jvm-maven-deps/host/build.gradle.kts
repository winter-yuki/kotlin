
plugins {
    kotlin("jvm")
}

dependencies {
    api(project(":examples:scripting-jvm-maven-deps"))
    api(project(":kotlin-scripting-jvm-host-unshaded"))
    api(kotlinStdlib())
    compileOnly(project(":compiler:util"))

    testRuntimeOnly(project(":kotlin-compiler"))
    testRuntimeOnly(project(":kotlin-scripting-compiler"))

    testApi(commonDependency("junit"))
}

sourceSets {
    "main" { projectDefault() }
    "test" { projectDefault() }
}
