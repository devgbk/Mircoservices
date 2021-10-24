package com.example.explorecali.service;

import com.example.explorecali.domain.Difficulty;
import com.example.explorecali.domain.Region;
import com.example.explorecali.domain.Tour;
import com.example.explorecali.domain.TourPackage;
import com.example.explorecali.repo.TourPackageRepository;
import com.example.explorecali.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Tour  Service
 * <p>
 * Created by Mary Ellen Bowman
 */
@Service
public class TourService {
    private final TourRepository tourRepository;
    private final TourPackageRepository tourPackageRepository;

    @Autowired
    public TourService(TourRepository tourRepository, TourPackageRepository tourPackageRepository) {
        this.tourRepository = tourRepository;
        this.tourPackageRepository = tourPackageRepository;
    }

    /**
     * Create a new Tour Object and persist it to the Database.
     *
     * @param title           title
     * @param description     description
     * @param blurb           blurb
     * @param price           price
     * @param duration        duration
     * @param bullets         bullets
     * @param keywords        keywords
     * @param tourPackageName tour package name
     * @param difficulty      difficulty
     * @param region          region
     * @return Tour Entity
     */
    public Tour createTour(String title, String description, String blurb, Integer price,
                           String duration, String bullets,
                           String keywords, String tourPackageName, Difficulty difficulty, Region region) {
        TourPackage tourPackage = tourPackageRepository.findByName(tourPackageName)
                .orElseThrow(() -> new RuntimeException("Tour Package doesnt not exist"));
        return tourRepository.save(new Tour(title, description, blurb, price, duration, bullets, keywords, tourPackage, difficulty, region));
    }

    /**
     * Calculate the number of Tours in the Database.
     *
     * @return the total.
     */
    public long total() {
        return tourRepository.count();
    }
}

