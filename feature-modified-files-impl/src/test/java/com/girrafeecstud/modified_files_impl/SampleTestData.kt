package com.girrafeecstud.modified_files_impl

import com.girrafeecstud.core_base.base.ExceptionType
import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.file_list_api.domain.FileInfo
import com.girrafeecstud.file_list_api.domain.FileType
import java.time.LocalDateTime
import java.time.Month

internal val fileOrDirNotExitsResult = BusinessResult.Exception(exceptionType = ExceptionType.FILE_OR_DIR_NOT_EXIST)

internal val noReadMemoryPermissionResult = BusinessResult.Exception(exceptionType = ExceptionType.NO_READ_MEMORY_PERMISSION)

internal val files = listOf(
    FileInfo(
        path = "/path/to/file1.txt",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 0),
        name = "file1.txt",
        size = 1024,
        fileType = FileType.TXT
    ),
    FileInfo(
        path = "/path/to/file2.jpg",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 11, 30),
        name = "file2.jpg",
        size = 2048,
        fileType = FileType.JPG,
        isChanged = false
    ),
    FileInfo(
        path = "/path/to/file3.pdf",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 12, 45),
        name = "file3.pdf",
        size = 3072,
        fileType = FileType.PDF
    )
)

internal val filesOldHashes = mapOf(
    "/path/to/file1.txt" to "a1b2c3d4e5f6",
    "/path/to/file2.jpg" to "1a2b3c4d5e6f",
    "/path/to/file3.pdf" to "6f5e4d3c2b1a"
)

internal val filesHashesNotChanged = mapOf(
    FileInfo(
        path = "/path/to/file1.txt",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 0),
        name = "file1.txt",
        size = 1024,
        fileType = FileType.TXT
    ) to "a1b2c3d4e5f6",
    FileInfo(
        path = "/path/to/file2.jpg",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 11, 30),
        name = "file2.jpg",
        size = 2048,
        fileType = FileType.JPG,
        isChanged = false
    ) to "1a2b3c4d5e6f",
    FileInfo(
        path = "/path/to/file3.pdf",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 12, 45),
        name = "file3.pdf",
        size = 3072,
        fileType = FileType.PDF
    ) to "6f5e4d3c2b1a"
)

internal val filesNewHashes = mapOf(
    FileInfo(
        path = "/path/to/file1.txt",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 10, 0),
        name = "file1.txt",
        size = 1024,
        fileType = FileType.TXT
    ) to "a1b2c3d4e5f6",
    FileInfo(
        path = "/path/to/file2.jpg",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 11, 30),
        name = "file2.jpg",
        size = 2048,
        fileType = FileType.JPG,
        isChanged = false
    ) to "1a2b1c4d5e6f",
    FileInfo(
        path = "/path/to/file3.pdf",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 12, 45),
        name = "file3.pdf",
        size = 3072,
        fileType = FileType.PDF
    ) to "6f5e4d3c2b1a"
)

val modifiedFiles = listOf(
    FileInfo(
        path = "/path/to/file2.jpg",
        creationDate = LocalDateTime.of(2023, Month.JANUARY, 1, 11, 30),
        name = "file2.jpg",
        size = 2048,
        fileType = FileType.JPG,
        isChanged = false
    )
)