package com.spellofstar.expandablelistviewexample_forsnsreply;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.spellofstar.expandablelistviewexample_forsnsreply.adapter.ReplyAdapter;
import com.spellofstar.expandablelistviewexample_forsnsreply.listener.ReplyListener;
import com.spellofstar.expandablelistviewexample_forsnsreply.model.ReplyData;
import com.spellofstar.expandablelistviewexample_forsnsreply.model.ReplyHash;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<ReplyData> parentReplys;
    private HashMap<Integer, ArrayList<ReplyData>> childReplys; // for having replys from each parentReplys
    private HashMap<Integer, ReplyHash> replyHashs; // for manage childs counts and page


    private ExpandableListView replyListView;
    private ReplyAdapter replyAdapter;

    private ReplyListener replyListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        replyListView = findViewById(R.id.replyListview);

        parentReplys = new ArrayList<>();
        childReplys = new HashMap<>();
        replyHashs = new HashMap<>();

        // ****************************sample source ************************* //
        parentReplys.add(new ReplyData(1, "onegu","i like fruit", 5));
        childReplys.put(parentReplys.get(0).getId(), new ArrayList<ReplyData>());
        childReplys.get(1).add(new ReplyData(0, "chanhee", "i like banana"));
        childReplys.get(1).add(new ReplyData(1, "taewim", "i dont like anything"));
        childReplys.get(1).add(new ReplyData(2, "youngwoo" , "me too"));

        childReplys.get(1).add(new ReplyData(ReplyData.MORE));

        replyHashs.put(parentReplys.get(0).getId(), new ReplyHash(parentReplys.get(0).getId(), 0, parentReplys.get(0).getChildCounts()));

        parentReplys.add(new ReplyData(2, "jun", "hi", 10));
        childReplys.put(parentReplys.get(1).getId(), new ArrayList<ReplyData>());
        childReplys.get(2).add(new ReplyData(0, "onegu", "hello"));
        childReplys.get(2).add(new ReplyData(1, "chanhee", "nice meet to you"));
        childReplys.get(2).add(new ReplyData(2, "youngwoo", "me too"));
        childReplys.get(2).add(new ReplyData(3, "jinyoung", "where is here?"));
        childReplys.get(2).add(new ReplyData(4, "taewun", "i dont know"));

        childReplys.get(2).add(new ReplyData(ReplyData.MORE));

        // ****************************sample source ************************* //

        replyHashs.put(parentReplys.get(1).getId(), new ReplyHash(parentReplys.get(1).getId(), 0, parentReplys.get(1).getChildCounts()));

        replyAdapter = new ReplyAdapter(this, parentReplys, childReplys, replyHashs);

        // ****************************add more child reply with pagination ************************* //
        replyListener = new ReplyListener() {
            @Override
            public void onMoreClick(int groupPosition) {
                ReplyData parentReply = parentReplys.get(groupPosition);

                childReplys.get(parentReply.getId()).remove(childReplys.get(parentReply.getId()).size()-1);
                replyHashs.get(parentReply.getId()).setPage(replyHashs.get(parentReply.getId()).getPage() + 1);
                for (int i = 0; i < 3; i++)
                    childReplys.get(parentReply.getId()).add(new ReplyData(childReplys.get(parentReply.getId()).size(), "tesrUser", "test "+ i));

                if(replyHashs.get(parentReply.getId()).getCount() >= childReplys.get(parentReply.getId()).size()) {
                    childReplys.get(parentReply.getId()).add(new ReplyData(ReplyData.MORE));
                }
                replyAdapter.notifyDataSetChanged();

            }
        };
        // ****************************add more child reply with pagination ************************* //

        replyAdapter.setReplyListener(replyListener);
        replyListView.setAdapter(replyAdapter);



        for(int i = 0; i< parentReplys.size();i++){
            replyListView.expandGroup(i);
        }

        replyListView.setGroupIndicator(null);
        replyListView.setChildIndicator(null);

        replyListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                return true;
            }
        });
    }
}
