package william.servian.channelmessaging;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by sacquind on 29/01/2018.
 */
public class ChannelListActivity extends AppCompatActivity implements View.OnClickListener,OnDownloadListener{
    private ListView listView ;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_channellist);
        listView = (ListView) findViewById(R.id.listView);
        sharedPreferences = getSharedPreferences("user_prefs", Context.MODE_PRIVATE);
        HttpPostHandler http = new HttpPostHandler();
        http.addOnDownloadListener(this);
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("accesstoken",sharedPreferences.getString("accessToken",null));
        http.execute(new PostRequest("http://www.raphaelbischof.fr/messaging/?function=getchannels",hashMap));

    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onDownloadComplete(String downloadContent) {

        Gson gson = new Gson();
        ChannelResponse obj = gson.fromJson(downloadContent, ChannelResponse.class);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1);
        ArrayAdapter<String> arrayAdapter2 = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_2);
        for (Channel chan:obj.getChannels()
                ) {
            arrayAdapter.add("Channel :"+chan.getName().toString());
            arrayAdapter2.add("Nombre d'utilisateurs connectés: "+chan.getConnectedusers());
        }
        listView.setAdapter(arrayAdapter);
        listView.setAdapter(arrayAdapter2);
        arrayAdapter.notifyDataSetChanged();
        arrayAdapter2.notifyDataSetChanged();
    }

    @Override
    public void onDownloadError(String error) {

    }
}