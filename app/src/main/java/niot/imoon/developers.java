package niot.imoon;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.yalantis.flipviewpager.utils.FlipSettings;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class developers extends Fragment {


    List<ViewFlipperItem> patrons;

    public developers() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
View view = inflater.inflate(R.layout.fragment_developers, container, false);

        final ListView list_patrons = (ListView) view.findViewById(R.id.list_team);
        patrons = new ArrayList<>();

        patrons.add(new ViewFlipperItem(R.drawable.director, "  Dr. C. Muthamizhchelvan  ", R.color.green, " Director Engg. & Tech. - Chief Patron", "", ""));
        patrons.add(new ViewFlipperItem(R.drawable.convenor, "  Prof. Dr. A. Rathinam  ", R.color.saffron, "  Convenor - Aaruush  ", "", ""));
        patrons.add(new ViewFlipperItem(R.drawable.estate, " Mr. V. Thirumurugan  ", R.color.saffron, "  Associate Director  ", "", ""));


        FlipSettings settings = new FlipSettings.Builder().defaultPage(1).build();
        list_patrons.setAdapter(new ViewFlipperAdapter(getActivity(), patrons, settings));
        return view;
    }

}
