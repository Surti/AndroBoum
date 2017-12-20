package com.example.leman.androboum;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.List;

/**
 * Created by leman on 20/12/2017.
 */

class MyPagerAdapter extends PagerAdapter {
    List<Profil> liste;
    Context context;
    public MyPagerAdapter(Context context, List<Profil> liste) {
        this.liste = liste;
        this.context = context;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
    // on va chercher la layout
        ViewGroup layout = (ViewGroup) View.inflate(context, R.layout.other_user_fragment, null);
    // on l'ajoute à la vue
        container.addView(layout);
    // on le remplit en fonction du profil
        remplirLayout(layout, liste.get(position));
    // et on retourne ce layout
        return layout;
    }
    @Override
    public int getCount() {
        return liste.size();
    }
    @Override
    public void
    destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }
    private void remplirLayout(ViewGroup layout,Profil p) {
        ImageView imageView = (ImageView) layout.findViewById(R.id.imageView3);
        ImageView imageView5 = (ImageView) layout.findViewById(R.id.imageView5);
        TextView textView = (TextView) layout.findViewById(R.id.textView3);
        // on télécharge dans le premier composant l'image du profil
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference photoRef = storage.getReference().child(p.getEmail() + "/photo.jpg");
        if (photoRef != null) {
            Glide.with(context).using(new FirebaseImageLoader())
                    .load(photoRef)
                    .skipMemoryCache(true)
                    .diskCacheStrategy(DiskCacheStrategy.NONE)
                    .placeholder(R.drawable.ic_person_black_24dp)
                    .into(imageView);
        }
        if (!p.isConnected()) {
            imageView5.setVisibility(View.GONE);
        }
        // on positionne le email dans le TextView
        textView.setText(p.getEmail());
        Log.v("Androboum", "bingo" +p.getEmail()) ;
    }
}
