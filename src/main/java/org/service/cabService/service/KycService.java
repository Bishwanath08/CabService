package org.service.cabService.service;

import org.service.cabService.model.KycInfo;
import org.service.cabService.repository.KycRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class KycService {

    @Autowired
    private KycRepository kycRepository;


    public void saveOrUpdateFullKyc(Long userId, String adharNumber, String adharName, MultipartFile adharImage,
                                    String panNumber, String panName, LocalDate dob, MultipartFile panImage) throws  Exception{
        String adharPath = saveFile(adharImage, "uploads/adhar/");
        String panPath = saveFile(panImage, "uploads/pan/");

        KycInfo kyc = kycRepository.findByUserId(userId);
        if (kyc == null) kyc = new KycInfo();

        kyc.setUserId(userId);
        kyc.setAdharNumber(adharNumber);
        kyc.setAdharName(adharName);
        kyc.setAdharImagePath(adharPath);
        kyc.setPanNumber(panNumber);
        kyc.setPanName(panName);
        kyc.setDob(dob);
        kyc.setPanImagePath(panPath);

        kycRepository.save(kyc);
    }

    private String saveFile (MultipartFile file, String folderPath) throws  IOException {
        String fileName = UUID.randomUUID() + "_" +file.getOriginalFilename();
        Path path = Paths.get(folderPath + fileName);
        Files.createDirectories(path.getParent());
        Files.write(path, file.getBytes());
        return path.toString();
    }
    public boolean isKycComplete(Long userId){
        return kycRepository.findByUserId(userId) != null;
    }
}
