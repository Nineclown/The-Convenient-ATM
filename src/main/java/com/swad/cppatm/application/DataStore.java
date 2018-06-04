package com.swad.cppatm.application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.swad.cppatm.enums.Bank;
import com.swad.cppatm.exceptions.DataStoreError;

import java.io.File;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class DataStore {
    public Account loadAccountData(Bank bank, String accountNo) {
        Account account;
        Gson gson = new Gson();
        Path path = FileSystems.getDefault().getPath("data/" + bank + "/" + accountNo + ".json");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            account = gson.fromJson(br, Account.class);
        } catch (IOException e) {
            account = null;
        }
        return account;
    }

    public void saveAccountData(Account account) throws DataStoreError {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        File bankDirectory = new File(dataDirectory, account.getBank() + "");
        if (!bankDirectory.exists()) {
            bankDirectory.mkdirs();
        }

        Path path = FileSystems.getDefault().getPath(bankDirectory.getPath(), account.getAccountNo() + ".json");

        try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            writer.write(gson.toJson(account));
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }

    }

    public User loadUserData(String userId) {
        User user;
        Gson gson = new Gson();
        Path path = FileSystems.getDefault().getPath("data/user/" + userId + ".json");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            user = gson.fromJson(br, User.class);
        } catch (IOException e) {
            user = null;
        }
        return user;
    }

    public void saveUserData(User user) throws DataStoreError {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        File userDirectory = new File(dataDirectory, "user");
        if (!userDirectory.exists()) {
            userDirectory.mkdirs();
        }

        Path path = FileSystems.getDefault().getPath(userDirectory.getPath(), user.getUserId() + ".json");

        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            bw.write(gson.toJson(user));
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }
    }

    public ArrayList<Admin> loadAdminData() {
        ArrayList<Admin> admins;
        Gson gson = new Gson();
        Path path = FileSystems.getDefault().getPath("data/admins.json");

        try (BufferedReader br = Files.newBufferedReader(path, StandardCharsets.UTF_8)) {
            admins = gson.fromJson(br, new TypeToken<ArrayList<Admin>>() {
            }.getType());
        } catch (IOException e) {
            admins = new ArrayList<Admin>();
        }

        return admins;
    }

    public void saveAdminData(ArrayList<Admin> admins) throws DataStoreError {
        File dataDirectory = new File("data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }

        Path path = FileSystems.getDefault().getPath("data/admins.json");


        try (BufferedWriter bw = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            bw.write(gson.toJson(admins));
        } catch (Exception e) {
            throw new DataStoreError(e.getMessage());
        }
    }
}
