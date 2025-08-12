
package com.example.tools.localStorage

import android.content.Context
import android.util.Log
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException

class LocalStorage(private val context: Context, filePath: String) {
    var file: File
    var storageContent: String = String()

    init {
        CreateDirectories(filePath)

        file = File(context.getFilesDir(), filePath)

        if (!file.exists()) {
            InitialiseEmptyFile()
        }
        try {
            var content = ""
            content = ReadExistingContent()
            storageContent = content
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun WriteFile(content: String) {
        try {
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)

            bufferedWriter.write(content)

            bufferedWriter.close()
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun CreateDirectories(filePath: String) {
        val pathParts: Array<String?> =
            filePath.split("/".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
        val currentDirectory = StringBuilder()

        for (i in 0..<pathParts.size - 1) {
            currentDirectory.append(pathParts[i])

            Log.d("Debug", context.getFilesDir().getAbsolutePath())
            val directory = File(context.getFilesDir(), currentDirectory.toString())

            if (!directory.exists()) {
                val directoryCreated = directory.mkdirs()
                if (!directoryCreated) break
            }

            currentDirectory.append("/") // Add the separator for the next part
        }
    }

    private fun InitialiseEmptyFile() {
        try {
            val fileWriter = FileWriter(file)
            val bufferedWriter = BufferedWriter(fileWriter)

            bufferedWriter.write("{\n}")

            bufferedWriter.close()
            fileWriter.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun ReadExistingContent(): String {
        if (!file.exists()) return ""

        val contentBuilder = StringBuilder()
        BufferedReader(FileReader(file)).use { br ->
            var line: String?
            while ((br.readLine().also { line = it }) != null) {
                contentBuilder.append(line).append('\n')
            }
        }
        return contentBuilder.toString()
    }

    fun GetFileContent(): String {
        return storageContent
    }
}