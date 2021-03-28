package com.example.attendancerecord;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

class ClassAdapter extends RecyclerView.Adapter<ClassAdapter.ClassViewHolder> {

    public ClassAdapter(Context context,ArrayList<ClassItem> classItems) {
        this.classItems = classItems;
        this.context = context;
    }

    ArrayList<ClassItem> classItems;
    Context context;

    private OnItemClickListener onItemClickListener;
    public interface OnItemClickListener{
        void onclick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public static class ClassViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener{

        TextView className;
        TextView subjectName;

        public ClassViewHolder(@NonNull View itemView,OnItemClickListener onItemClickListener) {
            super(itemView);
            className=itemView.findViewById(R.id.class_tv);
            subjectName=itemView.findViewById(R.id.subject_tv);
            itemView.setOnClickListener(v-> onItemClickListener.onclick(getAdapterPosition()));
            itemView.setOnCreateContextMenuListener(this);
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            menu.add(getAdapterPosition(),0,0,"DELETE");

        }
    }

    @NonNull
    @Override
    public ClassViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.class_item,parent,false);
        return new ClassViewHolder(itemView,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ClassViewHolder holder, int position) {

        holder.className.setText(classItems.get(position).getClassName());
        holder.subjectName.setText(classItems.get(position).getSubjectName());

    }

    @Override
    public int getItemCount() {
        return classItems.size();
    }
}
