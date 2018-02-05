package william.servian.channelmessaging;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.HashMap;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener,OnDownloadListener {
    private TextView textView_login;
    private TextView textView_mdp;
    private EditText editText_login;
    private EditText editText_mdp;
    private Button button_valider ;
    public static final String PREFS_NAME = "MyPrefsFile";

    public LoginActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textView_login = (TextView) findViewById(R.id.TextView_login);
        textView_mdp = (TextView) findViewById(R.id.TextView_mdp);
        editText_login = (EditText) findViewById(R.id.EditText_login);
        editText_mdp = (EditText) findViewById(R.id.EditText_mdp);
        button_valider = (Button) findViewById(R.id.button_valider);
        button_valider.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        HttpPostHandler http = new HttpPostHandler();
        http.addOnDownloadListener(this);
        String url = "http://www.raphaelbischof.fr/messaging/?function=connect";
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("username",editText_login.getText().toString());
        hashMap.put("password",editText_mdp.getText().toString());
        http.execute(new PostRequest(url,hashMap));
    }

    @Override
    public void onDownloadComplete(String downloadedContent) {
        Gson gson = new Gson();
        Reponse.Response retour = gson.fromJson(downloadedContent,Reponse.Response.class);
        if(retour.getResponse().equals("Ok")){
            SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
            Intent intent = new Intent(getApplicationContext(),ChannelActivity.class);
            SharedPreferences.Editor editor = settings.edit();
            editor.putString("token",retour.getAccesstoken());
            editor.commit();
            startActivity(intent);
        }
        else{
            Toast.makeText(getApplicationContext(), "Connexion échouée", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onDownloadError(String error) {

    }
}
