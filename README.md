# MyCarsRent
____________Database____________
Database model: db-model.pgn (root folder);
Database creation script: db-create.sql (src/main/resources);
Test database creation: db-test.sql (src/main/resources , creating by script runner during Tests);

____________Users____________
Admin: Login - Dima, Password - Qwerty;

Manager: Login - Vasya, Password - Qwerty;

Client: Login - Olya, Password - Qwerty;

____________Tomcat____________
<<<<<<< HEAD
set Application context as: /cars;
=======
set Application context as: /cars

____________Task____________

Прокат авто

В системі існує перелік Автомобілів, для якого необхідно реалізувати:
- вибір по марці;
- вибір по класу якості;
- сортування за вартістю прокату;
- сортування за назвою.
Клієнт реєструється в системі, обирає автомобіль та робить замовлення на оренду. Незареєстрований клієнт не може зробити замовлення. В даних замовлення клієнт вказує паспортні дані, опцію 'з водієм'/'без водія', термін оренди. Система формує Рахунок, який клієнт оплачує.
Менеджер розглядає замовлення і може відхилити його, вказавши причину. Також менеджер реєструє повернення автомобіля, у випадку пошкодження автомобіля виставляє через систему рахунок на ремонт.
Адміністратор системи має права:
- додавання, видалення автомобілів, редагування інформації про автомобілі;
- блокування/розблокування користувачів;
- реєстрація менеджерів в системі.
>>>>>>> f5c73d46934058b759100a9b917a4bb04ef9f7a8
