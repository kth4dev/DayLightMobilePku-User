package daylight.com.daylightmobilepakokku.adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import daylight.com.daylightmobilepakokku.R;
import daylight.com.daylightmobilepakokku.productlist.Accessory_List;
import daylight.com.daylightmobilepakokku.viewmodel.Categories;

public class AccessoryNameAdapter extends RecyclerView.Adapter<AccessoryNameAdapter.PnViewHolder> {
    ArrayList<Categories>myary=new ArrayList<>();
    LayoutInflater layoutInflater;
    Context context;
    public AccessoryNameAdapter(Context c, ArrayList<Categories> arylist){
        context=c;
        myary=arylist;
    }

    @NonNull
    @Override
    public PnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.from(context).inflate(R.layout.category_view,parent,false);
        return new PnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PnViewHolder holder, int position) {
        final Categories k=myary.get(position);
        Glide.with(holder.itemView).load(k.getImgurl()).into(holder.imgone);
        holder.imgone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fragmentManager = ((AppCompatActivity)view.getContext()).getSupportFragmentManager();
                Accessory_List detail=new Accessory_List();
                Bundle bundle = new Bundle();
                bundle.putSerializable("ProductTitle",k.getCname());
                detail.setArguments(bundle);
                detail.show(fragmentManager,"slideshow");

            }
        });
   /* holder.savefav.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
          /*  if(holder.before.getVisibility()==View.VISIBLE){
                holder.before.setVisibility(View.GONE);
                holder.after.setVisibility(View.VISIBLE);
            }
            else{
                holder.before.setVisibility(View.VISIBLE);
                holder.after.setVisibility(View.GONE);
            }
        }
    }); */
    }

    @Override
    public int getItemCount() {
        return myary.size();
    }

    class PnViewHolder extends RecyclerView.ViewHolder {
       // ImageView before,after;
        ImageView imgone;
        //RelativeLayout savefav;
        public PnViewHolder(@NonNull View itemView) {
            super(itemView);
            imgone=itemView.findViewById(R.id.ccimg);
           // before=itemView.findViewById(R.id.beforefav);
           // after=itemView.findViewById(R.id.afterfav);
            //savefav=itemView.findViewById(R.id.savefav);
        }
    }

    }


