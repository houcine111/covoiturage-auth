package com.covoiturage.authentication.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.covoiturage.authentication.Models.Admin;
public interface Adminrepo extends JpaRepository<Admin, Long> {


}
