package com.thuxin.retroapi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
    private List<Hero> dataList;
    private Context context;
    private OnItemClickListener listener;



    public CustomAdapter(List<Hero> dataList, Context context,OnItemClickListener listener) {
        this.dataList = dataList;
        this.context=context;
        this.listener =listener;
    }

    @NonNull
    @Override
    public CustomAdapter.CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.custom_row, parent, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomAdapter.CustomViewHolder holder, int position) {

        holder.nametxt.setText(dataList.get(position).getName());
        holder.aliastxt.setText(dataList.get(position).getAlias());
        holder.bind(dataList.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {
        public  View mView;
        TextView nametxt,aliastxt,idtxt;
        public CustomViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            nametxt = mView.findViewById(R.id.nametxt);
            aliastxt = mView.findViewById(R.id.aliastxt);
        }

        public void bind(final Hero hero,final OnItemClickListener listener) {
            nametxt.setText(hero.getName());
            aliastxt.setText(hero.getAlias());

            itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    PopupMenu popup = new PopupMenu(context,itemView);

                    popup.inflate(R.menu.menu_main);

                    popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {


                        @Override
                        public boolean onMenuItemClick(MenuItem item) {
                            if(item.getItemId()==R.id.menu1){
                                Toast.makeText(context,hero.getName(),Toast.LENGTH_SHORT).show();
                            }
                                else if(item.getItemId()==R.id.menu2){
                                Toast.makeText(context,hero.getAlias(),Toast.LENGTH_SHORT).show();
                            }else{
                                return false;
                            }
                            return true;
                        }
                    });

                    popup.show();


                    listener.onItemClick(hero);
                }

            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(Hero hero);

    }
}
