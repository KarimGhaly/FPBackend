package com.example.admin.fpbackend.Benfits;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.admin.fpbackend.DataObjects.InsuranceInfoClass;
import com.example.admin.fpbackend.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Admin on 10/20/2017.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    Context context;
    List<InsuranceInfoClass> insuranceList;

    public RecyclerViewAdapter(List<InsuranceInfoClass> insuranceList) {
        this.insuranceList = insuranceList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.benifits_recycler_view,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final InsuranceInfoClass insurance = insuranceList.get(position);
        holder.txtRecyType.setText(insurance.getType());
        holder.txtRecyCompanyName.setText(insurance.getCompanyName());
        holder.txtRecyPackage.setText(insurance.getPackageName());
        holder.txtRecyPackageDetails.setText(insurance.getPackageDetailsUrl());
        holder.btnRecyDeleteBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insuranceList.remove(insurance);
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return insuranceList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtRecy_type)
        TextView txtRecyType;
        @BindView(R.id.txtRecy_companyName)
        TextView txtRecyCompanyName;
        @BindView(R.id.txtRecy_package)
        TextView txtRecyPackage;
        @BindView(R.id.txtRecy_packageDetails)
        TextView txtRecyPackageDetails;
        @BindView(R.id.btnRecy_deleteBTN)
        Button btnRecyDeleteBTN;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
