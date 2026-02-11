# Aplikacja SOS - System Obsługi Studiów

## Opis projektu

Aplikacja SOS to system wspomagający zarządzanie procesem dydaktycznym na uczelni. Projekt wykorzystuje tradycyjny framework Spring (nie Spring Boot) oraz technologie Java 8.

## Struktura projektu

Projekt składa się z następujących głównych pakietów:
- `config` - konfiguracja aplikacji
- `controller` - kontrolery obsługujące żądania HTTP
- `dao` - interfejsy warstwy dostępu do danych
- `dao.impl` - implementacje interfejsów DAO
- `model` - klasy encji reprezentujące obiekty biznesowe
- `service` - usługi biznesowe
- `util` - pomocnicze klasy narzędziowe

## Obecne encje modelu

System obsługuje następujące encje:
- Student
- Course (Kurs)
- Enrollment (Zapis na kurs)
- Grade (Ocena)
- Notification (Powiadomienie)
- Payment (Płatność)
- Loan (Pożyczka)
- Role (Rola użytkownika)
- Schedule (Harmonogram)
- ClassSession (Sesja klasowa)

## Obecne warstwy DAO

### Już istniejące warstwy DAO:
- StudentDao i StudentDaoImpl
- PaymentDao i PaymentDaoImpl
- LoanDao i LoanDaoImpl

### Nowo dodane warstwy DAO (w ramach tej pracy):

#### 1. CourseDao i CourseDaoImpl
Interfejs i implementacja dla encji Course (Kurs) z metodami:
- createCourse - tworzenie nowego kursu
- getCourse - pobranie kursu po ID
- getAllCourses - pobranie wszystkich kursów
- updateCourse - aktualizacja kursu
- deleteCourse - usunięcie kursu
- getEnrollments - pobranie zapisów na dany kurs
- getGrades - pobranie ocen z danego kursu
- getClassSessions - pobranie sesji klasowych dla danego kursu

#### 2. EnrollmentDao i EnrollmentDaoImpl
Interfejs i implementacja dla encji Enrollment (Zapis na kurs) z metodami:
- createEnrollment - tworzenie nowego zapisu
- getEnrollment - pobranie zapisu po ID
- getAllEnrollments - pobranie wszystkich zapisów
- updateEnrollment - aktualizacja zapisu
- deleteEnrollment - usunięcie zapisu
- getEnrollmentsByStudent - pobranie zapisów danego studenta
- getEnrollmentsByCourse - pobranie zapisów na dany kurs
- getEnrollmentByStudentAndCourse - pobranie zapisu studenta na konkretny kurs

#### 3. GradeDao i GradeDaoImpl
Interfejs i implementacja dla encji Grade (Ocena) z metodami:
- createGrade - tworzenie nowej oceny
- getGrade - pobranie oceny po ID
- getAllGrades - pobranie wszystkich ocen
- updateGrade - aktualizacja oceny
- deleteGrade - usunięcie oceny
- getGradesByStudent - pobranie ocen danego studenta
- getGradesByCourse - pobranie ocen z danego kursu
- getGradesByStudentAndCourse - pobranie ocen studenta z danego kursu
- getGradesByType - pobranie ocen danego typu

#### 4. ClassSessionDao i ClassSessionDaoImpl
Interfejs i implementacja dla encji ClassSession (Sesja klasowa) z metodami:
- createClassSession - tworzenie nowej sesji klasowej
- getClassSession - pobranie sesji klasowej po ID
- getAllClassSessions - pobranie wszystkich sesji klasowych
- updateClassSession - aktualizacja sesji klasowej
- deleteClassSession - usunięcie sesji klasowej
- getClassSessionsBySchedule - pobranie sesji klasowych dla danego harmonogramu
- getClassSessionsByCourse - pobranie sesji klasowych dla danego kursu
- getClassSessionsByDay - pobranie sesji klasowych w danym dniu
- getClassSessionsByRoom - pobranie sesji klasowych w danym pokoju

#### 5. NotificationDao i NotificationDaoImpl
Interfejs i implementacja dla encji Notification (Powiadomienie) z metodami:
- createNotification - tworzenie nowego powiadomienia
- getNotification - pobranie powiadomienia po ID
- getAllNotifications - pobranie wszystkich powiadomień
- updateNotification - aktualizacja powiadomienia
- deleteNotification - usunięcie powiadomienia
- getNotificationsByStudent - pobranie powiadomień danego studenta
- getNotificationsByType - pobranie powiadomień danego typu
- getNotificationsByStatus - pobranie powiadomień o danym statusie
- getNotificationsByDateRange - pobranie powiadomień z danego zakresu dat

#### 6. RoleDao i RoleDaoImpl
Interfejs i implementacja dla encji Role (Rola użytkownika) z metodami:
- createRole - tworzenie nowej roli
- getRole - pobranie roli po ID
- getAllRoles - pobranie wszystkich ról
- updateRole - aktualizacja roli
- deleteRole - usunięcie roli
- getRoleByName - pobranie roli po nazwie

#### 7. ScheduleDao i ScheduleDaoImpl
Interfejs i implementacja dla encji Schedule (Harmonogram) z metodami:
- createSchedule - tworzenie nowego harmonogramu
- getSchedule - pobranie harmonogramu po ID
- getAllSchedules - pobranie wszystkich harmonogramów
- updateSchedule - aktualizacja harmonogramu
- deleteSchedule - usunięcie harmonogramu
- getScheduleByStudent - pobranie harmonogramu danego studenta
- getClassSessionsForSchedule - pobranie sesji klasowych dla danego harmonogramu

## Technologie

- Java 8
- Spring Framework 4.3.8.RELEASE
- Hibernate 5.2.10.Final
- JPA 2.1
- HSQLDB (bazodanowy silnik testowy)
- Apache Tomcat 7 (kontener servletów)

## Budowanie i uruchamianie

Aby zbudować projekt:
```
mvn clean package
```

Aby uruchomić aplikację:
```
mvn tomcat7:run
```

Aplikacja będzie dostępna pod adresem http://localhost:8080/sos

## Podsumowanie

W ramach tej pracy dodano kompleksowe wsparcie dla warstwy DAO dla wszystkich encji modelu, które wcześniej nie miały takiego wsparcia. Dzięki temu aplikacja ma teraz pełną warstwę dostępu do danych dla wszystkich encji, co umożliwia efektywną obsługę operacji CRUD oraz bardziej zaawansowane operacje biznesowe.

Praca dokończona przez: Marcin Łagódka