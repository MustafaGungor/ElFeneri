package com.example.speakingjoy.elfeneri;

import android.graphics.Color;
import android.hardware.Camera;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;
//Mustafa Güngör
public class MainActivity extends Activity {
    ImageButton light;
    Button btn;
    int a=0;
    Camera cam = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        light=(ImageButton)findViewById(R.id.buton);
        btn=(Button)findViewById(R.id.button);
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        relativeLayout.setBackgroundColor(Color.parseColor("#F80D0D"));
        light.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(a==0){
                    FenerAc();
                }
                else if(a==1){
                    FenerKapat();
                 }
             }
         });
    }
    public void FenerKapat() {
        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        btn.setText("Fener Kapatıldı");
        try {
            relativeLayout.setBackgroundColor(Color.parseColor("#000000"));
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

                cam.stopPreview();
                cam.release();
                cam = null;
                a = 0;

            }

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this,"Işığı kapatırken problem oluştu", Toast.LENGTH_SHORT).show();
        }

    }

    public void FenerAc() {

        RelativeLayout relativeLayout=(RelativeLayout)findViewById(R.id.relative);
        btn.setText("Fener Açıldı");
        try {

            relativeLayout.setBackgroundColor(Color.parseColor("#FAE123"));
            if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH)) {

                cam = Camera.open();
                cam.cancelAutoFocus();

                Parameters p = cam.getParameters();
                p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

                cam.setParameters(p);
                cam.startPreview();

                a=1;

            }

        } catch (Exception e) {

            e.printStackTrace();
            Toast.makeText(this,"Işığı açarken problem oluştu.", Toast.LENGTH_SHORT).show();

        }

    }
}

