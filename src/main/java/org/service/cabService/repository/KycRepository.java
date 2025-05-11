package org.service.cabService.repository;

import org.service.cabService.model.KycInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KycRepository extends JpaRepository<KycInfo, Long> {
    KycInfo findByUserId(Long userId);
}
