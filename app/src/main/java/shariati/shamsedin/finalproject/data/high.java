package shariati.shamsedin.finalproject.data;
import com.google.gson.annotations.SerializedName;
public class high {
    @SerializedName("fingerprint")
    private String fingerprint;

    @SerializedName("url")
    private String url;

    public String getFingerprint(){
        return fingerprint;
    }

    public String getUrl(){
        return url;
    }
}
