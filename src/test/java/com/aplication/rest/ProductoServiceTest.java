package com.aplication.rest;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.aplication.rest.entity.Producto;
import com.aplication.rest.repositorio.ProductoRepository;
import com.aplication.rest.service.ProductoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;
import java.util.List;
import java.util.Arrays;
public class ProductoServiceTest {
    @Mock
    private ProductoRepository productoRepository;

    @InjectMocks
    private ProductoService productoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa los mocks
    }

    // Prueba para obtener todos los productos
    @Test
    void testObtenerTodosLosProductos() {
        List<Producto> productosMock = Arrays.asList(
                new Producto(1L, "Producto 1", 100.0, "Categoría 1"),
                new Producto(2L, "Producto 2", 200.0, "Categoría 2")
        );

        when(productoRepository.findAll()).thenReturn(productosMock);

        List<Producto> productos = productoService.obtenerTodosLosProductos();

        assertEquals(2, productos.size());
        assertEquals("Producto 1", productos.get(0).getNombre());
        verify(productoRepository, times(1)).findAll();
    }

    // Prueba para obtener producto por ID
    @Test
    void testObtenerProductoPorId() {
        Producto productoMock = new Producto(1L, "Producto 1", 100.0, "Categoría 1");

        when(productoRepository.findById(1L)).thenReturn(Optional.of(productoMock));

        Optional<Producto> producto = productoService.obtenerProductoPorId(1L);

        assertTrue(producto.isPresent());
        assertEquals("Producto 1", producto.get().getNombre());
        verify(productoRepository, times(1)).findById(1L);
    }

    // Prueba para crear producto
    @Test
    void testCrearProducto() {
        Producto productoMock = new Producto(1L, "Producto 1", 100.0, "Categoría 1");

        when(productoRepository.save(productoMock)).thenReturn(productoMock);

        Producto producto = productoService.crearProducto(productoMock);

        assertEquals("Producto 1", producto.getNombre());
        verify(productoRepository, times(1)).save(productoMock);
    }

    // Prueba para eliminar producto
    @Test
    void testEliminarProducto() {
        doNothing().when(productoRepository).deleteById(1L);

        productoService.eliminarProducto(1L);

        verify(productoRepository, times(1)).deleteById(1L);
    }

    // Prueba para actualizar un producto existente
    @Test
    void testActualizarProductoExistente() {
        Producto productoMock = new Producto(1L, "Producto Actualizado", 150.0, "Categoría 1");

        when(productoRepository.existsById(1L)).thenReturn(true);
        when(productoRepository.save(productoMock)).thenReturn(productoMock);

        Optional<Producto> productoActualizado = productoService.actualizarProducto(1L, productoMock);

        assertTrue(productoActualizado.isPresent());
        assertEquals("Producto Actualizado", productoActualizado.get().getNombre());
        verify(productoRepository, times(1)).save(productoMock);
    }
}
