package com.panini.core.data.database

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "recipes",
    indices = [Index(value = ["recipeId"], unique = true)]
)

data class RecipeEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val recipeId: Int,
    val name: String,
    val description: String?,
    val prepTimeMinutes: Int?,
    val thumbnailUrl: String?,
    val videoUrl: String?,
    val yields: String?
)