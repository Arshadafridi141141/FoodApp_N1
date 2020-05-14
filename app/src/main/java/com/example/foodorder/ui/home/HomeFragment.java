package com.example.foodorder.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodorder.Customer.Cart;
import com.example.foodorder.Customer.Fooddetails;
import com.example.foodorder.Customer.Interface.ItemClickListener;
import com.example.foodorder.Customer.Menu;
import com.example.foodorder.Model.Fooditem;
import com.example.foodorder.R;
import com.example.foodorder.Viewholder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeFragment extends Fragment {
    RecyclerView recyclerView;
    FirebaseDatabase database;
    DatabaseReference reference;
    FloatingActionButton cart;
    FirebaseRecyclerAdapter<Fooditem, Viewholder> firebaseRecyclerAdapter;

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        database=FirebaseDatabase.getInstance();
        reference=database.getReference().child("Food_details");
        recyclerView=(RecyclerView) root.findViewById(R.id.RecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));




        firebaseRecyclerAdapter=

                new FirebaseRecyclerAdapter<Fooditem, Viewholder>(
                        Fooditem.class,
                        R.layout.customized_menu,
                        Viewholder.class,
                        reference
                ) {
                    @Override
                    protected void populateViewHolder(Viewholder viewholder, final Fooditem food, int i) {

                        viewholder.setdetails(getContext(),food.getImage(),food.getName());

                        viewholder.setItemClickListener(new ItemClickListener() {
                            @Override
                            public void onClick(View view, int position, boolean islongclick) {
                                Intent foodetail=new Intent(getContext(), Fooddetails.class);
                                foodetail.putExtra("Foodid",firebaseRecyclerAdapter.getRef(position).getKey());
                                startActivity(foodetail);
                            }
                        });
                    }
                };
        recyclerView.setAdapter(firebaseRecyclerAdapter);

        return root;
    }
}