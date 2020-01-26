package com.apexmall;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class SigninFragment extends Fragment {

private TextView dont_have_account;
private FrameLayout parentframelayout;
    public SigninFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
     View view= inflater.inflate(R.layout.fragment_signin, container, false);
     dont_have_account=view.findViewById(R.id.dont_have_account);
     parentframelayout=getActivity().findViewById(R.id.register_frame_layout);
     return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

dont_have_account.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        setFragment(new SignupFragment());
    }

    private void setFragment(SignupFragment signupFragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentframelayout.getId(),signupFragment);

        fragmentTransaction.setCustomAnimations(R.anim.slide_right,R.anim.slideoutleft);
        fragmentTransaction.commit();

    }

});
    }
}
