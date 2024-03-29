package com.starter.project.dao;

import com.starter.project.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountDao extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountNumber(String code);
//    @Query("SELECT a from Account a " +
//            "LEFT JOIN FETCH a.customer c "+
//            "WHERE(UPPER(a.accountNumber) LIKE CONCAT('%',UPPER(%:searchText%),'%') or UPPER(c.firstName) LIKE CONCAT('%',UPPER(%:searchText%),'%')" +
//            " or UPPER(c.lastName) LIKE CONCAT('%',UPPER(%:searchText%),'%')) and rownum< :size")
@Query(value = "SELECT a.* FROM Account a " +
        "LEFT JOIN customer c ON a.customer_id = c.id " +
        "WHERE (UPPER(a.accountNumber) LIKE CONCAT('%', UPPER(:searchText), '%') " +
        "OR UPPER(c.firstName) LIKE CONCAT('%', UPPER(:searchText), '%') " +
        "OR UPPER(c.lastName) LIKE CONCAT('%', UPPER(:searchText), '%')) " +
        "LIMIT :size", nativeQuery = true)
    List<Account> find(@Param("searchText") String searchText,@Param("size") int size);

}
