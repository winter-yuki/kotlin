/*
 * Copyright 2010-2021 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.native

/**
 * Makes top level function available from C/C++ code with the given name.
 *
 * [externName] controls the name of top level function, [shortName] controls the short name.
 * If [externName] is empty, no top level declaration is being created.
 */
@SinceKotlin("1.5")
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@OptionalExpectation
public expect annotation class CName(val externName: String = "", val shortName: String = "")

/**
 * This annotation marks the experimental Objective-C export refinement annotations.
 */
@RequiresOptIn
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@OptionalExpectation
public expect annotation class ExperimentalObjCRefinement()

/**
 * Meta-annotation that instructs the Kotlin compiler to remove the annotated function or property from the public Objective-C API.
 *
 * Annotation processors that refine the public Objective-C API can annotate their annotations with this meta-annotation
 * to have the original declarations automatically removed from the public API.
 *
 * Note: only annotations with [AnnotationTarget.FUNCTION] and/or [AnnotationTarget.PROPERTY] are supported.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@OptionalExpectation
@ExperimentalObjCRefinement
public expect annotation class RefinesForObjC()

/**
 * Instructs the Kotlin compiler to remove this function or property from the public Objective-C API.
 */
@RefinesForObjC
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@OptionalExpectation
@ExperimentalObjCRefinement
public expect annotation class RefinedForObjC()

/**
 * Meta-annotation that instructs the Kotlin compiler to mark the annotated function or property as
 * [`swift_private`](https://developer.apple.com/documentation/swift/objective-c_and_c_code_customization/improving_objective-c_api_declarations_for_swift).
 *
 * Annotation processors that refine the public API in Swift can annotate their annotations with this meta-annotation
 * to automatically hide the annotated declarations from Swift.
 *
 * Note: only annotations with [AnnotationTarget.FUNCTION] and/or [AnnotationTarget.PROPERTY] are supported.
 */
@Target(AnnotationTarget.ANNOTATION_CLASS)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@OptionalExpectation
@ExperimentalObjCRefinement
public expect annotation class RefinesInSwift()

/**
 * Instructs the Kotlin compiler to mark this function or property as [`swift_private`](https://developer.apple.com/documentation/swift/objective-c_and_c_code_customization/improving_objective-c_api_declarations_for_swift).
 */
@RefinesInSwift
@Target(AnnotationTarget.PROPERTY, AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.BINARY)
@MustBeDocumented
@OptionalExpectation
@ExperimentalObjCRefinement
public expect annotation class RefinedInSwift()
