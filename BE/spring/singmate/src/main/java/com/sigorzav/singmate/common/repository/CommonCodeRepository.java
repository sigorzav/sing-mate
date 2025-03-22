package com.sigorzav.singmate.common.repository;

import com.sigorzav.singmate.common.dto.CommonCodeDTO;
import com.sigorzav.singmate.common.entity.CommonCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommonCodeRepository extends JpaRepository<CommonCodeEntity, Long> {

    List<CommonCodeDTO> findBy();

    List<CommonCodeDTO> findByDivisionAndCodeGroup(String division, String codeGroup);
}
