package com.softorea.schoolsen.dialogs;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.softorea.schoolsen.R;
import com.softorea.schoolsen.databasehelper.DatabaseHandler;
import com.softorea.schoolsen.m_monthly.M_ProxyTeacherList;
import com.softorea.schoolsen.m_monthly.M_ProxyTeacherUpdate;
import com.softorea.schoolsen.models.DetailsProxyTeacher;

import java.util.ArrayList;

public class DialogAdapterProxy extends BaseAdapter {

    private static ArrayList<DetailsProxyTeacher> searchArrayList;
    DatabaseHandler databaseHandler;
    private Context context;
    private LayoutInflater mInflater;

    public DialogAdapterProxy(Context context, ArrayList<DetailsProxyTeacher> results) {
        searchArrayList = results;
        mInflater = LayoutInflater.from(context);
        databaseHandler = new DatabaseHandler(context);
    }

    @Override
    public int getCount() {
        return searchArrayList.size();
    }

    @Override
    public Object getItem(int p) {
        return searchArrayList.get(p);
    }

    @Override
    public long getItemId(int p) {
        return p;
    }

    @Override
    public View getView(final int p, View v, ViewGroup parent) {
        ViewHolder holder;
        context = parent.getContext();
        if (v == null) {
            v = mInflater
                    .inflate(R.layout.dialogadapterproxy, null);
            holder = new ViewHolder();

            holder.name = (TextView) v.findViewById(R.id.name);
            holder.cnic = (TextView) v.findViewById(R.id.cnicc);
            holder.proxyforname = (TextView) v.findViewById(R.id.proxyname);
            holder.pcnic = (TextView) v.findViewById(R.id.pcnic);
            holder.ppno = (TextView) v.findViewById(R.id.pno);
            holder.designation = (TextView) v.findViewById(R.id.designation);
            holder.ptime = (TextView) v.findViewById(R.id.timesince);
            holder.edit = (ImageView) v.findViewById(R.id.edit);
            holder.delete = (ImageView) v.findViewById(R.id.delete);

            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        holder.name.setText(searchArrayList.get(p).getName());
        holder.cnic.setText(searchArrayList.get(p).getCnic());
        holder.proxyforname.setText(searchArrayList.get(p).getProxyName());
        holder.pcnic.setText(searchArrayList.get(p).getProxycnic());
        holder.ppno.setText(searchArrayList.get(p).getProxyno());
        holder.designation.setText(searchArrayList.get(p).getDesignation());
        holder.ptime.setText(searchArrayList.get(p).getProxytimeSince());
        holder.edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, M_ProxyTeacherUpdate.class);
                i.putExtra("ID", searchArrayList.get(p).getId());
                i.putExtra("emiscode", searchArrayList.get(p).getEmisCode());
                i.putExtra("name", searchArrayList.get(p).getName());
                i.putExtra("cnic", searchArrayList.get(p).getCnic());
                i.putExtra("pname", searchArrayList.get(p).getProxyName());
                i.putExtra("pcnic", searchArrayList.get(p).getProxycnic());
                i.putExtra("ppno", searchArrayList.get(p).getProxyno());
                i.putExtra("des", searchArrayList.get(p).getDesignation());
                i.putExtra("date", searchArrayList.get(p).getProxytimeSince());
                context.startActivity(i);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //final int id = searchArrayList.get(p).getId();
                final int idno = searchArrayList.get(p).getId();
                //final String proxyname = searchArrayList.get(p).getProxyName();
                //final String cnic = searchArrayList.get(p).getCnic();
                //databasehandler.removeSingleContact(name);
                //cusadapter.notifyDataSetChanged();
                AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
                builder1.setMessage("Are you sure to delete?");
                builder1.setCancelable(false);

                builder1.setPositiveButton(
                        "Delete",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                databaseHandler.removeProxyTeacher(idno);
                                ((Activity) context).finish();
                                context.startActivity(new Intent(context, M_ProxyTeacherList.class));
                            }
                        });

                builder1.setNegativeButton(
                        "Cancel",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.dismiss();
                            }
                        });

                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        });
        return v;
    }

    static class ViewHolder {
        TextView name, cnic, proxyforname, pcnic, ppno, designation, ptime;
        ImageView edit, delete;

    }

}