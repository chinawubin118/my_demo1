package com.tuoyan.myapplication.previous.ui.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.tuoyan.myapplication.R;
import com.tuoyan.myapplication.previous.utils.LotteUtil;
import com.tuoyan.myapplication.previous.view.NoScrollListView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Radio1Fragment extends Fragment {
    private NoScrollListView list_view1;
    private List1Adapter adapter;

    public Radio1Fragment() {
        // Required empty public constructor
    }

    public void setListViewSelection(int selection) {
        if (null != list_view1) {
            list_view1.setSelection(selection);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_radio1, container, false);
    }

    private View loadingFooterView;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        list_view1 = (NoScrollListView) view.findViewById(R.id.list_view1);

        loadingFooterView = View.inflate(getActivity(), R.layout.list_view_loading_footer, null);
        list_view1.addFooterView(loadingFooterView);

        list_view1.setFocusable(false);

        if (null == adapter) {
            adapter = new List1Adapter(20);
        }
        list_view1.setAdapter(adapter);
    }

    private int lastItem = 0;

    @Override
    public void onResume() {
        super.onResume();
        list_view1.setOnScrollListener(new AbsListView.OnScrollListener() {
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                lastItem = firstVisibleItem + visibleItemCount - 1;
            }

            public void onScrollStateChanged(AbsListView view, int scrollState) {
//                if (lastItem == adapter.count && scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
//                    adapter.count += 10;
//                    adapter.notifyDataSetChanged();
//
//                    Log.i("json", "加载更多了.....");
//                }

                if (scrollState == SCROLL_STATE_IDLE) {
                    if (LotteUtil.isListViewReachBottomEdge(list_view1)) {
                        adapter.count += 10;
                        adapter.notifyDataSetChanged();

                        Log.i("json", "到底.....加载更多了.....");
                    }
                }
            }
        });
    }

    private class List1Adapter extends BaseAdapter {
        public int count = 0;

        public List1Adapter(int count) {
            this.count = count;
        }

        @Override
        public int getCount() {
            return this.count;
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

            holder.text_view.setText("List1.....List1.....List1.....");
            return convertView;
        }

        class ListViewholder {
            TextView text_view;
        }
    }
}
