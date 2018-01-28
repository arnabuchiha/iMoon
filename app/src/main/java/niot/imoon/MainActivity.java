package niot.imoon;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ToxicBakery.viewpager.transforms.CubeOutTransformer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabReselectListener;
import com.roughike.bottombar.OnTabSelectListener;

public class MainActivity extends AppCompatActivity {
ViewPager pager;
    private aboutus frag_about;
    private dataavailability frag_dataavail;
    private developers frag_dev;
    private realtimedata frag_realtdata;
    private tutorial frag_tut;
    BottomBarTab prevMenuItem;
    BottomBar bottomBar;
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();
        
        pager = (ViewPager)findViewById(R.id.viewpager);
        setUpViewPager(pager);

        bottomBar = (BottomBar) findViewById(R.id.bottomBar);

        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                if (tabId == R.id.aboutus) {
                    pager.setCurrentItem(0);
                } else
                if (tabId == R.id.realtimedata) {
                    pager.setCurrentItem(1);
                } else
                if (tabId == R.id.tutorial) {
                    pager.setCurrentItem(2);
                }  /*else
                if (tabId == R.id.developers) {
                    pager.setCurrentItem(3);
                } */
            }
        });

        bottomBar.setOnTabReselectListener(new OnTabReselectListener() {
            @Override
            public void onTabReSelected(@IdRes int tabId) {
                if (tabId == R.id.aboutus) {
                    pager.setCurrentItem(0);
                } else
                if (tabId == R.id.realtimedata) {
                    pager.setCurrentItem(1);
                } else
                if (tabId == R.id.tutorial) {
                    pager.setCurrentItem(2);
                }
                /*else
                if (tabId == R.id.developers) {
                    pager.setCurrentItem(3);
                } */
            }
        });



    }

    private void setUpViewPager(ViewPager pager) {



        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        frag_about = new aboutus();
        //frag_dataavail = new dataavailability();
        //frag_dev = new developers();
        frag_realtdata = new realtimedata();
        frag_tut = new tutorial();

        adapter.addFragment(frag_about);
        adapter.addFragment(frag_tut);
        adapter.addFragment(frag_realtdata);
        //adapter.addFragment(frag_dataavail);
        //adapter.addFragment(frag_dev);

        pager.setAdapter(adapter);
        pager.setPageTransformer(true, new CubeOutTransformer());



        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setActivated(false);
                } else {
                    bottomBar.getTabAtPosition(0).setActivated(false);

                }

                bottomBar.getTabAtPosition(position).setActivated(true);
                prevMenuItem = bottomBar.getTabAtPosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch (item.getItemId()) {
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser = mAuth.getCurrentUser();
        if(currentuser==null){
            sendtostart();
        }

    }

    private void sendtostart() {
        Intent startintent = new Intent(MainActivity.this,Login.class);
        startActivity(startintent);
        finish();

    }

}


