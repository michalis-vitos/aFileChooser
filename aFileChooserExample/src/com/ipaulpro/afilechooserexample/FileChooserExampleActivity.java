/*
 * Copyright (C) 2012 Paul Burke
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.ipaulpro.afilechooserexample;

import android.app.ActionBar;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;
import com.ipaulpro.afilechooser.FileInfo;
import com.ipaulpro.afilechooser.utils.FileUtils;

/**
 * @author paulburke (ipaulpro)
 */
public class FileChooserExampleActivity extends Activity {

    private static final String TAG = "FileChooserExampleActivity";

    private static final int REQUEST_CODE = 6384; // onActivityResult request
                                                  // code

    private ImageView imageView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        imageView = new ImageView(this);
        imageView.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));
        imageView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                showChooser();
            }
        });

        setContentView(imageView);
    }

    private void showChooser() {
        // Use the GET_CONTENT intent from the utility class
        Intent target = FileUtils.createGetContentIntent(null);
        // Create the chooser Intent
        Intent intent = Intent.createChooser(
                target, "title");
        try {
            startActivityForResult(intent, REQUEST_CODE);
        } catch (ActivityNotFoundException e) {
            // The reason for the existence of aFileChooser
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case REQUEST_CODE:
                // If the file selection was successful
                if (resultCode == RESULT_OK) {
                    if (data != null) {
                        // Get the URI of the selected file
                        final Uri uri = data.getData();
                        Log.i(TAG, "Uri = " + uri.toString());
                        try {
                            // Get the file path from the URI
                            FileInfo fileInfo = FileUtils.getFileInfo(this, uri);
                            Toast.makeText(FileChooserExampleActivity.this,
                                    "File Selected: " + fileInfo.getDisplayName() + " size: " + fileInfo.getSize(), Toast.LENGTH_LONG).show();

                            if (!fileInfo.isExternal()) {
                                imageView.setImageBitmap(BitmapFactory.decodeFile(fileInfo.getPath()));
                            } else {
                                Bitmap bitmap = MediaStore.Images.Media.getBitmap(
                                        getApplicationContext().getContentResolver(),
                                        uri);
                                imageView.setImageBitmap(bitmap);
                              //  imageView.setImageBitmap(BitmapFactory.decodeStream(fileInfo.getInputStream()));
                            }
                        } catch (Exception e) {
                            Log.e("FileSelectorTestActivity", "File select error", e);
                        }
                    }
                }
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
