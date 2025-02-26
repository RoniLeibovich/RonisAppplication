package com.roni2024.ronisfirstappplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class NoamActivity2 extends AppCompatActivity {

    Button selectImageButton;
    ImageView imageView;

    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;
    private static final int STORAGE_PERMISSION_REQUEST_CODE = 101;

    // ActivityResultLauncher to handle the image picking result
    ActivityResultLauncher<Intent> activityResultLauncher =
            registerForActivityResult(
                    new ActivityResultContracts.StartActivityForResult(),
                    new ActivityResultCallback<ActivityResult>() {
                        @Override
                        public void onActivityResult(ActivityResult result) {
                            if (result.getResultCode() == RESULT_OK) {
                                Intent data = result.getData();
                                if (data != null) {
                                    // Display the selected image in the ImageView
                                    imageView.setImageURI(data.getData());
                                    Toast.makeText(NoamActivity2.this, "תמונה נבחרה בהצלחה!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    }
            );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_noam2);
        // Check if camera permission is granted
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Request camera permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, CAMERA_PERMISSION_REQUEST_CODE);
        }

        // Check if external storage permission is granted for reading images
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            // Request storage permission if not granted
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_REQUEST_CODE);
        }

        // Connect the button and image view
        selectImageButton = findViewById(R.id.selectImageButton);
        imageView = findViewById(R.id.imageView);

        selectImageButton.setOnClickListener(v -> {
            // Create an intent to open the gallery for picking an image
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            // Launch the gallery via the ActivityResultLauncher
            activityResultLauncher.launch(intent);
        });
    }

    // Handle the permission request results
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case CAMERA_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "הרשאת מצלמה אושרה", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "הרשאת מצלמה נדחתה", Toast.LENGTH_SHORT).show();
                }
                break;

            case STORAGE_PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "הרשאת אחסון אושרה", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "הרשאת אחסון נדחתה", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
}
