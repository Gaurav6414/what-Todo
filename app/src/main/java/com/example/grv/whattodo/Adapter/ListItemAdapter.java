package com.example.grv.whattodo.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.grv.whattodo.MainActivity;
import com.example.grv.whattodo.Model.ToDo;
import com.example.grv.whattodo.R;

import java.util.List;


/**
 * Created by GRV on 3/11/2018.
 */

class ListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnCreateContextMenuListener{

    ItemClickListener itemClickListener;



    TextView item_title,item_desc;

    public ListItemViewHolder(View itemView) {
        super(itemView);
        itemView.setOnClickListener(this);
        itemView.setOnCreateContextMenuListener(this);

        item_title = (TextView)itemView.findViewById(R.id.item_title);
        item_desc = (TextView) itemView.findViewById(R.id.item_desc);
    }
    public void setItemClickListener(ItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }



    @Override
    public void onClick(View view) {
        itemClickListener.onClick(view,getAdapterPosition(),false);

    }

    @Override
    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
        contextMenu.setHeaderTitle("Select your Action");
        contextMenu.add(0,0,getAdapterPosition(),"Delete");

    }
}

public class ListItemAdapter extends RecyclerView.Adapter<ListItemViewHolder>{

    MainActivity mainActivity;
    List<ToDo> toDoList;

    public ListItemAdapter(MainActivity mainActivity, List<ToDo> toDoList) {
        this.mainActivity = mainActivity;
        this.toDoList = toDoList;
    }

    @Override
    public ListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(mainActivity.getBaseContext());
        View view = layoutInflater.inflate(R.layout.list_item,parent,false);
        return new ListItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListItemViewHolder holder, int position) {
//will code later



        //setting up data

        holder.item_title.setText(toDoList.get(position).getTitle());
        holder.item_desc.setText(toDoList.get(position).getDesc());

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int adapterPosition, boolean isLongClick) {
                mainActivity.title.setText(toDoList.get(adapterPosition).getTitle());
                mainActivity.desc.setText(toDoList.get(adapterPosition).getDesc());

                mainActivity.isUpdate=true;
                mainActivity.idUpadte = toDoList.get(adapterPosition).getId();

            }
        });
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }
}
