package br.com.alura.orgs.db.config

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import br.com.alura.orgs.db.converter.Converters
import br.com.alura.orgs.db.daos.ProdutoDao
import br.com.alura.orgs.models.Produto

@Database(
    entities = [Produto::class],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun produtoDao(): ProdutoDao

    companion object {
        fun getInstance(context: Context): AppDatabase {
            return Room
                .databaseBuilder(context, AppDatabase::class.java, "orgs.db")
                .allowMainThreadQueries()
                .build()
        }
    }
}