package daylight.com.daylightmobilepakokku.customdialog;

import android.app.Dialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import daylight.com.daylightmobilepakokku.R;


public class About extends Dialog implements View.OnClickListener {
    Context context;
    public About(Context c){
        super(c);
        this.context=c;
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about);
    }


    @Override
    public void onClick(View v) {

    }
}
