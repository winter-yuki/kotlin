/*
 * Copyright 2010-2015 JetBrains s.r.o.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jetbrains.kotlin.codegen;

import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.kotlin.codegen.inline.FileMapping;
import org.jetbrains.kotlin.codegen.inline.SMAPBuilder;
import org.jetbrains.kotlin.codegen.inline.SourceMapper;
import org.jetbrains.kotlin.codegen.serialization.JvmSerializationBindings;
import org.jetbrains.kotlin.resolve.jvm.diagnostics.JvmDeclarationOrigin;
import org.jetbrains.org.objectweb.asm.*;

import java.util.List;

import static org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt.GENERATE_SMAP;

public abstract class AbstractClassBuilder implements ClassBuilder {
    protected static final MethodVisitor EMPTY_METHOD_VISITOR = new MethodVisitor(Opcodes.API_VERSION) {};
    public static final RecordComponentVisitor EMPTY_RECORD_VISITOR = new RecordComponentVisitor(Opcodes.API_VERSION) {};
    protected static final FieldVisitor EMPTY_FIELD_VISITOR = new FieldVisitor(Opcodes.API_VERSION) {};

    private String thisName;

    private final JvmSerializationBindings serializationBindings = new JvmSerializationBindings();

    private String sourceName;

    private String debugInfo;

    public static class Concrete extends AbstractClassBuilder {
        private final ClassVisitor v;

        public Concrete(@NotNull ClassVisitor v) {
            this.v = v;
        }

        @Override
        @NotNull
        public ClassVisitor getVisitor() {
            return v;
        }
    }

    @Override
    @NotNull
    public FieldVisitor newField(
            @NotNull JvmDeclarationOrigin origin,
            int access,
            @NotNull String name,
            @NotNull String desc,
            @Nullable String signature,
            @Nullable Object value
    ) {
        FieldVisitor visitor = getVisitor().visitField(access, name, desc, signature, value);
        if (visitor == null) {
            return EMPTY_FIELD_VISITOR;
        }
        return visitor;
    }

    @Override
    @NotNull
    public MethodVisitor newMethod(
            @NotNull JvmDeclarationOrigin origin,
            int access,
            @NotNull String name,
            @NotNull String desc,
            @Nullable String signature,
            @Nullable String[] exceptions
    ) {
        MethodVisitor visitor = getVisitor().visitMethod(access, name, desc, signature, exceptions);
        if (visitor == null) {
            return EMPTY_METHOD_VISITOR;
        }
        return visitor;
    }

    @NotNull
    @Override
    public RecordComponentVisitor newRecordComponent(@NotNull String name, @NotNull String desc, @Nullable String signature) {
        RecordComponentVisitor visitor = getVisitor().visitRecordComponent(name, desc, signature);
        if (visitor == null) {
            return EMPTY_RECORD_VISITOR;
        }
        return visitor;
    }

    @Override
    @NotNull
    public JvmSerializationBindings getSerializationBindings() {
        return serializationBindings;
    }

    @Override
    @NotNull
    public AnnotationVisitor newAnnotation(@NotNull String desc, boolean visible) {
        return getVisitor().visitAnnotation(desc, visible);
    }

    @Override
    public void done() {
        getVisitor().visitSource(sourceName, debugInfo);
        if (debugInfo != null) {
            getVisitor().visitAttribute(new KotlinSourceDebugExtension(debugInfo));
        }
        getVisitor().visitEnd();
    }

    @Override
    public void defineClass(
            @Nullable PsiElement origin,
            int version,
            int access,
            @NotNull String name,
            @Nullable String signature,
            @NotNull String superName,
            @NotNull String[] interfaces
    ) {
        thisName = name;
        getVisitor().visit(version, access, name, signature, superName, interfaces);
    }

    @Override
    public void visitSource(@NotNull String name, @Nullable String debug) {
        assert sourceName == null || sourceName.equals(name) : "inconsistent file name: " + sourceName + " vs " + name;
        sourceName = name;
        debugInfo = debug;
    }

    @Override
    public void visitSMAP(@NotNull SourceMapper smap, boolean backwardsCompatibleSyntax) {
        if (!GENERATE_SMAP) return;

        List<FileMapping> fileMappings = smap.getResultMappings();
        if (fileMappings.isEmpty()) return;

        visitSource(fileMappings.get(0).getName(), SMAPBuilder.INSTANCE.build(fileMappings, backwardsCompatibleSyntax));
    }

    @Override
    public void visitOuterClass(@NotNull String owner, @Nullable String name, @Nullable String desc) {
        getVisitor().visitOuterClass(owner, name, desc);
    }

    @Override
    public void visitInnerClass(@NotNull String name, @Nullable String outerName, @Nullable String innerName, int access) {
        getVisitor().visitInnerClass(name, outerName, innerName, access);
    }

    @Override
    @NotNull
    public String getThisName() {
        assert thisName != null : "This name isn't set";
        return thisName;
    }
}
