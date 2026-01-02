package org.example.sync

import java.io.File

object FolderSelector {

    fun selectFolder(args: Array<String>): File {
        val path = if (args.isNotEmpty()) {
            args[0]
        } else {
            print("Enter folder path to sync: ")
            readLine() ?: ""
        }

        val folder = File(path)

        require(folder.exists()) { "Folder does not exist: $path" }
        require(folder.isDirectory) { "Path is not a folder: $path" }

        return folder
    }
}