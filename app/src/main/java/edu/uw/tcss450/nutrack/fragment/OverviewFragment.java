package edu.uw.tcss450.nutrack.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import edu.uw.tcss450.nutrack.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OverviewFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link OverviewFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class OverviewFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    /**
     * First parameter string.
     */
    private static final String ARG_PARAM1 = "param1";

    /**
     * Second parameter string.
     */
    private static final String ARG_PARAM2 = "param2";
    /**
     * First Parameter string.
     */
    private String mParam1;
    /**
     * Second Parameter string.
     */
    private String mParam2;

    /**
     * height of the graph
     */
    private int mGraphHeight;

    /**
     * Fragment interaction listener.
     */
    private OnFragmentInteractionListener mListener;

    public OverviewFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment OverviewFragment.
     */
    public static OverviewFragment newInstance(String param1, String param2) {
        OverviewFragment fragment = new OverviewFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


        if (savedInstanceState == null) {
            Class topFragmentClass = DailyIntakeOverviewFragment.class;
            Class middleFragmentClass = WeeklyIntakeOverviewFragment.class;
            Class bottomFragmentClass = MonthlyWeightOverviewFragment.class;

            Fragment topFragment = null;
            Fragment middleFragment = null;
            Fragment bottomFragment = null;

            try {
                topFragment = (Fragment) topFragmentClass.newInstance();
                middleFragment = (Fragment) middleFragmentClass.newInstance();
                bottomFragment = (Fragment) bottomFragmentClass.newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }

            FragmentTransaction fragmentTracs = getActivity().getSupportFragmentManager().beginTransaction();

            fragmentTracs.add(R.id.ovContentTop, topFragment, "top");
            fragmentTracs.add(R.id.ovContentMiddle, middleFragment, "middle");
            fragmentTracs.add(R.id.ovContentBottom, bottomFragment,"bottom");

            fragmentTracs.commit();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_overview, container, false);
        return view;
    }

    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
