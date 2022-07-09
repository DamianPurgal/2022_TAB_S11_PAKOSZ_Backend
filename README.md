# SkiSlope - Stacja narciarski
 _REST API studenckiego projektu odpowiedzialnego za obsługę stacji narciarskiej._ 
  
# Opis projektu
Projekt umożliwia obsługę stacji narciarskiej poprzez dostarczenie niezbędnych funkcjonalności.
Funkcjonalności dostarczone są przez trzy aplikacje:
* strone WWW (ReactJS),
* aplikację mobilną (Flutter),
* skaner kodów QR - bramka narciarska (Unity).
Wszystkie komponenty połączone są ze sobą poprzez REST API zawarte w powyższym repozytorium.

# Najważniejsze funkcjonalności projektu
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

# Schemat bazy danych (MySQL)
Schemat bazy danych utworzonej w MySQL.

<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Database.png">
</p>


# Endpoints
Endpointy aplikacji przedstawione na podstawie zrzutu ekranu z aplikacji Postman.

<p align="center">
  <img width="75%" height="75%" src="https://github.com/DamianPurgal/2022_TAB_S11_PAKOSZ_Backend/blob/main/readmeImages/Postman.png">
</p>

Przykładowa dokumentacja API płatności:
[link](https://app.swaggerhub.com/apis-docs/SkiSlope/Payments/1.0.0)

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
Płatności za bilety oraz karnety są wykonywane całkowicie za pomocą payPal.

<p align="center">
  <img width="25%" height="25%" src="https://user-images.githubusercontent.com/72508414/178113267-0960ff55-a10b-41a7-8966-e32d7b4fd5cd.png">
</p>

## Generowanie raportów
Istnieją dwa rodzaje raportów. Raporty dla klientów oraz administratorów generowane są za pomocą JasperReport. 
Przykład raportu wygenerowanego dla klienta:

<p align="center">
  <img width="50%" height="50%" src="https://user-images.githubusercontent.com/72508414/178113397-552b8ec7-e283-4305-a6a4-5aa06c225cd2.png">
</p>



# Technologie używane do stworzenia Backend'u
* Java,
* Spring Boot,
* Spring Security,
* Spring Data,
* Hibernate,
* Lombok,
* MySQL,
* PayPal API,
* JasperReport.

# Przydatne aplikacje przy tworzeniu Backendu:
* Postman,
* Github,
* Swagger,
* Trello.

