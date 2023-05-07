/* Created by Girrafeec */

package com.girrafeecstud.file_list_api.domain

enum class FileType(val extension: String) {
    FOLDER("folder"),
    UNKNOWN("unknown"),
    TXT("txt"),
    DOC("doc"),
    XLS("xls"),
    PPT("ppt"),
    JPG("jpg"),
    JPEG("jpeg"),
    PNG("png"),
    WEBP("webp"),
    PDF("pdf"),
    MP3("mp3"),
    MP4("mp4"),
    GIF("gif");

    companion object {
        private val map = FileType.values().associateBy(FileType::extension)
        fun fromExtension(extension: String) = map[extension.lowercase()] ?: UNKNOWN
    }

}