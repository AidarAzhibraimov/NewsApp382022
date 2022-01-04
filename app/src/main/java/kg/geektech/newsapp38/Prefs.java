package kg.geektech.newsapp38;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;

public class Prefs {

    private SharedPreferences preferences;

    public  Prefs(Context context) {
        preferences = context.getSharedPreferences("settings", Context.MODE_PRIVATE);
    }

    public void saveBoardState(){
         preferences.edit().putBoolean( "isShown", true).apply();
    }

    public boolean isBoarShown() {
        return  preferences.getBoolean("isShown", false);
    }

    public void isName(String name) {
        preferences.edit().putString("name",name).apply();
    }
    public String getIsName(){
        return preferences.getString("name", null);
    }

    public void isImage(Uri uri){
        preferences.edit().putString("image", uri.toString()).apply();
    }
    public String getImage(){
        return preferences.getString("image", "");
    }
    public void isEmail(String email) {
        preferences.edit().putString("email", email).apply();
    }
    public String getIsEmail(){
        return preferences.getString("email", null);
    }
    public void isPhone(String phone){
        preferences.edit().putString("phone", phone).apply();
    }
    public String getPhone(){
        return preferences.getString("phone", null);
    }
    public void isGender(String gender){
        preferences.edit().putString("gender", gender).apply();
    }
    public String getGender(){
        return preferences.getString("gender", null);
    }
    public void isDate(String date){
        preferences.edit().putString("date", date).apply();
    }
    public String getDate(){
        return preferences.getString("date", null);
    }
}
