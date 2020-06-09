package com.codehub.marvelheroesapp.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.codehub.marvelheroesapp.R;
import com.squareup.picasso.Picasso;

public class ItemDetails extends AppCompatActivity {

    TextView Title, subtitle;
    ImageView imageview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true); //display back button in toolbar
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        getIncomingIntent();
    }

//Inflate what top_app_bar menu has
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       /* if(item.getItemId() == android.R.id.home) {
            Intent Home = new Intent(ItemDetails.this, MainActivity.class);
            startActivity(Home);
        }
        return true;*/
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent Home = new Intent(ItemDetails.this, MainActivity.class);
                startActivity(Home);
                return true;

            case R.id.share:
                //sharing as text
                Intent intent = getIntent();
                Intent share_intent = new Intent(Intent.ACTION_SEND);
                share_intent.setType("text/plain");
                share_intent.putExtra(Intent.EXTRA_SUBJECT, "Heroes Name: ");
                share_intent.putExtra(Intent.EXTRA_TEXT, intent.getStringExtra("image"));
                startActivity(Intent.createChooser(share_intent, "Share"));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }

    //get values from View Holder and set them to ItemDetails layout
    private void getIncomingIntent() {
        imageview = findViewById(R.id.thumbnail);
        Title = findViewById(R.id.title);
        subtitle = findViewById(R.id.subtitle);
        Intent intent = getIntent();

        if (getIntent().hasExtra("title") && getIntent().hasExtra("subtitle") && getIntent().hasExtra("image")) {
            String title = intent.getStringExtra("title");
            Title.setText(title);
            String subTitle = intent.getStringExtra("subtitle");
            subtitle.setText(subTitle);
            String image = intent.getStringExtra("image");
            Picasso.get().load(image).into(imageview);
        }
    }
}
