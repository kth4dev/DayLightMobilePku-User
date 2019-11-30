package daylight.com.daylightmobilepakokku.fav;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.wang.avi.AVLoadingIndicatorView;

import java.io.IOException;
import java.util.ArrayList;

import daylight.com.daylightmobilepakokku.R;
import daylight.com.daylightmobilepakokku.adapter.AccessoryListAdapter;
import daylight.com.daylightmobilepakokku.adapter.SecondProductListAdapter;
import daylight.com.daylightmobilepakokku.adapter.productListAdapter;
import daylight.com.daylightmobilepakokku.database.DataBaseHelper;
import daylight.com.daylightmobilepakokku.viewmodel.NewProdcutModel;
import daylight.com.daylightmobilepakokku.viewmodel.SecondProdcutModel;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Favourite.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Favourite#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Favourite extends DialogFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Favourite() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Product_List.
     */
    private DatabaseReference mdb= FirebaseDatabase.getInstance().getReference();
    // TODO: Rename and change types and number of parameters
    public static Favourite newInstance(String param1, String param2) {
        Favourite fragment = new Favourite();
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

        View view=inflater.inflate(R.layout.fragment_fav, container, false);
        ImageView back=(ImageView)view.findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        RecyclerView bf=(RecyclerView)view.findViewById(R.id.bf);
        RecyclerView sf=(RecyclerView)view.findViewById(R.id.sf);
        RecyclerView af=(RecyclerView)view.findViewById(R.id.af);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getContext(),2);
        RecyclerView.LayoutManager l2=new GridLayoutManager(getContext(),2);
        RecyclerView.LayoutManager l3=new GridLayoutManager(getContext(),2);
        DataBaseHelper db=new DataBaseHelper(getContext());
        SecondProductListAdapter sa;
        productListAdapter ba;
        AccessoryListAdapter aa;
        try {
            ArrayList<NewProdcutModel> bary=new ArrayList<>();
            ArrayList<SecondProdcutModel> sary=new ArrayList<>();
            ArrayList<NewProdcutModel> aary=new ArrayList<>();
            bary=db.getAllBAProductData("brandnew");
            sary=db.getAllSProductData();
            aary=db.getAllAProductData();
            TextView tb=view.findViewById(R.id.bt);
            TextView ts=view.findViewById(R.id.st);
            TextView ta=view.findViewById(R.id.ta);
            if(bary.size()==0){
                tb.setVisibility(View.GONE);
            }
            if(sary.size()==0){
                ts.setVisibility(View.GONE);
            }
            if(aary.size()==0){
                ta.setVisibility(View.GONE);
            }
          /*  if(bary.size()>0){
                RelativeLayout empty=(RelativeLayout)view.findViewById(R.id.nothing);
                empty.setVisibility(View.GONE);
            }*/
            sa = new SecondProductListAdapter(getContext(),sary);
            ba = new productListAdapter(getContext(),bary);
            aa = new AccessoryListAdapter(getContext(),aary);
            bf.setAdapter(ba);
            bf.setLayoutManager(layoutManager);
            sf.setAdapter(sa);
            sf.setLayoutManager(l2);
            af.setAdapter(aa);
            af.setLayoutManager(l3);
        } catch (IOException e) {
            e.printStackTrace();
        }


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
