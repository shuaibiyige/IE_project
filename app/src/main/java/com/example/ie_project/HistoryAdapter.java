package com.example.ie_project;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends BaseAdapter
{
    private List<HistoryList> mData;
    private LayoutInflater mInflater;

    public HistoryAdapter(LayoutInflater inflater, List<HistoryList> data)
    {
        mInflater = inflater;
        mData = data;
    }

    @Override
    public int getCount() {
        return mData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertview, ViewGroup viewGroup)
    {
        View historyView = mInflater.inflate(R.layout.history_listview, null);

        HistoryList historyList = mData.get(position);

        TextView date = historyView.findViewById(R.id.history_date);
        TextView name = historyView.findViewById(R.id.history_name);
        View color = historyView.findViewById(R.id.history_color);
        RatingBar ratingBar = historyView.findViewById(R.id.history_star);

        date.setText(historyList.getDate());
        name.setText(historyList.getName());
        ratingBar.setRating(historyList.getStars());
        color.setBackgroundColor(historyList.getColor());

        return historyView;
    }
}
