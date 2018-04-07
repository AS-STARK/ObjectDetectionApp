package apoorv.detection;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.File;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private int imgClick = 1;
    private Button imgClickBtn;
    private Button pickerBtn;
    private String picCurrPath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imgClickBtn = (Button)findViewById(R.id.imgButton);
        imgClickBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                takePicture();
            }
        });
        pickerBtn = (Button)findViewById(R.id.pickerButton);
        pickerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent t = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(t, imgClick);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == imgClick && resultCode == RESULT_OK) {
            File photoFile = null;
            try {
                photoFile = createImgFile();
            } catch (IOException e) {
                e.printStackTrace();
                Log.d("Errorrrr", "File not created");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this, "com.example.android.fileprovider", photoFile);
                data.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                Log.d("TAG", "Image captured");
            }
        }
    }

    protected void takePicture()
    {
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if(i.resolveActivity(getPackageManager()) != null)
            startActivityForResult(i, imgClick);
    }

    private File createImgFile()throws IOException
    {
        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File imgFileName = File.createTempFile("JPEG_", ".jpg", storageDir);

        picCurrPath = imgFileName.getAbsolutePath();
        return imgFileName;
    }
}
