package com.example.dell.dialog_menu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class ContextMenuActivity extends AppCompatActivity {

    private String[] nums = new String[]{"One","Two","Three","Four","Five"};
    private int ImageId = R.drawable.robot;
    int pos;

    List<Map<String, Object>> items = new ArrayList<Map<String, Object>>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context_menu);

        final ListView listview = (ListView)findViewById(R.id.list_view);

        for (int i = 0 ; i < nums.length; i++) {
            Map<String, Object> item = new HashMap<String, Object>();
            item.put("num", nums[i]);
            item.put("image", ImageId);
            items.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, items, R.layout.simple_item,
                new String[]{"num", "image"}, new int[]{R.id.txt, R.id.image});

        listview.setAdapter(simpleAdapter);

        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
        listview.setMultiChoiceModeListener(new AbsListView.MultiChoiceModeListener() {

            @Override
            public void onItemCheckedStateChanged(ActionMode mode, int position, long id, boolean checked) {
                if(listview.getCheckedItemCount() == 1)
                    mode.setSubtitle("1 item selected");
                else
                    mode.setSubtitle(listview.getCheckedItemCount() + " items selected");

            }

            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu) {
                mode.setTitle("Select Items");

                mode.setSubtitle(pos+"selected");

                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
                return false;
            }

            @Override
            public void onDestroyActionMode(ActionMode mode) {

            }

        });
    }
}
