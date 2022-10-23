// DO NOT EDIT MANUALLY!
// Generated by org/jetbrains/kotlin/generators/arguments/GenerateGradleOptions.kt
// To regenerate run 'generateGradleOptions' task
@file:Suppress("RemoveRedundantQualifierName", "Deprecation", "DuplicatedCode")

package org.jetbrains.kotlin.gradle.dsl

@Deprecated("Use KotlinJsDceCompilerToolOptions instead", level = DeprecationLevel.WARNING)
interface KotlinJsDceOptions : org.jetbrains.kotlin.gradle.dsl.KotlinCommonToolOptions {
    override val options: org.jetbrains.kotlin.gradle.dsl.KotlinJsDceCompilerToolOptions

    /**
     * Development mode: don't strip out any code, just copy dependencies
     * Default value: false
     */
    var devMode: kotlin.Boolean
        get() = options.devMode.get()
        set(value) = options.devMode.set(value)

    /**
     * Output directory
     * Default value: null
     */
    @Deprecated(message = "Use task 'destinationDirectory' to configure output directory", level = DeprecationLevel.WARNING)
    var outputDirectory: kotlin.String?
        get() = options.outputDirectory.orNull
        set(value) = options.outputDirectory.set(value)
}
