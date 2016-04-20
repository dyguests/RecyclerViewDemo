package com.fanhl.recyclerview.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanhl.recyclerview.R;
import com.fanhl.recyclerview.model.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fanhl on 16/4/20.
 */
public class LinearLayoutManagerAdapter extends RecyclerView.Adapter<LinearLayoutManagerAdapter.ViewHolder> {
    private final Context    context;
    private final List<User> list;

    public LinearLayoutManagerAdapter(Context context) {
        this.context = context;
        this.list = new ArrayList<>();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_linear, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    //-----------------------数据增删相关-------------------------------

    public void addItem(User item) {
        int positionStart = list.size();
        list.add(item);
        notifyItemInserted(positionStart);
    }

    public void addItems(List<User> items) {
        int positionStart = list.size();
        list.addAll(items);
        notifyItemRangeInserted(positionStart, items.size());
    }

    public void clear() {
        int itemCount = list.size();
        list.clear();
        notifyItemRangeRemoved(0, itemCount);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTextView;
        private       User     data;

        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = ((TextView) itemView.findViewById(R.id.text_view));
        }

        public void bind(User data) {
            mTextView.setText(data.getName());

            this.data = data;
        }
    }
}
