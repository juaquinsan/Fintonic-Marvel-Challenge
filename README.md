# Fintonic Marvel Challenge - [José Joaquín Sánchez Sánchez] Agosto 2022

En este challenge se ha desarrollado el proyecto siguiendo una arquitectura MVVM (Módel-View-ViewModel)
con Clean Architecture.

En cuanto a librerías se han utilizado las siguientes:

- [Retrofit], para las llamadas a la API de Marvel
- [Kotlin Coroutine], para la gestión de las llamadas.
- [Dagger Hilt], para la inyección de dependencias.
- [Picasso], para la carga de imágenes.
- [Mock], para la realización de unit tests.

La aplicación se basa en tres activities.

- [SplashActivity]: se ha creado un activity para mostrar el logo de Marvel.
- [MainActivity]: muestra toda la informacion recibida del API organizado cada personaje en un CardView,
    si el API devuelve un error o información incorrecta se informará al usuario.
- [DetailActivity]: muestra la información detallada de un personaje previamente seleccionado en el 
    activity anterior (MainActivity).

Los test realizandos son unitarios y se ha probado lo siguiente:
- [Domain]
- [View Model]