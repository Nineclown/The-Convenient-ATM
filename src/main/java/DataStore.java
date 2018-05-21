import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;

public class DataStore{
    public Account loadAccountData(Bank bank, String accountNo){
        Account acct=null;
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/" + bank + "/" + accountNo + ".json"));
            acct = gson.fromJson(br, Account.class);
        }catch(IOException e){
            acct = null;
        }
        return acct;
    }

    public void saveAccountData(Account account){
        try{
            File datadir = new File("data");
            if(!datadir.exists()){
                datadir.mkdirs();
            }
            File bankdir = new File(datadir, account.getBank()+"");
            if(!bankdir.exists()){
                bankdir.mkdirs();
            }
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(bankdir, account.getAccountNo() + ".json")), "UTF8"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            output.write(gson.toJson(account));
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    public User loadUserData(String userId){
        User user=null;
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/user/" + userId + ".json"));
            user = gson.fromJson(br, User.class);
        }catch(IOException e){
            user = null;
        }
        return user;
    }

    public void saveUserData(User user){
        try{
            File datadir = new File("data");
            if(!datadir.exists()){
                datadir.mkdirs();
            }
            File userdir = new File(datadir, "user");
            if(!userdir.exists()){
                userdir.mkdirs();
            }
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(userdir, user.getUserId() + ".json")), "UTF-8"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            output.write(gson.toJson(user));
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Admin> loadAdminData(){
        ArrayList<Admin> admins;
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/admins.json"));
            admins = gson.fromJson(br, new TypeToken<ArrayList<Admin>>(){}.getType());
        }catch(IOException e){
            admins = null;
        }
        return admins;
    }

    public void saveAdminData(ArrayList<Admin> admins){
        try{
            File datadir = new File("data");
            if(!datadir.exists()){
                datadir.mkdirs();
            }
            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(new File(datadir, "admins.json")), "UTF-8");
            BufferedWriter bw = new BufferedWriter(output);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(admins, output);
            bw.write(gson.toJson(admins));
            bw.write("aa");
            output.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
