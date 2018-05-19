import com.google.gson.Gson;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DataStore{
    public Account loadAccountData(Bank bank, String accountNo){
        Account acct=null;
        Gson gson = new Gson();
        try{
            BufferedReader br = new BufferedReader(new FileReader("data/" + bank + "/" + accountNo));
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
            FileWriter fileWriter = new FileWriter(new File(bankdir, account.getAccountNo()));
            PrintWriter printWriter = new PrintWriter(fileWriter);
            Gson gson = new Gson();
            printWriter.print(gson.toJson(account));
            printWriter.close();
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }

    public User loadUserData(String userID){
        User user=null;

        return user;
    }
}
