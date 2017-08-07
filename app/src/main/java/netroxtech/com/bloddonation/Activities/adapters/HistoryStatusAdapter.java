package netroxtech.com.bloddonation.Activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import netroxtech.com.bloddonation.Activities.Models.MyStatus;
import netroxtech.com.bloddonation.R;

/**
 * Created by mac on 8/5/2017.
 */

public class HistoryStatusAdapter extends BaseAdapter {
    Context context;
    LayoutInflater mInflater;
    List<MyStatus> statusList;
    public HistoryStatusAdapter(Context context, List<MyStatus> myStatusList){
        this.context = context;
        this.statusList = myStatusList;
        mInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return statusList.size();
    }

    @Override
    public Object getItem(int i) {
        return statusList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder = new  ViewHolder();
        if(view==null) {
            view =mInflater.inflate(R.layout.list_status_history, null);
            holder.title =  (TextView)view.findViewById(R.id.bloodList_history_title);
            holder.date =  (TextView)view.findViewById(R.id.list_date);
            holder.bG =  (TextView)view.findViewById(R.id.list_status_bGroup);
            holder.city =  (TextView)view.findViewById(R.id.city_history);
            holder.address =  (TextView)view.findViewById(R.id.address_message);
            view.setTag(holder);
        }
        else{

            holder= (ViewHolder)view.getTag();
        }
        MyStatus  status = statusList.get(i);
        holder.title.setText(status.getTitle());
        holder.date.setText(status.getDate());
        holder.bG.setText(status.getBloodGroup());
        holder.city.setText(status.getCity());
        holder.address.setText(status.getAddress());

        return view;
    }
    private class ViewHolder{
        TextView title,address,date,city,bG;

    }
}
