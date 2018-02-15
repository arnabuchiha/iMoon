package niot.imoon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.bumptech.glide.Glide;
import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * A simple {@link Fragment} subclass.
 */
public class developers extends Fragment {

CircleImageView cimage1,cimage2;

    public developers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View view = inflater.inflate(R.layout.fragment_developers, container, false);
cimage1 = (CircleImageView)view.findViewById(R.id.ris_fb);
String url = "http://graph.facebook.com/"+"100000989618812"+"/picture?type=large";
        Glide.with(getContext()).load(url).into(cimage1);

        cimage2 = (CircleImageView)view.findViewById(R.id.arn_fb);
        String url1 = "http://graph.facebook.com/"+"100007625071035"+"/picture?type=large";
        Glide.with(getContext()).load(url1).into(cimage2);

        return view;
    }

}
