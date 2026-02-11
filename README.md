# SOS - System Obsługi Studenta

SOS (System Obsługi Studenta) to nowoczesna aplikacja webowa napisana w języku Java z wykorzystaniem Spring Boot, która zarządza informacjami o studentach, zapisami na zajęcia, ocenami, pożyczkami książek i płatnościami.

## Technologie użyte

- **Java 17+** - Język programowania
- **Spring Boot 3.2.2** - Framework aplikacji
- **Spring MVC** - Framework webowy
- **Spring Data JPA** - Persystencja danych
- **Hibernate** - ORM (Mapowanie obiektowo-relacyjne)
- **Jakarta Persistence** - API persystencji (kompatybilność ze Spring Boot 3)
- **HSQLDB** - Baza danych w pamięci RAM do celów deweloperskich
- **Maven** - Narzędzie do zarządzania zależnościami i budowania
- **Bootstrap 5** - Framework CSS dla interfejsu użytkownika
- **HTML/CSS/JavaScript** - Technologie frontendowe
- **BCrypt** - Haszowanie haseł

## Funkcjonalności

- **Uwierzytelnianie użytkowników**: System logowania i rejestracji z bezpiecznym haszowaniem haseł
- **Panel studenta**: Przeglądanie informacji osobistych, zapisów, ocen, pożyczeń i płatności
- **Interfejs API REST**: Kompleksowe endpointy API dla wszystkich operacji systemowych
- **Responsywny interfejs**: Interfejs przyjazny urządzeniom mobilnym z wykorzystaniem Bootstrapa

## Struktura projektu

```
src/
├── main/
│   ├── java/com/laa66/
│   │   ├── controller/     # Kontrolery REST i webowe
│   │   ├── dao/           # Obiekty dostępu do danych
│   │   ├── dao/impl/      # Implementacje DAO
│   │   ├── model/         # Modele encji
│   │   ├── service/       # Usługi logiki biznesowej
│   │   └── util/          # Klasy pomocnicze
│   ├── resources/
│   │   ├── static/       # Zasoby statyczne (HTML, CSS, JS)
│   │   ├── sql/          # Skrypty inicjalizacji bazy danych
│   │   └── application.properties  # Konfiguracja
│   └── webapp/           # Zasoby webowe (jeśli istnieją)
```

## Budowanie aplikacji

### Wymagania wstępne
- Java 17 lub nowsza
- Maven 3.6.0 lub nowszy

### Krok po kroku
1. Sklonuj repozytorium:
   ```bash
   git clone <adres-repozytorium>
   cd sos
   ```

2. Zbuduj aplikację:
   ```bash
   mvn clean package
   ```

Spowoduje to skompilowanie kodu, uruchomienie testów i utworzenie pliku JAR w katalogu `target/`.

## Uruchamianie aplikacji

### Metoda 1: Używając Mavena (dla deweloperów)
```bash
mvn spring-boot:run
```

### Metoda 2: Używając Javy (produkcja)
Po zbudowaniu za pomocą `mvn package`, uruchom:
```bash
java -jar target/sos-1.0-SNAPSHOT.jar
```

### Dostęp do aplikacji
Po uruchomieniu aplikacji można uzyskać do niej dostęp pod adresem:
- **Interfejs webowy**: http://localhost:8080
- **Dokumentacja API**: http://localhost:8080/api/info

## Endpointy API

### Uwierzytelnianie
- `POST /api/auth/login` - Logowanie użytkownika
- `POST /api/auth/register` - Rejestracja użytkownika

### Studenci
- `GET /api/students/{id}` - Pobierz studenta po ID
- `POST /api/students` - Utwórz nowego studenta
- `PUT /api/students/{id}/lock` - Zablokuj konto studenta
- `PUT /api/students/{id}/activate` - Aktywuj konto studenta
- `GET /api/students/{id}/enrollments` - Pobierz zapisy studenta
- `GET /api/students/{id}/grades` - Pobierz oceny studenta

### Pożyczenia
- `GET /api/loans/{id}` - Pobierz pożyczkę po ID
- `POST /api/loans` - Utwórz nową pożyczkę
- `GET /api/loans/student/{studentId}` - Pobierz pożyczki dla studenta

### Płatności
- `GET /api/payments/{id}` - Pobierz płatność po ID
- `POST /api/payments` - Utwórz nową płatność
- `GET /api/payments/student/{studentId}/unpaid` - Pobierz niezapłacone płatności dla studenta

## Schemat bazy danych

Aplikacja wykorzystuje bazę danych HSQLDB w pamięci RAM z następującymi głównymi encjami:
- **Role**: Role użytkowników (STUDENT, ADMIN, LECTURER)
- **Student**: Informacje o studentach i kontach
- **Course**: Kursy akademickie
- **Enrollment**: Zapisy studentów na kursy
- **Grade**: Oceny akademickie
- **Loan**: Pożyczenia książek
- **Payment**: Płatności finansowe
- **Schedule**: Harmonogramy studentów
- **ClassSession**: Indywidualne sesje zajęciowe
- **Notification**: Powiadomienia systemowe

## Konfiguracja

Aplikacja jest konfigurowana przez plik `src/main/resources/application.properties`:
- Ustawienia połączenia z bazą danych
- Port serwera (domyślnie: 8080)
- Ustawienia JPA/Hibernate
- Poziomy logowania

## Wykorzystane wzorce projektowe

- **DAO (Obiekt Dostępu do Danych)**: Oddzielenie logiki biznesowej od persystencji danych
- **MVC (Model-Widok-Kontroler)**: Oddzielenie problemów w warstwie webowej
- **Wstrzykiwanie zależności**: Zarządzane przez framework Spring
- **Wzorzec Repozytorium**: Poprzez Spring Data JPA
- **Warstwa usług**: Hermetyzacja logiki biznesowej

## Uwagi deweloperskie

- Aplikacja wykorzystuje bazę danych HSQLDB w pamięci RAM do celów rozwoju i testowania
- Hasła są bezpiecznie haszowane przy użyciu BCrypt
- Frontend jest zbudowany z wykorzystaniem Bootstrapa dla responsywnego projektu
- Zastosowano zasady projektowania RESTful API
- Zaimplementowano odpowiednie obsługę błędów i walidację