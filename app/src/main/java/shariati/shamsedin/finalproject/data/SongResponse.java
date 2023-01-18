package shariati.shamsedin.finalproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class SongResponse {
    @SerializedName("total")
    private int total;

    @SerializedName("results")
    private List<Song> results;

    public int getTotal(){
        return total;
    }

    public List<Song> getResults(){
        return results;
    }
}
