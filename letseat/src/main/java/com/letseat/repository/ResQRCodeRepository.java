package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.qrcode.ResQRCode;

public interface ResQRCodeRepository extends JpaRepository<ResQRCode, Integer>{

}
