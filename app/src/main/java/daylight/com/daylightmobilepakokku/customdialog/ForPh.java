package daylight.com.daylightmobilepakokku.customdialog;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;

import daylight.com.daylightmobilepakokku.R;

public class ForPh extends Dialog implements DialogInterface.OnClickListener {
    Context c;
    Activity a;
    public ForPh(Context context,Activity activity) {
        super(context);
        this.c=context;
        this.a=activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_forph);
        TextView got=(TextView)findViewById(R.id.got);
        got.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        ImageView one1=findViewById(R.id.one1);
        one1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Phonecall("tel:09771616178");
                                    }
                                }
        );
        ImageView one2=findViewById(R.id.one2);
        one2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Phonecall("tel:09771616178");
                                    }
                                }
        );
        //end shop 1
        ImageView two1=findViewById(R.id.two1);
        two1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Phonecall("tel:09771616178");
                                    }
                                }
        );
        ImageView two2=findViewById(R.id.two2);
        two2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Phonecall("tel:09771616178");
                                    }
                                }
        );
        //end shop 2

        ImageView three1=findViewById(R.id.three1);
        three1.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Phonecall("tel:09771616178");
                                    }
                                }
        );
        ImageView three2=findViewById(R.id.three2);
        three2.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Phonecall("tel:09771616178");
                                    }
                                }
        );
        //end shop 3


    }
    public void Phonecall(String s){
        Intent i=new Intent(Intent.ACTION_CALL);
        i.setData(Uri.parse(s));
        if(ActivityCompat.checkSelfPermission(c, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(a,new String[]{Manifest.permission.CALL_PHONE},1);
        }
        else{
            a.startActivity(i);
        }
    }


    @Override
    public void onClick(DialogInterface dialog, int which) {

    }
}
