package com.example.fluidsynthandroidhelloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    // Used to load the 'native-lib' library on application startup.
    static {
        System.loadLibrary("native-lib");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            String tempSoundfontPath = copyAssetToTmpFile("sndfnt.sf2");
            loadSoundFont(tempSoundfontPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        findViewById(R.id.randomPlayBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int randomKey = new Random().nextInt(108 - 21) + 21;
                TextView randomKeyTextView = findViewById(R.id.randomKey);
                randomKeyTextView.setText(randomKey + "");
                playNote(randomKey);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        release();
    }

    private String copyAssetToTmpFile(String fileName) throws IOException {
        try (InputStream is = getAssets().open(fileName)) {
            String tempFileName = "tmp_" + fileName;
            try (FileOutputStream fos = openFileOutput(tempFileName, Context.MODE_PRIVATE)) {
                int bytes_read;
                byte[] buffer = new byte[4096];
                while ((bytes_read = is.read(buffer)) != -1) {
                    fos.write(buffer, 0, bytes_read);
                }
            }
            return getFilesDir() + "/" + tempFileName;
        }
    }

    /**
     * A native method that is implemented by the 'native-lib' native library,
     * which is packaged with this application.
     */
    public native void loadSoundFont(String soundfontPath);

    public native void playNote(int key);

    public native void release();
}
