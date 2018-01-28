package niot.imoon;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;

public class aboutniot extends Fragment {
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_niot, container, false);

        Context current = getActivity();
        AssetManager mng = current.getAssets();
        InputStream input;
        String data = null;
        TextView content = (TextView) rootView.findViewById(R.id.aboutniot);
        TextView hyper = (TextView) rootView.findViewById(R.id.extlink);


        try
        {
            input = mng.open("aboutniot.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            data = new String(buffer);
            content.setText(data);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        try
        {
            input = mng.open("links.txt");
            int size = input.available();
            byte[] buffer = new byte[size];
            input.read(buffer);
            input.close();

            // byte buffer into a string
            data = new String(buffer);
            hyper.setText(data);
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

        return rootView;
    }
}
