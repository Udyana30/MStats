import android.os.Parcel
import android.os.Parcelable

data class PlayerModel(
    val overall: String,
    val name: String,
    val number: String,
    val position: String,
    val flagResId: Int,
    val imageResId: Int,
    val foot: String,
    val age: String,
    val height: String,
    val weight: String,
    val contract: String,
    val description: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readInt(),
        parcel.readInt(),
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(overall)
        parcel.writeString(name)
        parcel.writeString(number)
        parcel.writeString(position)
        parcel.writeInt(flagResId)
        parcel.writeInt(imageResId)
        parcel.writeString(foot)
        parcel.writeString(age)
        parcel.writeString(height)
        parcel.writeString(weight)
        parcel.writeString(contract)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PlayerModel> {
        override fun createFromParcel(parcel: Parcel): PlayerModel {
            return PlayerModel(parcel)
        }

        override fun newArray(size: Int): Array<PlayerModel?> {
            return arrayOfNulls(size)
        }
    }
}