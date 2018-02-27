[Android Studio]: https://developer.android.com/studio/index.html
[Microsoft Visual Studio Code]: https://code.visualstudio.com/
[Git]: https://git-scm.com/
[H2]: http://www.h2database.com/
[JPA]: https://www.tutorialspoint.com/jpa/index.htm
[Thymeleaf]: http://www.thymeleaf.org/
[Lombok]: https://projectlombok.org/
[Java Spring Boot]: https://spring.io/
[JAVA 7/8]: https://en.wikipedia.org/wiki/Java_(programming_language)
[Eötvös Loránd Tudományegyetem Informatikai Kar]: http://inf.elte.hu

[ER]: ./diagrams/img/Entity_Relationship_diagram.png "Egyed-kapcsolat diagram"

# Projekt Eszközök - Sport Mates

A **Sport Mates** egy Android operációs rendszerre készülő mobilalkalmazás:
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
+ Rendelkezésre állás
+ Teljesítmény

## Használt technológiák, fejlesztői környezetek, programozási nyelvek:

+ **[Git]** - Verziókezelő rendszer
+ **[Java Spring Boot]** - Szerveroldali Java
+ **[H2]** - Relációs adatbáziskezelő rendszer
+ **[JPA]** - (Java Persistence API) Relációs adatkezelő
+ **[Thymeleaf]** - Szerveroldali template motor XHTML/HTML5/XML-hez
+ **[Lombok]** - Szerveroldali automatikus erőforrás menedzser
+ **[Android Studio]** - A hivatalos IDE Androidhoz
+ **[Microsoft Visual Studio Code]** - Forráskód szerkesztő
+ **[JAVA 7/8]** - Programozási nyelv (szerveroldal)

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
        <td align="center" width="33%">VARCHAR2(25)</td>
        <td align="justify" width="33%">jelszó</td>
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
        <td align="center" width="33%">NAME</td>
        <td align="center" width="33%">VARCHAR2(50)</td>
        <td align="justify" width="33%">az esemény neve</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">LOCALE</td>
        <td align="center" width="33%">VARCHAR2(100)</td>
        <td align="justify" width="33%">helyszín</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">PRICE</td>
        <td align="center" width="33%">SMALLINT</td>
        <td align="justify" width="33%">belépődíj (ha nincs, akkor 0 értékű)</td>
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
        <td align="justify" width="33%">célközönség (nő, férfi, vegyes)</td>
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

### Explicit kapcsoló-táblák:

<table align="center" width="100%">
    <th colspan="3" width="100%">EVENT_SPORT_CATEGORY</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">esemény - sportág azonosítója (PK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">CATEGORY</td>
        <td align="center" width="33%">VARCHAR2(30)</td>
        <td align="justify" width="33%">sportág megnevezése (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">EVENT_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">hozzátartozó esemény azonosítója (FK)</td>
    </tr>
</table>

### Implicit kapcsoló-táblák:

<table align="center" width="100%">
    <th colspan="3" width="100%">USER_EVENT</th>
    <tr align="center" width="100%">
        <th width="33%">ATTRIBÚTUM</th>
        <th width="33%">TÍPUS</th>
        <th width="33%">LEÍRÁS</th>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">USER_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">felhasználó azonosítója (FK)</td>
    </tr>
    <tr align="center" width="100%">
        <td align="center" width="33%">EVENT_ID</td>
        <td align="center" width="33%">BIGINT</td>
        <td align="justify" width="33%">foglalás azonosítója (FK)</td>
    </tr>
</table>

## Egyed-kapcsolat diagram:

![alt text][ER]

## Használat (Backend):

1. CMD: **mvnw spring-boot:run** parancs futtatása a **./sport_mates** útvonal alatt
2. Az alkalmazás gyökere a **localhost:8080** socketen érhető el böngészőből
3. Adatbázis elérése és létrehozása: **localhost:8080/h2** címen a következő konfigurálással <br />
-> **JDBC URL**: **jdbc:h2:mem:testdb** (minden más maradhat alapértelmezett)

## Szerzők:

+ **Kertész Kászon**
+ **Polozgai Máté**
+ **Szebenyi Gergely**
+ **Szendrei Ferenc**

**[Eötvös Loránd Tudományegyetem Informatikai Kar]**