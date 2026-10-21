# TuStudio

**TuStudio** es una app Android para estudiantes, hecha en Kotlin, que permite crear una cuenta, iniciar sesión y ver un perfil personalizado. Los usuarios se guardan en una base de datos local SQLite dentro del teléfono.

Proyecto EP2 de Andrés Popoca, José Arenal y Karla Poluceno.

<p align="center">
  <img src="Evidencia/capturas/01_inicio.png" width="200" alt="Pantalla de inicio">
  <img src="Evidencia/capturas/05_registro_lleno.png" width="200" alt="Crear cuenta">
  <img src="Evidencia/capturas/08_login_lleno.png" width="200" alt="Iniciar sesión">
  <img src="Evidencia/capturas/09_perfil.png" width="200" alt="Perfil">
</p>

## Pantallas

| Pantalla | Qué hace |
|---|---|
| **Inicio** (`MainActivity`) | Muestra el nombre de la app, la mascota, las materias (Matemáticas, Física, Ciencias, Inglés y Química) y el botón **"Comienza a estudiar YA!"**, que abre el inicio de sesión. |
| **Iniciar sesión** (`LoginActivity`) | Pide usuario y contraseña y los compara con la base de datos. Si son correctos muestra "Bienvenido" y abre el perfil; si no, avisa "Usuario o contraseña incorrectos". Tiene un enlace para crear una cuenta. |
| **Crea tu cuenta** (`RegistroActivity`) | Pide usuario, contraseña, nivel académico, correo y apodo, y permite elegir una de tres fotos de perfil. Revisa que no haya campos vacíos y que el usuario no exista antes de guardarlo. |
| **Perfil** (`PerfilActivity`) | Muestra el apodo, la foto elegida, el nombre de usuario, el nivel académico y el correo. Tiene el botón **"Empezar a estudiar"** y la opción para cerrar sesión. |

## Base de datos

La clase `BaseDatos` (`SQLiteOpenHelper`) crea la base `tustudio.db` con la tabla `usuarios`:

| Campo | Tipo | Descripción |
|---|---|---|
| `id` | INTEGER | Identificador, se genera solo |
| `usuario` | TEXT | Nombre de usuario, único |
| `contrasena` | TEXT | Contraseña |
| `nivel` | TEXT | Nivel académico |
| `correo` | TEXT | Correo electrónico |
| `apodo` | TEXT | Nombre que se muestra en el perfil |
| `foto` | INTEGER | Foto de perfil elegida (1, 2 o 3) |

Funciones principales: `registrar`, `existeUsuario`, `iniciarSesion` y `buscarUsuario`.

## Tecnologías

- Kotlin y vistas XML (AppCompat, Material Components, ConstraintLayout)
- SQLite con `SQLiteOpenHelper`
- Android Gradle Plugin 9.4.1, Java 11
- `minSdk` 24 (Android 7.0), `targetSdk` 37

## Cómo ejecutarla

1. Clona el repositorio:
   ```bash
   git clone https://github.com/andres25e0/EP2DAM.git
   ```
2. Ábrelo en Android Studio (**File > Open** y elige la carpeta `EP2DAM`).
3. Espera a que termine la sincronización de Gradle.
4. Ejecuta la app en un emulador o en un teléfono con Android 7.0 o superior.

Para revisar los usuarios guardados: con la app abierta, ve a **View > Tool Windows > App Inspection > Database Inspector** y abre `tustudio.db`.

## Estructura del proyecto

```
app/src/main/
├── java/com/example/ep2_popocaandres_arenaljose_polucenokarla/
│   ├── MainActivity.kt       # Pantalla de inicio
│   ├── LoginActivity.kt      # Iniciar sesión
│   ├── RegistroActivity.kt   # Crear cuenta
│   ├── PerfilActivity.kt     # Perfil del usuario
│   └── BaseDatos.kt          # Base de datos SQLite
└── res/                      # Layouts, colores, avatares e imágenes
Evidencia/
├── descripciones.md          # Descripción del diseño, la base de datos y las pruebas
└── capturas/                 # Capturas de las pruebas de registro e inicio de sesión
```

## Pruebas

En [`Evidencia/capturas`](Evidencia/capturas) están las pruebas de la app: registro con campos vacíos, registro exitoso, inicio de sesión con contraseña incorrecta e inicio de sesión correcto que lleva al perfil. La explicación de cada captura está en [`Evidencia/descripciones.md`](Evidencia/descripciones.md).

## Nota

Es un proyecto escolar: las contraseñas se guardan tal cual en la base de datos local, sin cifrar.
