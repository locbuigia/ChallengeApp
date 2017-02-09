package group5.tcss450.uw.edu.challengeapp;


import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment {


    public FirstFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_first, container, false);

        ImageView shapeTopLeft = (ImageView) view.findViewById(R.id.shapeTopLeft);
        shapeTopLeft.setColorFilter(Color.GREEN);
        ImageView shapeTopRight = (ImageView) view.findViewById(R.id.shapeTopRight);
        shapeTopRight.setColorFilter(getResources().getColor(R.color.lightPurple));
        ImageView shapeBottomRight = (ImageView) view.findViewById(R.id.shapeBottomRight);
        shapeBottomRight.setColorFilter(Color.BLUE);
        ImageView shapeBottomLeft = (ImageView) view.findViewById(R.id.shapeBottomLeft);
        shapeBottomLeft.setColorFilter(Color.RED);

        TextView title = (TextView) view.findViewById(R.id.titleTextView);
        title.setTextSize(20);
        title.setTextColor(Color.BLACK);
        title.setTypeface(Typeface.SERIF, Typeface.ITALIC);


        view.setBackgroundColor(getResources().getColor(R.color.lightBlue));
        Button loginButton = (Button) view.findViewById(R.id.loginButton);
        loginButton.setBackgroundColor(getResources().getColor(R.color.lightPurple));
        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.loginButton:
                        //what to put here
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                R.anim.enter_from_left, R.anim.exit_to_right);
                        ft.replace(R.id.fragmentContainer, new LoginFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                }
            }
        });

        Button registerButton = (Button) view.findViewById(R.id.registerButton);
        registerButton.setBackgroundColor(getResources().getColor(R.color.lightPurple));
        registerButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.registerButton:
                        //what to put here
                        FragmentManager fm = getFragmentManager();
                        FragmentTransaction ft = fm.beginTransaction();
                        ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                                R.anim.enter_from_left, R.anim.exit_to_right);
                        ft.replace(R.id.fragmentContainer, new RegisterFragment());
                        ft.addToBackStack(null);
                        ft.commit();
                        break;
                }
            }
        });

        return view;
    }

}
