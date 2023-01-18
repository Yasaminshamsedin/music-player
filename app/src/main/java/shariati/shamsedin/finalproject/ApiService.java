package shariati.shamsedin.finalproject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import shariati.shamsedin.finalproject.data.ArtistResponse;
import shariati.shamsedin.finalproject.data.SearchResponse;
import shariati.shamsedin.finalproject.data.Song;
import shariati.shamsedin.finalproject.data.SongResponse;
public interface ApiService {
    @GET("song/new/0/11")
    Call<SongResponse> getLatestSongs();

    @GET("song/slider/latest")
    Call<SongResponse> getLatestSliders();

    @GET("artist/trending/0/4")
    Call<ArtistResponse> getTrendingArtists();

    @GET("song/top/day/0/100")
    Call<SongResponse> getTop10DaySongs();

    @GET("song/top/week/0/100")
    Call<SongResponse> getTop10WeekSongs();

    @GET("song/{id}")
    Call<Song> getSongById(@Path("id") String songId);

    @GET("search/query/{query}/0/50")
    Call<SearchResponse> search(@Path("query") String query);
}
