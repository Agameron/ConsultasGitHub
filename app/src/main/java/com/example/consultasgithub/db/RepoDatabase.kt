package com.example.consultasgithub.db

//
//@Database(
//    entities = [ResponsesItem::class],
//    version = 1
//)
//@TypeConverters(Converters::class)
//abstract class RepoDatabase:RoomDatabase() {
//
//    abstract fun getRepoDao(): RepoDao
//
//    companion object{
//        @Volatile
//        private var instance: RepoDatabase? = null
//        private val LOCK = Any()
//
//        operator fun invoke(context:Context) = instance ?: synchronized(LOCK){
//            instance ?: createDatabase(context).also { instance = it }
//        }
//
//        private fun createDatabase(context: Context) =
//            Room.databaseBuilder(
//                context.applicationContext,
//                RepoDatabase::class.java,
//                "repository_db.db"
//            ).build()
//    }
//}