package com.pluralsight.activitywithcameraresult;

import android.app.Activity;
import android.media.Image;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Will on 6/22/2014.
 */
public class PhotoHelper {

    public static String LOG_TAG = "PhotoHelper Failure";

    public static Uri generateTimeStampPhotoFileUri() {
        Uri photoFileUri = null;

        File outputDir = getPhotoDirectory();

        if (outputDir != null) {
            String timeStamp = new SimpleDateFormat("yyyMMDD_HHmmss").format(new Date());
            String photoFileName = "IMG_" + timeStamp + ".jpg";

            File photoFile = new File(outputDir, photoFileName);

            photoFileUri = Uri.fromFile(photoFile);
        }

        return photoFileUri;
    }

    public static File getPhotoDirectory() {
        File outputDir = null;

        String externalStorageState = Environment.getExternalStorageState();
        if (externalStorageState.equals(Environment.MEDIA_MOUNTED)) {
            File pictureDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

            outputDir = new File(pictureDir, "Pluralsight");

            if (!outputDir.exists()) {
                if (!outputDir.mkdirs()) {
                    Log.e(LOG_TAG, "Failed to create directory: " + outputDir.getAbsolutePath());
                    outputDir = null;
                }
            }
        }

        return outputDir;
    }

    public static void addPhotoToMediaStoreAndDisplayThumbnail(String pathName, Activity activity, ImageView imageView) {
        final ImageView thumbnailImageView = imageView;
        final Activity thumbnailActivity = activity;

        String[] filesToScan = {pathName};

        MediaScannerConnection.scanFile(thumbnailActivity, filesToScan, null);
    }
}
