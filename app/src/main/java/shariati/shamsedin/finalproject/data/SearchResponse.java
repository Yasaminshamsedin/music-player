package shariati.shamsedin.finalproject.data;

import com.google.gson.annotations.SerializedName;

import java.util.List;
public class SearchResponse {

    @SerializedName("total")
    private int total;

    @SerializedName("results")
    private List<SearchResultItem> results;

    public int getTotal(){
        return total;
    }

    public List<SearchResultItem> getResults(){
        return results;
    }
}

