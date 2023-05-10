/* Created by Girrafeec */

package com.girrafeecstud.modified_files_impl.data.database

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [FileEntity::class], version = 1)
abstract class ModifiedFilesDatabase : RoomDatabase() {

    abstract fun filesDao(): FilesDao

}