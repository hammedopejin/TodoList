package com.codepath.todolist;

    import android.app.AlertDialog;
    import android.app.Dialog;
    import android.content.DialogInterface;
    import android.content.Intent;
    import android.os.Bundle;
    import android.support.v4.app.DialogFragment;
    import android.support.v7.app.AppCompatActivity;

    /**
     * Created by hammedopejin on 2/16/17.
     */

    public class MyDeleteDialogFragment extends DialogFragment {
        public interface OnCompleteListener{
            void onComplete(String task);
        }
        OnCompleteListener mListener;

        public void onAttach(AppCompatActivity activity){
            super.onAttach(activity);
            try {
                this.mListener = (OnCompleteListener)activity;
            }catch (final ClassCastException e){
                throw new ClassCastException(activity.toString() + " must implement OnCompleteListener");
            }
        }
        public MyDeleteDialogFragment() {
            // Empty constructor required for DialogFragment
        }

        public static MyDeleteDialogFragment newInstance(String title, String task) {
            MyDeleteDialogFragment frag = new MyDeleteDialogFragment();
            Bundle args = new Bundle();
            args.putString("title", title);
            args.putString("task", task);
            frag.setArguments(args);
            return frag;
        }

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            String title = getArguments().getString("title");
            final String task = getArguments().getString("task");


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
            alertDialogBuilder.setTitle(title);
            alertDialogBuilder.setMessage("Are you sure you want to delete the task?");
            alertDialogBuilder.setPositiveButton("OK",  new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // on success

                    TodoItemDatabase td = new TodoItemDatabase(getContext());
                    td.deleteItem(task);
                    Intent i = new Intent(MyDeleteDialogFragment.super.getContext(), TodoList.class).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(i);
                    //mListener.onComplete("deleted !");

                }
            });
            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });

            return alertDialogBuilder.create();
        }




    }

