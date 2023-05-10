/* Created by Girrafeec */

package com.girrafeecstud.file_list_api

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.util.Log
import android.widget.ImageView
import androidx.core.content.FileProvider
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import java.io.File


fun getFileCreationDateString(date: LocalDateTime): String {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return date.format(formatter)
}

fun getFileSizeString(size: Long): String {
    if (size <= 0) return "0 B"

    val units = arrayOf("B", "KB", "MB", "GB", "TB")
    val digitGroups = (Math.log10(size.toDouble()) / Math.log10(1024.0)).toInt()
    var formattedSize = String.format("%.1f", size / Math.pow(1024.0, digitGroups.toDouble()))

    if (formattedSize.endsWith(".0"))
        formattedSize = formattedSize.substring(0, formattedSize.length - 2)

    return "- $formattedSize ${units[digitGroups]}"
}

fun ImageView.setFileType(fileType: FileType) {
    val resId = when (fileType) {
        FileType.FOLDER -> com.girrafeecstud.core_ui.R.drawable.ic_folder
        FileType.UNKNOWN -> com.girrafeecstud.core_ui.R.drawable.ic_unknown
        FileType.TXT -> com.girrafeecstud.core_ui.R.drawable.ic_text
        FileType.DOC -> com.girrafeecstud.core_ui.R.drawable.ic_document
        FileType.XLS -> com.girrafeecstud.core_ui.R.drawable.ic_xls
        FileType.PPT -> com.girrafeecstud.core_ui.R.drawable.ic_unknown //TODO change
        FileType.JPG -> com.girrafeecstud.core_ui.R.drawable.ic_jpg
        FileType.JPEG -> com.girrafeecstud.core_ui.R.drawable.ic_jpg
        FileType.PNG -> com.girrafeecstud.core_ui.R.drawable.ic_png
        FileType.WEBP -> com.girrafeecstud.core_ui.R.drawable.ic_unknown //TODO change
        FileType.PDF -> com.girrafeecstud.core_ui.R.drawable.ic_png
        FileType.MP3 -> com.girrafeecstud.core_ui.R.drawable.ic_mp3
        FileType.MP4 -> com.girrafeecstud.core_ui.R.drawable.ic_mp4
        FileType.GIF -> com.girrafeecstud.core_ui.R.drawable.ic_unknown //TODO change
    }
    this.setImageResource(resId)
}

fun Activity.openFile(fileInfo: FileInfo) {
    val mimeType = when (fileInfo.fileType) {
        FileType.TXT -> "text/plain"
        FileType.DOC -> "application/msword"
        FileType.XLS -> "application/vnd.ms-excel"
        FileType.PPT -> "application/vnd.ms-powerpoint"
        FileType.JPG, FileType.JPEG, FileType.PNG, FileType.WEBP -> "image/*"
        FileType.PDF -> "application/pdf"
        FileType.MP3 -> "audio/mpeg"
        FileType.MP4 -> "video/mp4"
        FileType.GIF -> "image/gif"
        else -> null // For unsupported file types or folders, no specific MIME type is set
    }

    mimeType?.let { type->
        val intent = Intent(Intent.ACTION_VIEW).apply {
            Log.i("files open file", "path=${fileInfo.path}")
            val file = File(fileInfo.path)
            val uri = if (VERSION.SDK_INT >= VERSION_CODES.N)
                FileProvider.getUriForFile(applicationContext, "com.girrafeecstud.example_file_manager.girrafeecFileProvider", file)
                else
                    Uri.fromFile(file)
            setDataAndType(uri, type)
            flags = Intent.FLAG_GRANT_READ_URI_PERMISSION
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }
    }
}