package com.example.leman.androboum;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class OtherUserActivity extends AppCompatActivity {

    private List<Profil> userList = new ArrayList<>();
    private MyArrayAdapter adapter;
    private boolean filterConnected = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        final List<Profil> userList = new ArrayList<>();
        final MyPagerAdapter adapter = new MyPagerAdapter(this, userList);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other_user);
        final ViewPager pager = (ViewPager) findViewById(R.id.pager);
        // on obtient l'intent utilisé pour l'appel
        Intent intent = getIntent();
        // on va chercher la valeur du paramètre position, et on
        // renvoie zéro si ce paramètre n'est pas positionné (ce qui ne devrait
        // pas arriver dans notre cas).
        final int position = intent.getIntExtra("position", 0);
        filterConnected = intent.getBooleanExtra("filter", false);
        //Log.v("Test",""+filterConnected);
        DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");
        ValueEventListener postListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                userList.clear();
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    if (filterConnected) {
                        if (child.getValue(Profil.class).isConnected() == true)
                            userList.add(child.getValue(Profil.class));
                    }else{
                        userList.add(child.getValue(Profil.class));
                    }
                }

                adapter.notifyDataSetChanged();
                pager.setCurrentItem(position);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            // Getting Post failed, log a message
                Log.v("AndroBoum", "loadPost:onCancelled", databaseError.toException());
            }
        };
        mDatabase.addValueEventListener(postListener);
        pager.setAdapter(adapter);
    }

    private void downloadImage(Profil p) {
        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference photoRef = storage.getReference().child(p.getEmail() + "/photo.jpg");
        if (photoRef == null) return;
        ImageView imageView = (ImageView) findViewById(R.id.imageView3);
        // Load the image using Glide
        Glide.with(this /* context */).using(new FirebaseImageLoader())
                .load(photoRef)
                .skipMemoryCache(true)
                .diskCacheStrategy(DiskCacheStrategy.NONE)
                .placeholder(R.drawable.ic_person_black_24dp)
                .into(imageView);

    }
    private class MyArrayAdapter extends ArrayAdapter<Profil> {
        List<Profil> liste;
        private MyArrayAdapter(Context context, int resource, List<Profil> liste) {
            super(context, resource, liste);
            this.liste = liste;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            TextView tv = new TextView(getContext());
            tv.setText(liste.get(position).getEmail());
            return tv;
        }
        @Override
        public int getCount() {
            return liste.size();
        }
    }
}
