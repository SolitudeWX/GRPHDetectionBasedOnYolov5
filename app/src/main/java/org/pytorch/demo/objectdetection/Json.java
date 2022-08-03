package org.pytorch.demo.objectdetection;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.channels.FileChannel;
import java.sql.Time;

public class Json {
    public String name;
    public String time;
    private Context context;
    public int[] data;
    private static File CacheRoot;
    public Json(Context context){
        this.context=context;
    }
    public void save(){
        JSONObject jsonObject = new JSONObject();
        JSONObject Data = new JSONObject();

        try {
            Data.put("adult",this.data[0]);
            Data.put("larvae",this.data[1]);
            jsonObject.put("path",this.name);
            jsonObject.put("time",this.time);
            jsonObject.put("data",Data);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String fileName="history.json";
        boolean append=true;
        Log.i("TESTJSON", jsonObject.toString());
        CacheRoot = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED ? context.getExternalCacheDir() : context.getCacheDir();
        FileOutputStream fos = null;
        ObjectOutputStream os = null;
        try {
            File ff = new File(CacheRoot, fileName);
            boolean boo = ff.exists();
            fos = new FileOutputStream(ff, append);
            os = new ObjectOutputStream(fos);
            if (append && boo) {
                FileChannel fc = fos.getChannel();
                fc.truncate(fc.position() - 4);

            }

            os.writeObject(jsonObject.toString());

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            if (os != null) {
                try {
                    os.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

        }

    }

}
