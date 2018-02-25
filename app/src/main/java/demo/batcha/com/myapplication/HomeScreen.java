package demo.batcha.com.myapplication;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import demo.batcha.com.myapplication.Adapter.Recycler_View_Adapter;
import demo.batcha.com.myapplication.ModelPOJO.DataRecycler;
import demo.batcha.com.myapplication.utils.Const;
import okhttp3.FormBody;
import okhttp3.RequestBody;

import static demo.batcha.com.myapplication.utils.APICall.post;

public class HomeScreen extends AppCompatActivity {

    List<DataRecycler> data;
    Recycler_View_Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);

        /*String email = getIntent().hasExtra("email")
                ?getIntent().getStringExtra("email").toString() : "";

        String mobno = getIntent().getStringExtra("mobilno").toString();*/

        data = new ArrayList<>();
        //fill_with_data();

        new GETContest().execute();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        adapter = new Recycler_View_Adapter(data, getApplication());

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
        itemAnimator.setAddDuration(1000);
        itemAnimator.setRemoveDuration(1000);
        recyclerView.setItemAnimator(itemAnimator);

    }

    public List<DataRecycler> fill_with_data() {

        data.add(new DataRecycler("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_local_movies_black_24dp));

        data.add(new DataRecycler("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_local_movies_black_24dp));


        data.add(new DataRecycler("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Alice in Wonderland", "Alice in Wonderland: Through the Looking Glass ", R.drawable.ic_local_movies_black_24dp));

        data.add(new DataRecycler("Batman vs Superman", "Following the destruction of Metropolis, Batman embarks on a personal vendetta against Superman ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("X-Men: Apocalypse", "X-Men: Apocalypse is an upcoming American superhero film based on the X-Men characters that appear in Marvel Comics ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Captain America: Civil War", "A feud between Captain America and Iron Man leaves the Avengers in turmoil.  ", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Kung Fu Panda 3", "After reuniting with his long-lost father, Po  must train a village of pandas", R.drawable.ic_local_movies_black_24dp));
        data.add(new DataRecycler("Warcraft", "Fleeing their dying home to colonize another, fearsome orc warriors invade the peaceful realm of Azeroth. ", R.drawable.ic_local_movies_black_24dp));



        return data;
    }




    String resContestGETAPI;
    protected ProgressDialog progressDialog;
    private class GETContest extends AsyncTask<Object, Void, String> {

        @Override
        protected void onPreExecute()//execute thaya pela
        {
            super.onPreExecute();
            // Log.d("pre execute", "Executando onPreExecute ingredients");
            //inicia diálogo de progress, mostranto processamento com servidor.
            progressDialog = ProgressDialog.show(HomeScreen.this, "Loading", "Please Wait...", true, false);

        }

        @Override
        protected String doInBackground(Object... parametros) {

            try {
               /* RequestBody loginbody = new FormBody.Builder()
                        .add("email",str_email)
                        .add("password",str_password)
                        .add("device_id","deviceID")
                        .add("device_type","android")
                        .add("device_token","token")
                        .build();*/


                String responseUSerTitles = post(Const.SERVER_URL_API +"contest/?limit=500&username=shah.jai75&api_key=434a1084d357751c87dbd1dc9a8e0885accdd30b", "","get");
                Log.d("URL ====",Const.SERVER_URL_API +"contest/?limit=500&username=shah.jai75&api_key=434a1084d357751c87dbd1dc9a8e0885accdd30b");
                resContestGETAPI=responseUSerTitles;


            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return resContestGETAPI;
        }


        @Override
        protected void onPostExecute(String result) {

            String response_string = "";
            // System.out.println("OnpostExecute----done-------");
            super.onPostExecute(result);


            try{
                Log.i("RES Contest---", resContestGETAPI);
                JsonParser parser = new JsonParser();
                JsonObject rootObj = parser.parse(resContestGETAPI).getAsJsonObject();

                JsonArray objJson = rootObj.get("objects").getAsJsonArray();

                for (int a=0;a<objJson.size();a++){

                    String Contest = objJson.get(a).getAsJsonObject().get("event").getAsString();
                    String id = objJson.get(a).getAsJsonObject().get("id").getAsString();


                    String comapyname = objJson.get(a).getAsJsonObject().get("resource").getAsJsonObject().get("name").getAsString();
                    String id_resource = objJson.get(a).getAsJsonObject().get("resource").getAsJsonObject().get("id").getAsString();

                    data.add(new DataRecycler(id+"---"+comapyname, id_resource+"----"+Contest, R.drawable.ic_local_movies_black_24dp));

                    adapter.notifyDataSetChanged();
                }
                progressDialog.dismiss();
            }
            catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            progressDialog.dismiss();
        }
    }



}

