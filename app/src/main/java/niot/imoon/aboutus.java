package niot.imoon;


import android.content.Intent;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;
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
    ArcMenu arcMenu;
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
        arcMenu=(ArcMenu)v.findViewById(R.id.arcMenu);
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
                arcMenu.toggleMenu();
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,buoy_status_map);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();

            }
        });

        final FloatingActionButton data_req = (FloatingActionButton) v.findViewById(R.id.data_request);
        data_req.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcMenu.toggleMenu();
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,data_request);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        final FloatingActionButton about_niot = (FloatingActionButton) v.findViewById(R.id.about_niot);
        about_niot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcMenu.toggleMenu();
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,aboutniot);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        final FloatingActionButton imd_forecast = (FloatingActionButton) v.findViewById(R.id.imd_forecast);
        imd_forecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcMenu.toggleMenu();
                ft= getFragmentManager().beginTransaction();
                ft.replace(R.id.container_frag,insat);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
        final FloatingActionButton settings = (FloatingActionButton) v.findViewById(R.id.settings);
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arcMenu.toggleMenu();
                Intent myIntent = new Intent(aboutus.this.getActivity(), settings.class);
                startActivity(myIntent);
            }
        });

        return v;

}
}


