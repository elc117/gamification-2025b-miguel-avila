package com.mubification.services;

import com.mubification.models.Store;
import com.mubification.repositories.StoreRepository;
import com.mubification.repositories.UserRepository;

import java.util.List;

public class StoreService {

    private StoreRepository storeRepo;
    private UserRepository userRepo;

    public StoreService() {
        this.storeRepo = new StoreRepository();
        this.userRepo = new UserRepository();
    }

    public List<Store> getStoreItems() {
        return storeRepo.getAllItems();
    }

    public String buyItem(int userId, int itemId) {
        Store item = storeRepo.getItemById(itemId);
        if (item == null) return "ITEM_NOT_FOUND";
        int userPoints = userRepo.getPoints(userId);
        if (userPoints < item.getPrice()) return "NOT_ENOUGH_POINTS";
        userRepo.updatePoints(userId, userPoints - item.getPrice());
        storeRepo.insertPurchase(userId, itemId);
        return "PURCHASE_OK";
    }
}
