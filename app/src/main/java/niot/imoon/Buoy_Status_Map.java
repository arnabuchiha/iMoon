package niot.imoon;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.renderscript.Double2;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.Gravity;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 */
public class Buoy_Status_Map extends Fragment implements OnMapReadyCallback,GoogleMap.OnMarkerClickListener {
    public Buoy_Status_Map() {
    }
    @Nullable
    private static View rootView;
    public String bid[] = {"CALVAL", "CB01","CB02", "CB04", "CB06", "AD06", "AD07", "AD08", "AD09", "AD10", "BD08", "BD09", "BD10", "BD11", "BD12", "BD13", "BD14"};

    public Double lat[] = {10.62,11.589,10.874,15.404,13.1,18.470734,14.963593,11.775299,8.228638,10.332642,17.774536,17.450439,16.489044,13.476318,10.43457,13.963989,6.564819};
    public Double lon[] = {72.281,92.596,72.209,73.768,80.317,67.436188,69.001282,68.631866,73.303345,72.581512,89.20874,89.106201,87.963379,84.149689,94.005188,86.930115,88.203705};

    public Context mContext;
    private int MY_PERMISSION;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView != null) {
            ViewGroup parent = (ViewGroup) rootView.getParent();
            if (parent != null)
                parent.removeView(rootView);
        }
        try {
            rootView = inflater.inflate(R.layout.fragment_buoy__status__map, container, false);

            mContext = getContext();

            if(ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)
                ActivityCompat.requestPermissions(getActivity(),new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},MY_PERMISSION);

            SupportMapFragment fragment = (SupportMapFragment)this.getChildFragmentManager().findFragmentById(R.id.map);
            fragment.getMapAsync(this);
        } catch (InflateException e) {
            return rootView;
        }

        return rootView;
    }


    @Override
    public void onMapReady(GoogleMap map) {


        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        if(ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED)
            map.setMyLocationEnabled(true);


        for(int i=0; i<17; i++)
           map.addMarker(new MarkerOptions().position(new LatLng(lat[i],lon[i])).title(bid[i]).snippet(Double.toString(lat[i])+"N\n"+ Double.toString(lon[i])+"E\n"));

        LatLng l = new LatLng(21.7749,80.0917);
        map.moveCamera(CameraUpdateFactory.newLatLng(l));
        map.moveCamera(CameraUpdateFactory.zoomTo(4));

        map.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker arg0) {
                return null;
            }

            @Override
            public View getInfoContents(Marker marker) {

                LinearLayout info = new LinearLayout(mContext);
                info.setOrientation(LinearLayout.VERTICAL);

                TextView title = new TextView(mContext);
                title.setTextColor(Color.BLUE);
                title.setGravity(Gravity.CENTER);
                title.setTypeface(null, Typeface.BOLD);
                title.setText(marker.getTitle());

                TextView snippet = new TextView(mContext);
                snippet.setTextColor(Color.BLACK);
                snippet.setText(marker.getSnippet());

                info.addView(title);
                info.addView(snippet);

                return info;
            }
        });

    }


    @Override
    public boolean onMarkerClick(Marker marker) {
        /***
         * //Just put the below code into the if statement
         * Intent intent=new Intent(Buoy_Status_Map.this.getActivity(),buoy_info.class);
         intent.putExtra("buoys",position);
         startActivity(intent);
         */
        return true;
    }
}