package com.ansiyida.cgjl.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ansiyida.cgjl.MyApplication;
import com.ansiyida.cgjl.R;
import com.ansiyida.cgjl.http.Constant;
import com.ansiyida.cgjl.tab.ChannelItem;
import com.ansiyida.cgjl.util.LogUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by CYX on 2017/12/6.
 * 使用方法：
 * DbMannager mannager=new DbMannager(MainActivity.this);//创建此类对象
 * SQLiteDatabase sb = mannager.openDatabase();          //访问ansiyida表
 * manager.closeDatabase();                              //关闭数据库
 */
public class DbMannager {
    private File dBFile;
    private static SQLiteDatabase database;
    private static DbMannager instance;

    public static DbMannager getInstance() {
        LogUtil.i("DbMannager", "instance---：" + instance);
        if (instance == null) {
            instance = new DbMannager();
        }
        return instance;
    }

    private DbMannager() {
        //存放.db文件的目录File1
        LogUtil.i("DbMannager", "Constant.DbAbsolutePath---：" + Constant.dbPath);
        dBFile = new File(Constant.dbPath);
        if (!dBFile.exists()) {
            dBFile.mkdirs();//创建文件夹
        }
        openDatabase();
    }

    public void openDatabase() {
        if (database == null) {
            database = openDatabase("ansiyida.db");
            LogUtil.i("DbMannager", "打开数据库成功,database:" + database);
        }else {
            LogUtil.i("DbMannager", "打开数据库失败,database:" + database);

        }
    }


    //重载
    public static DbMannager getInstance(boolean isUpgrade) {
        LogUtil.i("DbMannager", "instance---：" + instance);
//        instance = null;
        instance = new DbMannager(isUpgrade);
        return instance;
    }

    private DbMannager(boolean isUpgrade) {
        LogUtil.i("DbMannager", "Constant.DbAbsolutePath---：" + Constant.dbPath);
        dBFile = new File(Constant.dbPath);
        openDatabase(isUpgrade);
    }

    public void openDatabase(boolean isUpgrade) {
        LogUtil.i("DbMannager", "database---：" + database);
//        database = null;
        database = openDatabase("ansiyida.db");
        LogUtil.i("zip", "打开数据库成功,database:" + database);
//        showDatabaseAllTabelName();
    }

    /**
     * 显示数据库中所有的表名
     */
    public void showDatabaseAllTabelName() {
        try {
        String sql = "select name from sqlite_master where type='table' order by name";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            LogUtil.i("DbMannager", "cursor.getCount()-----：" + cursor.getCount());
            while (cursor.moveToNext()) {
                LogUtil.i("DbMannager", "数据库表名-----：" + cursor.getString(0));
            }
            cursor.close();
        }
    }
             catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
    }

    public void closeDatabase() {
        if(database!=null) {
            if (database.isOpen()) {
                database.close();
                database = null;
                LogUtil.i("zip", "关闭数据库成功");
            }
            if (instance != null) {
                instance = null;
            }
        }
    }

    public SQLiteDatabase getDB() {
        openDatabase();
        return database;
    }

    private SQLiteDatabase openDatabase(String dbfile) {
        try {
            LogUtil.i("DbMannager", "dBFile.getPath()---：" + dBFile.getPath());
            File newDbFile = new File(dBFile, dbfile);
            if (!newDbFile.exists()) {
                LogUtil.i("DbMannager", "开始讲db文件导入本地");
                //判断数据库文件是否存在，若不存在则执行导入，否则直接打开数据库
                InputStream is = MyApplication.getInstance().getmContext().getResources().openRawResource(R.raw.ansiyida); //欲导入的数据库
                FileOutputStream fos = new FileOutputStream(newDbFile);
                byte[] buffer = new byte[1024 * 3];
                int count = 0;
                while ((count = is.read(buffer)) > 0) {
                    fos.write(buffer, 0, count);
                    fos.flush();
                }
                fos.close();
                is.close();
                LogUtil.i("DbMannager", "导入本地成功");
            }
            SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(newDbFile, null);
            return db;

        } catch (IOException e) {
            LogUtil.i("DbMannager", "没有找到指定的File" + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public void setData(List<ChannelItem> list, String tableName) {
        int length = list.size();
        if (length > 0) {
            database.beginTransaction();
            try {
                for (int x = 0; x < length; x++) {
                    String sql = "replace into " + tableName + "(id,name,orderId,selected) values(?,?,?,?)";
                    ChannelItem channelItem = list.get(x);
                    int id = channelItem.getId();
                    if (id == 0) {
                        id = -10000 + x;
                        database.execSQL(sql, new String[]{id + "," + id, channelItem.getName(), (x + 1) + "", channelItem.getSelected() + ""});
                    } else {
                        database.execSQL(sql, new String[]{channelItem.getId() + "," + channelItem.getJat_pid(), channelItem.getName(), (x + 1) + "", channelItem.getSelected() + ""});

                    }
                    LogUtil.i("name","name:"+channelItem.getName());
                }
                database.setTransactionSuccessful();
                LogUtil.i("sql", "插入完成");
            } catch (Exception e) {
                LogUtil.i("sql", "erro:" + e.getMessage());
            } finally {
                database.endTransaction();
            }
        }
    }

    public HashMap<String, String> getData(String tableName) {
        HashMap<String, String> hashMap = null;
        String sql = "select name,selected from " + tableName + " where name='图片' or name='视频'";
        try {


        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            hashMap = new HashMap<>();
            while (cursor.moveToNext()) {
                hashMap.put(cursor.getString(0), cursor.getString(1));
            }

            cursor.close();
        }

    }
         catch (NullPointerException ex)
    {
        LogUtil.i("NULL", ex.toString());
    }
        return hashMap;

    }

    public void clearTable(String tableName) {
        String sql_delete = "delete from " + tableName;
        database.execSQL(sql_delete);
        LogUtil.i("sql", "删除完成");
    }

    public void showSQL(String tableName) {
        String sql = "select * from " + tableName;
        try {


        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            while (cursor.moveToNext()) {
                if ("mine_tab".equals(tableName)) {
                    LogUtil.i("sql7", "id:" + cursor.getString(0) + ",name:" + cursor.getString(1) + ",orderId:" + cursor.getString(2) + ",selected:" + cursor.getString(3));
                }
            }
            cursor.close();
        }

    }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
    }

    public int getTableDataCount(String tableName) {
        String sql = "select * from " + tableName;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                int length=cursor.getCount();
                cursor.close();
                return length;
            }
        }

         catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }

        return 0;
    }

    public ArrayList<ChannelItem> getChoiceItem(String tableName) {
        ArrayList<ChannelItem> channelItems = null;
        String sql = "select * from " + tableName + " where selected=1 order by orderId";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                channelItems = new ArrayList<>();
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    String[] arr = string.split(",");
                    ChannelItem item = new ChannelItem(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), "", cursor.getString(1), 0, 1);
                    channelItems.add(item);
                }
                cursor.close();
            }
        }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }

        return channelItems;

    }

    public ArrayList<ChannelItem> getDefaultItem(String tableName) {
        ArrayList<ChannelItem> channelItems = null;
        String sql = "select * from " + tableName + " where selected=0 order by orderId";
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                channelItems = new ArrayList<>();
                while (cursor.moveToNext()) {
                    String string = cursor.getString(0);
                    String[] arr = string.split(",");
                    ChannelItem item = new ChannelItem(Integer.parseInt(arr[0]), Integer.parseInt(arr[1]), "", cursor.getString(1), 0, 0);
                    channelItems.add(item);
                }
                cursor.close();
            }
        }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
        return channelItems;
    }

    public int getTableItemCount(String tableName) {
        int count = 0;
        String sql = "select count(id) as count from " + tableName;
        try {
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                cursor.moveToNext();
                count = cursor.getInt(0);
                cursor.close();
            }
        }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
        return count;
    }

    public HashMap<String, Object> getTabMsg(int position) {
        HashMap<String, Object> map = null;
        String sql = "select * from channel2 where selected =1 and orderId=" + (position + 1);
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.getCount() > 0) {
                map = new HashMap<>();
                cursor.moveToNext();
                map.put("id", cursor.getString(0));

                map.put("name", cursor.getString(1));
            }
            cursor.close();
        }
        return map;
    }

    public String getChoiceChannelId(String tableName) {
        StringBuffer channelItems = null;
        String sql = "select id from " + tableName + " where selected=1 order by orderId";
        try {

            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                channelItems = new StringBuffer();
                int position = 0;
                while (cursor.moveToNext()) {
                    if (position > 1) {
                        String s = cursor.getString(0).split(",")[0];
                        if (!"98".equals(s) && !"97".equals(s)) {
                            channelItems.append(s + ",");
                        }
                        LogUtil.i("dsd", "id:" + cursor.getString(0));

                    }
                    position++;
                }
                cursor.close();
            }
        }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
        if (channelItems == null) {
            return null;
        } else {
            return channelItems.toString();

        }
    }

    public ArrayList<String> saveSQLData() {

        String[] saveTableName = {"mine_tab", "channel2"};
        int length = saveTableName.length;
        if (length > 0) {
            ArrayList<String> sqlList = new ArrayList<>();
            try {
                for (int x = 0; x < length; x++) {
                    String sql = "select * from " + saveTableName[x];
                    Cursor cursor = database.rawQuery(sql, null);
                    if (cursor != null) {
                        while (cursor.moveToNext()) {
                            sqlList.add("replace into " + saveTableName[x] + " values(" + cursor.getString(0) + "," + cursor.getString(1) + "," + cursor.getInt(2) + "," + cursor.getString(3) + ")");
                        }
                        cursor.close();
                    }
                }
            }
            catch (NullPointerException ex)
            {
                LogUtil.i("NULL", ex.toString());
            }
            return sqlList;
        } else {
            return null;
        }


    }

    public void updateSQLData(ArrayList<String> sqlList) {
        if (sqlList != null) {
            int length = sqlList.size();
            if (length > 0) {
                database.beginTransaction();
                try {
                    for (String sqlStr : sqlList) {
                        database.execSQL(sqlStr);
                    }
                    database.setTransactionSuccessful();
                } catch (Exception e) {
                    LogUtil.i("exception", "exception:" + e.getMessage());
                } finally {
                    database.endTransaction();
                }

            }
        }
    }

    public boolean isTableHave(String table) {
        try {
            String sql = "select count(*) as c from Sqlite_master  where type ='table' and name ='" + table + "'";
            Cursor cursor = database.rawQuery(sql, null);
            if (cursor != null) {
                if (cursor.moveToNext()) {
                    int count = cursor.getInt(0);
                    if (count > 0) {
                        cursor.close();
                        return true;
                    }
                }
                cursor.close();
            }
        }
        catch (NullPointerException ex)
        {
            LogUtil.i("NULL", ex.toString());
        }
        return false;
    }

    public String getValueForKey(String key) {
        String value = "";
        String sql = "select uValue from cache_info where ukey='" + key + "'";
        Cursor cursor = database.rawQuery(sql, null);
        if (cursor != null) {
            if (cursor.moveToNext()) {
                value = cursor.getString(0);
            }
            cursor.close();
        }
        return value;
    }
    public String getInterst(){
        String data="";
        String sql="select name from interst_tab where selected=1";
        Cursor cursor=database.rawQuery(sql,null);
        if (cursor!=null){
            StringBuffer sb=new StringBuffer();
            while (cursor.moveToNext()){
                sb.append(cursor.getString(0)+",");
            }
            data=sb.toString();
            cursor.close();
        }

        return data;
    }
    public void changeChoiceChannel(String itemName){
        String sql_select="select * from channel2 where name=?";
        Cursor cursor=database.rawQuery(sql_select,new String[]{itemName});
        if (cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToNext();
                String string = cursor.getString(3);
                if ("0".equals(string)){
                    String id=cursor.getString(0);
                    String sql="select * from channel2 where selected=1";
                    Cursor cursor1=database.rawQuery(sql,null);
                    if (cursor1!=null){
                        int length=cursor1.getCount();
                        String sql_update="update  channel2 set orderId=?,selected=1 where name=?";
                        database.execSQL(sql_update,new String[]{(length+1)+"",itemName});
                        cursor1.close();
                    }
                }
            }
            cursor.close();
        }
    }
    public int getChannelPosition(String itemName){
        int position=0;
        String sql="select orderId from channel2 where name=?";
        Cursor cursor=database.rawQuery(sql,new String[]{itemName});
        if (cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToNext();
                position=cursor.getInt(0);
            }
            cursor.close();
        }
        return position;
    }
    public int getChannelisChooice(String itemName){
        int position=0;
        String sql="select orderId,selected from channel2 where name=?";
        Cursor cursor=database.rawQuery(sql,new String[]{itemName});
        if (cursor!=null){
            if (cursor.getCount()>0){
                cursor.moveToNext();
                String selected=cursor.getString(1);
                if ("1".equals(selected)){
                    position=cursor.getInt(0);
                }
            }
            cursor.close();
        }
        return position;
    }
}
