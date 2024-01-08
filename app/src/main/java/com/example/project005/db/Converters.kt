package com.example.project005.db

import androidx.room.TypeConverter
import com.example.project005.models.Source

class Converters {

    @TypeConverter
    fun fromSource(source: Source):String{
        return source.name
    }

    @TypeConverter
    fun fromSource(name: String):Source{
        return Source(name,name)
    }
}