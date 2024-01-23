package com.devgym.gymmanager.member.repository;

import com.devgym.gymmanager.member.domain.Member;
import com.devgym.gymmanager.member.domain.Membership;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);

    List<Member> findByMembership(Membership membership);

    Optional<Member> findByPhoneNumber(String phoneNumber);
}
