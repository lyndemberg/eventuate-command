package com.webafilmes.repository;

import com.webafilmes.domain.Filme;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FilmeRepository extends JpaRepository<Filme,Long> {
}
