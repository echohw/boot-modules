package com.example.accessrecord.persistence.repos;

import com.example.accessrecord.persistence.entity.AccessRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface AccessRecordRepos extends JpaRepository<AccessRecord, Long>, JpaSpecificationExecutor<AccessRecord> {

}
