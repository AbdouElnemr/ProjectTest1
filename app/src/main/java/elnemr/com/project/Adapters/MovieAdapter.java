package elnemr.com.project.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.List;

import elnemr.com.project.DetailActivity;
import elnemr.com.project.R;

/**
 * Created by root on 3/28/16.
 */
public class MovieAdapter extends BaseAdapter {

    Context mycontext;
    List<Pojo> objects;
    public MovieAdapter(Context context,List<Pojo> objects) {
        this.mycontext = context;
        this.objects = objects;
    }

    public class ViewHolder
    {
        ImageView imageView;
        TextView  year;
        TextView  vote_average;
        TextView  overview;
        TextView  title;

    }

    @Override
    public int getCount() {
        return objects.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        final Pojo pojo = (Pojo) objects.get(position);

        if (convertView == null )
        {
            LayoutInflater layoutInflater = (LayoutInflater) mycontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.image, null);

            holder = new ViewHolder();
            holder.imageView = (ImageView) convertView.findViewById(R.id.imageView);
            holder.year = (TextView) convertView.findViewById(R.id.deatil_movie_year);
            holder.vote_average = (TextView) convertView.findViewById(R.id.detail_movie_vote_Average);
            holder.overview = (TextView) convertView.findViewById(R.id.detail_movie_overview);
            holder.title = (TextView) convertView.findViewById(R.id.detail_movie_title);


            convertView.setTag(holder);
        }else
        {
            holder = (ViewHolder) convertView.getTag();
        }
        Picasso.with(mycontext)
                .load("http://image.tmdb.org/t/p/w500/" + pojo.getImageurl())
                .into(holder.imageView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mycontext.startActivity(DetailActivity.getActivityIntent(mycontext, pojo));
            }
        });
        return convertView;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }
}
