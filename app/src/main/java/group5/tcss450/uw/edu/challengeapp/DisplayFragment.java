package group5.tcss450.uw.edu.challengeapp;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;


/**
 * A simple {@link Fragment} subclass.
 */
public class DisplayFragment extends Fragment {

    public DisplayFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_display, container, false);
        view.setBackgroundColor(getResources().getColor(R.color.lightBlue));

        TextView usernameTextView = (TextView) view.findViewById(R.id.usernameTextView);
        usernameTextView.setTextSize(25);
        usernameTextView.setTextColor(getResources().getColor(R.color.lightPurple));
        TextView passwordTextView = (TextView) view.findViewById(R.id.passwordTextView);
        passwordTextView.setTextSize(25);
        passwordTextView.setTextColor(getResources().getColor(R.color.lightPurple));

        if (LoginFragment.username == null && LoginFragment.password == null) {
            usernameTextView.setText(RegisterFragment.username.getText().toString());
            passwordTextView.setText(RegisterFragment.passwordConfirm.getText().toString());
        } else {
            usernameTextView.setText(LoginFragment.username.getText().toString());
            passwordTextView.setText(LoginFragment.password.getText().toString());
        }

        return view;
    }


}
