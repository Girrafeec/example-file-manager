/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl

import com.girrafeecstud.core_base.domain.base.BusinessResult
import com.girrafeecstud.core_base.domain.base.EmptyResult
import com.girrafeecstud.file_list_api.data.IFilesDataSource
import com.girrafeecstud.modified_files_impl.data.FileHashCalculator
import com.girrafeecstud.modified_files_impl.data.IFilesHashDataSource
import com.girrafeecstud.modified_files_impl.data.ModifiedFilesRepository
import com.girrafeecstud.modified_files_impl.domain.IModifiedFilesRepository
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.any
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class ModifiedFilesRepositoryTest {

    private lateinit var filesHashDataSource: IFilesHashDataSource
    private lateinit var filesDataSource: IFilesDataSource
    private lateinit var hashCalculator: FileHashCalculator
    private lateinit var modifiedFilesRepository: IModifiedFilesRepository

    @Before
    fun setUp() {
        filesHashDataSource = mock()
        filesDataSource = mock()
        hashCalculator = mock()
        modifiedFilesRepository = ModifiedFilesRepository(
            filesHashDataSource = filesHashDataSource,
            filesDataSource = filesDataSource,
            hashCalculator = hashCalculator
        )
    }

    @Test
    fun `EXPECT Success result with modified files list`() = runBlocking {
        val filesResult = BusinessResult.Success(data = files)
        val filesHashesResult = BusinessResult.Success(data = filesOldHashes)
        val filesAndHashesResult = filesNewHashes

        whenever(
            filesDataSource.getAllFilesList(any())
        )
            .thenReturn(
                flowOf(filesResult)
            )

        whenever(
            filesHashDataSource.getFilesAndHashes()
        )
            .thenReturn(
                flowOf(filesHashesResult)
            )

        whenever(
            hashCalculator.calculateFilesHashes(any())
        )
            .thenReturn(
                flowOf(filesAndHashesResult)
            )

        whenever(
            filesHashDataSource.updateFilesHashes(any())
        ).thenReturn(
            flowOf(BusinessResult.Success(data = EmptyResult))
        )

        // when
        val result = modifiedFilesRepository.getModifiedFiles("").toList()

        // then
        assertEquals(listOf(BusinessResult.Success(modifiedFiles)), result)
    }

    @Test
    fun `EXPECT Success result with null as empty modified files list`() = runBlocking {
        val filesResult = BusinessResult.Success(data = files)
        val filesHashesResult = BusinessResult.Success(data = filesOldHashes)
        val newFilesHashesResult = filesHashesNotChanged

        whenever(
            filesDataSource.getAllFilesList(any())
        )
            .thenReturn(
                flowOf(filesResult)
            )

        whenever(
            filesHashDataSource.getFilesAndHashes()
        )
            .thenReturn(
                flowOf(filesHashesResult)
            )

        whenever(
            hashCalculator.calculateFilesHashes(any())
        )
            .thenReturn(
                flowOf(newFilesHashesResult)
            )

        whenever(
            filesHashDataSource.updateFilesHashes(any())
        ).thenReturn(
            flowOf(BusinessResult.Success(data = EmptyResult))
        )

        // when
        val result = modifiedFilesRepository.getModifiedFiles("").toList()

        // then
        assertEquals(listOf(BusinessResult.Success(data = null)), result)
    }

    @Test
    fun `EXPECT FILE_OR_DIR_NOT_EXIST result from filesDataSource`() = runBlocking {
        val filesResult = fileOrDirNotExitsResult
        val filesHashesResult = BusinessResult.Success(data = filesOldHashes)
        val newFilesHashesResult = filesHashesNotChanged

        whenever(
            filesDataSource.getAllFilesList(any())
        )
            .thenReturn(
                flowOf(filesResult)
            )

        whenever(
            filesHashDataSource.getFilesAndHashes()
        )
            .thenReturn(
                flowOf(filesHashesResult)
            )

        whenever(
            hashCalculator.calculateFilesHashes(any())
        )
            .thenReturn(
                flowOf(newFilesHashesResult)
            )

        whenever(
            filesHashDataSource.updateFilesHashes(any())
        ).thenReturn(
            flowOf(BusinessResult.Success(data = EmptyResult))
        )

        // when
        val result = modifiedFilesRepository.getModifiedFiles("").toList()

        // then
        assertEquals(listOf(fileOrDirNotExitsResult), result)
    }

    @Test
    fun `EXPECT NO_READ_MEMORY_PERMISSION result from filesDataSource`() = runBlocking {
        val filesResult = noReadMemoryPermissionResult
        val filesHashesResult = BusinessResult.Success(data = filesOldHashes)
        val newFilesHashesResult = filesHashesNotChanged

        whenever(
            filesDataSource.getAllFilesList(any())
        )
            .thenReturn(
                flowOf(filesResult)
            )

        whenever(
            filesHashDataSource.getFilesAndHashes()
        )
            .thenReturn(
                flowOf(filesHashesResult)
            )

        whenever(
            hashCalculator.calculateFilesHashes(any())
        )
            .thenReturn(
                flowOf(newFilesHashesResult)
            )

        whenever(
            filesHashDataSource.updateFilesHashes(any())
        ).thenReturn(
            flowOf(BusinessResult.Success(data = EmptyResult))
        )

        // when
        val result = modifiedFilesRepository.getModifiedFiles("").toList()

        // then
        assertEquals(listOf(noReadMemoryPermissionResult), result)
    }

//    @Test
//    fun `EXPECT FILE_OR_DIR_NOT_EXIST result from hashCalculator`() = runBlocking {
//        val filesResult = BusinessResult.Success(data = files)
//        val filesHashesResult = BusinessResult.Success(data = filesOldHashes)
//        val newFilesHashesResult = filesHashesNotChanged
//
//        whenever(
//            filesDataSource.getAllFilesList(any())
//        )
//            .thenReturn(
//                flowOf(filesResult)
//            )
//
//        whenever(
//            filesHashDataSource.getFilesAndHashes()
//        )
//            .thenReturn(
//                flowOf(filesHashesResult)
//            )
//
//        whenever(
//            hashCalculator.calculateFilesHashes(any())
//        )
//            .thenThrow(
//                FileNotFoundException()
//            )
//
//        whenever(
//            filesHashDataSource.updateFilesHashes(any())
//        ).thenReturn(
//            flowOf(BusinessResult.Success(data = EmptyResult))
//        )
//
//        // when
//        val result = modifiedFilesRepository.getModifiedFiles("").toList()
//
//        // then
//        assertEquals(listOf(fileOrDirNotExitsResult), result)
//    }

}