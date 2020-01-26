package com.apexmall;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import static com.apexmall.R.*;
import static com.apexmall.R.id.sign_upbtn;



/**
 * A simple {@link Fragment} subclass.
 */
public class SignupFragment extends Fragment {
 private TextView alreadyhaveanaccoutn;

 private FrameLayout parentframelayout;
    private EditText email;
    private EditText fullname;
    private EditText password;
    private EditText confirm_password;
    private ImageButton closebtn;
    private Button sign_upbtn;


    private FirebaseAuth firebaseAuth;
    private  String Emailpattern="[A-Z0-9._%+-]+@[A-Z0-9.-]+.[A-Z]+";

    public SignupFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(layout.fragment_signup, container, false);
   alreadyhaveanaccoutn=view.findViewById(id.tv_already_have_an_account);
   parentframelayout=getActivity().findViewById(id.register_frame_layout);
   email=view.findViewById(id.sign_up_email);
   fullname=view.findViewById(id.sign_up_name);
   password=view.findViewById(id.sign_up_password);
   confirm_password=view.findViewById(id.sign_up_confirmpassword);
   closebtn=view.findViewById(id.sign_up_close_btn);
  sign_upbtn=view.findViewById(id.sign_upbtn);
  firebaseAuth=FirebaseAuth.getInstance();


   return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    alreadyhaveanaccoutn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            setFragment(new SigninFragment());
        }
    });

    email.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        checkInputs();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    fullname.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
    checkInputs();

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    password.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
checkInputs();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });
    confirm_password.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
checkInputs();
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    });

sign_upbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        //todo send data to fire base
        checkEmailAndPassword();



            }
        });
    }

    private void setFragment(SigninFragment signinFragment) {
        FragmentTransaction fragmentTransaction=getActivity().getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(parentframelayout.getId(),signinFragment);
        fragmentTransaction.setCustomAnimations(anim.slide_fromleft, anim.slideoutfromright_exitanimation);
        fragmentTransaction.commit();

    }
    private  void checkInputs()
    {
        if (!TextUtils.isEmpty(email.getText()))
        {
          if (!TextUtils.isEmpty(fullname.getText()))
          {
           if (!TextUtils.isEmpty(password.getText())&& password.length()>=8)
           {
               if (!TextUtils.isEmpty(confirm_password.getText()))
               {
                   sign_upbtn.setEnabled(true);

               }else
               {
                   sign_upbtn.setEnabled(false);
                   sign_upbtn.setTextColor(Color.argb(50,255,255,255));
               }

           }else{ sign_upbtn.setEnabled(false);
               sign_upbtn.setTextColor(Color.argb(50,255,255,255));

           }
          }else
          {
              sign_upbtn.setEnabled(false);
              sign_upbtn.setTextColor(Color.argb(50,255,255,255));
          }
        }
        else
        {
            sign_upbtn.setEnabled(false);
            sign_upbtn.setTextColor(Color.argb(50,255,255,255));
        }
    }
    private  void  checkEmailAndPassword(){
        if (email.getText().toString().matches(Emailpattern))
        {

            if (password.getText().toString().equals(confirm_password.getText().toString()))
            {
                firebaseAuth.createUserWithEmailAndPassword(email.getText().toString(),password.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful())
                                {
                                    Intent intent =new Intent(getActivity(),Main2Activity.class);
                                    startActivity(intent);
                                    getActivity().finish();
                                }else
                                {String error=task.getException().getMessage();
                                    Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT).show();

                                }
                            }
                        });
            }
            else
            {
                confirm_password.setError("Password doesn't Match");
            }
        }
        else
        {
            email.setError("Invaled Email!");
        }


    }
}
