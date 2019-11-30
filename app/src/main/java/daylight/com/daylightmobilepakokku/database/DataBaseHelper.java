package daylight.com.daylightmobilepakokku.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import java.io.IOException;
import java.util.ArrayList;

import daylight.com.daylightmobilepakokku.viewmodel.NewProdcutModel;
import daylight.com.daylightmobilepakokku.viewmodel.SecondProdcutModel;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String dbname="favproduct.db";
    public static final String table1name="brandnew";
    public static final String table2name="seconduse";
    public static final String table3name="accessory";
    public DataBaseHelper(Context context){
        super(context,dbname,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE "+table1name+"(B_id Text PRIMARY KEY,B_name TEXT,B_model TEXT,B_price NUMBER,B_detail Text,B_img Text)");
        db.execSQL("CREATE TABLE "+table2name+"(S_id Text PRIMARY KEY,S_name TEXT,S_model TEXT,S_price NUMBER,S_detail Text,S_img Text,S_shop TEXT)");
        db.execSQL("CREATE TABLE "+table3name+"(A_id Text PRIMARY KEY,A_name TEXT,A_model TEXT,A_price NUMBER,A_detail Text,A_img Text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+table1name);
        db.execSQL("DROP TABLE IF EXISTS "+table2name);
        db.execSQL("DROP TABLE IF EXISTS "+table3name);
    }
    public int BremoveData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete(table1name,"B_id=?",new String[]{name});
        return i;
    }
    public int SremoveData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete(table2name,"S_id=?",new String[]{name});
        return i;
    }
    public int AremoveData(String name){
        SQLiteDatabase db=this.getWritableDatabase();
        int i=db.delete(table3name,"A_id=?",new String[]{name});
        return i;
    }

    public boolean BinsertData(String id,String name,String model,int price,String detail,String imgurl){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("B_id",id);
        contentValues.put("B_name",name);
        contentValues.put("B_model",model);
        contentValues.put("B_price",price);
        contentValues.put("B_detail",detail);
        contentValues.put("B_img",imgurl);
        long result=db.insert(table1name,null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean SinsertData(String id,String name,String model,int price,String detail,String imgurl,String shop){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("S_id",id);
        contentValues.put("S_name",name);
        contentValues.put("S_model",model);
        contentValues.put("S_price",price);
        contentValues.put("S_detail",detail);
        contentValues.put("S_img",imgurl);
        contentValues.put("S_shop",shop);
        long result=db.insert(table2name,null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public boolean AinsertData(String id,String name,String model,int price,String detail,String imgurl){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("A_id",id);
        contentValues.put("A_name",name);
        contentValues.put("A_model",model);
        contentValues.put("A_price",price);
        contentValues.put("A_detail",detail);
        contentValues.put("A_img",imgurl);
        long result=db.insert(table3name,null,contentValues);
        db.close();
        if(result==-1){
            return false;
        }
        else {
            return true;
        }
    }
    public ArrayList<NewProdcutModel> getAllBAProductData(String tableName) throws IOException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NewProdcutModel> bary = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + tableName, null);

        if(cursor.moveToFirst()) {
            do {
                bary.add(new NewProdcutModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        close();
        return bary;
    }
    public ArrayList<NewProdcutModel> getAllAProductData() throws IOException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<NewProdcutModel> bary = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + table3name, null);

        if(cursor.moveToFirst()) {
            do {
                bary.add(new NewProdcutModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        close();
        return bary;
    }
    public ArrayList<SecondProdcutModel> getAllSProductData() throws IOException {
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<SecondProdcutModel> bary = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM " + table2name, null);

        if(cursor.moveToFirst()) {
            do {
                bary.add(new SecondProdcutModel(cursor.getString(0),cursor.getString(1),cursor.getString(2),cursor.getInt(3),cursor.getString(4),cursor.getString(5),cursor.getString(6)));
            }while(cursor.moveToNext());
        }
        cursor.close();
        close();
        return bary;
    }


}
