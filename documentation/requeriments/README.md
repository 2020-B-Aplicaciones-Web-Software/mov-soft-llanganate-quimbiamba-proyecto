<div style="text-align: center;">
<h2>Historias de Usuario - SCRIMA (Network Scanner)</h2>
</div>

**Integrantes:** Alejandro Llanganate y Edison Quimbiamba

A continuación se detallan las épicas e historias usuario de la aplicación android por desarrollar.

### Épica 1
Como usuario de la aplicación SCRIMA requiero registrarme e iniciar sesión.

| Historia de usuario - H1 |
| ------------- |
| **Título:** Inicio de sesión   |
| **Descripción:** Como usuario deseo realizar un escaneo de las redes cercanas, utilizando el dispositivo wifi del móvil  de manera que pueda visualizar las redes cercanas en donde se mostrará las características  de la red Gateway  y estas son : fecha, hora del scaneo y el fabricante. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** Cuando el usuario ha proporcionado lo campos correspondientes en formulario de login y pulse el botón de ingresar, los datos serán válidos y posteriormente el usuario será redirigido al historial de escaneos. |

| Historia de usuario - H2 |
| ------------- |
| **Título:** Registro de usuario  |
| **Descripción:** Como usuario de la aplicación quiero registrarme con mi nombre, apellido, correo electrónico y una contraseña para tener una experiencia más personalizada y tener acceso a la información. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** Una vez ingresado la contraseña se da click en registrar  se  debe validar correo y contraseña posteriormente se redirige al usuario a la vista de login. |

### Épica 2
Como usuario de la aplicación SCRIMA requiero realizar escaneo de los hosts dentro de mi red local.

| Historia de usuario - H3 |
| ------------- |
| **Título:** Escaneo de dispositivos  |
| **Descripción:** Como usuario deseo realizar un escaneo de la red local host utilizan el wifi del móvil para que pueda visualizar los dispositivos conectados a la red mayormente conocidos como hosts. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** Una vez que el usuario ingrese a la opción de escaneo de dispositivos, verificar que el usuario esté conectado a internet y a una red local. |

| Historia de usuario - H4 |
| ------------- |
| **Título:** Visualización de dispositivos encontrados |
| **Descripción:** Como usuario deseo visualizar luego de un escaneo la información de los hosts encontrados, es decir, visualizar el tipo de dispositivo, la dirección IP, la dirección MAC, el fabricante y el nombre del dispositivo, para obtener una información general del mismo. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** Desplegar una lista ordenada de dispositivos encontrados. Cada dispositivo debe tener los atributos mencionados, y en caso de no tenerlos se mostrará un campo vacío. |

| Historia de usuario - H5 |
| ------------- |
| **Título:** Visualizar información detallada de cada host |
| **Descripción:** Como usuario deseo acceder a una vista detallada de las características del host para visualizar información adicional como el DNS, las versiones de la IP, el response de cada dispositivo, y el gateway del host. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** El usuario selecciona un dispositivo de la lista ordenada de hosts y selecciona la opción "información detallada" y es redirigido a una vista con la información ordenada del host.  |

| Historia de usuario - H6 |
| ------------- |
| **Título:** Eliminar un host de la lista ordenada |
| **Descripción:** Como usuario deseo poder eliminar a un host de la lista de dispositivos encontrados luego de un escaneo para así personalizar mi lista con los dispositivos de mi elección. |
| **Prioridad:** Alta |
| **Criterios de aceptación:** Al eliminar un host dando click en la opción "Eliminar" se muestra un mensaje de confirmación para eliminarlo. De pulsar sí al mensaje se actualiza la lista ordenada de dispositivos. Al presionar cancelar no se mostrarán cambios en la lista original. |

### Épica 3
Como usuario de la aplicación SCRIMA requiero tener un historial de los escaneos realizados.

| Historia de usuario - H7 |
| ------------- |
| **Título:** Registrar escaneo |
| **Descripción:** Como usuario deseo poder registrar un escaneo luego de realizar uno, para guardar la lista detallada de los hosts encontradas.|
| **Prioridad:** Media |
| **Criterios de aceptación:** Al pulsar "Guardar" se deberá almacenar la lista de dispositivos actual que visualiza el usuario. |

| Historia de usuario - H8 |
| ------------- |
| **Título:** Visualización de escaneos |
| **Descripción:** Como usuario deseo poder visualizar los escaneos registrados en donde se pueda identificar el gateway, la fecha y hora del escaneo y fabricante, para así obtener una información ordenada de los escaneos realizados previamente.|
| **Prioridad:** Media |
| **Criterios de aceptación:** Se deberá mostrar los escaneos en una lista ordenada de manera ascendente respecto a la fecha de escaneo. |


| Historia de usuario - H9 |
| ------------- |
| **Título:** Perfil de usuario |
| **Descripción:** Como usuario deseo poder visualizar la información de la cuenta para así acceder a la información proporcionada previamente en mi registro de usuario.|
| **Prioridad:** Media |
| **Criterios de aceptación:** Se deberá mostrar en el perfil de usuario el nombre completo del usuario, su correo electrónico y la cantidad de escaneos registrados. |

| Historia de usuario - H10 |
| ------------- |
| **Título:** Actualizar información personal |
| **Descripción:** Como usuario deseo poder actualizar mi información personal para registrar nuevos datos sobre mi.|
| **Prioridad:** Alta |
| **Criterios de aceptación:** Se deberán validar los campos ingresados. En caso de actualizar la contraseña está no deberá ser la misma que la contraseña previa. |
