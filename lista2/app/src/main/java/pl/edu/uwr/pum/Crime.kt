package pl.edu.uwr.pum

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Crime(
    val title: String,
    val description: String,
    val studentIndex: Int,
    val isSolved: Boolean
): Parcelable
