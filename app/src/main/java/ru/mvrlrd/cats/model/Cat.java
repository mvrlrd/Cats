package ru.mvrlrd.cats.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Cat {
        @Expose
        @SerializedName("url")
        public String url;
    }
