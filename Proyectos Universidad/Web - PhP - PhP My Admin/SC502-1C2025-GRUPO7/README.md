# SC502-1C2025-GRUPO7

# Guía para iniciar el proyecto en XAMPP

Sigue los pasos a continuación para poner en marcha el proyecto localmente usando XAMPP.

## 1. Clonar o descargar el repositorio

- Dirigete al repositorio del proyecto en GitHub.
- Haz clic en el botón verde **Code**.
- Selecciona **Download ZIP** y descomprime el archivo en tu computadora.

## 2. Instalar y configurar XAMPP

- Asegúrate de tener **XAMPP** instalado en tu computadora.
- Si no lo tienes, puedes descargarlo desde:  
  [https://www.apachefriends.org/es/index.html](https://www.apachefriends.org/es/index.html)


## 3. Mover el proyecto a la carpeta `htdocs`

- Copia la carpeta del repositorio descargado.
- Pega la carpeta en la siguiente ruta:

C:\xampp\htdocs\
> Ejemplo: el proyecto se llama `SC502-1C2025-GRUPO7`, la ruta será:  
> `C:\xampp\htdocs\SC502-1C2025-GRUPO7`


## 4. Abrir XAMPP y acceder a phpMyAdmin

1. Abre **XAMPP Control Panel**.
2. Haz clic en **Start** en los servicios de **Apache** y **MySQL**.
3. Haz clic en el botón **Admin** que está a la derecha de **MySQL** para abrir **phpMyAdmin** en el navegador.


## 5. Importar la base de datos

1. En **phpMyAdmin**, haz clic en **SQL** para abrirla.
2. Abre el archivo del proyecto que contiene el código SQL (normalmente termina en `.sql`).
3. Copia todo el contenido de ese archivo.
4. Pega el contenido en el cuadro SQL de phpMyAdmin y haz clic en **Continuar**.


## 6. Configurar la conexión a la base de datos

Busca el archivo donde se configura la conexión (por ejemplo, `conexion.php` o similar) y asegúrate de que los valores estén correctamente definidos.

El valor **`dbname`** debe ser exactamente:

dbname = 'sc502_1c2025_grupo7';
host = 'localhost';
user = 'root';
pass = '';


## 7. Abrir el proyecto en el navegador
1. En el XAMPP Control Panel, haz clic en el botón Admin al lado de Apache.
2. Se abrirá el navegador en http://localhost.
3. Agrega el nombre de la carpeta del proyecto en la barra de direcciones. Por ejemplo:
`http://localhost/SC502-1C2025-GRUPO7`
Asegúrate de que el nombre de la carpeta coincida con el que pegaste en htdocs.
4. Dentro de esa carpeta, haz clic en la carpeta **`proyecto`** y luego en **`app`** para acceder al sistema.
> La ruta final puede verse como:  
> `http://localhost/SC502-1C2025-GRUPO7/proyecto/app`




