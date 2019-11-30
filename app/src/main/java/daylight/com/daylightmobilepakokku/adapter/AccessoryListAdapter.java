package daylight.com.daylightmobilepakokku.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import daylight.com.daylightmobilepakokku.R;
import daylight.com.daylightmobilepakokku.detailview.AccessorydetailView;
import daylight.com.daylightmobilepakokku.detailview.PdetailView;
import daylight.com.daylightmobilepakokku.viewmodel.NewProdcutModel;

public class AccessoryListAdapter extends RecyclerView.Adapter<AccessoryListAdapter.PnViewHolder> {
    ArrayList<NewProdcutModel>myary=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    public AccessoryListAdapter(Context c, ArrayList<NewProdcutModel> arylist){
        context=c;
        myary=arylist;
    }

    @NonNull
    @Override
    public PnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.product_view,parent,false);
        return new PnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PnViewHolder holder, int position) {
        final NewProdcutModel k=myary.get(position);
        Glide.with(holder.itemView).load(k.getImgurl()).into(holder.imgone);
        holder.model.setText(k.getPmodel());
        holder.price.setText(k.getPrice()+" Ks");
        holder.pname.setText(k.getPnmae());
        holder.whole.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        FragmentManager fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                        AccessorydetailView detail=new AccessorydetailView();
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("Pdetails",k);
                        detail.setArguments(bundle);
                        detail.show(fragmentManager,"slideshow");



            }
        });


    }

    @Override
    public int getItemCount() {
        return myary.size();
    }

    class PnViewHolder extends RecyclerView.ViewHolder {
       // ImageView before,after;
        ImageView imgone;
        CardView whole;
        TextView model,pname,price;
        public PnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgone=itemView.findViewById(R.id.pimg);
            model=itemView.findViewById(R.id.model);
            pname=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            whole=itemView.findViewById(R.id.whole);

        }
    }

    }


