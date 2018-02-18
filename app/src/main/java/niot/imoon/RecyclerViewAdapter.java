package niot.imoon;

/**
 * Created by arnab on 12/18/2017.
 */

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    Context context;
    List<buoy_parameters> MainImageUploadInfoList;

    public RecyclerViewAdapter(Context context, List<buoy_parameters> TempList) {

        this.MainImageUploadInfoList = TempList;

        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_items, parent, false);

        ViewHolder viewHolder = new ViewHolder(view);
        //notifyDataSetChanged();
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        buoy_parameters buoyParameters = MainImageUploadInfoList.get(position);

        holder.parameter.setText(buoyParameters.getParameter());

        holder.value.setText(buoyParameters.getValue());

    }

    @Override
    public int getItemCount() {

        return MainImageUploadInfoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public TextView parameter;
        public TextView value;

        public ViewHolder(View itemView) {

            super(itemView);

            parameter = (TextView) itemView.findViewById(R.id.parameter);

            value = (TextView) itemView.findViewById(R.id.value);
        }
    }
}
