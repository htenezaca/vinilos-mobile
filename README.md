# vinilos-mobile
Repositorio de carácter formativo, donde se presenta la interfaz de usuario de una aplicación nativa en Android para la administración de vinilos, usando Kotlin.

## Aplicación 
- Nombre de la aplicación: **_Vinilos_**
- Versión: **v.1.0.0**

### Descripción

El auge de las plataformas digitales de música como Spotify, va en incremento un grupo de personas que disfrutan de escuchar música que se encuentran en formatos tradicionales como los discos en vinilo. Esta es la razón de ser de Vinilos-mobile, una aplicación móvil que proporciona al aficionado la posibilidad de compartir sus álbumes, artistas y canciones favoritas. Se puede hacer uso de la app a través de dos tipos de perfiles de usuarios: visitantes y coleccionistas.

En la información del coleccionista se incluye su nombre, información de contacto y sus artistas favoritos. Por otro lado los visitantes pueden revisar toda la información anterior, la facultad de agregar álbumes es inherente a las acciones de los coleccionistas.

### Funcionalidades Core

A continuación se presenta el listado inicial con las funcionalidades presentadas por el sistema, este listado pretende plantear una base inicial de los escenarios que serán probados y analizados:

| ID |    Nombre   | Descripción | 
|:--------------:|:--------------------:|:--------------------:|
| HU01         | Consultar catálogo de álbumes | Muestra el catálogo de álbumes para ver aquellos que están disponibles | 
| HU02         | Consultar información detallada de un álbum   |  Muestra la información detallada de un álbum, como lo es su nombre, cover, fecha de publicación, descripción, género y record label |
| HU03         | Consultar el listado de artistas | Muestra el listado actual de artistas disponibles en la aplicación |

## Requerimientos

* Instalación de APK
  * Dispositivo Android físico habilitado para instalación de APK de terceros
* Ejecución desde código fuente
  * JDK 11 actualizado e instalado y variables de entorno de Java configuradas
  * Android Studio en su versión más reciente, instalado y configurado con el SDK
  * Un dispositivo Android virtual o físico con la depuración por Android Debug Bridge (ADB) habilitada
  * Acceso al repositorio https://github.com/htenezaca/vinilos-mobile/ 

## Pasos de ejecución de la aplicación

1. Configurar e iniciar un device en el emulador de Android Studio con las características deseadas 
``` La versión mínima soportada es Lollipop ```
2. Descargar o clonar proyecto en git
```bash
git clone https://github.com/htenezaca/vinilos-mobile.git
```
3. Abrir proyecto en Android Studio
4. Ejecutar aplicación en el emulador configurado
5. Experimentar las funcionalidades de la aplicación

## Pasos de ejecución de pruebas con Espresso

1. Ejecutar los pasos 1 al 3 de los pasos previos de ejecución
2. Clic derecho en jerarquía de objetos Android en el ítem _tests_
3. Run tests
