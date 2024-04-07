package com.scorac.stockmanager.service.Repository;
import com.scorac.stockmanager.model.DayEvent;
import com.scorac.stockmanager.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;

@Repository
public interface DayEventRepository extends JpaRepository<DayEvent, Date> {
    Optional<DayEvent> findByDate(Date username);

}
