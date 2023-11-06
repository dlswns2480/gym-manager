package com.devgym.gymmanager.repository;

import com.devgym.gymmanager.domain.entity.Member;
import com.devgym.gymmanager.domain.type.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByName(String name);
    Optional<Member> findByMembership(Membership membership);
    Optional<Member> findByPhoneNumber(String phoneNumber);
}
