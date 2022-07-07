# SkiSlope - Stacja narciarski
  REST API studenckiego projektu odpowiedzialnego za obsługe stacji narciarskiej. 
  
## Opis projektu
  Projekt umożliwia obsługe stacji narciarskiej poprzez dostarczenie niezbędnych funkcjonalności.
Funkcjonalności dostarczone są przez trzy aplikacje:
* strone WWW (ReactJS)
* aplikację mobilną (Flutter)
* skaner kodów QR - bramka narciarska (Unity)

Połączonych ze sobą poprzez REST API zawarte w powyższym repozytorium.

### Najważniejsze funkcjonalności projektu
* Logowanie oraz rejestracja użytkownika,
* Uwierzytelnianie za pomocą konta Google,
* Możliwość zakupu biletu/karnetu korzystając z systemu płatności,
* Skanowanie biletu/karnetu,
* Generowanie raportów.

## Diagram przypadków użycia


Diagram przedstawiający przypadki użycia niezbędne do działania projektu. Backend implementuje również funkcjonalności, które nie zostały przedstawione na poniższym diagramie.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/UseCases.png">
</p>

## Schemat bazy danych (MySQL)


Schemat bazy danych utworzonej w MySQL.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Database.png">
</p>


## Endpoints


Endpointy aplikacji przedstawione na podstawie zrzutu ekranu z aplikacji Postman.


<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Postman.png">
</p>


# Opis implementacji najważniejszych funkcjonalności - Backend


## Autoryzacja oraz uwierzytelnianie użytkowników lokalnych

Autoryzacja użytkownika odbywa się poprzez JWT które użytkownik uzyskuje podczas procesu uwierzytelniania. 
Podczas uwierzytelniania użytkownik uzyskuje również refresh token pozwalający na przedłużenie żywotności access token.

<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/JWT.png">
</p>

## Google Authentication

Uwierzytelnianie użytkowników Google odbywa się poprzez Spring Security. 
Poniżej przedstawiony został przepływ procesu logowania użytkownika poprzez Google.

<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/GoogleAuthenticationFlow.png">
</p>

## Połącznie z systemem płatności

Płatności dokonywane są za pomocą API PayPal.

## Generowanie raportów

Raporty dla klientów oraz administratorów generowane są za pomocą JasperReport. 

# Technologie używane do stworzenia Backend'u

* Java
* Spring Boot
* Spring Security
* Spring Data
* Hibernate
* Lombok
* MySQL
* PayPal API
* JasperReport

Przydatne aplikacje przy tworzeniu Backendu:
* Postman
* Github
* Trello.
