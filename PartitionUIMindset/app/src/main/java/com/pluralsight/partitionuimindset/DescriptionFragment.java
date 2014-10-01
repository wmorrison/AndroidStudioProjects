package com.pluralsight.partitionuimindset;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Will on 6/25/2014.
 */
public class DescriptionFragment extends Fragment {

    String[] courseDescriptions;
    TextView courseDescriptionTextView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        courseDescriptions = getResources().getStringArray(R.array.course_descriptions);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View theView = inflater.inflate(R.layout.fragment_description, container, false);

        courseDescriptionTextView = (TextView) theView.findViewById(R.id.courseDescription_textView);

        return theView;
    }

    public void setCourse(int courseIndex){
        courseDescriptionTextView.setText(courseDescriptions[courseIndex]);
    }
}
