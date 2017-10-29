package com.github.ansonliao.selenium.google.utils;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

import java.util.concurrent.CompletableFuture;

public class CredentialUtils {
    private static Config config;

    static {
        config = ConfigFactory.load();
    }

    public static String getUserName() {
        return config.getString("credential.username");
    }

    public static CompletableFuture<String> getFutureUserName() {
        return CompletableFuture.supplyAsync(
                () -> config.getString("credential.username"));
    }

    public static String getPassword() {
        return config.getString("credential.password");
    }
}
