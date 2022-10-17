/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.analysis.api.fe10.test.cases.generated.cases.symbols;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.util.KtTestUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.kotlin.analysis.api.fe10.test.configurator.AnalysisApiFe10TestConfiguratorFactory;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfiguratorFactoryData;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiTestConfigurator;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.TestModuleKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.FrontendKind;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisSessionMode;
import org.jetbrains.kotlin.analysis.test.framework.test.configurators.AnalysisApiMode;
import org.jetbrains.kotlin.analysis.api.impl.base.test.cases.symbols.AbstractSingleSymbolByPsi;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.analysis.api.GenerateAnalysisApiTestsKt}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi")
@TestDataPath("$PROJECT_ROOT")
public class Fe10IdeNormalAnalysisSourceModuleSingleSymbolByPsiGenerated extends AbstractSingleSymbolByPsi {
    @NotNull
    @Override
    public AnalysisApiTestConfigurator getConfigurator() {
        return AnalysisApiFe10TestConfiguratorFactory.INSTANCE.createConfigurator(
            new AnalysisApiTestConfiguratorFactoryData(
                FrontendKind.Fe10,
                TestModuleKind.Source,
                AnalysisSessionMode.Normal,
                AnalysisApiMode.Ide
            )
        );
    }

    @Test
    public void testAllFilesPresentInSingleSymbolByPsi() throws Exception {
        KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi"), Pattern.compile("^(.+)\\.kt$"), null, true);
    }

    @Test
    @TestMetadata("functionWithReceiverAnnotation.kt")
    public void testFunctionWithReceiverAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/functionWithReceiverAnnotation.kt");
    }

    @Test
    @TestMetadata("getterWithAnnotations.kt")
    public void testGetterWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/getterWithAnnotations.kt");
    }

    @Test
    @TestMetadata("getterWithReceiverAndAnnotations.kt")
    public void testGetterWithReceiverAndAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/getterWithReceiverAndAnnotations.kt");
    }

    @Test
    @TestMetadata("propertyWithAnnotations.kt")
    public void testPropertyWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithAnnotations.kt");
    }

    @Test
    @TestMetadata("propertyWithAnnotationsAndAccessors.kt")
    public void testPropertyWithAnnotationsAndAccessors() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithAnnotationsAndAccessors.kt");
    }

    @Test
    @TestMetadata("propertyWithDelegateAndAnnotations.kt")
    public void testPropertyWithDelegateAndAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithDelegateAndAnnotations.kt");
    }

    @Test
    @TestMetadata("propertyWithReceiverAnnotation.kt")
    public void testPropertyWithReceiverAnnotation() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/propertyWithReceiverAnnotation.kt");
    }

    @Test
    @TestMetadata("setterWithAnnotations.kt")
    public void testSetterWithAnnotations() throws Exception {
        runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/setterWithAnnotations.kt");
    }

    @Nested
    @TestMetadata("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors")
    @TestDataPath("$PROJECT_ROOT")
    public class Errors {
        @Test
        public void testAllFilesPresentInErrors() throws Exception {
            KtTestUtil.assertAllTestsPresentByMetadataWithExcluded(this.getClass(), new File("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors"), Pattern.compile("^(.+)\\.kt$"), null, true);
        }

        @Test
        @TestMetadata("anonympuseObjectInInvalidPosition.kt")
        public void testAnonympuseObjectInInvalidPosition() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors/anonympuseObjectInInvalidPosition.kt");
        }

        @Test
        @TestMetadata("objectWithTypeArgsAsExpression.kt")
        public void testObjectWithTypeArgsAsExpression() throws Exception {
            runTest("analysis/analysis-api/testData/symbols/singleSymbolByPsi/errors/objectWithTypeArgsAsExpression.kt");
        }
    }
}
