package com.letseat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.letseat.model.qrcode.TableQRCode;

public interface TableQRCodeRepository extends JpaRepository<TableQRCode, Integer>{

}
