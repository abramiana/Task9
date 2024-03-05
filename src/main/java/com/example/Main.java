package com.example;

import com.example.checker.HttpStatusChecker;
import com.example.cli.HttpImageStatusCli;
import com.example.downloader.HttpStatusImageDownloader;

public class Main {
    public static void main(String[] args) {
        HttpStatusChecker checker = new HttpStatusChecker();
        HttpStatusImageDownloader downloader = new HttpStatusImageDownloader(checker);
        HttpImageStatusCli cli = new HttpImageStatusCli(checker, downloader);
        cli.askStatus();
    }
}
