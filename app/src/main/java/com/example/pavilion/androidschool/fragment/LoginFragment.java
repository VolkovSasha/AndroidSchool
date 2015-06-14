package com.example.pavilion.androidschool.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.pavilion.androidschool.R;

/**
 * Created by Pavilion on 22.04.2015.
 */
public class LoginFragment extends Fragment implements View.OnClickListener{


    private Activity    mActivity;
    private View        mView;
    private EditText    mETEmail, mETPassword;
    private Button      mBTNSignIn, mBTNGoRegistration;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.login_fragment, container, false);
        initComponent();
        setListeners();
        return mView;
    }



    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    private void initComponent(){
        mETEmail            = (EditText) mView.findViewById(R.id.etEmail_LF);
        mETPassword         = (EditText) mView.findViewById(R.id.etPassword_LF);
        mBTNSignIn          = (Button) mView.findViewById(R.id.btnSignIn_LF);
        //mBTNGoRegistration  = (Button) mView.findViewById(R.id.btnGoRegistation_FSI);

    }

    private boolean isValidateParam(EditText _et){
        String check = _et.getText().toString();
        return !check.isEmpty();
    }

    private void setListeners() {
        mBTNSignIn.setOnClickListener(this);
        mBTNGoRegistration.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignIn_LF:
                if (isValidateParam(mETEmail) && isValidateParam(mETPassword))   //TODO login in
                break;
           // case R.id.btnGoRegistation_FSI:
           //     ((LoginActivity) mActivity).openRegisterFragment();
          //      break;
        }
    }
}
