package com.mi.parseoffline;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.parse.SignUpCallback;

import java.util.List;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            /*ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore").fromLocalDatastore();

            ParseObject.unpinAllInBackground(query.find());*/


            /*ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore").fromLocalDatastore();

            query.orderByAscending("score");

            query.whereMatchesKeyInQuery("score","score",ParseQuery.getQuery("GameScore1").fromLocalDatastore());

            List<ParseObject> listParse=query.find();

            for (int i = 0; i <listParse.size() ; i++) {
                System.out.println("listParse.get(0).playerName() = " + listParse.get(i).get("playerName"));
                System.out.println("listParse.get(0).score() = " + listParse.get(i).get("score"));
            }*/

            ParseObject gameScore1 = new ParseObject("GameScore1");
            gameScore1.put("score", 1331);
            gameScore1.put("playerName", "HEllo");
            gameScore1.put("cheatMode", false);
            gameScore1.pinInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null)
                    {
                        System.out.println("MainActivity.done");
                        try {


                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
                    else
                    {
                        System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
                    }
                }
            });


            ParseObject gameScore = new ParseObject("GameScore");
            gameScore.put("score", 1331);
            gameScore.put("playerName", "Sean Plott1");
            gameScore.put("cheatMode", false);
            gameScore.pinInBackground(new SaveCallback() {
                @Override
                public void done(ParseException e) {
                    if(e==null)
                    {
                        System.out.println("MainActivity.done");
                        try {
                            ParseQuery<ParseObject> query = ParseQuery.getQuery("GameScore").fromLocalDatastore();

                            query.orderByAscending("score");

                            query.whereMatchesKeyInQuery("score","score",ParseQuery.getQuery("GameScore1").fromLocalDatastore());

                            List<ParseObject> listParse=query.find();


                            for (int i = 0; i <listParse.size() ; i++) {
                                System.out.println("listParse.get(0).playerName() = " + listParse.get(i).get("playerName"));
                                System.out.println("listParse.get(0).score() = " + listParse.get(i).get("score"));
                            }

                        } catch (Exception e1) {
                            e1.printStackTrace();
                        }

                    }
                    else
                    {
                        System.out.println("e.getLocalizedMessage() = " + e.getLocalizedMessage());
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        //throw new RuntimeException("Test Exception123456789!");


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
