package com.tuoyan.myapplication.previous.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.view.NoScrollListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Radio2Fragment extends Fragment {
    private NoScrollListView list_view2;
    private List1Adapter adapter;

    public Radio2Fragment() {
        // Required empty public constructor
    }

    public void setListViewSelection(int selection) {
        if (null != list_view2) {
            list_view2.setSelection(selection);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_view2 = (NoScrollListView) view.findViewById(R.id.list_view2);

        list_view2.setFocusable(false);

        if (null == adapter) {
            adapter = new List1Adapter();
        }
        list_view2.setAdapter(adapter);
    }

    private class List1Adapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 20;
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
        public View getView(int position, View convertView, ViewGroup parent) {
            ListViewholder holder = null;
            if (null == convertView) {
                convertView = LayoutInflater.from(getActivity()).inflate(R.layout.item_radio_fragment, null);
                holder = new ListViewholder();
                holder.text_view = (TextView) convertView.findViewById(R.id.text_view);
                convertView.setTag(holder);
            } else {
                holder = (ListViewholder) convertView.getTag();
            }

            holder.text_view.setText("List2.....List2.....List2.....");
            return convertView;
        }

        class ListViewholder {
            TextView text_view;
        }
    }
}
