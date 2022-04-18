package com.example.demo.service;

import com.example.demo.domain.model.Profile;
import com.example.demo.domain.model.Tip;
import com.example.demo.domain.repository.ProfileRepository;
import com.example.demo.domain.repository.SubscriptionRepository;
import com.example.demo.domain.repository.TipRepository;
import com.example.demo.domain.service.TipService;
import com.example.demo.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TipServiceImpl implements TipService {
    @Autowired
    TipRepository tipRepository;
    @Autowired
    ProfileRepository profileRepository;

    @Override
    public Page<Tip> getTips(Pageable pageable) {
        return tipRepository.findAll(pageable);
    }

    @Override
    public Tip createTip(Long chefId, Long readerId, Tip tip) {
        Profile chef = profileRepository.findById(chefId)
                .orElseThrow(()-> new ResourceNotFoundException("Chef","Id",chefId));
        Profile reader = profileRepository.findById(readerId)
                .orElseThrow(()-> new ResourceNotFoundException("Reader","Id",readerId));

        return tipRepository.save(tip.setReceiver(chef).setSender(reader));
    }

    @Override
    public Tip getTipById(Long tipId) {
        return tipRepository.findById(tipId)
                .orElseThrow(()-> new ResourceNotFoundException("Tip","Id",tipId));
    }
}
