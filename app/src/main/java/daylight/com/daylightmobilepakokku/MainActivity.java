package daylight.com.daylightmobilepakokku;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import android.view.View;
import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;
import android.view.MenuItem;
import com.google.android.material.navigation.NavigationView;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import java.util.HashMap;
import daylight.com.daylightmobilepakokku.categorylist.Accessory;
import daylight.com.daylightmobilepakokku.categorylist.Brand;
import daylight.com.daylightmobilepakokku.categorylist.Second;
import daylight.com.daylightmobilepakokku.customdialog.About;
import daylight.com.daylightmobilepakokku.customdialog.ForPh;
import daylight.com.daylightmobilepakokku.detailview.AccessorydetailView;
import daylight.com.daylightmobilepakokku.detailview.PdetailView;
import daylight.com.daylightmobilepakokku.detailview.SecondPdetailView;
import daylight.com.daylightmobilepakokku.fav.Favourite;
import daylight.com.daylightmobilepakokku.productlist.Accessory_List;
import daylight.com.daylightmobilepakokku.productlist.Product_List;
import daylight.com.daylightmobilepakokku.productlist.SecondProduct_List;

public class MainActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener,Favourite.OnFragmentInteractionListener, Brand.OnFragmentInteractionListener, Product_List.OnFragmentInteractionListener, PdetailView.OnFragmentInteractionListener,Second.OnFragmentInteractionListener, SecondProduct_List.OnFragmentInteractionListener,Accessory.OnFragmentInteractionListener, Accessory_List.OnFragmentInteractionListener, SecondPdetailView.OnFragmentInteractionListener, AccessorydetailView.OnFragmentInteractionListener
        ,NavigationView.OnNavigationItemSelectedListener{
    SliderLayout slider;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

        //slider start
        slider = findViewById(R.id.slider);

        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("1", R.drawable.d1);
        file_maps.put("2", R.drawable.d2);
        file_maps.put("3", R.drawable.d3);


        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(this);

            textSliderView
                    .description(name)
                    .image(file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);


            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", name);

            slider.addSlider(textSliderView); // end slider

        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }





    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_fav) {
            FragmentManager fragmentManager = this.getSupportFragmentManager();
            Favourite brand=new Favourite();
            brand.show(fragmentManager, "slideshow");
        } else if (id == R.id.nav_fb) {
            gotofbpage(getResources().getString(R.string.fb_page_id));
        } else if (id == R.id.nav_feedback) {
            sent_feedback();
        } else if (id == R.id.nav_share) {
            share_apk();
        } else if (id == R.id.nav_about) {
            About about = new About(this);
            about.show();
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {

    }
    public void gotobrand(View view) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Brand brand=new Brand(MainActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ct","Brand New");
        brand.setArguments(bundle);
        brand.show(fragmentManager, "slideshow");


    }
    public void gotosecond(View view) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Second brand=new Second(MainActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ct","Second Use");
        brand.setArguments(bundle);
        brand.show(fragmentManager, "slideshow");
    }
    public void gotoaccessory(View view) {
        FragmentManager fragmentManager = this.getSupportFragmentManager();
        Accessory brand=new Accessory(MainActivity.this);
        Bundle bundle = new Bundle();
        bundle.putSerializable("ct","Accessories");
        brand.setArguments(bundle);
        brand.show(fragmentManager, "slideshow");
    }
    public void gotofbpage(String id){
        try{
            Uri uri=Uri.parse("fb://page/"+id);
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }catch (ActivityNotFoundException e){
            Uri uri=Uri.parse("https://www.facebook.com/"+id);
            Intent i=new Intent(Intent.ACTION_VIEW,uri);
            startActivity(i);
        }
    }

    public void share_apk(){
        Intent i=new Intent(Intent.ACTION_SEND);
        i.putExtra(Intent.EXTRA_TEXT,getResources().getString(R.string.app_link));
        i.setType("text/plain");
        startActivity(Intent.createChooser(i,""));
    }

    public void sent_feedback(){
        Intent email= new Intent(Intent.ACTION_SENDTO);
        email.setData(Uri.parse("mailto:"+getResources().getString(R.string.feedback_mail)));
        email.putExtra(Intent.EXTRA_SUBJECT, "DayLight Mobile PKU-Applicaton");
        startActivity(email);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
