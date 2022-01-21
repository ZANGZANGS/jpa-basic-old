package hellojpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.List;

public class JpaMain {
    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");//persistence.xml의 persistence-unit name 속성

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            //Member member = new Member();

            Team team = new Team();
            team.setName("teamA");;
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.changeTeam(team);
            em.persist(member);


            Member findMember = em.find(Member.class, member.getId());
            List<Member> members = findMember.getTeam().getMembers();

            for (Member mem : members){
                System.out.println("==============");
                System.out.println(mem.getUsername() );
            }




            tx.commit();

        } catch (Exception e) {
            tx.rollback();
        }finally {
            em.close();
        }

        emf.close();
    }
}
