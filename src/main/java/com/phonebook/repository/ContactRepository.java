package com.phonebook.repository;

import com.phonebook.entity.Contact;
import com.phonebook.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {
    
    List<Contact> findByUserOrderByFirstNameAsc(User user);
    
    Optional<Contact> findByIdAndUser(Long id, User user);
    
    Page<Contact> findByUserOrderByFirstNameAsc(User user, Pageable pageable);
    
    @Query("SELECT c FROM Contact c WHERE c.user = :user AND " +
           "(LOWER(c.firstName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "LOWER(c.lastName) LIKE LOWER(CONCAT('%', :search, '%')) OR " +
           "c.phoneNumber LIKE CONCAT('%', :search, '%') OR " +
           "LOWER(c.email) LIKE LOWER(CONCAT('%', :search, '%')))")
    Page<Contact> searchContacts(@Param("user") User user, @Param("search") String search, Pageable pageable);
    
    boolean existsByPhoneNumberAndUser(String phoneNumber, User user);
    
    long countByUser(User user);
}