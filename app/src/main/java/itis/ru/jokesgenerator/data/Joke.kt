package itis.ru.jokesgenerator.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

@Entity(tableName = "joke")
data class Joke(
    @PrimaryKey(autoGenerate = true)
    var dbId: Long,
    @SerializedName("id")
    @Expose
    var id: String? = null,
    @SerializedName("joke")
    @Expose
    var text: String? = null,
    @SerializedName("status")
    @Expose
    var status: Int? = null
)