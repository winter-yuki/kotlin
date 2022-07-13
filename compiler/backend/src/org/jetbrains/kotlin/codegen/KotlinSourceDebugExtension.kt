/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.codegen

import org.jetbrains.org.objectweb.asm.*

class KotlinSourceDebugExtension(private val data: String) : Attribute("KotlinSourceDebugExtension") {
    override fun read(
        classReader: ClassReader,
        offset: Int,
        length: Int,
        charBuffer: CharArray?,
        codeAttributeOffset: Int,
        labels: Array<out Label>?,
    ): Attribute = KotlinSourceDebugExtension(classReader.readUTF8(offset, charBuffer))

    override fun write(classWriter: ClassWriter, code: ByteArray?, codeLength: Int, maxStack: Int, maxLocals: Int): ByteVector {
        val result = ByteVector()
        result.putShort(classWriter.newUTF8(data))
        return result
    }
}
