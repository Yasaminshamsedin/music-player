package shariati.shamsedin.finalproject.data;
import com.google.gson.annotations.SerializedName;
public class Audio {
    @SerializedName("high")
    private high high;

    @SerializedName("medium")
    private Medium medium;

    public high getHigh(){
        return high;
    }

    public Medium getMedium(){
        return medium;
    }
}
