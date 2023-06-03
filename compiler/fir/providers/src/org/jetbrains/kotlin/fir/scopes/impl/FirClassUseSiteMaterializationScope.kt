/*
 * Copyright 2010-2023 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.fir.scopes.impl

import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus
import org.jetbrains.kotlin.fir.declarations.builder.buildSimpleFunctionCopy
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSelfTypeMaterializationSubstitutor
import org.jetbrains.kotlin.fir.scopes.FirTypeScope
import org.jetbrains.kotlin.fir.scopes.ProcessorAction
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol
import org.jetbrains.kotlin.fir.types.ConeKotlinType
import org.jetbrains.kotlin.fir.types.ConeSelfType
import org.jetbrains.kotlin.fir.types.ConeTypeContext
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef
import org.jetbrains.kotlin.fir.types.builder.buildResolvedTypeRefCopy
import org.jetbrains.kotlin.name.Name

class FirClassUseSiteMaterializationScope(
    private val wrappedScope: FirTypeScope,
    private val materializationTarget: ConeKotlinType,
    private val typeContext: ConeTypeContext,
) : FirTypeScope() {
    override fun processFunctionsByName(name: Name, processor: (FirNamedFunctionSymbol) -> Unit) {
        wrappedScope.processFunctionsByName(name) {
            val materialized = if (it.rawStatus !is FirResolvedDeclarationStatus) it else {
                val substitutor = ConeSelfTypeMaterializationSubstitutor(typeContext, materializationTarget)
                val f = buildSimpleFunctionCopy(it.fir) {
                    valueParameters.forEach { param ->
                        val ref = substitutor.transformedTypeRef(param.returnTypeRef as FirResolvedTypeRef)
                        param.replaceReturnTypeRef(ref)
                    }
                    returnTypeRef = substitutor.transformedTypeRef(it.resolvedReturnTypeRef)
                }
                FirNamedFunctionSymbol(it.callableId).apply { bind(f) }
            }
            processor(materialized)
        }
    }

    private fun ConeSelfTypeMaterializationSubstitutor.transformedTypeRef(typeRef: FirResolvedTypeRef): FirResolvedTypeRef =
        substituteOrNull(typeRef.type)?.let { newType ->
            buildResolvedTypeRefCopy(typeRef) {
                type = newType
            }
        } ?: typeRef

    override fun processDirectOverriddenFunctionsWithBaseScope(
        functionSymbol: FirNamedFunctionSymbol,
        processor: (FirNamedFunctionSymbol, FirTypeScope) -> ProcessorAction,
    ): ProcessorAction =
        wrappedScope.processDirectOverriddenFunctionsWithBaseScope(functionSymbol, processor)

    override fun processDirectOverriddenPropertiesWithBaseScope(
        propertySymbol: FirPropertySymbol,
        processor: (FirPropertySymbol, FirTypeScope) -> ProcessorAction,
    ): ProcessorAction =
        wrappedScope.processDirectOverriddenPropertiesWithBaseScope(propertySymbol, processor)

    override fun getCallableNames(): Set<Name> = wrappedScope.getCallableNames()

    override fun getClassifierNames(): Set<Name> = wrappedScope.getClassifierNames()
}
