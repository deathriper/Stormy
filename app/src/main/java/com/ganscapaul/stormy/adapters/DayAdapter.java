package com.ganscapaul.stormy.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ganscapaul.stormy.R;
import com.ganscapaul.stormy.weather.Day;

import static com.ganscapaul.stormy.ui.MainActivity.TAG;
//DayAdapter to display all our day data in list format

public class DayAdapter extends BaseAdapter {

    private Context mContext;
    private Day[] mDays;

    public DayAdapter(Context context, Day[] days){
        mContext = context;
        mDays = days;
    }

    @Override
    public int getCount() {
        return mDays.length;
    }

    @Override
    public Object getItem(int position) {
        return mDays[position];
    }

    @Override
    public long getItemId(int position) {
        return 0; //tag items for easy reference
    }
    //recycle the views for smooth scrolling
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            //brand new View
            convertView = LayoutInflater.from(mContext).inflate(R.layout.daily_list_item, null);
            holder = new ViewHolder();
            //what the list view will display
            holder.iconImageView = convertView.findViewById(R.id.iconImageView);
            holder.temperatureLabel = convertView.findViewById(R.id.temperatureLabel);
            holder.dayLabel = convertView.findViewById(R.id.dayNameLabel);

            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Day day = mDays[position];

        holder.iconImageView.setImageResource(day.getIconId());
        try {
            holder.temperatureLabel.setText(day.getTemperatureMax() + "");}
        catch (Exception e){
            Log.e(TAG, "Fatal Exception", e);
        }

        if (position == 0) {
            holder.dayLabel.setText("Today");
        } else {
            holder.dayLabel.setText(day.getDayOfTheWeek());
        }

        return convertView;
    }

    //helper class holding the views
    private static class ViewHolder {
        ImageView iconImageView; // public by default
        TextView temperatureLabel;
        TextView dayLabel;
    }

}
