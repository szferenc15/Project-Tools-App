[Git]: https://git-scm.com/
[Java Spring Boot]: https://spring.io/
[H2]: http://www.h2database.com/
[JPA]: https://www.tutorialspoint.com/jpa/index.htm
[Thymeleaf]: http://www.thymeleaf.org/
[Lombok]: https://projectlombok.org/
[Spring Security Crypto]: https://mvnrepository.com/artifact/org.springframework.security/spring-security-crypto/4.0.1.RELEASE
[Android Studio]: https://developer.android.com/studio/index.html
[Microsoft Visual Studio Code]: https://code.visualstudio.com/
[JAVA 7/8]: https://en.wikipedia.org/wiki/Java_(programming_language)
[Genymotion]: https://www.genymotion.com/

[Eötvös Loránd Tudományegyetem Informatikai Kar]: http://inf.elte.hu

[ER]: ./resources/diagrams/img/Entity_Relationship_diagram.png "Egyed-kapcsolat diagram"

# Projekt Eszközök - Sportmates

A **Sportmates** egy Android operációs rendszerre készülő mobilalkalmazás:
+ Összehozza azokat az embereket, akik a sportot csoportosan szeretnék űzni.
+ Az elkészítendő profil tartalmaz minden olyan információt, mely tökéletesen tükrözi az egyén sportolási szokásait.
+ Regisztráció után lehetőség van új sportesemény létrehozására.
+ A főoldalon szabadon lehet böngészni a különféle sportesemények között, megtekinteni annak részleteit, jelentkezőit, illetve csatlakozni azokhoz, amennyiben van még férőhely.
+ Tetszés szerint szűrhetőek a találatok, ezáltal bárki könnyedén megtalálhatja a neki megfelelő sportot/időpontot.

## Célközönség:

A célközönség bármely személy, aki a sportolást nem pusztán egyhangú tevénykenységként végezné el a mindennapokban, hanem egy közös élménnyé szeretné kovácsolni azt.

## Szerepkörök:

+ Vendég
+ Felhasználó

## Funkcionális követelmények:

+ A vendég képes legyen az oldalra regisztrálni.
+ A felhasználó tudjon az oldalra bejelentkezni.
+ A felhasználó tudjon módosítani az adatain.
+ A felhasználó tudjon a főoldalon böngészni az események között.
+ A felhasználó tudjon a szűrni különféle eseményekre.
+ A felhasználó tudjon jelentkezni eseményekre.
+ A felhasználó tudjon új eseményt hozzáadni a meglévőkhöz.

## Nem funkcionális követelmények:

+ Biztonság
+ Felhasználóbarát design
+ Hatékonyság
+ Karbantarthatóság
+ Teljesítmény

## Használt technológiák, fejlesztői környezetek, programozási nyelvek:

+ **[Git]** - Verziókezelő rendszer
+ **[Java Spring Boot]** - Szerveroldali Java
+ **[H2]** - Relációs adatbáziskezelő rendszer
+ **[JPA]** - (Java Persistence API) Relációs adatkezelő
+ **[Thymeleaf]** - Szerveroldali template motor XHTML/HTML5/XML-hez
+ **[Lombok]** - Szerveroldali automatikus erőforrás menedzser
+ **[Spring Security Crypto]** - Szerveroldali titkosító, kulcsgeneráló, kódoló modul
+ **[Android Studio]** - A hivatalos IDE Androidhoz (kliensoldal)
+ **[Microsoft Visual Studio Code]** - Forráskód szerkesztő
+ **[JAVA 7/8]** - Programozási nyelv

## Adatbázis séma:

<table align="center" width="100%">
    <th colspan="3" width="100%">USER</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">felhasználó azonosítója (PK)</td>
    </tr>
        <tr align="center" width="100%">
        <td align="center" width="33%">PICTURE_URL</td>
        <td align="center" width="33%">VARCHAR2(200)</td>
        <td align="justify" width="33%">felhasználó profilképe</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">FIRST_NAME</td>
        <td align="center" width="33%">VARCHAR2(15)</td>
        <td align="justify" width="33%">felhasználó keresztneve</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">LAST_NAME</td>
        <td align="center" width="33%">VARCHAR2(15)</td>
        <td align="justify" width="33%">felhasználó vezetékneve</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">USERNAME</td>
        <td align="center" width="33%">VARCHAR2(15)</td>
        <td align="justify" width="33%">felhasználónév</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">PASSWORD</td>
        <td align="center" width="33%">VARCHAR2(60)</td>
        <td align="justify" width="33%">hashelt jelszó</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">EMAIL</td>
        <td align="center" width="33%">VARCHAR2(50)</td>
        <td align="justify" width="33%">email cím</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">PHONE_NUMBER</td>
        <td align="center" width="33%">VARCHAR2(14)</td>
        <td align="justify" width="33%">telefonszám</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">CITY</td>
        <td align="center" width="33%">VARCHAR2(30)</td>
        <td align="justify" width="33%">sportolásra preferált város (lakhely)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">BIRTH_DATE</td>
        <td align="center" width="33%">DATE</td>
        <td align="justify" width="33%">születési dátum</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">IS_MALE</td>
        <td align="center" width="33%">BOOLEAN</td>
        <td align="justify" width="33%">a felhasználó neme</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">EVENT</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">esemény azonosítója (PK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ORGANIZER</td>
        <td align="center" width="33%">VARCHAR2(15)</td>
        <td align="justify" width="33%">szervező felhasználóneve (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">CATEGORY</td>
        <td align="center" width="33%">VARCHAR2(30)</td>
        <td align="justify" width="33%">az esemény kategóriája (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">NAME</td>
        <td align="center" width="33%">VARCHAR2(50)</td>
        <td align="justify" width="33%">az esemény neve</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">COUNTRY</td>
        <td align="center" width="33%">VARCHAR2(50)</td>
        <td align="justify" width="33%">ország</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">CITY</td>
        <td align="center" width="33%">VARCHAR2(50)</td>
        <td align="justify" width="33%">város</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">LOCALE</td>
        <td align="center" width="33%">VARCHAR2(100)</td>
        <td align="justify" width="33%">helyszín</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">PRICE</td>
        <td align="center" width="33%">SMALLINT</td>
        <td align="justify" width="33%">belépődíj (ha nincs, akkor értéke 0)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">DATE_OF_EVENT</td>
        <td align="center" width="33%">DATE</td>
        <td align="justify" width="33%">az esemény dátuma</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">START</td>
        <td align="center" width="33%">TIME</td>
        <td align="justify" width="33%">kezdés</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">FINISH</td>
        <td align="center" width="33%">TIME</td>
        <td align="justify" width="33%">befejezés</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">HEADCOUNT</td>
        <td align="center" width="33%">SMALLINT</td>
        <td align="justify" width="33%">létszám</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">AUDIENCE</td>
        <td align="center" width="33%">VARCHAR2(6)</td>
        <td align="justify" width="33%">célközönség (nő / férfi / vegyes)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">DESCRIPTION</td>
        <td align="center" width="33%">VARCHAR2(500)</td>
        <td align="justify" width="33%">általános leírás</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">COMMENT</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">komment azonosítója (PK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">MESSAGE</td>
        <td align="center" width="33%">VARCHAR2(500)</td>
        <td align="justify" width="33%">komment</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">EVENT_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">hozzátartozó esemény azonosítója (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">USER_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">hozzátartozó felhasználó azonosítója (FK)</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">SPORT_CATEGORY</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">sportág azonosítója (PK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">CATEGORY</td>
        <td align="center" width="33%">VARCHAR2(30)</td>
        <td align="justify" width="33%">sportág megnevezése</td>
    </tr>
</table>

### Implicit kapcsoló-táblák:

<table align="center" width="100%">
    <th colspan="3" width="100%">EVENT_USER</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">EVENT_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">foglalás azonosítója (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">USER_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">felhasználó azonosítója (FK)</td>
    </tr>
</table>

## Egyed-kapcsolat diagram:

![alt text][ER]

## A backend végpontjai:

+ A végpontokra az adatokat (ahol szükséges) JSON formátumba kell küldeni (példák lejjebb találhatóak).

<table align="center" width="100%">
    <th colspan="5" width="100%">USER</th>
    <tr align="center" width="100%">
        <th width="20%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="30%">LEÍRÁS</th>
        <th width="20%">INPUT</th>
        <th width="20%">OUTPUT</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/user/all</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">az összes felhasználó kilistázása</td>
        <td align="center" width="20%">nincs</td>
        <td align="center" width="20%">az összes felhasználó listája</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/user/by_username</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">felhasználó lekérdezése felhasználónév szerint</td>
        <td align="center" width="20%">a felhasználónév</td>
        <td align="center" width="20%">siker esetén: az felhasználó adatai; <br /> hiba esetén: "User: no user found with this username: &lt;username&gt;" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/user/login</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="30%">a felhasználó beléptetése</td>
        <td align="center" width="20%">felhasználónév vagy email; jelszó</td>
        <td align="center" width="20%">siker esetén: a felhasználó összes adata; <br /> hiba esetén: "User: login failure"</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/user/register</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="30%">új felhasználó regisztrálása</td>
        <td align="center" width="20%">keresztnév; vezetéknév; felhasználónév; jelszó; email cím; telefonszám; város; születési dátum; férfi-e</td>
        <td align="center" width="20%">siker esetén: a felhasználó összes adata; <br /> hiba esetén: "User: login failure"</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/user/delete</b></td>
        <td align="center" width="10%">DELETE</td>
        <td align="center" width="30%">a felhasználó törlése minden adatával együtt, beleértve a kommenteket és eseményeket</td>
        <td align="center" width="20%">felhasználónév</td>
        <td align="center" width="20%">siker esetén: "User: deletion success"; <br /> hiba esetén: "User: deletion failure"</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="5" width="100%">EVENT</th>
    <tr align="center" width="100%">
        <th width="20%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="30%">LEÍRÁS</th>
        <th width="20%">INPUT</th>
        <th width="20%">OUTPUT</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/event/all</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">az összes esemény kilistázása</td>
        <td align="center" width="20%">nincs</td>
        <td align="center" width="20%">az összes esemény listája</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/event/by_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">esemény lekérdezése azonosító szerint</td>
        <td align="center" width="20%">az esemény azonosítója</td>
        <td align="center" width="20%">siker esetén: az esemény adatai; <br /> hiba esetén: "Event: no event found with this id: &lt;id&gt;" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/event/filter</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">esemény lekérdezése szűrési szempontok szerint</td>
        <td align="center" width="20%">az esemény szűrési szempontjai</td>
        <td align="center" width="20%">siker esetén: az esemény adatai; <br /> hiba esetén: "Event: no event found with these filters" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/event/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="30%">új esemény hozzáadása</td>
        <td align="center" width="20%">szervező, név, helyszín, belépődíj, dátum, kezdés, befejezés, létszám, közönség, leírás</td>
        <td align="center" width="20%">siker esetén: "Event: addition success"; <br /> hiba esetén: "Event: addition failure"</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/event/delete</b></td>
        <td align="center" width="10%">DELETE</td>
        <td align="center" width="30%">az esemény törlése a kommentjeivel együtt</td>
        <td align="center" width="20%">az esemény azonosítója</td>
        <td align="center" width="20%">siker esetén: "Event: deletion success"; <br /> hiba esetén: "Event: deletion failure"</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="5" width="100%">COMMENT</th>
    <tr align="center" width="100%">
        <th width="20%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="30%">LEÍRÁS</th>
        <th width="20%">INPUT</th>
        <th width="20%">OUTPUT</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/comment/all</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">az összes komment kilistázása</td>
        <td align="center" width="20%">nincs</td>
        <td align="center" width="20%">az összes komment listája</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/comment/by_event_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">komment lekérdezése eseményazonosító szerint</td>
        <td align="center" width="20%">az esemény azonosítója</td>
        <td align="center" width="20%">siker esetén: a komment adatai; <br /> hiba esetén: "Comment: no comment found with this event id: &lt;id&gt;" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/comment/by_user_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">komment lekérdezése felhasználó azonosító szerint</td>
        <td align="center" width="20%">az felhasználó azonosítója</td>
        <td align="center" width="20%">siker esetén: a komment adatai; <br /> hiba esetén: "Comment: no comment found with this user id: &lt;id&gt;" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/comment/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="30%">új komment hozzáadása</td>
        <td align="center" width="20%">üzenet, esemény azonosító, felhasználó azonosító</td>
        <td align="center" width="20%">siker esetén: "Comment: addition success"; <br /> hiba esetén: "Comment: addition failure"</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/comment/delete</b></td>
        <td align="center" width="10%">DELETE</td>
        <td align="center" width="30%">a komment törlése</td>
        <td align="center" width="20%">a komment azonosítója</td>
        <td align="center" width="20%">siker esetén: "Comment: deletion success"; <br /> hiba esetén: "Comment: deletion failure"</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="5" width="100%">SPORT_CATEGORY</th>
    <tr align="center" width="100%">
        <th width="20%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="30%">LEÍRÁS</th>
        <th width="20%">INPUT</th>
        <th width="20%">OUTPUT</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/sport_category/all</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">az összes sport kategória kilistázása</td>
        <td align="center" width="20%">nincs</td>
        <td align="center" width="20%">az összes sport kategória listája</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/sport_category/by_category</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="30%">kategória lekérdezése kategória szerint</td>
        <td align="center" width="20%">a kategória azonosítója</td>
        <td align="center" width="20%">siker esetén: a kategória adatai; <br /> hiba esetén: "SportCategory: no category found with this category: &lt;category&gt;" </td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/sport_category/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="30%">új sport kategória hozzáadása</td>
        <td align="center" width="20%">kategória neve</td>
        <td align="center" width="20%">siker esetén: "SportCategory: addition success"; <br /> hiba esetén: "SportCategory: addition failure"</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="20%"><b>http://localhost:5000/sport_category/delete</b></td>
        <td align="center" width="10%">DELETE</td>
        <td align="center" width="30%">a sport kategória törlése</td>
        <td align="center" width="20%">a sport kategória neve</td>
        <td align="center" width="20%">siker esetén: "SportCategory: deletion success"; <br /> hiba esetén: "SportCategory: deletion failure"</td>
    </tr>
</table>

### Példák:

<table align="center" width="100%">
    <th colspan="3" width="100%">USER</th>
    <tr align="center" width="100%">
        <th width="25%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="65%">PÉLDA</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/user/by_username</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ "username": "username" }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/user/login</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "identifier": "usernameOrEmail", "password": "password" }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/user/register</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "firstName": "firstName", "lastName": "lastName", "pictureUrl": "pictureUrl", "username": "username", "password": "password", "email": "email", "phoneNumber": "phoneNumber", "city": "city", "birthDate": "1996-01-01", "isMale": false }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/user/delete</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "username": "username" }</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">EVENT</th>
    <tr align="center" width="100%">
        <th width="25%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="65%">PÉLDA</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/event/by_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ "id": 1 }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/event/filter</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ TODO }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/event/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "organizer": "organizer", "category": "category", "name": "name", "country": "country", "city": "city" "locale": "locale", "price": 0, "dateOfEvent": "2018-12-31", "start": "10:00:00", "finish": "15:00:00", "headCount": 4, "audience": "vegyes", "description": "description" }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/event/delete</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "id": 1 }</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">COMMENT</th>
    <tr align="center" width="100%">
        <th width="25%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="65%">PÉLDA</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/comment/by_event_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ "eventId": 1 }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/comment/by_user_id</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ "userId": 1 }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/comment/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "message": "message", "eventId": 1, "userId": 1 }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/comment/delete</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "id": 1 }</td>
    </tr>
</table>

<table align="center" width="100%">
    <th colspan="3" width="100%">SPORT_CATEGORY</th>
    <tr align="center" width="100%">
        <th width="25%">ÚTVONAL</th>
        <th width="10%">METÓDUS</th>
        <th width="65%">PÉLDA</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/sport_category/by_category</b></td>
        <td align="center" width="10%">GET</td>
        <td align="center" width="65%">{ "category": "category" }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/sport_category/add</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "category": "category" }</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="25%"><b>http://localhost:5000/sport_category/delete</b></td>
        <td align="center" width="10%">POST</td>
        <td align="center" width="65%">{ "category": "category" }</td>
    </tr>
</table>

## Használat (Frontend):

0. Először a backend-et kell elindítani
1. Android Studio-ba kell betölteni a (sportmates_frontend) projektet
2. Android 5.0-s emulátort kell indítani, például a [Genymotion] programmal
3. Ha fut az emulátor, akkor indítható az Android Studio-ból a projekt

## Használat (Backend):

0. ***JAVA_HOME** (**JDK** elérési útvonala) környezeti változó felvétele
1. CMD: **mvnw spring-boot:run** parancs futtatása a **./sportmates_backend** útvonal alatt
2. Az alkalmazás gyökere a **localhost:5000** socketen érhető el böngészőből
3. Adatbázis elérése és létrehozása: **localhost:5000/h2** címen a következő konfigurálással <br />
-> **JDBC URL**: **jdbc:h2:mem:testdb** (minden más maradhat alapértelmezett)

## Szerzők:

+ **Kertész Kászon**
+ **Polozgai Máté**
+ **Szebenyi Gergely**
+ **Szendrei Ferenc**

**[Eötvös Loránd Tudományegyetem Informatikai Kar]**