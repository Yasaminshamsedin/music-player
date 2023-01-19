package shariati.shamsedin.finalproject.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import shariati.shamsedin.finalproject.R;
import shariati.shamsedin.finalproject.data.Artist;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> {

    Context context;
    List<Artist> artists;

    public ArtistAdapter(Context context, List<Artist> artists) {
        this.context = context;
        this.artists = artists;
    }
    public static class ArtistViewHolder extends RecyclerView.ViewHolder {

        ImageView imgbests;
        TextView txtname;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            imgbests = itemView.findViewById(R.id.imgbests);
            txtname = itemView.findViewById(R.id.txtname);

        }
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ArtistViewHolder(LayoutInflater.from(context).inflate(R.layout.activity_best, parent, false));

    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        holder.txtname.setText(artists.get(position).getFullName());
        Glide.with(context)
                .load(artists.get(position).getImage().getCover().getUrl())
                .circleCrop()
                .into(holder.imgbests);
    }

    @Override
    public int getItemCount() {
        return artists.size();
    }
}