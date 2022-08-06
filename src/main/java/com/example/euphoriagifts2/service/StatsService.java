package com.example.euphoriagifts2.service;

import com.example.euphoriagifts2.model.view.StatsView;

public interface StatsService {
    void onRequest();
    StatsView getStats();
}
