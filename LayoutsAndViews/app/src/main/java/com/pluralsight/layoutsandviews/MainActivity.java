package com.pluralsight.layoutsandviews;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity {
    static final String button2EnabledState = "button2EnabledState";
    static final String mainTextViewState = "mainTextViewState";

    public TextView getTextView() {
        if(mTextView == null) {
            mTextView = (TextView) findViewById(R.id.main_textView);
        }
        return mTextView;
    }

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initClickListeners();

        if(savedInstanceState != null) {
            restoreState(savedInstanceState);
        }
    }

    private void restoreState(Bundle savedInstanceState) {
        boolean isEnabled = savedInstanceState.getBoolean(button2EnabledState, false);
        String text = savedInstanceState.getString(mainTextViewState, "Hello World");

        Button button2 = (Button) findViewById(R.id.button_2);
        button2.setEnabled(isEnabled);

        getTextView().setText(text);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean handled = true;

        int id = item.getItemId();

        switch(id) {
            case R.id.activity_other:
                onActivityOtherSelected(item);
                break;
            case R.id.activity_exit:
                onActivityExitSelected(item);
                break;
            default:
                handled = super.onOptionsItemSelected(item);
        }

        return handled;
    }

    private void onActivityExitSelected(MenuItem item) {
        finish();
    }

    private void onActivityOtherSelected(MenuItem item) {
        Intent intent = new Intent(this, OtherActivity.class);
        startActivity(intent);
    }

    private void initClickListeners() {
        Button button1 = (Button) findViewById(R.id.button_1);
        Button button2 = (Button) findViewById(R.id.button_2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonOneClick((Button) view);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onButtonTwoClick((Button) view);
            }
        });
    }

    private void onButtonOneClick(Button button){
        Button button2 = (Button) findViewById(R.id.button_2);
        if(button2.isEnabled() == false){
            button2.setEnabled(true);
        }
        getTextView().setText("Button 1 Click");
    }

    private void onButtonTwoClick(Button button) {
        getTextView().setText("Button 2 Click");
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

        Button button2 = (Button) findViewById(R.id.button_2);

        outState.putBoolean(button2EnabledState, button2.isEnabled());
        outState.putString(mainTextViewState,mTextView.getText().toString());
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
