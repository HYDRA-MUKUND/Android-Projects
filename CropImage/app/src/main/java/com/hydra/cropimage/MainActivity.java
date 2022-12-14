package com.hydra.cropimage;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import android.widget.TextView;

import com.mikhaellopez.circularimageview.CircularImageView;
import com.theartofdev.edmodo.cropper.CropImageView;


public class MainActivity extends AppCompatActivity {
    private CircularImageView circularImageView;
    TextView button;
    CropImageView cropImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        circularImageView = (CircularImageView) findViewById(R.id.cir);
        button = (TextView) findViewById(R.id.iiii);
        cropImageView=(CropImageView)findViewById(R.id.cropImageView);



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageeeee();
            }
        });
    }

    private void imageeeee() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK && data != null) {

            Uri uri = data.getData();
            cropImageView.setImageUriAsync(uri);


        }

    }
    public void lastact(View view){
        circularImageView.setImageBitmap(cropImageView.getCroppedImage());
        cropImageView.setVisibility(View.GONE);
    }

}