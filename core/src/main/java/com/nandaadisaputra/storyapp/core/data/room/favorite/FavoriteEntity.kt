package com.nandaadisaputra.storyapp.core.data.room.favorite

import android.os.Parcelable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bumptech.glide.Glide
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "favorite")
data class FavoriteEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "name")
    val name: String?,

    @ColumnInfo(name ="photoUrl")
    val photoUrl: String?,

    @ColumnInfo(name ="createdAt")
    val createdAt: String?,

    @ColumnInfo(name ="description")
    val description: String?,

    @ColumnInfo(name ="lat")
    val lat: Double?,

    @ColumnInfo(name ="lon")
    val lon: Double?
) : Parcelable {
    companion object {
        @JvmStatic
        @BindingAdapter("photoUrl")
        fun loadImage(imageView: ImageView, image: String?) {
            Glide.with(imageView.context).load(image).centerCrop().into(imageView)
        }
    }
}