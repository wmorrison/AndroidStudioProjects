package com.pluralsight.partitionuimindset;

import android.app.ListFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by Will on 6/26/2014.
 */
public class TitlesFragment extends ListFragment {
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        String[] titles = getResources().getStringArray(R.array.course_titles);

        ArrayAdapter<String> titleAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1, titles);
        setListAdapter(titleAdapter);

    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //position gives us the zero-based index of the list item clicked
        OnCourseSelectionChangeListener listener = (OnCourseSelectionChangeListener) getActivity();

        listener.onCourseSelectionChanged(position);
    }

}
