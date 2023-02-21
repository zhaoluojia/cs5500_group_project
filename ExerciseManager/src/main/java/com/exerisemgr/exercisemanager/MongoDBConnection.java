package com.exerisemgr.exercisemanager;

import com.exerisemgr.exercisemanager.model.User;
import com.mongodb.client.MongoClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoClients;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RestController;
import

import java.util.List;


public static void main(String[] args) {

        }
public class MongoDBConnection {
    MongoTemplate mt;
    List<User> users = mt.findAll(User.class, "users");
    System.out.println(users.get(0));
}
