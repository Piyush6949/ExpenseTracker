package org.expensetracker.category.repository;

import org.expensetracker.authentication.entity.User;
import org.expensetracker.category.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {

//    public List<Category> getAllbyUser()

//    @Override
    List<Category> findAllByUser(User user);

    Optional<Category> findByIdAndUser(UUID id, User user);
}

