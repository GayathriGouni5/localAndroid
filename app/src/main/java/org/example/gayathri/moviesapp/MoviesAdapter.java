package org.example.gayathri.moviesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by gayathri on 4/17/17.
 */

class MoviesAdapter<T extends Movies> extends ArrayAdapter{
    private static final String TAG = "MoviesAdapter";

        private final List<T> movies;
        private final int layoutResource;
        private final LayoutInflater layoutInflator;


        public MoviesAdapter(Context context, int resource, List<T> movies) {
            super(context, resource);
            this.movies = movies;
            this.layoutResource = resource;
            this.layoutInflator = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return movies.size();
        }

        @NonNull
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            GetViewHolder viewholder;
            if(convertView == null){
                convertView = layoutInflator.inflate(layoutResource , parent ,false);
                viewholder = new GetViewHolder(convertView);
                convertView.setTag(viewholder);
            }else {
                viewholder = (GetViewHolder) convertView.getTag();
            }


            TextView name = (TextView)convertView.findViewById(R.id.txtname);
            TextView hidden = (TextView)convertView.findViewById(R.id.txtHidden);

            Movies fe = movies.get(position);
            viewholder.txtname.setText(fe.getTitle());
            viewholder.txtHidden.setText(fe.toString());

            return convertView;
        }

        private class GetViewHolder{
            final TextView txtname;
            final TextView txtHidden;

            GetViewHolder(View v){
                txtname = (TextView) v.findViewById(R.id.txtname);
                txtHidden = (TextView)  v.findViewById(R.id.txtHidden);
            }
        }


}
