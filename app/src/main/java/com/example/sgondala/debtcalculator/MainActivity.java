package com.example.sgondala.debtcalculator;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.inputmethod.EditorInfo;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    EditText name, cost;
    ListView itemList;
    MyAdapter adapter;
    Context ctx = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemList = (ListView) findViewById(R.id.itemsList);
        name = (EditText) findViewById(R.id.nameField);
        cost = (EditText) findViewById(R.id.costField);
        adapter = new MyAdapter(ctx, R.layout.single_list_item);
        itemList.setAdapter(adapter);
        itemList.setTranscriptMode(AbsListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);
        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                itemList.setSelection(adapter.getCount()-1);
            }
        });
        cost.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled= false;
                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String personName = name.getText().toString();
                    String personCostString = cost.getText().toString();
                    if(!personName.isEmpty() && !personCostString.isEmpty()) {
                        int personCost = Integer.parseInt(personCostString);
                        adapter.add(new Pair(personName, personCost));
                        name.setText("");
                        cost.setText("");
                    }
                    handled = true;
                }
                return handled;
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
