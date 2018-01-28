package niot.imoon;


import android.Manifest;
import android.content.Context;
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
public class Buoy_Status_Map extends Fragment implements OnMapReadyCallback {
    public Buoy_Status_Map() {
    }

    @Nullable
    private static View rootView;
    public String bid[] = {"AD02", "AD04", "AD06", "AD07", "AD08", "AD09", "AD10", "BD08",
            "BD09", "BD10", "BD11", "BD12", "BD13", "BD14", "CALVAL", "CB01",
            "CB02", "CB04", "CB06", "TB03", "TB05", "TB06", "TB09", "TB12"};

    public Double lat[] = {14.86, 8.49, 18.51, 15.04, 11.91, 8.25, 10.32, 18.16, 17.85, 16.50, 13.48, 10.50, 14.00, 7.00, 10.61, 11.59, 10.87, 15.40, 13.10, 6.31, 10.99,
            14.74, 17.31, 20.34};
    public Double lon[] = {68.91, 73.11, 67.47, 68.89, 68.64, 73.35, 72.59, 89.67, 89.67, 87.99, 84.01, 94.00, 87.00, 88.06, 72.23, 92.60, 72.21, 73.77, 80.32, 88.60, 89.58, 89.57, 89.78, 67.55
    };

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


        for(int i=0; i<24; i++)
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



}