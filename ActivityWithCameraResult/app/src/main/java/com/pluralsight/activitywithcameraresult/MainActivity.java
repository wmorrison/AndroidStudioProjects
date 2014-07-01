package com.pluralsight.activitywithcameraresult;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    static final int PROVIDE_INFO_REQUEST_CODE = 1000;
    static final int TAKE_PICTURE_REQUEST_CODE = 1010;


    Uri photoPathUri;
    String photoPathName;

    //<editor-fold desc="View Field Getters">

    public TextView getClassNameTextView() {
        if (classNameTextView == null) {
            classNameTextView = (TextView) findViewById(R.id.classNameTextView);
        }
        return classNameTextView;
    }

    public TextView getPersonNameTextView() {
        if (personNameTextView == null) {
            personNameTextView = (TextView) findViewById(R.id.personNameTextView);
        }
        return personNameTextView;
    }

    public TextView getEmailTextView() {
        if (emailTextView == null) {
            emailTextView = (TextView) findViewById(R.id.emailTextView);
        }
        return emailTextView;
    }

    public ImageView getThumbNailImageView() {
        if (thumbNailImageView == null) {
            thumbNailImageView = (ImageView) findViewById(R.id.thumbnailImagePreview);
        }
        return thumbNailImageView;
    }

    //</editor-fold>

    //<editor-fold desc="Private View Fields - Access Only Through Getters">

    private TextView classNameTextView;
    private TextView personNameTextView;
    private TextView emailTextView;
    private ImageView thumbNailImageView;

    //</editor-fold>

    public Uri getPhotoPathUri() {
        return photoPathUri;
    }

    public String getPhotoPathName() {
        return photoPathName;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();
    }

    private void setupViews() {
        Button moreInfomationButton = (Button) findViewById(R.id.photoInfoButton);
        moreInfomationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleMoreInformationButton((Button) view);
            }
        });

        Button takePictureButton = (Button) findViewById(R.id.takePictureButton);
        takePictureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleTakePictureButton((Button) view);
            }
        });

        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleSendButton((Button) view);
            }
        });
    }

    private void handleMoreInformationButton(Button button) {
        Intent intent = new Intent(this, PhotoInfoActivity.class);
        startActivityForResult(intent, PROVIDE_INFO_REQUEST_CODE);
    }

    private void handleTakePictureButton(Button button) {
        photoPathUri = PhotoHelper.generateTimeStampPhotoFileUri();

        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, photoPathUri);
        startActivityForResult(intent, TAKE_PICTURE_REQUEST_CODE);
    }

    private void handleSendButton(Button button) {

    }

    public void doTransfer() {
        String classNameToSend = (String) getClassNameTextView().getText();
        String personNameToSend = (String) getPersonNameTextView().getText();
        String emailToSend = (String) getEmailTextView().getText();
        String photoPathName = (String) photoPathUri.getPath();

        // Pretend to send the values wherever they are suppose to go
        Toast.makeText(this, "Transferring", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent resultIntent) {
        switch(requestCode) {
            case PROVIDE_INFO_REQUEST_CODE:
                handlePhotoInfoResult(resultCode, resultIntent);
                break;
            case TAKE_PICTURE_REQUEST_CODE:
                handleTakePictureResult(resultCode, resultIntent);
                break;

        }
    }

    private void handleTakePictureResult(int resultCode, Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            String photoPathName = photoPathUri.getPath();
        }
        else {
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
    }

    private void handlePhotoInfoResult(int resultCode, Intent resultIntent) {
        if (resultCode == RESULT_OK) {
            String className = resultIntent.getStringExtra(PhotoInfoActivity.CLASS_NAME_EXTRA);
            String personName = resultIntent.getStringExtra(PhotoInfoActivity.PERSON_NAME_EXTRA);
            String personEmail = resultIntent.getStringExtra(PhotoInfoActivity.PERSON_EMAIL_EXTRA);

            getClassNameTextView().setText(className);
            getPersonNameTextView().setText(personName);
            getEmailTextView().setText(personEmail);
        } else {
            Toast.makeText(this, "User Canceled", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
