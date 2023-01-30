package com.nandaadisaputra.storyapp.core.data.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nandaadisaputra.storyapp.core.data.remote.story.StoryEntity
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteDao
import com.nandaadisaputra.storyapp.core.data.room.favorite.FavoriteEntity

@Database(
    entities = [StoryEntity::class, FavoriteEntity::class, RemoteKeys::class],
    version = 8,
    exportSchema = false
)
abstract class StoryDatabase: RoomDatabase() {
    abstract fun favoriteDao(): FavoriteDao
    abstract fun storyDao(): StoryDao
    abstract fun remoteKeysDao(): RemoteKeysDao

    companion object {
        @Volatile
        private var INSTANCE: StoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): StoryDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    StoryDatabase::class.java, "story_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}