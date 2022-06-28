/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.generators.gradle.dsl

import org.jetbrains.kotlin.konan.target.KonanTarget
import org.jetbrains.kotlin.konan.target.presetName
import kotlin.io.path.Path
import kotlin.io.path.writeText

fun main() {
    generateKpmNativeVariantPresets()
}

fun generateKpmNativeVariantPresets() {
    val sourceCode = kpmNativeVariantsSourceCode()
    Path(outputSourceRoot)
        .resolve(packageName.replace(".", "/"))
        .resolve(fileName)
        .writeText(sourceCode)
}

fun kpmNativeVariantsSourceCode() = """
    package $packageName

    import org.gradle.api.artifacts.Configuration
    import org.jetbrains.kotlin.konan.target.KonanTarget
    import javax.inject.Inject

    // DO NOT EDIT MANUALLY! Generated by ${object {}.javaClass.enclosingClass.name}
    ${variantClasses().indented(skipFirstLine = true)}

    ${allVariantConstructors().indented(skipFirstLine = true)}
    
    ${kpmVariantClassFunction().indented(skipFirstLine = true)}
""".trimIndent()

private val fileName = "nativeVariants.kt"

private val packageName = "org.jetbrains.kotlin.gradle.plugin.mpp.pm20"

private fun allKonanTargets() = KonanTarget.predefinedTargets.values

private fun variantClasses() = allKonanTargets()
    .map { variantClass(it) }
    .joinToString("\n\n")

private fun variantClass(konanTarget: KonanTarget) = """
    abstract class ${konanTarget.variantClassName} @Inject constructor(
        containingModule: GradleKpmModule,
        fragmentName: String,
        dependencyConfigurations: GradleKpmFragmentDependencyConfigurations,
        compileDependencyConfiguration: Configuration,
        apiElementsConfiguration: Configuration,
        hostSpecificMetadataElementsConfiguration: Configuration?
    ) : GradleKpmNativeVariantInternal(
        containingModule = containingModule,
        fragmentName = fragmentName,
        konanTarget = KonanTarget.${konanTarget.className},
        dependencyConfigurations = dependencyConfigurations,
        compileDependencyConfiguration = compileDependencyConfiguration,
        apiElementsConfiguration = apiElementsConfiguration,
        hostSpecificMetadataElementsConfiguration = hostSpecificMetadataElementsConfiguration
    ) {
        companion object {
            val constructor = GradleKpmNativeVariantConstructor(
                KonanTarget.${konanTarget.className},
                ${konanTarget.variantClassName}::class.java
            ) { containingModule: GradleKpmModule,
                fragmentName: String,
                dependencyConfigurations: GradleKpmFragmentDependencyConfigurations,
                compileDependencyConfiguration: Configuration,
                apiElementsConfiguration: Configuration,
                hostSpecificMetadataElementsConfiguration: Configuration? ->
                containingModule.project.objects.newInstance(
                    ${konanTarget.variantClassName}::class.java,
                    containingModule,
                    fragmentName,
                    dependencyConfigurations,
                    compileDependencyConfiguration,
                    apiElementsConfiguration,
                    hostSpecificMetadataElementsConfiguration
                )
            }
        }
    }
""".trimIndent()

private fun kpmVariantClassFunction(): String {
    val konanTargetToVariant = allKonanTargets()
        .joinToString("\n") { "KonanTarget.${it.className} -> ${it.variantClassName}::class.java" }

    return """
        internal fun kpmNativeVariantClass(konanTarget: KonanTarget): Class<out GradleKpmNativeVariantInternal>? = when (konanTarget) {
            ${konanTargetToVariant.indented(nSpaces = 12, skipFirstLine = true)}
            else -> null
        }
    """.trimIndent()
}

private fun allVariantConstructors() = allKonanTargets()
    .joinToString(
        prefix = "internal val allKpmNativeVariantConstructors = listOf(\n",
        separator = ",\n",
        postfix = "\n)"
    ) { "${it.variantClassName}.constructor".indented() }

private val KonanTarget.variantClassName
    get() = presetName
        .capitalizeUS()
        .let { "GradleKpm${it}Variant" }

private val KonanTarget.className get() = javaClass.simpleName
