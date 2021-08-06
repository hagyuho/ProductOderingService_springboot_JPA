package com.jpabook.jpashop.repository;

import com.jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class MemberRepository {

    //@PersistenceContext
    private final EntityManager em;

    public void save(Member member){
        em.persist(member); // 영속성컨테스트에 맴버 객체를 넣는다 -> 커밋 시점에 db에 반영됨
    }

    public Member findOne(Long id){
        return em.find(Member.class, id); // 단건조회(타입,pk)
    }

    public List<Member> findAll(){
       return em.createQuery("select m from member ,", Member.class).getResultList();
    }

    public List<Member> findByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }

}
