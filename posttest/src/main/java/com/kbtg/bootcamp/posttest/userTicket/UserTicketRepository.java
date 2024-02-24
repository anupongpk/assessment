package com.kbtg.bootcamp.posttest.userTicket;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTicketRepository extends JpaRepository<UserTicket, Long> {
}
