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

public class DataStore {
    public Account loadAccountData(Bank bank, String accountNo) {
        Account account;
        Gson gson = new Gson();
        try {
            BufferedReader br = new BufferedReader(new FileReader("data/" + bank + "/" + accountNo + ".json"));
            account = gson.fromJson(br, Account.class);
        } catch (IOException e) {
            account = null;
        }
        return account;
    }

    public void saveAccountData(Account account) throws DataStoreError {
        try {
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdirs();
            }

            File bankDirectory = new File(dataDirectory, account.getBank() + "");
            if (!bankDirectory.exists()) {
                bankDirectory.mkdirs();
            }

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(bankDirectory, account.getAccountNo() + ".json")), "UTF8"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            output.write(gson.toJson(account));
            output.close();
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }

    }

    public User loadUserData(String userId) {
        User user;
        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/user/" + userId + ".json"));
            user = gson.fromJson(br, User.class);
        } catch (IOException e) {
            user = null;
        }

        return user;
    }

    public void saveUserData(User user) throws DataStoreError {
        try {
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdirs();
            }

            File userDirectory = new File(dataDirectory, "user");
            if (!userDirectory.exists()) {
                userDirectory.mkdirs();
            }

            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(new File(userDirectory, user.getUserId() + ".json")), "UTF-8"));
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            output.write(gson.toJson(user));
            output.close();
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }
    }

    public ArrayList<Admin> loadAdminData() {
        ArrayList<Admin> admins;
        Gson gson = new Gson();

        try {
            BufferedReader br = new BufferedReader(new FileReader("data/admins.json"));
            admins = gson.fromJson(br, new TypeToken<ArrayList<Admin>>() {
            }.getType());
        } catch (IOException e) {
            admins = null;
        }

        return admins;
    }

    public void saveAdminData(ArrayList<Admin> admins) throws DataStoreError {
        try {
            File dataDirectory = new File("data");
            if (!dataDirectory.exists()) {
                dataDirectory.mkdirs();
            }

            OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(new File(dataDirectory, "admins.json")), "UTF-8");
            BufferedWriter bw = new BufferedWriter(output);
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(admins, output);
            bw.write(gson.toJson(admins));
            bw.write("aa");
            output.close();
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }
    }
}
