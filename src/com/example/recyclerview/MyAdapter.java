package com.example.recyclerview;

import java.util.List;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ItemViewHolder> {
	private  LayoutInflater mLayoutInflater;
	private  Context mContext ;
	private List<Fruit> fruits;
	
	private MyItemClickListener listener;
	interface MyItemClickListener {  
	    public void onItemClick(View view,int postion);  
	}
	
	public void setListener(MyItemClickListener listener){
			this.listener = listener;
	}
	
	
	
	
	public MyAdapter(Context context, List<Fruit> fruits) {
		super();
		this.mContext = mContext;
		this.fruits = fruits;
		mLayoutInflater = LayoutInflater.from(context);
	}

	public MyAdapter(List<Fruit> fruits) {
		super();
		this.fruits = fruits;
	}

	@Override
	public int getItemCount() {
		// TODO Auto-generated method stub
		return fruits.size();
	}

	@Override
	public void onBindViewHolder(ItemViewHolder viewHolder, int position) {
		// TODO Auto-generated method stub
		Fruit fruit = fruits.get(position);
		viewHolder.imageView.setImageResource(fruit.getImg());
		viewHolder.title.setText(fruit.getTitle());
		
		
	}

	@Override
	public ItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
		// TODO Auto-generated method stub
		
			return new ItemViewHolder(mLayoutInflater.inflate(R.layout.item, viewGroup, false));
	}
	
	public static class ItemViewHolder extends RecyclerView.ViewHolder{
		ImageView imageView;
		TextView title;
		public ItemViewHolder(View view) {
			super(view);
			// TODO Auto-generated constructor stub
			imageView = (ImageView) view.findViewById(R.id.img);
			title = (TextView) view.findViewById(R.id.title);
			
			
			}
		
	}

	

	

	

}
