package niot.imoon;

import android.app.ListActivity;
import android.app.ListFragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.jpardogo.android.flabbylistview.lib.FlabbyListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class realtimedata extends Fragment{
    private ListAdapters mAdapter;
    ArrayList<String> buoys=new ArrayList<>();

    ListView listView;

    public realtimedata() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_realtimedata, container, false);
        listView = (ListView)v.findViewById(R.id.listv);
        buoyRetrofit buoy = buoyRetrofit.retrofit.create(buoyRetrofit.class);
        Call<BuoyDataAdapter> call = buoy.buoy_data();
        call.enqueue(new Callback<BuoyDataAdapter>() {
            @Override
            public void onResponse(Call<BuoyDataAdapter> call, Response<BuoyDataAdapter> response) {
                List<Row> row = response.body().getRow();
                //See the response
                System.out.println("HAHA: " + response.raw().toString());
                //List<Translation> list = data.getTranslations();

                ListIterator<Row> los = row.listIterator();
                //String txt1 = "";
                while (los.hasNext()) {
                    buoys.add(los.next().getParameterBuoyID());
                    System.out.println(buoys);
                }

                mAdapter = new ListAdapters(getActivity().getBaseContext(),buoys);
                mAdapter.notifyDataSetChanged();
                listView.setAdapter(mAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        mAdapter.notifyDataSetChanged();
                        Intent intent=new Intent(realtimedata.this.getActivity(),buoy_info.class);
                        intent.putExtra("buoys",position);
                        startActivity(intent);

                    }
                });
            }


            @Override
            public void onFailure(Call<BuoyDataAdapter> call, Throwable t) {
                Toast.makeText(getActivity(), "Translate failed", Toast.LENGTH_LONG).show();
            }
        });




        return v;
    }

}