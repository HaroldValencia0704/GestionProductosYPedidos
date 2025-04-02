# Gestion de Productos y Pedidos

Este es un proyecto de API REST desarrollado con **Spring Boot**, que permite la gestión de productos y pedidos. Se incluyen pruebas unitarias con **Mockito**, pruebas de integración con **MockMvc**, y un pipeline de **CI/CD** con GitHub Actions.

## 📌 Características
- CRUD de productos y pedidos
- Persistencia de datos en archivos JSON
- Seguridad con Spring Security
- Pruebas unitarias y de integración
- Pipeline de integración continua (CI) con GitHub Actions

## 🚀 Requisitos
- **Java 17**
- **Maven**
- **Git**

## ⚙️ Instalación y Ejecución
1. Clona el repositorio:
   ```bash
   git clone https://github.com/HaroldValencia0704/GestionProductosYPedidos.git
   cd GestionProductosYPedidos
   ```
2. Compila el proyecto:
   ```bash
   mvn clean install
   ```
3. Ejecuta la aplicación:
   ```bash
   mvn spring-boot:run
   ```

## 🛠️ Pruebas
Ejecuta las pruebas unitarias y de integración con:
```bash
mvn test
```

## 📄 API Endpoints
| Método | Endpoint          | Descripción                   |
|--------|------------------|-------------------------------|
| GET    | `/productos`     | Obtener todos los productos  |
| POST   | `/productos`     | Agregar un nuevo producto    |
| GET    | `/productos/{id}` | Obtener un producto por ID   |
| PUT    | `/productos/{id}` | Actualizar un producto       |
| DELETE | `/productos/{id}` | Eliminar un producto         |

## 🔄 CI/CD con GitHub Actions
El proyecto incluye un workflow en `.github/workflows/test.yml` que:
1. Configura el entorno con Java 17
2. Instala dependencias y ejecuta las pruebas
3. Genera artefactos y los sube en caso de éxito

## 📌 Autor
👤 **Harold Valencia**

