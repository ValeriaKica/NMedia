package ru.netology.nmedia.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.netology.nmedia.entity.PostEntity
import ru.netology.nmedia.dao.PostDao

@Database(entities = [PostEntity::class], version = 2)
 abstract class AppDb : RoomDatabase() {
    abstract fun postDao(): PostDao

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context)
                .also { instance = it }
            }
        }
//вот тут может упасть прога , смотри презентацию
        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context, AppDb::class.java, "app.db")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()//вот этого не было в видео
                .build()

    }
}

