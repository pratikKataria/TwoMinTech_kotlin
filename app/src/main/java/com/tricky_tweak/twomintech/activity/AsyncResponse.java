package com.tricky_tweak.twomintech.activity;

import com.tricky_tweak.twomintech.model.News;

import java.util.ArrayList;

public interface AsyncResponse {
    void processFinish(ArrayList<News> output);
}