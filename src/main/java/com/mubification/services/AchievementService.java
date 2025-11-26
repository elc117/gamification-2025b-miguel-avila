package com.mubification.services;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.mubification.models.Achievement;
import com.mubification.models.AchievementDTO;
import com.mubification.repositories.AchievementRepository;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;

public class AchievementService {

    private AchievementRepository repo = new AchievementRepository();
    private UserStatsService statsService = new UserStatsService();
    
    public List<AchievementDTO> getUserAchievements(int userId) {

        System.out.println("SERVICE → getUserAchievements(" + userId + ")");

        List<Achievement> all;
        List<Integer> completed;

        try {
            all = repo.getAll();
            System.out.println("SERVICE → Achievements carregados: " + all.size());
        } catch (Exception e) {
            System.err.println("ERRO ao carregar achievements:");
            e.printStackTrace();
            throw e;
        }

        try {
            completed = repo.getCompletedAchievements(userId);
            System.out.println("SERVICE → Completed carregados: " + completed.size());
        } catch (Exception e) {
            System.err.println("ERRO ao carregar completed achievements:");
            e.printStackTrace();
            throw e;
        }

        List<AchievementDTO> result = new ArrayList<>();

        for (Achievement a : all) {

            boolean alreadyCompleted = completed.contains(a.getId());

            result.add(new AchievementDTO(
                a.getId(),
                a.getName(),
                a.getDescription(),
                alreadyCompleted,
                a.getPoints()
            ));
        }

        return result;
    }

    public void checkAchievements(int userId) {

        System.out.println("SERVICE → checkAchievements(" + userId + ")");

        List<Achievement> all = repo.getAll();
        List<Integer> completed = repo.getCompletedAchievements(userId);

        for (Achievement a : all) {

            if (completed.contains(a.getId()))
                continue;

            JsonObject criteria = JsonParser.parseString(a.getCriteria()).getAsJsonObject();

            boolean met = true;

            for (Map.Entry<String, ?> entry : criteria.entrySet()) {
                String key = entry.getKey();
                int required = criteria.get(key).getAsInt();

                int value = statsService.getStat(userId, key);

                if (value < required) {
                    met = false;
                    break;
                }
            }

            if (met) {
                repo.markCompleted(userId, a.getId());
                System.out.println("🏆 User " + userId + " ganhou: " + a.getName());
            }
        }
    }
}
