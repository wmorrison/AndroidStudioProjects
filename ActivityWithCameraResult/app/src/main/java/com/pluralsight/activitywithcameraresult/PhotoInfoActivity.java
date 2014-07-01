package com.pluralsight.activitywithcameraresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.pluralsight.activitywithcameraresult.R;

public class PhotoInfoActivity extends Activity {

    public static final String CLASS_NAME_EXTRA = "classNameExtra";
    public static final String PERSON_NAME_EXTRA = "personNameExtra";
    public static final String PERSON_EMAIL_EXTRA = "personEmailExtra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_info);

        setupViews();
    }

    private void setupViews() {
        Button doneButton = (Button) findViewById(R.id.done_button);
        doneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDoneButton((Button) view);
            }
        });
    }

    private void handleDoneButton(Button view) {
        String className = getSelectedValueOfRadioGroup(R.id.classSelectGroup);
        String personName = getEditTextView(R.id.your_name_editText);
        String personEmail = getEditTextView(R.id.email_editText);

        Intent resultIntent = new Intent();
        resultIntent.putExtra(CLASS_NAME_EXTRA, className);
        resultIntent.putExtra(PERSON_NAME_EXTRA, personName);
        resultIntent.putExtra(PERSON_EMAIL_EXTRA, personEmail);

        setResult(RESULT_OK, resultIntent);
        finish();
    }

    private String getEditTextView(int textViewId) {
        EditText editText = (EditText) findViewById(textViewId);
        Editable editable = editText.getText();
        return editable != null ? editable.toString() : "";
    }

    private String getSelectedValueOfRadioGroup(int groupId) {
        RadioGroup radioGroup = (RadioGroup) findViewById(groupId);

        int radioButtonId = radioGroup.getCheckedRadioButtonId();
        RadioButton radioButton = (RadioButton) findViewById(radioButtonId);

        return (String) radioButton.getText();
    }
}
