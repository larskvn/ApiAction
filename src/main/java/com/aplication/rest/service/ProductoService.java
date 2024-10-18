package com.aplication.rest.service;

import com.aplication.rest.entity.Producto;
import com.aplication.rest.repositorio.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Producto> obtenerTodosLosProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Optional<Producto> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }

    // Crear un nuevo producto
    public Producto crearProducto(Producto producto) {
        return productoRepository.save(producto);
    }

    // Eliminar un producto por ID
    public void eliminarProducto(Long id) {
        productoRepository.deleteById(id);
    }

    // Actualizar un producto
    public Optional<Producto> actualizarProducto(Long id, Producto producto) {
        if (productoRepository.existsById(id)) {
            producto.setId(id); // Establecer el ID del producto que se está actualizando
            return Optional.of(productoRepository.save(producto));
        }
        return Optional.empty(); // Retorna vacío si el producto no existe
    }

    // Buscar productos por nombre
    public List<Producto> buscarPorNombre(String nombre) {
        return productoRepository.findByNombreContainingIgnoreCase(nombre);
    }

    // Obtener productos en un rango de precios
    public List<Producto> obtenerEnRangoDePrecios(double minPrecio, double maxPrecio) {
        return productoRepository.findByPrecioBetween(minPrecio, maxPrecio);
    }

    // Contar el total de productos
    public long contarTotal() {
        return productoRepository.count();
    }

    // Obtener productos ordenados por precio
    public List<Producto> obtenerOrdenadosPorPrecio() {
        return productoRepository.findAllByOrderByPrecioAsc();
    }
}
