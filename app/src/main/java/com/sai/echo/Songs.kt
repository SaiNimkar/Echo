package com.sai.echo
import android.os.Parcel
import android.os.Parcelable

class Songs(var songID: Long, var songTitle: String, var artist: String, var songData: String, var dateAdded: Long) : Parcelable {
    constructor(parcel: Parcel) : this(
            parcel.readLong(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong()) {
    }

    override fun writeToParcel(dest: Parcel?, flags: Int) {
        dest?.writeLong(songID)
        dest?.writeString(songTitle)
        dest?.writeString(artist)
        dest?.writeString(songData)
        dest?.writeLong(dateAdded)
    }
    override fun describeContents(): Int {
        return 0
    }
    /*The comparators are used to compare two entities together with each other
    * Here we create two comparators i.e. nameComparator and dateComparator*/
    object Statified {
        /*Here we sort the songs according to their names*/
        var nameComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.songTitle.toUpperCase()
            val songTwo = song2.songTitle.toUpperCase()
            songOne.compareTo(songTwo)
        }
        /*Here we sort them according to the date*/
        var dateComparator: Comparator<Songs> = Comparator<Songs> { song1, song2 ->
            val songOne = song1.dateAdded.toDouble()
            val songTwo = song2.dateAdded.toDouble()
            songTwo.compareTo(songOne)
        }
    }

    companion object CREATOR : Parcelable.Creator<Songs> {
        override fun createFromParcel(parcel: Parcel): Songs {
            return Songs(parcel)
        }

        override fun newArray(size: Int): Array<Songs?> {
            return arrayOfNulls(size)
        }
    }
}


