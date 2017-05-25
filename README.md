Proyecto Ingeniería de software II FP-UNA 2017
  Aplicación de libreta de Vacunación pediátrica

Integrantes:
-José Álvarez
-Cristian Cáceres
-Belén Desvars
-Rafael Estigarribia 


en el directorio del github 

- carpeta SysVac: se encuentra el proyecto de aplicación Android con base de datos sqLite y notificaciones Calendar
- carpeta SysVac2: se encuentra el proyecto de aplicación Android con base de datos sqLite y notificaciones 
                push internas de la propia app
- carpeta Version_Final : se encuentra el proyecto de aplicación Android con notificaciones push internas de la propia app en 
                        donde los datos de datos son proporcionados por web services RESTful consumiendo Json.
- carpeta Back_End: se encuentra el proyecto donde se implementa el web services RESTful en netbeans 8.2
- archivo SysVac.backup : se encuentra el punto de restauración de la base de datos Postgres de los datos en cuestión

A modo de prueba el inicio de sesión de google debe configurarse al descargar el archivo de configuración 
json para poder compilar desde su maquina

https://developers.google.com/mobile/add?platform=android&cntapi=signin&cntapp=Default%20Demo%20App&cntpkg=com.google.samples.quickstart.signin&cnturl=https:%2F%2Fdevelopers.google.com%2Fidentity%2Fsign-in%2Fandroid%2Fstart%3Fconfigured%3Dtrue&cntlbl=Continue%20with%20Try%20Sign-In

poner en el App name: SysVac
Android pack name: example.com.sysvac
y agregar el SHA1 de su computador

después de descargar, copiar el archivo google-services.json en el directorio de su  proyecto Android Studio  dentro de app/ o mobile/


Detalles de compilación

- Versión de Android objetivo (ideal) para correr la app: Android 5.1 
- Versión mínima de Android requerida para correr la app: Android 4.0 
- compileSdkVersion 25
- minSdkVersion 14
- targetSdkVersion 25
- versionCode 1
- versionName "1.0"
