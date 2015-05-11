package com.mi.parseoffline.database;

import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by punit on 4/5/15.
 */
public class Operations {
    public static void insertRecord(boolean isToDoInBackGround, List<ParseObject> list) {
        try {
            if (isToDoInBackGround) {
                ParseObject.pinAllInBackground(list);
            } else {
                ParseObject.pinAll(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteData(boolean isToDoInBackGround, String tableName, HashMap<String, Object> matchCases) {
        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName).fromLocalDatastore();

            if (matchCases != null) {
                for (Object map : matchCases.keySet()) {
                    query.whereEqualTo(map.toString(), matchCases.get(map));
                }
            }

            List<ParseObject> listParse = query.find();
            if (isToDoInBackGround) {
                ParseObject.unpinAllInBackground(listParse);
            } else {
                ParseObject.unpinAll(listParse);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateIfAvailOrInsert(boolean isToDoInBackGround, String tableName, HashMap<String, Object> matchCases, ParseObject object) {
        try {

            if (matchCases != null && getCount(tableName, matchCases) > 0) {
                ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName).fromLocalDatastore();

                if (matchCases != null) {
                    for (String map : matchCases.keySet()) {
                        query.whereEqualTo(map, matchCases.get(map));
                    }
                }

                List<ParseObject> listParse = query.find();

                for (int i = 0; i < listParse.size(); i++) {
                    ParseObject po = listParse.get(i);

                    for (String map : object.keySet()) {
                        po.put(map, object.get(map));
                    }
                    if (isToDoInBackGround) {
                        po.pinInBackground();
                    } else {
                        po.pin();
                    }
                }


            } else {
                if (isToDoInBackGround) {
                    object.pinInBackground();
                } else {
                    object.pin();
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getCount(String tableName, HashMap<String, Object> matchCases) {
        int countData = 0;

        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName).fromLocalDatastore();

            if (matchCases != null) {
                for (String map : matchCases.keySet()) {
                    query.whereEqualTo(map, matchCases.get(map));
                }
            }

            countData = query.count();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return countData;
    }

    public static List<ParseObject> getData(String tableName, HashMap<String, Object> matchCases) {
        List<ParseObject> listParse = new ArrayList<ParseObject>();

        try {
            ParseQuery<ParseObject> query = ParseQuery.getQuery(tableName).fromLocalDatastore();

            if (matchCases != null) {
                for (String map : matchCases.keySet()) {
                    query.whereEqualTo(map, matchCases.get(map));
                }
            }

            listParse = query.find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listParse;
    }




}
