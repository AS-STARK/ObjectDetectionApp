package apoorv.detection;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.File;

public class UploadActivity extends AppCompatActivity {

    private ImageView imgView;
    private Button uploadBtn;
    protected String filePat="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload);

        imgView = (ImageView)findViewById(R.id.imgView);
        Bundle b = getIntent().getExtras();

        filePat = b.getString("filePath");
        Log.d("picpath", filePat);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(filePat,bmOptions);
        //bitmap = Bitmap.createScaledBitmap(bitmap,100,100,true);
        if(bitmap==null)
            Log.d("errrr", "Bitmap still null");
        Log.d("bitmap",bitmap.toString());
        imgView.setImageBitmap(bitmap);
        imgView.setVisibility(View.VISIBLE);
    }
}
