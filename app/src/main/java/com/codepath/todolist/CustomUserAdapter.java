package com.codepath.todolist;

    import android.content.Context;
    import android.view.LayoutInflater;
    import android.view.View;
    import android.view.ViewGroup;
    import android.widget.ArrayAdapter;
    import android.widget.TextView;

    /**
     * Created by hammedopejin on 2/8/17.
     */

    public class CustomUserAdapter extends ArrayAdapter<Items> {
        //View lookup cache
        private static class ItemHolder{
            TextView name;
            TextView home;
        }
        Context context;
        int layoutResourceId;
        Items data[] = null;
        int flag;

        public CustomUserAdapter(Context context, int layoutResourceId, Items[] data) {
            super(context, layoutResourceId, data);
            this.layoutResourceId = layoutResourceId;
            this.context = context;
            this.data = data;
        }


        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // Get the data item for this position

            //ViewHolder viewHolder;//view lookup cache stored in tag
            View row = convertView;
            ItemHolder holder = null;


            // Check if an existing view is being reused, otherwise inflate the view
            if (row == null) {

                LayoutInflater inflater = LayoutInflater.from(getContext());
                row = inflater.inflate(R.layout.item_user, parent, false);

                holder = new ItemHolder();

                // Lookup view for data population
                holder.name = (TextView) row.findViewById(R.id.tvName);
                holder.home = (TextView) row.findViewById(R.id.tvHometown);


                //Cache the viewHolder object inside the fresh view
                row.setTag(holder);
            } else {
                //View is being recycled, retrieve the viewHolder object from tag
                holder = (ItemHolder)row.getTag();
            }
            Items item = data[position];
            // Populate the data from the data object using the viewHolder object

            holder.name.setText(item.todo);
            holder.home.setText(item.pri);

            return row;
        }
    }


