package com.spellofstar.expandablelistviewexample_forsnsreply.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.spellofstar.expandablelistviewexample_forsnsreply.R;
import com.spellofstar.expandablelistviewexample_forsnsreply.listener.ReplyListener;
import com.spellofstar.expandablelistviewexample_forsnsreply.model.ReplyData;
import com.spellofstar.expandablelistviewexample_forsnsreply.model.ReplyHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ReplyAdapter extends BaseExpandableListAdapter {
    private List<ReplyData> parentReplys;
    private HashMap<Integer, ArrayList<ReplyData>> childReplys;
    private HashMap<Integer, ReplyHash> replyHashs;

    private ReplyListener replyListener;

    private Context context;

    public void setReplyListener(ReplyListener replyListener) {
        this.replyListener = replyListener;
    }

    public ReplyAdapter(Context context, List<ReplyData> parentReplys, HashMap<Integer, ArrayList<ReplyData>> childReplys, HashMap<Integer, ReplyHash> replyHashs) {
        this.context = context;
        this.parentReplys = parentReplys;
        this.childReplys = childReplys;
        this.replyHashs = replyHashs;
    }


    @Override
    public int getGroupCount() {
        return parentReplys.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return childReplys.get(replyHashs.get(parentReplys.get(groupPosition).getId()).getParentId()).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return parentReplys.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return childReplys.get(replyHashs.get(parentReplys.get(groupPosition).getId()).getParentId()).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean b, View view, ViewGroup viewGroup) {
        ReplyData replyData = (ReplyData) getGroup(groupPosition);
        ParentHolder holder;

        if(view == null){
            holder = new ParentHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_reply_parent, null);
            holder.userNameTextView = view.findViewById(R.id.userNameTextView);
            holder.contentTextView = view.findViewById(R.id.contentTextView);
            view.setTag(holder);
        } else {
            holder = (ParentHolder) view.getTag();
        }
        holder.userNameTextView.setText(replyData.getUserName());
        holder.contentTextView.setText(replyData.getContent());
        return view;
    }

    @Override
    public View getChildView(final int groupPosition, int childPosition, boolean b, View view, ViewGroup viewGroup) {
        ReplyData replyData = (ReplyData) getChild(groupPosition, childPosition);
        ChildHolder holder;

        if(view == null){
            holder = new ChildHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_reply_child, null);
            holder.contentTextView = view.findViewById(R.id.contentTextView);
            holder.userNameTextView = view.findViewById(R.id.userNameTextView);
            holder.moreTextView = view.findViewById(R.id.moreTextView);
            holder.replyLayout = view.findViewById(R.id.replyLayout);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }

        if(replyData.getType() == ReplyData.MORE){
            holder.moreTextView.setVisibility(View.VISIBLE);
            holder.replyLayout.setVisibility(View.GONE);

            holder.moreTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    replyListener.onMoreClick(groupPosition);
                }
            });

        } else if(replyData.getType() == ReplyData.CHILD){
            holder.moreTextView.setVisibility(View.GONE);
            holder.replyLayout.setVisibility(View.VISIBLE);

            holder.userNameTextView.setText(replyData.getUserName());
            holder.contentTextView.setText(replyData.getContent());
        }

        return view;
    }

    @Override
    public boolean isChildSelectable(int i, int i1) {
        return false;
    }

    private class ParentHolder{
        private TextView userNameTextView;
        private TextView contentTextView;
    }

    private class ChildHolder{
        private TextView userNameTextView;
        private TextView contentTextView;
        private TextView moreTextView;
        private LinearLayout replyLayout;
    }
}
