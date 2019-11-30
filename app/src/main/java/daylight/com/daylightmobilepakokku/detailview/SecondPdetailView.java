package daylight.com.daylightmobilepakokku.detailview;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.ArrayList;

import daylight.com.daylightmobilepakokku.R;
import daylight.com.daylightmobilepakokku.customdialog.ForPh;
import daylight.com.daylightmobilepakokku.database.DataBaseHelper;
import daylight.com.daylightmobilepakokku.viewmodel.NewProdcutModel;
import daylight.com.daylightmobilepakokku.viewmodel.SecondProdcutModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link SecondPdetailView.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link SecondPdetailView#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SecondPdetailView extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    boolean test=false;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SecondPdetailView() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PdetailView.
     */
    // TODO: Rename and change types and number of parameters
    public static SecondPdetailView newInstance(String param1, String param2) {
        SecondPdetailView fragment = new SecondPdetailView();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Light_NoTitleBar);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_pdetail_view, container, false);
        ImageView back=(ImageView)view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });;
        final SecondProdcutModel pdata=(SecondProdcutModel) getArguments().getSerializable("Pdetails");

        DataBaseHelper db=new DataBaseHelper(getContext());
        ArrayList<SecondProdcutModel> allfavproduct=new ArrayList<>();
        test = false;
        try {
            allfavproduct=db.getAllSProductData();
            for(int i=0;i<allfavproduct.size();i++){
                if(allfavproduct.get(i).getId().equals(pdata.getId())){
                    test=true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        final ImageView before=view.findViewById(R.id.before);
        final ImageView after=view.findViewById(R.id.after);
    if(test){
        after.setVisibility(View.VISIBLE);
        before.setVisibility(View.GONE);
    }
        ImageView ppimg=view.findViewById(R.id.ppimg);
        Glide.with(getContext()).load(pdata.getImgurl()).into(ppimg);
        TextView ptitle,model,price,pdetail,sname;
        ptitle=view.findViewById(R.id.pname);
        ptitle.setText(pdata.getPnmae());
        model=view.findViewById(R.id.ptitle);
        model.setText(pdata.getPmodel());
        price=view.findViewById(R.id.price);
        price.setText(pdata.getPrice()+"Ks");
        sname=view.findViewById(R.id.shopname);
        sname.setText(pdata.getShop().toUpperCase());
        pdetail=view.findViewById(R.id.detail);
        pdetail.setText(pdata.getDetail());

        RelativeLayout fav=view.findViewById(R.id.savefav);
        fav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper db=new DataBaseHelper(getContext());
                if(before.getVisibility()==View.VISIBLE){
                    boolean result=db.SinsertData(pdata.getId(),pdata.getPnmae(),pdata.getPmodel(),pdata.getPrice(),pdata.getDetail(),pdata.getImgurl(),pdata.getShop());
                    if(result){
                        Toast.makeText(getContext(),"Add to Favourite list!",Toast.LENGTH_SHORT).show();
                        after.setVisibility(View.VISIBLE);
                        before.setVisibility(View.GONE);
                    }
                    else{
                        Toast.makeText(getContext(),"Fail!",Toast.LENGTH_LONG).show();
                    }

                }
                else{
                    db.SremoveData(pdata.getId());
                    Toast.makeText(getContext(),"Remove from Favourite list!",Toast.LENGTH_LONG).show();
                    after.setVisibility(View.GONE);
                    before.setVisibility(View.VISIBLE);

                }
            }
        });
        ImageView phcall=view.findViewById(R.id.phcall);
        phcall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ForPh sent = new ForPh(getContext(),getActivity());
                sent.setCancelable(false);
                sent.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                sent.show();
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
