package niot.imoon;


import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mingle.entity.MenuEntity;
import com.mingle.sweetpick.DimEffect;
import com.mingle.sweetpick.SweetSheet;
import com.mingle.sweetpick.ViewPagerDelegate;
import com.ogaclejapan.arclayout.ArcLayout;
import com.sa90.materialarcmenu.ArcMenu;


/**
 * A simple {@link Fragment} subclass.
 */
public class aboutus extends Fragment {


    private RelativeLayout rl;
    private SweetSheet mSweetSheet2;
    private Buoy_Status_Map buoy_status_map;
    private insat insat;
    private aboutniot aboutniot;
    private settings settings;
    private data_request data_request;
    FragmentTransaction ft;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_aboutus, container, false);

        rl = (RelativeLayout) v.findViewById(R.id.rl);

        buoy_status_map = new Buoy_Status_Map();
        insat=new insat();
        aboutniot=new aboutniot();
        data_request=new data_request();
        settings=new settings();
         //ft= getFragmentManager().beginTransaction();
        //setupViewpager();
//        mSweetSheet2.show();
        //////////

        ft= getFragmentManager().beginTransaction();
        ft.replace(R.id.container_frag,aboutniot);
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.addToBackStack(null);
        ft.commit();


        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
        drawable.getPaint().setColor(getResources().getColor(R.color.white));
        //((FloatingActionButton)v.findViewById(R.id.setter_drawable)).setIconDrawable(drawable);

        final FloatingActionButton buoy_map = (FloatingActionButton) v.findViewById(R.id.buoy_status_map);
        buoy_map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,buoy_status_map);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                buoy_map.setVisibility(View.GONE);
                ft.commit();

            }
        });

        final FloatingActionButton data_req = (FloatingActionButton) v.findViewById(R.id.data_request);
        data_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,data_request);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                data_req.setVisibility(View.GONE);
                ft.commit();
            }
        });
        final FloatingActionButton about_niot = (FloatingActionButton) v.findViewById(R.id.about_niot);
        about_niot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,aboutniot);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                about_niot.setVisibility(View.GONE);
                ft.commit();
            }
        });
        final FloatingActionButton imd_forecast = (FloatingActionButton) v.findViewById(R.id.imd_forecast);
        imd_forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,insat);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
imd_forecast.setVisibility(View.GONE);
                ft.commit();
            }
        });
        final FloatingActionButton settings = (FloatingActionButton) v.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
settings.setVisibility(View.GONE);
                Intent myIntent = new Intent(aboutus.this.getActivity(), settings.class);
                startActivity(myIntent);
            }
        });


        ////////

/******




        final GestureDetector gesture = new GestureDetector(getActivity(), new GestureDetector.OnGestureListener() {
            @Override
            public boolean onDown(MotionEvent e) {
                return false;
            }

            @Override
            public void onShowPress(MotionEvent e) {

            }

            @Override
            public boolean onSingleTapUp(MotionEvent e) {
                if(mSweetSheet2.isShow()){
                    mSweetSheet2.dismiss();
                }
                else
                    mSweetSheet2.show();

                return true;
            }

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                return false;
            }

            @Override
            public void onLongPress(MotionEvent e) {
                mSweetSheet2.toggle();

            }

            @Override
            public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
                return false;
            }
        });


        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                gesture.onTouchEvent(event);
                return true;            }
        });


        return v;

    }

    private void setupViewpager() {

        mSweetSheet2 = new SweetSheet(rl);
        mSweetSheet2.setMenuList(R.menu.menu_sweet);
        mSweetSheet2.setDelegate(new ViewPagerDelegate());
        mSweetSheet2.setBackgroundEffect(new DimEffect(0.5f));
        mSweetSheet2.setOnMenuItemClickListener(new SweetSheet.OnMenuItemClickListener() {
            @Override
            public boolean onItemClick(int position, MenuEntity menuEntity1) {
                switch (position){
                    case 0:
                        ft.replace(R.id.container_frag,buoy_status_map);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);
                        ft.commit();
                    break;
                    case 1:
                        ft.replace(R.id.container_frag,aboutniot);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case 2:
                        ft.replace(R.id.container_frag,data_request);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case 3:
                        ft.replace(R.id.container_frag,insat);
                        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                    case 4:
                        Intent myIntent = new Intent(aboutus.this.getActivity(), settings.class);
                        startActivity(myIntent);
                        break;
                }

                Toast.makeText(getContext(), menuEntity1.title + "  " + position, Toast.LENGTH_SHORT).show();

                return true;
            }
        });
    }

    *********/




        return v;

}
}


