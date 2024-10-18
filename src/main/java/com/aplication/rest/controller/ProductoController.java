package com.aplication.rest.controller;

import com.aplication.rest.entity.Producto;
import com.aplication.rest.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    // Obtener todos los productos
    @GetMapping
    public ResponseEntity<List<Producto>> obtenerTodos() {
        List<Producto> productos = productoService.obtenerTodosLosProductos();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Obtener un producto por ID
    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtenerPorId(@PathVariable Long id) {
        Optional<Producto> producto = productoService.obtenerProductoPorId(id);
        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Crear un nuevo producto
    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoProducto);
    }

    // Actualizar un producto
    @PutMapping("/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto) {
        Optional<Producto> productoActualizado = productoService.actualizarProducto(id, producto);
        return productoActualizado.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Eliminar un producto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id) {
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar productos por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Producto>> buscarPorNombre(@RequestParam String nombre) {
        List<Producto> productos = productoService.buscarPorNombre(nombre);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Obtener productos en un rango de precios
    @GetMapping("/rango")
    public ResponseEntity<List<Producto>> obtenerEnRangoDePrecios(
            @RequestParam double minPrecio,
            @RequestParam double maxPrecio) {
        List<Producto> productos = productoService.obtenerEnRangoDePrecios(minPrecio, maxPrecio);
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }

    // Contar total de productos
    @GetMapping("/total")
    public ResponseEntity<Long> contarTotal() {
        long total = productoService.contarTotal();
        return new ResponseEntity<>(total, HttpStatus.OK);
    }

    // Obtener productos ordenados por precio
    @GetMapping("/ordenados/precio")
    public ResponseEntity<List<Producto>> obtenerOrdenadosPorPrecio() {
        List<Producto> productos = productoService.obtenerOrdenadosPorPrecio();
        return new ResponseEntity<>(productos, HttpStatus.OK);
    }



}
