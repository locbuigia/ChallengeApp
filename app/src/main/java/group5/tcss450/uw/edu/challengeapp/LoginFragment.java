package group5.tcss450.uw.edu.challengeapp;

//Hello

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends Fragment {
    public static EditText username, password;


    private static final String PARTIAL_URL
            = "http://cssgate.insttech.washington.edu/" +
            "~locbui/";

    public LoginFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container,
                false);
        username = (EditText) view.findViewById(R.id.usenameText);
        password = (EditText) view.findViewById(R.id.passwordText);

        //view.setBackgroundColor(getResources().getColor(R.color.lightBlue));

        final CheckBox showPassword = (CheckBox) view.findViewById(R.id.showPasswordLogin);
        showPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (showPassword.isChecked()) {
                    password.setTransformationMethod(null);
                } else {
                    password.setTransformationMethod(new PasswordTransformationMethod());
                }
            }
        });

        Button submit = (Button) view.findViewById(R.id.submitButton1);
        submit.setBackgroundColor(getResources().getColor(R.color.lightPurple));

        submit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AsyncTask<String, Void, String> task = null;
                String theUsername = username.getText().toString();
                String thePassword = password.getText().toString();
                switch (v.getId()) {
                    case R.id.submitButton1:
                        task = new GetWebServiceTask();
                        RegisterFragment.username = null;
                        RegisterFragment.password = null;
                        RegisterFragment.passwordConfirm = null;
                        if (username.getText().toString().trim().equalsIgnoreCase("")) {
                            username.setError("Please enter username");
                        } else if (password.getText().toString().trim().equalsIgnoreCase("")) {
                            password.setError("Please enter password");
                        } else {
                            task.execute(PARTIAL_URL, theUsername, thePassword);
                        }
                    }
                }
            });

        return view;
    }

    private class GetWebServiceTask extends AsyncTask<String, Void, String> {
        private final String SERVICE = "login.php";
        @Override
        protected String doInBackground(String... strings) {
            if (strings.length != 3) {
                throw new IllegalArgumentException("Two String arguments required.");
            }
            String response = "";
            HttpURLConnection urlConnection = null;
            String url = strings[0];
            String arg1 = "?user_name=" + strings[1];
            String arg2 = "&pass_word=" + strings[2];
            try {
                URL urlObject = new URL(url + SERVICE + arg1 + arg2);
                urlConnection = (HttpURLConnection) urlObject.openConnection();
                InputStream content = urlConnection.getInputStream();
                BufferedReader buffer = new BufferedReader(new InputStreamReader(content));
                String s = "";
                while ((s = buffer.readLine()) != null) {
                    response += s;
                }
            } catch (Exception e) {
                response = "Unable to connect, Reason: "
                        + e.getMessage();
            } finally {
                if (urlConnection != null)
                    urlConnection.disconnect();
            }
            return response;
        }
        @Override
        protected void onPostExecute(String result) {
            // Something wrong with the network or the URL.
            if (result.startsWith("Unable to")) {
                Toast.makeText(getActivity(), result, Toast.LENGTH_LONG)
                        .show();
                return;
            } else if (!result.isEmpty() && !result.startsWith("Unable to")) {
                Toast.makeText(getActivity(), "Login Success!", Toast.LENGTH_LONG).show();
                FragmentManager fm = getFragmentManager();
                FragmentTransaction ft = fm.beginTransaction();
                ft.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right);
                ft.replace(R.id.fragmentContainer, new DisplayFragment());
                ft.addToBackStack(null);
                ft.commit();
            } else if (result.isEmpty() && !result.startsWith("Unable to")) {
                Toast.makeText(getActivity(), "Login Failed! Invalid Username or Password", Toast.LENGTH_LONG).show();
            }
        }
    }

}
