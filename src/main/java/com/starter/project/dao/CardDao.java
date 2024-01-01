package com.starter.project.dao;

import com.starter.project.model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CardDao extends JpaRepository<Card, Long> {

//    @Query("SELECT cd from Card cd " +
//            "LEFT JOIN FETCH cd.account a "+
//            "LEFT JOIN FETCH a.customer c "+
//            "WHERE (UPPER(cd.cardNum) LIKE CONCAT('%',UPPER(%:searchText%),'%') or UPPER(c.firstName) LIKE CONCAT('%',UPPER(%:searchText%),'%')" +
//            " or UPPER(c.lastName) LIKE CONCAT('%',UPPER(%:searchText%),'%')) and rownum< :size")
@Query(value = "SELECT cd.* FROM Card cd " +
        "LEFT JOIN account a ON cd.account_id = a.id " +
        "LEFT JOIN customer c ON a.customer_id = c.id " +
        "WHERE (UPPER(cd.cardNum) LIKE CONCAT('%', UPPER(:searchText), '%') " +
        "OR UPPER(c.firstName) LIKE CONCAT('%', UPPER(:searchText), '%') " +
        "OR UPPER(c.lastName) LIKE CONCAT('%', UPPER(:searchText), '%')) " +
        "LIMIT :size", nativeQuery = true)
    List<Card> find(@Param("searchText") String searchText, @Param("size") int size);
}
