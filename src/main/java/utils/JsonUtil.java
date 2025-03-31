package com.productosypedidos.gestion_productos_pedidos.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

@Component
public class JsonUtil {
    private final ObjectMapper objectMapper;

    public JsonUtil() {
        this.objectMapper = new ObjectMapper();
    }

    // ✅ Método genérico para leer desde JSON
    public <T> List<T> leerDesdeJson(String filePath, Class<T> clazz) {
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                return new ArrayList<>();
            }
            return objectMapper.readValue(file, objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    // ✅ Método genérico para escribir a JSON
    public <T> void escribirAJson(String filePath, List<T> datos) {
        try {
            objectMapper.writeValue(new File(filePath), datos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
