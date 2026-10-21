# Evidencia - TuStudio

Textos listos para copiar al documento. Cada imagen de codigo y de pruebas lleva su descripcion.

Datos del usuario de prueba que se uso en las capturas:

- Usuario: andres01
- Contrasena: Estudio123
- Nivel academico: Universidad
- Correo: prueba@tustudio.com
- Apodo: Andy
- Foto: 2 (verde)

---

## 1. Diseno de la interfaz

### Sketches

(Los sketches son dibujos a mano en papel. Se dibujan las mismas pantallas: Inicio, Iniciar sesion, Crear cuenta, Apodo, Foto de perfil y Perfil.)

Descripcion: Los sketches son la primera idea de la aplicacion TuStudio, dibujados a mano. Muestran de forma rapida la posicion de los elementos principales de cada pantalla: el titulo, los campos de texto, los botones y la mascota. No tienen colores ni detalles, solo sirven para pensar el orden de las pantallas y como se mueve el usuario entre ellas.

### Wireframes

Descripcion general: Los wireframes muestran la estructura de cada pantalla en blanco y negro. Se usan cajas, lineas y textos para indicar donde va cada elemento, sin colores finales. Los cuadros con una X indican el lugar de una imagen.

- Inicio: titulo "TuStudio", boton "Comienza a Estudiar YA!", botones de materias (Matematicas, Fisica, Ciencias, Ingles, Quimica), menu y la mascota.
- Crear cuenta: opcion "volver", enlace "iniciar sesion", campos de nombre de usuario, contrasena, nivel academico y correo electronico, y el boton "crear".
- Apodo: campo para escribir el apodo y boton "Siguiente".
- Foto de perfil: circulo para la foto, boton "seleccionar foto" y una cuadricula para elegir la imagen.
- Iniciar sesion: campos de nombre de usuario y contrasena, boton "iniciar" y el texto "Crear cuenta o olvide contrasena".
- Perfil: apodo del usuario arriba, una imagen de portada, la foto de perfil, el nombre del usuario y el boton "Empezar a estudiar".

### Mockups

(Se pueden usar las capturas 01_inicio, 02_login_vacio, 03_registro_vacio y 09_perfil de la carpeta capturas.)

Descripcion: Los mockups muestran el diseno final de la aplicacion con colores, imagenes y tipografia. El color principal es el morado, que da identidad a la app, y el amarillo se usa en los botones mas importantes para llamar la atencion. Cada materia tiene su propio color (azul, rojo, verde, naranja y rosa). El fondo de todas las pantallas es una imagen de Ladybug sobre un fondo rosa con puntos, con una capa oscura ligera encima para que los titulos en blanco se lean bien; los formularios van en tarjetas blancas. La mascota de la app es Ladybug, una heroina con traje rojo de puntos negros; aparece en la pantalla de inicio, en el inicio de sesion y en el icono de la app, y su color rojo combina con el resto del diseno. En el perfil se muestran el apodo, la foto que eligio el usuario, su nombre de usuario y sus datos. Las pantallas de apodo y foto de perfil se unieron en la pantalla "Crea tu cuenta" para que el registro sea mas rapido.

---

## 2. Base de datos

Imagen: archivo `BaseDatos.kt`, lineas 1 a 29 (clase y creacion de la tabla).

Descripcion: Esta es la clase `BaseDatos`, que hereda de `SQLiteOpenHelper` y crea la base de datos local `tustudio.db`. En el metodo `onCreate` se crea la tabla `usuarios`, que guarda la informacion de cada persona registrada. El metodo `onUpgrade` borra la tabla y la vuelve a crear si cambia la version de la base de datos.

Campos de la tabla `usuarios`:

| Campo      | Tipo    | Para que sirve                                  |
|------------|---------|-------------------------------------------------|
| id         | INTEGER | Numero unico de cada usuario (se crea solo)     |
| usuario    | TEXT    | Nombre de usuario, no se puede repetir (UNIQUE) |
| contrasena | TEXT    | Contrasena para entrar a la app                 |
| nivel      | TEXT    | Nivel academico (secundaria, prepa, etc.)       |
| correo     | TEXT    | Correo electronico                              |
| apodo      | TEXT    | Nombre corto que se muestra en el perfil        |
| foto       | INTEGER | Numero de la foto de perfil elegida (1, 2 o 3)  |

---

## 3. Registro de usuario

Imagen 1: archivo `BaseDatos.kt`, lineas 30 a 54 (funciones `registrar` y `existeUsuario`).

Descripcion: La funcion `registrar` recibe los datos del usuario y los guarda en la tabla `usuarios`. Se usa un objeto `ContentValues` para poner cada dato en su campo y despues `insert` para guardarlo. Si `insert` regresa -1 hubo un error; si no, el registro fue correcto y la funcion regresa `true`. La funcion `existeUsuario` busca con una consulta `SELECT` si el nombre de usuario ya esta guardado, para no repetirlo.

Imagen 2: archivo `RegistroActivity.kt`, lineas 43 a 56 (eleccion de la foto de perfil).

Descripcion: Cuando el usuario toca una de las tres fotos, se guarda su numero en la variable `foto` y se muestra en grande con `setImageResource`. Ese numero es el que se guarda despues en la base de datos.

Imagen 3: archivo `RegistroActivity.kt`, lineas 58 a 80 (boton Crear).

Descripcion: Al presionar el boton "Crear" se leen los textos de los campos. Primero se revisa que ningun campo este vacio; si falta alguno se muestra el mensaje "Llena todos los campos". Despues se revisa que el usuario no exista. Si todo esta bien, se llama a `db.registrar(...)`, se muestra el mensaje "Cuenta creada" y se regresa a la pantalla de inicio de sesion.

---

## 4. Inicio de sesion

Imagen 1: archivo `BaseDatos.kt`, lineas 56 a 66 (funcion `iniciarSesion`).

Descripcion: La funcion `iniciarSesion` hace una consulta `SELECT` a la tabla `usuarios` buscando un registro donde el usuario y la contrasena sean iguales a los que se escribieron. Si la consulta encuentra al menos un registro (`cursor.count > 0`), los datos son correctos y regresa `true`. Al final se cierra el cursor.

Imagen 2: archivo `LoginActivity.kt`, lineas 35 a 51 (boton Iniciar).

Descripcion: Al presionar "Iniciar" se leen el usuario y la contrasena. Si estan vacios se pide que se escriban. Si `db.iniciarSesion(...)` regresa `true`, se muestra "Bienvenido" y se abre la pantalla de perfil, mandando el nombre de usuario con `putExtra`. Si regresa `false`, se muestra el mensaje "Usuario o contrasena incorrectos".

Imagen 3 (opcional): archivo `PerfilActivity.kt`, lineas 24 a 46.

Descripcion: En el perfil se recibe el nombre de usuario que mando el login y se buscan sus datos con `db.buscarUsuario(...)`. Con el cursor se muestran el apodo, el nivel academico, el correo y la foto que eligio al registrarse.

---

## 5. Pruebas

Prueba de registro:

- `03_registro_vacio.png`: Pantalla "Crea tu cuenta" al abrirla. Tiene los campos de usuario, contrasena, nivel academico, correo, apodo y las tres fotos de perfil para elegir.
- `04_registro_campos_vacios.png`: Se presiono "Crear" sin llenar los campos. La app no guarda nada y muestra el mensaje "Llena todos los campos".
- `05_registro_lleno.png`: Se llenaron todos los campos con los datos del usuario de prueba "andres01" y se eligio la foto verde.
- `06_registro_exitoso.png`: Despues de presionar "Crear", el usuario se guardo en la base de datos SQLite. Aparece el mensaje "Cuenta creada" y la app regresa a la pantalla de inicio de sesion.

Prueba de inicio de sesion:

- `02_login_vacio.png`: Pantalla de inicio de sesion con los campos de usuario y contrasena.
- `07_login_incorrecto.png`: Se escribio el usuario "andres01" con una contrasena equivocada. La consulta no encuentra el registro y se muestra "Usuario o contrasena incorrectos".
- `08_login_lleno.png`: Se escribio el usuario "andres01" con su contrasena correcta.
- `09_perfil.png`: El inicio de sesion fue correcto. Se muestra "Bienvenido" y se abre el perfil con los datos guardados en la base de datos: apodo "Andy", foto verde, nivel "Universidad" y correo.

Otra captura:

- `01_inicio.png`: Pantalla de inicio de TuStudio con la mascota, las materias y el boton "Comienza a estudiar YA!".

(Extra opcional: en Android Studio, con la app abierta, ir a View > Tool Windows > App Inspection > Database Inspector, abrir `tustudio.db` y la tabla `usuarios` para tomar una captura del usuario guardado.)
