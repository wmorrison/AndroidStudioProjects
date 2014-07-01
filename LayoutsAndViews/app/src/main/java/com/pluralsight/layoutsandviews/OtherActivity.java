package com.pluralsight.layoutsandviews;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.pluralsight.layoutsandviews.R;

public class OtherActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);

        initEventListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.other, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;

        int id = item.getItemId();

        switch(id) {
            case R.id.action_close:
                onCloseActionSelected(item);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void onCloseActionSelected(MenuItem item) {
        finish();
    }

    public void initEventListeners() {
        final Button changeButton = (Button) findViewById(R.id.changeButton);
        changeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleChangeButtonClick(changeButton);
            }
        });
    }

    private void handleChangeButtonClick(Button button) {
        TextView textView = (TextView) findViewById(R.id.changeable_textView);
        textView.setText("Change Button Clicked!");
    }



    //<editor-fold desc="lifecycle callbacks">

    @Override
    protected void onStart() {
        super.onStart();
        LogHelper.Log(this, "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogHelper.Log(this, "onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogHelper.Log(this, "onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        LogHelper.Log(this, "onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogHelper.Log(this, "onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogHelper.Log(this, "onDestroy");
    }

    //</editor-fold>
}
