package org.example.sync

import java.io.*
import java.util.zip.ZipEntry
import java.util.zip.ZipOutputStream

object ZipCompressor {

    fun compressFolder(sourceDir: File, outputZip: File) {
        ZipOutputStream(BufferedOutputStream(FileOutputStream(outputZip))).use { zipOut ->
            zipDirRecursive(sourceDir, sourceDir, zipOut)
        }
    }

    private fun zipDirRecursive(
        rootDir: File,
        currentFile: File,
        zipOut: ZipOutputStream
    ) {
        if (currentFile.isDirectory) {
            currentFile.listFiles()?.forEach { file ->
                zipDirRecursive(rootDir, file, zipOut)
            }
        } else {
            val relativePath = rootDir.toURI().relativize(currentFile.toURI()).path
            val entry = ZipEntry(relativePath)
            zipOut.putNextEntry(entry)

            currentFile.inputStream().use { input ->
                input.copyTo(zipOut)
            }

            zipOut.closeEntry()
        }
    }
}