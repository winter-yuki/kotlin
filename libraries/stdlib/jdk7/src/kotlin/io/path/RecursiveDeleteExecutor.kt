/*
 * Copyright 2010-2022 JetBrains s.r.o. and Kotlin Programming Language contributors.
 * Use of this source code is governed by the Apache 2.0 license that can be found in the license/LICENSE.txt file.
 */

package kotlin.io.path

import java.nio.file.*
import java.nio.file.attribute.BasicFileAttributeView

internal class RecursiveDeleteExecutor {
    private val maxCollectedExceptions = 10_000
    private val suppressedExceptions = mutableListOf<Throwable>()
    private val notEmptyDirectoriesToSkip = hashSetOf<Path>()

    fun delete(path: Path): List<Throwable> {
        check(suppressedExceptions.isEmpty())
        // test parent is symlink
        // test path is symlink
        // test path contains symlink in the middle
        // test parent doesn't exist
        // test path doesn't exist

        // TODO: https://github.com/google/guava/blob/78d67c94dbe95b918c839b3a0b50d44508c2838b/guava/src/com/google/common/io/MoreFiles.java#L722
        //       Does it make sense in our case?

        var useInsecure = true

        path.parent?.let { parent ->
            val directoryStream = try { Files.newDirectoryStream(parent) } catch (exception: Throwable) { null }
            directoryStream?.use { stream ->
                if (stream is SecureDirectoryStream<Path>) {
                    useInsecure = false
                    stream.handleEntry(path.fileName)
                }
            }
        }

        if (useInsecure) {
            insecureHandleEntry(path)
        }

        return suppressedExceptions
    }

    private fun onFail(path: Path, exception: Exception) {
        val shouldCollect = suppressedExceptions.size < maxCollectedExceptions
                || (exception is DirectoryNotEmptyException && path in notEmptyDirectoriesToSkip).not()
        if (shouldCollect) {
            suppressedExceptions.add(exception)
        }
        path.parent?.let { notEmptyDirectoriesToSkip.add(it) }
    }

    private inline fun suppressIfThrows(path: Path, function: () -> Unit) {
        try {
            function()
        } catch (exception: Exception) {
            onFail(path, exception)
        }
    }

    // secure walk

    private fun SecureDirectoryStream<Path>.handleEntry(entry: Path) {
        suppressIfThrows(entry) {
            if (this.isDirectory(entry)) {
                this.enterDirectory(entry)

                try { this.deleteDirectory(entry) } catch (_: NoSuchFileException) { /* ignore */ }
            } else {
                try { this.deleteFile(entry) } catch (_: NoSuchFileException) { /* ignore */ }  // deletes symlink itself, not its target
            }
        }
    }

    private fun SecureDirectoryStream<Path>.enterDirectory(path: Path) {
        suppressIfThrows(path) {
            this.newDirectoryStream(path, LinkOption.NOFOLLOW_LINKS).use { directoryStream ->
                for (entry in directoryStream) {
                    this.handleEntry(entry.fileName)
                }
            }
        }
    }

    private fun SecureDirectoryStream<Path>.isDirectory(path: Path): Boolean {
        return getFileAttributeView(path, BasicFileAttributeView::class.java, LinkOption.NOFOLLOW_LINKS).readAttributes().isDirectory
    }

    // insecure walk

    private fun insecureHandleEntry(entry: Path) {
        suppressIfThrows(entry) {
            if (entry.isDirectory(LinkOption.NOFOLLOW_LINKS)) {
                insecureEnterDirectory(entry)

                entry.deleteIfExists()
            } else {
                entry.deleteIfExists() // deletes symlink itself, not its target
            }
        }
    }

    private fun insecureEnterDirectory(path: Path) {
        suppressIfThrows(path) {
            Files.newDirectoryStream(path).use { directoryStream ->
                for (entry in directoryStream) {
                    insecureHandleEntry(entry)
                }
            }
        }
    }
}
