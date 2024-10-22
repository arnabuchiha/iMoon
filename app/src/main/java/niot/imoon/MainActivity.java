package niot.imoon;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.sdsmdg.tastytoast.TastyToast;

import android.support.design.widget.BottomNavigationView;

/**
 * Created by Risabh
 */

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener,
        ViewPager.OnPageChangeListener{
    private aboutus frag_about;
    private dataavailability frag_dataavail;
    private developers frag_dev;
    private realtimedata frag_realtdata;
    private tutorial frag_tut;
    private FirebaseAuth mAuth;
    ViewPager viewPager;
    BottomNavigationView navigationView;
    MenuItem prevMenuItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setOffscreenPageLimit(2);
        navigationView = (BottomNavigationView) findViewById(R.id.navigationMain);

        navigationView.setOnNavigationItemSelectedListener(this);
        viewPager.addOnPageChangeListener(this);

        BottomNavigationViewHelper.removeShiftMode(navigationView);
        setupViewPager();

        if (AppCompatDelegate.getDefaultNightMode()
                ==AppCompatDelegate.MODE_NIGHT_YES) {
            setTheme(R.style.ThemeOverlay_AppCompat_Dark);
        }

        if(!haveNetworkConnection()){
            TastyToast.makeText(getApplicationContext(), "No Internet! Check your Network Connection...", TastyToast.LENGTH_LONG, TastyToast.ERROR);
            AppCompatDelegate
                    .setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())   {
            case R.id.aboutus:
                viewPager.setCurrentItem(0);
                break;
            case R.id.realtimedata:
                viewPager.setCurrentItem(1);
                break;
            case R.id.tutorial:
                viewPager.setCurrentItem(2);
                break;
            case R.id.developers:
                viewPager.setCurrentItem(3);
                break;
        }
        return false;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        if (prevMenuItem != null) {
            prevMenuItem.setChecked(false);
        }
        else {
            navigationView.getMenu().getItem(0).setChecked(false);
        }

        navigationView.getMenu().getItem(position).setChecked(true);
        prevMenuItem = navigationView.getMenu().getItem(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();
        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase("WIFI"))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase("MOBILE"))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }
        return haveConnectedWifi || haveConnectedMobile;
    }

    private void setupViewPager() {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        frag_about = new aboutus();
        frag_dataavail = new dataavailability();
        frag_dev = new developers();
        frag_realtdata = new realtimedata();
        frag_tut = new tutorial();

        adapter.addFragment(frag_about);
        adapter.addFragment(frag_realtdata);
        adapter.addFragment(frag_tut);
        adapter.addFragment(frag_dev);

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new CubeOutTransformer());
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() == 0){
            super.onBackPressed();
        }
        else{
            viewPager.setCurrentItem(0);
        }
    }

}


