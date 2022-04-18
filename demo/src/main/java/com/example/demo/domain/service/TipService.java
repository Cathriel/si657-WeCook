package com.example.demo.domain.service;

import com.example.demo.domain.model.Subscription;
import com.example.demo.domain.model.Tip;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TipService {

    Page<Tip> getTips(Pageable pageable);

    Tip createTip(Long chefId,Long readerId, Tip tip);

    Tip getTipById(Long tipId);

}
