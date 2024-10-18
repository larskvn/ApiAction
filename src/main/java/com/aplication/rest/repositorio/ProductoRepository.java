package com.aplication.rest.repositorio;

import com.aplication.rest.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductoRepository  extends JpaRepository<Producto, Long> {
    // Buscar productos por nombre, ignorando mayúsculas
    List<Producto> findByNombreContainingIgnoreCase(String nombre);

    // Obtener productos en un rango de precios
    List<Producto> findByPrecioBetween(double minPrecio, double maxPrecio);

    // Obtener productos ordenados por precio ascendente
    List<Producto> findAllByOrderByPrecioAsc();
}
