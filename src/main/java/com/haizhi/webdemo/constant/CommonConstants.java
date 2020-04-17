package com.haizhi.webdemo.constant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class CommonConstants {
    public static final Gson KAFKA_GSON = new GsonBuilder().create();

    public static final String TEST_TOPIC = "test_topic";

    public static final String TEST_TOPIC_KEY = "test_topic_key";
}
